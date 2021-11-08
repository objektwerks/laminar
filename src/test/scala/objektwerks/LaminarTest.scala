package objektwerks

import com.raquo.laminar.api.L._

import utest._

object LaminarTest extends TestSuite {
  val tests = Tests {
    test("var") {
      val integerVar = Var(1)
      assert( integerVar.now() == 1 )
      integerVar.set(2)
      assert( integerVar.now() == 2 )
      integerVar.update(i => i + 1)
      assert( integerVar.now() == 3 )
    }
  }
}