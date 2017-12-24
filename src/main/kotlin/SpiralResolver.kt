class SpiralResolver {

    private val sequence = linkedSetOf(Direction.RIGHT, Direction.UP, Direction.LEFT, Direction.DOWN)
    private var positionGrid: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
    private var lastValue: Int = 1

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

    fun findCoordinatesModified(goal: Int): Int {
        positionGrid.put(Pair(0,0), 1)
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

        while (lastValue <= goal) {
            while (lastValue <= goal && stepsInThisDirection != range) {
                position = direction[directionToTake]?.invoke(position)!!
                lastValue = calculateValue(position)
                positionGrid.put(position, lastValue)
                stepsInThisDirection++
                stepsTaken++
//                println(position)
//                println(lastValue)
            }
            stepsInThisDirection = 0
            directionToTake = getNext(directionToTake)
            directionChanged++
            if (directionChanged == 2) {
                directionChanged = 0
                range++
            }
        }
        println(positionGrid)
        return lastValue
    }

    private fun calculateValue(position: Pair<Int, Int>): Int {
//        if(position.first == 0 && position.second == 0) {
//            return 1
//        }
        val map = Direction.values().map { value -> findSumValue(position, value) }
        return map.sum()
    }

    private fun findSumValue(position: Pair<Int, Int>, direction: Direction): Int {
        val modifiedX = position.first + direction.addedPosition.first
        val modifiedY = position.second + direction.addedPosition.second

        var sum = 0
        val found = positionGrid[Pair(modifiedX, modifiedY)]
        if (found != null) {
            sum = found
        }
        return sum
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

    fun goToCenter(position: Pair<Int, Int>): Int {
        var first = position.first
        var second = position.second
        if (first.toString().startsWith("-")) first = first.unaryMinus()
        if (second.toString().startsWith("-")) second = second.unaryMinus()
        return first + second
    }

    enum class Direction(val addedPosition: Pair<Int, Int>) {
        LEFT(Pair(-1, 0)), RIGHT(Pair(1, 0)), UP(Pair(0, 1)), DOWN(Pair(0, -1)),
        TOP_LEFT(Pair(-1, 1)), TOP_RIGHT(Pair(1, 1)), BOTTOM_LEFT(Pair(-1, -1)), BOTTOM_RIGHT(Pair(1, -1));
    }
}