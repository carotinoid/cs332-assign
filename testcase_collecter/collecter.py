import os
import re
import sys
import argparse
import requests
from typing import List, Tuple, Optional

# --- Configuration ---
LINKS_FILE = 'Links.txt'
# --- End Configuration ---


def parse_links_file(filepath: str) -> List[Tuple[str, str]]:
    """Reads the links.txt file and returns a list of (github_id, repo_url)."""
    if not os.path.exists(filepath):
        print(f"Error: The file '{filepath}' was not found.")
        sys.exit(1)
    
    users = []
    with open(filepath, 'r') as f:
        for line in f:
            line = line.strip()
            if line and not line.startswith('#'):
                parts = line.split()
                if len(parts) >= 2:
                    users.append((parts[0], parts[1]))
    return users


def get_raw_github_url(repo_url: str, file_path: str) -> Optional[str]:
    """
    Constructs the raw content URL for a file on GitHub, trying both 'main' and 'master' branches.
    Returns the URL if the file exists, otherwise None.
    """
    if 'github.com' not in repo_url:
        print(f"Warning: '{repo_url}' does not seem to be a standard GitHub URL. Skipping.")
        return None

    base_raw_url = repo_url.replace("github.com", "raw.githubusercontent.com")
    
    # Repositories can have 'main' or 'master' as the default branch. We check both.
    branches_to_try = ['main', 'master']
    
    for branch in branches_to_try:
        url = f"{base_raw_url}/{branch}{file_path}"
        try:
            # HEAD request is faster as it doesn't download the body
            response = requests.head(url, timeout=10)
            if response.status_code == 200:
                return url
        except requests.RequestException:
            # Ignore connection errors and try the next branch
            continue
            
    return None


def download_test_file(github_id: str, raw_url: str, temp_filename: str) -> bool:
    """Downloads the content from the raw URL and saves it to a temporary file."""
    try:
        response = requests.get(raw_url, timeout=15)
        response.raise_for_status()  # Raises an HTTPError for bad responses (4xx or 5xx)
        
        with open(temp_filename, 'w', encoding='utf-8') as f:
            f.write(response.text)
        print(f"  Successfully downloaded to '{temp_filename}'")
        return True
        
    except requests.RequestException as e:
        print(f"  Failed to download from {github_id}: {e}")
        return False


def modify_test_file(temp_filepath: str, github_id: str):
    """
    Modifies the test file to prevent conflicts.
    Specifically, renames 'trait TestSets' and 'new TestSets' to be unique.
    """
    with open(temp_filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    pattern = r'\bignore\b\s*\(.*?\)\s*\{[\s\S]*?\}'
    content = re.sub(pattern, '', content)

    # Create unique names based on github_id
    new_trait_name = f"TestTrait_{github_id}"
    
    # Use regular expressions with word boundaries (\b) to avoid replacing parts of other words
    # This makes the replacement safer.
    # Pattern to find 'trait TestSets'
    content = re.sub(r'\b(trait\s+TestTrees)\b', f'trait {new_trait_name}', content)
    # Pattern to find 'new TestSets'
    content = re.sub(r'\b(new\s+TestTrees)\b', f'new {new_trait_name}', content)
    # test("...") --> test("...-github_id")
    # bug: test(".. \" .. \" .. ") --> test(".. \" .. \ -- by id ")")
    # content = re.sub(r'test\(\"(.*?)\"\)', r'test("\1 -- by ' + github_id + '")', content)
    # Corrected regex
    content = re.sub(r'test\(\"((?:\\.|[^"])*)\"\)', r'test("\1 -- by ' + github_id + '")', content)


    with open(temp_filepath, 'w', encoding='utf-8') as f:
        f.write(content)


def extract_class_body(content: str) -> Optional[str]:
    """Extracts the content inside the main 'FunSuite' class block."""
    # This regex looks for 'class ... extends FunSuite {' and captures everything
    # until the last '}' in the file. It handles multiline content.
    match = re.search(r'class\s+.*?Suite\s+extends\s+FunSuite\s*\{((?:.|\n)*)\}', content)
    if not match:
        return None
        
    body = match.group(1)
    # Strip the closing brace from the captured group
    return body.rsplit('}', 1)[0].strip()


def merge_files(temp_files: List[str], final_filename: str):
    """Merges the bodies of all temporary files into one final test suite."""
    if not temp_files:
        print("No temporary files to merge.")
        return

    print(f"\nMerging {len(temp_files)} files into '{final_filename}'...")

    # Use the first file as a template for the header (package, imports, class def)
    with open(temp_files[0], 'r', encoding='utf-8') as f:
        template_content = f.read()

    header_match = re.search(r'((?:.|\n)*class\s+.*?Suite\s+extends\s+FunSuite\s*\{)', template_content)
    if not header_match:
        print("Error: Could not find a valid class structure in the first file. Aborting merge.")
        return

    header = header_match.group(1)
    footer = "\n}\n"
    
    all_bodies = []
    for temp_file in temp_files:
        with open(temp_file, 'r', encoding='utf-8') as f:
            content = f.read()
        
        body = extract_class_body(content)
        if body:
            # Add a comment to identify the source of the test cases
            github_id = temp_file.split('.')[-2]
            all_bodies.append(f"\n  // ----- Test cases from {github_id} -----\n{body}\n  }}")
        else:
            print(f"Warning: Could not extract test body from '{temp_file}'. Skipping.")

    final_content = header + "\n".join(all_bodies) + footer

    if(not os.path.exists("collecting_output")):
        os.makedirs("collecting_output")
    with open(f"collecting_output/{final_filename}", 'w', encoding='utf-8') as f:
        f.write(final_content)
    print(f"Successfully created '{final_filename}'.")


def cleanup(temp_files: List[str]):
    """Removes all temporary files."""
    print("\nCleaning up temporary files...")
    for f in temp_files:
        try:
            os.remove(f)
            print(f"  Removed '{f}'")
        except OSError as e:
            print(f"  Error removing file {f}: {e}")


def main():
    """Main execution function."""
    script_dir = os.path.dirname(os.path.abspath(__file__))
    links_file_path = os.path.join(script_dir, LINKS_FILE)

    parser = argparse.ArgumentParser(
        description="Collects and merges Scala test cases from multiple GitHub repositories."
    )
    parser.add_argument('assignment', help="The name of the assignment (e.g., 'recfun' or 'funsets').")
    parser.add_argument('test_suite', help="The name of the test suite (e.g., 'FunSet').")
    args = parser.parse_args()

    assignment_name = args.assignment
    test_suite_name = args.test_suite
        
    target_filename = f"{test_suite_name}Suite.scala"
    file_path = f"/{assignment_name}/src/test/scala/{assignment_name}/{test_suite_name}Suite.scala"

    users_repos = parse_links_file(LINKS_FILE)
    if not users_repos:
        print("No user repositories found in links.txt. Exiting.")
        return
        
    temp_files_created = []

    print(f"Starting to collect test cases for '{assignment_name}'...")
    for github_id, repo_url in users_repos:
        print(f"\nProcessing user: {github_id}")
        raw_url = get_raw_github_url(repo_url, file_path)
        
        if not raw_url:
            print(f"  Could not find '{target_filename}' in repo for user {github_id} (tried main/master branches).")
            continue

        temp_filename = f"{target_filename}.{github_id}.tmp"
        if download_test_file(github_id, raw_url, temp_filename):
            modify_test_file(temp_filename, github_id)
            temp_files_created.append(temp_filename)

    try:
        if temp_files_created:
            merge_files(temp_files_created, target_filename)
        else:
            print("\nNo test files were successfully downloaded. Final file not created.")
    finally:
        if temp_files_created:
            cleanup(temp_files_created)

if __name__ == '__main__':
    main()
