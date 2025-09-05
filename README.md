# csed332 소프트웨어 설계 방법

1. Clone this repository.
```bash
git clone https://github.com/carotinoid/cs332-assign
```

2. Install sbt(simple build tool). More information: <https://www.scala-sbt.org/download/>
```bash
# Linux (deb)
echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list
echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list
curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo tee /etc/apt/trusted.gpg.d/sbt.asc
sudo apt-get update
sudo apt-get install sbt
```

3. Run sbt in an assignment sub directory.
```bash
cd {any_dir} # for example: $ cd recfun
sbt
```

4. Run `run` (run Main.scala) / `test` (run all testcases) / `console` (open scala console) command in sbt console.
```sbt
> test
```