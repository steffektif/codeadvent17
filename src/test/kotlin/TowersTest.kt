import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

class TowersTest: Spek({

    val testInput = "pbga (66)\n" +
            "xhth (57)\n" +
            "ebii (61)\n" +
            "havc (66)\n" +
            "ktlj (57)\n" +
            "fwft (72) -> ktlj, cntj, xhth\n" +
            "qoyq (66)\n" +
            "padx (45) -> pbga, havc, qoyq\n" +
            "tknk (41) -> ugml, padx, fwft\n" +
            "jptl (61)\n" +
            "ugml (68) -> gyxo, ebii, jptl\n" +
            "gyxo (61)\n" +
            "cntj (57)"

    describe("The towers class") {
        on("the given test input") {
            it("should find the most bottom program, which is the root of the tree") {
                val result = Towers().findRoot(testInput)
                val anotherResult = Towers().doCalculationStuff(testInput, result)
                assertEquals("tknk", result)
            }
        }
    }
})