package streams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Bloxorz._

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
    }
  }

  trait Level1 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }


  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0,0)), "0,0")
      assert(!terrain(Pos(4,11)), "4,11")
    }
  }

  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1,1))
    }
  }

  test("finding neighbors") {
    new Level1 {
      val a = neighborsWithHistory(Block(Pos(1,1),Pos(1,1)), List(Left,Up)).toSet
      val b = Set(
                    (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
                    (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
                 )
      assert(a === b, "Finding neighbors failed")
    }
  }

  test("avoiding circles") {
    new Level1 {
      val a = newNeighborsOnly(
                Set(
                  (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
                  (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
                ).toStream,

                Set(Block(Pos(1,2),Pos(1,3)), Block(Pos(1,1),Pos(1,1)))
              )
      val b = Set(
                (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
              ).toStream
      assert(a === b, "Avoiding circles failed")
    }
  }

  trait Level0 extends SolutionChecker {
    val level = 
      """ST
        |oo
        |oo""".stripMargin

    val optsolution = List(Down, Right, Up)
  }

  test("optimal solution for level 0") {
    new Level0 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 0") {
    new Level0 {
      assert(solution.length == optsolution.length)
    }
  }

  test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }


  trait Level3 extends SolutionChecker {
    val level = 
    """------ooooooo--
      |oooo--ooo--oo--
      |ooooooooo--oooo
      |oSoo-------ooTo
      |oooo-------oooo
      |------------ooo""".stripMargin

    val calculatedOptSolution = List(Right, Up, Right, Right, Right, Up, Left, Down, Right, Up, Up, Right, Right, Right, Down, Down, Down, Right, Up)
  }

  test("optimal solution for level 3") {
    new Level3 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("manual check optimal solution for level 3") {
    new Level3 {
      assert(solution == calculatedOptSolution)
    }
  }

  trait Level6 extends SolutionChecker {
    val level =
    """-----oooooo----
      |-----o--ooo----
      |-----o--ooooo--
      |Sooooo-----oooo
      |----ooo----ooTo
      |----ooo-----ooo
      |------o--oo----
      |------ooooo----
      |------ooooo----
      |-------ooo-----""".stripMargin

    val calculatedOptSolution = List(Right, Right, Right, Down, Right, Down, Down, Right, Down, Down, Right, Up, Left, Left, Left, Up, Up, Left, Up, Up, Up, Right, Right, Right, Down, Down, Left, Up, Right, Right, Down, Right, Down, Down, Right)
  }

  test("optimal solution for level 6") {
    new Level6 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("manual check optimal solution for level 6") {
    new Level6 {
      assert(solution == calculatedOptSolution)
    }
  }

  trait Level7 extends SolutionChecker {
    val level = 
    """--------oooo---
      |--------oooo---
      |ooo-----o--oooo
      |oSooooooo---oTo
      |ooo----ooo--ooo
      |ooo----ooo--ooo
      |-ooo---o-------
      |--oooooo-------""".stripMargin

    // To turn on the switch and return to startPos, 
    // Down, Left, Up, Right, Right, Right, Right, Right, Down, Right, 
    // Left, Up, Left, Left, Left, Left, Left, Down, Right, Up

    val calculatedOptSolution = List(Down, Down, Right, Down, Right, Right, Right, Up, Up, Right, Down, Left, Up, Right, Up, Up, Right, Right, Right, Down, Right, Down, Right, Down, Left, Up)
  }

  test("optimal solution for level 7") {
    new Level7 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("manual check optimal solution for level 7") {
    new Level7 {
      assert(solution == calculatedOptSolution)
    }
  }

}
