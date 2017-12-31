import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
class LabyrinthEscapeTest : Spek({

    describe("A labyrinth escaper") {
        on("the given input of [(0) 3  0  1  -3]") {
            it("should return 5 (steps)") {
                val result = LabyrinthEscape().calculateSteps("0\n" +
                        "3\n" +
                        "0\n" +
                        "1\n" +
                        "-3")
                assertEquals(5, result)
            }
        }
    }

})