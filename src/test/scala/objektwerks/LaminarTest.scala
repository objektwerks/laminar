package objektwerks

import com.raquo.laminar.api.L._

import utest._

object LaminarTest extends TestSuite {
  val tests = Tests {
    test("var") {
      val integerVar = Var(3)
      assert( integerVar.now() == 3 )
    }
  }
}