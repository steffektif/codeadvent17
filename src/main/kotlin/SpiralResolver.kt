class SpiralResolver {

    private val sequence = sortedSetOf(Direction.RIGHT, Direction.UP, Direction.LEFT, Direction.DOWN)


    fun findCoordinates(goal: Int): Pair<Int, Int> {
        var range = 1
        var stepsTaken = 1
        var stepsInThisDirection = 0
        var position = Pair(0, 0)

        var directionChanged = 0

        var directionToTake = Direction.RIGHT
        val direction = sortedMapOf(
                Pair(Direction.RIGHT, this::goRight),
                Pair(Direction.UP, this::gotUp),
                Pair(Direction.LEFT, this::goLeft),
                Pair(Direction.DOWN, this::goDown))

        while (stepsTaken != goal) {
            while (stepsTaken != goal && stepsInThisDirection != range) {
                position = direction[directionToTake]?.invoke(position)!!
                stepsInThisDirection++
                stepsTaken++
            }
            stepsInThisDirection = 0
            directionToTake = getNext(directionToTake)
            directionChanged++
            if (directionChanged == 2) {
                directionChanged = 0
                range++
            }
        }
        return position
    }

    private fun goRight(actual: Pair<Int, Int>): Pair<Int, Int> {
        var first = actual.first
        first = first.inc()
        return Pair(first, actual.second)
    }

    private fun gotUp(actual: Pair<Int, Int>): Pair<Int, Int> {
        var second = actual.second
        second = second.inc()
        return Pair(actual.first, second)
    }

    private fun goLeft(actual: Pair<Int, Int>): Pair<Int, Int> {
        var first = actual.first
        first = first.dec()
        return Pair(first, actual.second)
    }

    private fun goDown(actual: Pair<Int, Int>): Pair<Int, Int> {
        var second = actual.second
        second = second.dec()
        return Pair(actual.first, second)
    }

    private fun getNext(actual: Direction): Direction {
        var index = sequence.indexOf(actual)
        if (index == 3) index = -1
        return sequence.elementAt(index.inc())
    }

    fun goToCenter(position: Pair<Int,Int>): Int {
        println(position)
        var first = position.first
        var second = position.second
        if (first.toString().startsWith("-")) first = first.unaryMinus()
        if (second.toString().startsWith("-")) second = second.unaryMinus()
        return first + second
    }

    enum class Direction {
        LEFT, RIGHT, UP, DOWN;
    }

}