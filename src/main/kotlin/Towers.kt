import java.io.File

class Towers {

    fun doCalculationStuff(input: String, rootId: String) : MutableList<Tower> {
        val members = findAllMembers(input)
        val discVisitor = DiscVisitor(members)
        members.forEach { member -> member.accept(discVisitor) }

        return discVisitor.towers;
    }

    fun findRoot(input: String): String {
        val allMembers = findAllMembers(input)
        val children = allMembers.flatMap { member -> member.children }.toList()
        val find = allMembers.find { member ->
            !children.contains(member.disc.name)
        }
        return find!!.disc.name
    }

    private fun findAllMembers(input: String): List<DiscWithChildren> {
        val towerMembers = input.split("\n").filter { child -> child != "" }.toList()
        val memberHasChildren = towerMembers.filter { child -> child.contains("->") }.toList()
        val memberHasNoChildren = towerMembers.filter { child -> !child.contains("->") }.toList()

        val memberObjects = memberHasChildren
                .map({ string ->
                    val (name, height) = extractDisc(string)

                    DiscWithChildren(name, height,


                            string.split("->")[1].split(",")
                                    .map { child -> child.trim() }
                                    .filter { char -> char != "" })
                })
                .toList()

        val emptyTowers = findDiscsWithoutChildren(memberHasNoChildren)
//        println("empty towers: ")
//        emptyTowers.forEach { tower -> println(tower) }
//        println("towers with children : ")
//        memberObjects.forEach { tower -> println(tower) }
        return listOf(memberObjects, emptyTowers).flatMap { it }
    }

    private fun findDiscsWithoutChildren(memberHasNoChildren: List<String>): List<DiscWithChildren> {
        return memberHasNoChildren
                .map { string -> DiscWithChildren(extractDisc(string), emptyList()) }
                .toList()
    }

    private fun extractDisc(string: String): Disc {
        val name = string.split("->")[0].split(" ")[0].trim()
        val weight = string.split("->")[0].split("(")[1].substringBefore(")").toInt()
        return Disc(name, weight)
    }

    private data class Disc(val name: String, val weight: Int)

    data class DiscWithChildren(val name: String, val weight: Int, val children: List<DiscWithChildren>) : Visitable {
        override fun accept(visitor: Visitor) {
            visitor.visit(this)
        }

        override fun toString(): String {
            return "name: $name, weight: $weight ${if (!children.isEmpty()) "with children $children" else ""}"
        }

    }

    interface Visitable {
        fun accept(visitor: Visitor)
    }

    interface Visitor {
        fun visit(visitable: DiscWithChildren)
    }

    class DiscVisitor(val allmemebers: List<DiscWithChildren>) : Visitor {

        var towers : MutableList<DiscWithChildren> = mutableListOf()

        override fun visit(visitable: DiscWithChildren) {
            val childs = visitable.children.mapNotNull { childId ->
                allmemebers.find { disc -> disc.disc.name == childId }
            }.toList()
            towers.add(Tower(visitable.disc, childs))
            childs.forEach { child -> child.accept(this) }
        }

    }
}

fun main(args: Array<String>) {

    val root = Towers().findRoot(File("src/main/resources/inputDay7").readText())
    Towers().doCalculationStuff(File("src/main/resources/inputDay7").readText(),root)
//    println(root)
}
