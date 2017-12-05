class CaptchaResolver {

    fun resolvePartOne(input: String): Int {
        val iterator = input.split("").filter({ s -> s != "" }).listIterator()
        var last: Int = -1
        var current: Int
        var sum = 0

        while (iterator.hasNext()) {
            current = iterator.next().toInt()
            if (current == last) {
                sum += current
            }
            last = current
        }
        if (last == input.split("")[1].toInt()) {
            sum += last
        }
        return sum
    }

    fun resolvePartTwo(input: String): Int {
        var sum = 0
        val inputList = input.split("").filter({ s -> s != "" })

        for ((index, s) in inputList.withIndex()) {
            val digit = s.toInt() // 0 | 1 | 2
            val calculateIndex = calculateIndex(inputList.size, index) //2 | 3 | 4
            val foundDigit = inputList[calculateIndex].toInt()
            if (foundDigit == digit) {
                sum += digit
            }
        }
        return sum
    }

    private fun calculateIndex(max: Int, actualIndex: Int): Int {
        val exceedsMax = actualIndex + max / 2 >= max
        if (exceedsMax) {
            return (max - actualIndex - max / 2).unaryMinus()
        }
        return actualIndex + max/2
    }

}