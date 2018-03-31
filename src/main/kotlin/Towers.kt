import java.io.File
import java.util.*

class Towers {

    private var unbalancedStack: Stack<Program> = Stack()

    data class Program(val name: String, val weight: Int, val children: List<Program>) {
        fun childrenWeight(): Int {
            return if (children.isEmpty()) {
                this.weight
            } else {
                val sum = children.sumBy { it.childrenWeight() }
                sum + weight
            }
        }
    }

    private data class ProgramStrings(val name: String, val weight: Int, val children: List<String>)

    fun findRoot(input: String): Program {
        val programs = parseData(input)
        val childrenOnly = programs.map { program -> program.children }.flatMap { it }

        return programs.find { program -> !childrenOnly.contains(program) }!!
    }

    fun findUnbalanced(input: String, root: String): Int {
        val programs = parseData(input)

        val weightOfUnbalanced = findUnbalanced(programs, root).weight

        val unbalancedRootWeight = unbalancedStack.pop().childrenWeight()
        val above = createWeightPairs(unbalancedStack.peek())
        val shouldHave = above.filter { found -> (above.count { count -> found.second == count.second }) != 1 }[0].second
        val difference = unbalancedRootWeight - shouldHave
        return weightOfUnbalanced - difference
    }

    private fun findUnbalanced(programs: List<Program>, root: String): Program {
        val rootProgram = findProgram(programs, root)

        val unbalanced = findUnbalancedChild(rootProgram)
        if (unbalanced != null) {
            unbalancedStack.push(findProgram(programs, unbalanced.first))
            findUnbalanced(programs, unbalanced.first)
        }
        if (unbalancedStack.isEmpty()) {
            return rootProgram
        }
        return unbalancedStack.peek()
    }

    private fun findUnbalancedChild(rootProgram: Program): Pair<String, Int>? {
        val summedUpWeight = createWeightPairs(rootProgram)
        return summedUpWeight.firstOrNull { found -> (summedUpWeight.count { count -> found.second == count.second }) == 1 }
    }

    private fun createWeightPairs(rootProgram: Program) =
            rootProgram.children.map { child -> Pair(child.name, child.childrenWeight()) }

    private fun findProgram(programs: List<Program>, name: String) =
            programs.first { program -> program.name == name }

    private fun parseData(input: String): List<Program> {
        val programsWithStringChildren = input.split("\n")
                .filter { string -> string.isNotBlank() }
                .map { program -> buildProgramStrings(program) }.toSet()

        return programsWithStringChildren.map { program -> transformChildren(program, programsWithStringChildren) }
    }

    private fun transformChildren(program: ProgramStrings, programs: Set<ProgramStrings>): Program {
        return if (program.children.isEmpty()) {
            Program(program.name, program.weight, emptyList())
        } else {
            Program(program.name, program.weight, program.children.map { child -> findProgram(child, programs) })
        }
    }

    private fun findProgram(child: String, programs: Set<ProgramStrings>): Program {
        return transformChildren(programs.find { program -> program.name == child.trim() }!!, programs)
    }

    private fun buildProgramStrings(program: String): ProgramStrings {
        return if (program.contains("->")) {
            val name = program.split("->")[0].split(" ")[0].trim()
            val weight = program.split("->")[0].split("(")[1].substringBefore(")").toInt()
            val children = program.split("->")[1].split(",")
            ProgramStrings(name, weight, children)
        } else {
            val name = program.split(" ")[0].trim()
            val weight = program.split("(")[1].substringBefore(")").toInt()
            ProgramStrings(name, weight, emptyList())
        }
    }
}

fun main(args: Array<String>) {
    val input = File("src/main/resources/inputDay7").readText()
    val root = Towers().findRoot(input)
    println("root: ${root.name}")
    val unbalanced = Towers().findUnbalanced(input, root.name)
    println("unbalanced program should have: $unbalanced")
}
