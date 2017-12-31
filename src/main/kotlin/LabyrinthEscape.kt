import java.io.File

fun main(args: Array<String>) {
    val result = LabyrinthEscape().calculateSteps(File("src/main/resources/inputDay5").readText())
    println(result)
}

class LabyrinthEscape {

    fun calculateSteps(input: String): Int {
        var position = 0
        var list =  input.split("\n").filter { char -> char != "" }.map({ char -> char.toInt() }).toMutableList()
        val end = list.size
        var steps = 0

        while (true) {
            val jumpSize = list[position]
            val newIndex = position + jumpSize
            if(newIndex >= end) {
               return steps.inc()
            }
            list = decideAndManipulateList(list, position)
            position = newIndex
            steps = steps.inc()
        }
    }

    private fun incrementElementInList(list: MutableList<Int>, index: Int): MutableList<Int> {
        val copy = list.toMutableList()
        copy[index] = copy[index].inc()
        copy[index]
        return copy
    }

    private fun decideAndManipulateList(list: MutableList<Int>, index: Int): MutableList<Int> {
        val copy = list.toMutableList()
        var value = copy[index]
        value = if (value >= 3) {
            value.dec()
        } else {
            value.inc()
        }
        copy[index] = value
        return copy
    }
}

