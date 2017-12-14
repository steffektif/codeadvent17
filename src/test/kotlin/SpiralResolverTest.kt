package kotlin

import junit.framework.TestCase
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object SpreadsheetResolverTest: Spek({

    describe("A SpiralResolver") {
        on("a given tile number of 1") {
            it("should return the coordinates 0/0") {
                val result = SpiralResolver.findCoordinates(1)
                TestCase.assertEquals(Pair(0,0), result)
            }
        }

        on("a given tile number of 12") {
            it("should return the coordinates 2/1") {
                val result = SpiralResolver.findCoordinates(1)
                TestCase.assertEquals(Pair(2,1), result)
            }
        }

        on("a given tile number of 23") {
            it("should return the coordinates 0/-2") {
                val result = SpiralResolver.findCoordinates(1)
                TestCase.assertEquals(Pair(0,-2), result)
            }
        }
    }
})