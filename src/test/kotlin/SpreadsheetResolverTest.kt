import junit.framework.TestCase.assertEquals
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object SpreadsheetResolverTest: Spek({
    val example = "5\t1\t9\t5\n" +
            "7\t5\t3\n"+
            "2\t4\t6\t8"

    val secondExample = "5\t9\t2\t8\n" +
            "9\t4\t7\t3\n" +
            "3\t8\t6\t5"

    describe("A SpreadsheetResolver") {
        on("the given example input") {
            it("should return 18") {
                val result = SpreadsheetResolver.resolveMinMax(example)
                assertEquals(18, result)
            }
        }

        on("the second example input") {
            it("should return 9") {
                val result = SpreadsheetResolver.resolveModulo(secondExample)
                assertEquals(9, result)
            }
        }
    }
})