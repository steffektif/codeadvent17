import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
class PassPhraseValidatorTest : Spek({
    describe("The passphrase validator") {
        on("the input of 'aa bb cc dd ee'") {
            it("should return 1") {
                val result = PassphraseValidator().validate("aa bb cc dd ee")
                assertEquals(1, result)
                println(result)
            }
        }
        on("the input of 'aa bb cc dd aa'") {
            it("should return 0") {
                val result = PassphraseValidator().validate("aa bb cc dd aa")
                assertEquals(0, result)
                println(result)

            }
        }
        on("the input of 'aa bb cc dd aaa'") {
            it("should return 1") {
                val result = PassphraseValidator().validate("aa bb cc dd aaa")
                assertEquals(1, result)
                println(result)

            }
        }
        on("the input of 'aa bb cc dd aaa \n aa bb cc dd aa'") {
            it("should return 1") {
                val result = PassphraseValidator().validate("aa bb cc dd aaa")
                assertEquals(1, result)
                println(result)

            }
        }

        on("the input of 'abcde fghij'") {
            it("should return 1") {
                val result = PassphraseValidator().validateAnagram("abcde fghij")
                assertEquals(1, result)
                println(result)

            }
        }

        on("the input of 'abcde xyz ecdab'") {
            it("should return 0") {
                val result = PassphraseValidator().validateAnagram("abcde xyz ecdab")
                assertEquals(0, result)
                println(result)

            }
        }

        on("the input of 'a ab abc abd abf abj'") {
            it("should return 1") {
                val result = PassphraseValidator().validateAnagram("a ab abc abd abf abj")
                assertEquals(1, result)
                println(result)

            }
        }
        on("the input of 'oiii ioii iioi iiio'") {
            it("should return 1") {
                val result = PassphraseValidator().validateAnagram("oiii ioii iioi iiio")
                assertEquals(0, result)
                println(result)
            }
        }

        on("the input of 'iiii oiii ooii oooi oooo'") {
            it("should return 1") {
                val result = PassphraseValidator().validateAnagram("iiii oiii ooii oooi oooo")
                assertEquals(1, result)
                println(result)
            }
        }
    }
})