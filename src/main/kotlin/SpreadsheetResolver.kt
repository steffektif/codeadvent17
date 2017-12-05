object SpreadsheetResolver {

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

        }
        return sum
    }

}