import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals

class BlockBalanceTest : Spek({
    describe("A block balancer") {
        on("the input of 0 2 7 0") {
            it("should return 4") {
                val redistributions = BlockBalancer().findInfiniteLoop("0\t2\t7\t0")
                assertEquals(4, redistributions)
            }
        }
    }
})