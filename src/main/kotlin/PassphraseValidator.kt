class PassphraseValidator {

    fun validate(input: String): Int {
        return input.split("\n").map { line -> evaluateLine(line) }.filter { result -> result }.count()
    }

    fun validateAnagram(input: String): Int {
        return input.split("\n")
                .asSequence()
                .filter { line -> line != "" }
                .map { line -> evaluateAnagramLine(line) }
                .filter { result -> result }
                .count()
    }

    private fun evaluateAnagramLine(input: String): Boolean {
        val content = input.split(" ")
        val shouldHave = content.size
        val set = mutableSetOf<String>()
        content.forEach({ string -> addToSet(set, string) })
        return shouldHave == set.size
    }

    private fun addToSet(set: MutableSet<String>, string: String) {
        val find = set.find { entry -> checkForAnagram(entry, string) }
        if (find == null) {
            set.add(string)
        }
    }

    private fun checkForAnagram(first: String, second: String): Boolean {
        val copySecond = second.toMutableList()

        first.forEach { char ->
            if (copySecond.contains(char)) {
                copySecond.remove(char)
            }
        }
        return copySecond.isEmpty()
    }

    private fun evaluateLine(input: String): Boolean {
        if (input == "") {
            return false
        }
        val content = input.split(" ")
        val shouldHave = content.size
        val set = mutableSetOf<String>()
        content.forEach({ string -> set.add(string.toLowerCase()) })
        return shouldHave == set.size
    }

}