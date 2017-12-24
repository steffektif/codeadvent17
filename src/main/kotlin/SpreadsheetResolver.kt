class SpreadsheetResolver {

    fun resolveMinMax(input: String) : Int {
        var sum = 0
        val listIterator = input.split("\n").listIterator()
        while (listIterator.hasNext()) {
            val row = listIterator.next().split("\t").map({ s -> s.toInt() })
            val min = row.min()
            val max = row.max()
            if(min != null && max != null) {
                sum += max - min
            }
        }
        return sum
    }

    fun resolveModulo(input : String) : Int {
        var sum = 0
        val listIterator = input.split("\n").listIterator()
        while (listIterator.hasNext()) {
            val row = listIterator.next().split("\t").map({ s -> s.toInt() })
            row.forEach({number -> sum += checkRest(number, row)})
        }
        return sum
    }

    private fun checkRest(number: Int, row: List<Int>): Int {
        val others = row.filter({ currentNumber -> currentNumber != number })
        val divisor = others.find({ n -> (number % n) == 0 })
        if(divisor != null) {
            return number/divisor
        }
        return 0
    }

}