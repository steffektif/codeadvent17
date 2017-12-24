import junit.framework.TestCase
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class SpiralResolverTest : Spek({

    describe("A SpiralResolver") {
        on("a given tile number of 1") {
            val resolver = SpiralResolver()
            it("should return the coordinates 0/0") {
                val result = resolver.findCoordinates(1)
                TestCase.assertEquals(Pair(0, 0), result)
            }
        }

        on("a given tile number of 12") {
            val resolver = SpiralResolver()
            it("should return the coordinates 2/1") {
                val result = resolver.findCoordinates(12)
                TestCase.assertEquals(Pair(2, 1), result)
            }
        }

        on("a given tile number of 23") {
            val resolver = SpiralResolver()
            it("should return the coordinates 0/-2") {
                val result = resolver.findCoordinates(23)
                TestCase.assertEquals(Pair(0, -2), result)
            }
        }

        on("the given position of 0/0") {
            val resolver = SpiralResolver()
            it("should return the number of steps: 0") {
                val result = resolver.goToCenter(Pair(0, 0))
                TestCase.assertEquals(0, result)
            }
        }

        on("the given position of 2/1") {
            val resolver = SpiralResolver()
            it("should return the number of steps: 3") {
                val result = resolver.goToCenter(Pair(2, 1))
                TestCase.assertEquals(3, result)
            }
        }

        on("the given position of 0/-2") {
            val resolver = SpiralResolver()
            it("should return the number of steps: 2") {
                val result = resolver.goToCenter(Pair(0,-2))
                TestCase.assertEquals(2, result)
            }
        }

        on("the given input of 1024") {
            val resolver = SpiralResolver()
            it ("should return 31 steps") {
                val result = resolver.goToCenter(resolver.findCoordinates(1024))
                TestCase.assertEquals(31, result)
            }
        }

        on("actual puzzle input of 312051") {
            val resolver = SpiralResolver()
            it("should return the correct answer :)") {
                val result = resolver.goToCenter(resolver.findCoordinates(312051))
                println(result)
            }
        }
    }

    describe("a spiral resolver") {
        on("actual puzzle input of 312051") {
            val resolver = SpiralResolver()
            it("should return the next bigger value written") {
                val result = resolver.findCoordinatesModified(312051)
                println(result)
            }
        }
    }
})