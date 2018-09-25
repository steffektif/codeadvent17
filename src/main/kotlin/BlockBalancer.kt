
fun main(args: Array<String>) {
    val input = "2\t8\t8\t5\t4\t2\t3\t1\t5\t5\t1\t2\t15\t13\t5\t14"
    val blockBalancer = BlockBalancer()
    val result = blockBalancer.findInfiniteLoop(input)
    println(result)
}

class BlockBalancer {

    fun findInfiniteLoop(input: String): Int {
        val blocks = input.split("\t")
                .asSequence()
                .filter { char -> char != "" }
                .map { char -> char.toInt() }
                .toMutableList()
        val knownPatterns = mutableSetOf<List<Int>>(blocks)

        var copy = blocks.toMutableList() // make a copy
        while (true) {
            val redistributedBlocks = redistribute(copy)
            if (knownPatterns.contains(redistributedBlocks)) {
//                return knownPatterns.size // solves part 1
                val matchingPattern = redistributedBlocks.toList()
                var counter = 0
                while (true) {
                    val innerLoopBlocks = redistribute(copy)
                    counter = counter.inc()
                    if(innerLoopBlocks == matchingPattern) {
                        return counter
                    }
                }
            } else {
                knownPatterns.add(redistributedBlocks)
            }
            copy = redistributedBlocks
        }
    }

    private fun redistribute(blocks: MutableList<Int>): MutableList<Int> {
        var max = blocks.max()
        var index = blocks.indexOf(max) // first one wins
        blocks[index] = 0

        while (max != 0) {
            index = index.inc()
            if(index == blocks.size) {
                index = 0
            }
            blocks[index] = blocks[index].inc()
            max = max!!.dec()
        }
        return blocks
    }

}