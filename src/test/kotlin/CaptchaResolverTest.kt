import junit.framework.TestCase.assertEquals
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object CaptchaResolverTest: Spek({
    describe("a CaptchaResolver") {
        val resolver = CaptchaResolver
        on("the given input of 1111 and usage of resolver one") {
            it("should return 4") {
                assertEquals(4, resolver.resolvePartOne("1111"))
            }
        }
        on("the given input of 1122 and usage of resolver one") {
            it("should return 3") {
                assertEquals(3, resolver.resolvePartOne("1122"))
            }
        }
        on("the given input of 1234 and usage of resolver one") {
            it("should return 0") {
                assertEquals(0, resolver.resolvePartOne("1234"))
            }
        }
        on("the given input of 91212129 and usage of resolver one") {
            it("should return 9") {
                assertEquals(9, resolver.resolvePartOne("91212129"))
            }
        }
        on("the given input of 1212 and usage of resolver two") {
            it("should return 6") {
                assertEquals(6, resolver.resolvePartTwo("1212"))
            }
        }
        on("the given input of 1221 and usage of resolver two") {
            it("should return 0") {
                assertEquals(0, resolver.resolvePartTwo("1221"))
            }
        }
        on("the given input of 123425 and usage of resolver two") {
            it("should return 4") {
                assertEquals(4, resolver.resolvePartTwo("123425"))
            }
        }
        on("the given input of 123123 and usage of resolver two") {
            it("should return 12") {
                assertEquals(12, resolver.resolvePartTwo("123123"))
            }
        }
        on("the given input of 12131415 and usage of resolver two") {
            it("should return 4") {
                assertEquals(4, resolver.resolvePartTwo("12131415"))
            }
        }
    }
})