import java.io.File

class Towers {
    fun findRoot(input: String): String {
        val towerMembers = input.split("\n").filter { child -> child != "" }.toList()
        val memberHasChildren = towerMembers.filter { child -> child.contains("->") }.toList()
        val memberHasNoChildren = towerMembers.filter { child -> !child.contains("->") }.toList()

        val memberObjects = memberHasChildren
                .map({ string ->
                    val (name, height) = extractDisc(string)

                    Member(Disc(name, height),
                            string.split("->")[1].split(",")
                                    .map { child -> child.trim() }
                                    .filter { char -> char != "" })
                })
                .toList()

        val memberObjectsWithNoChildren = memberHasNoChildren
                .map { string -> Member(extractDisc(string), emptyList()) }
                .toList()

        val allMembers = listOf(memberObjects, memberObjectsWithNoChildren).flatMap { member -> member }
        val children = allMembers.flatMap { member -> member.children }.toList()
        val find = allMembers.find {
            member -> !children.contains(member.disc.name)
        }
        return find!!.disc.name
    }

    private fun extractDisc(string: String): Disc {
        val name = string.split("->")[0].split(" ")[0].trim()
        val height = string.split("->")[0].split("(")[1].substringBefore(")").toInt()
        return Disc(name, height)
    }

    data class Member(val disc: Disc, val children: List<String>)
    data class Disc(val name: String, val height: Int)
}

fun main(args: Array<String>) {
    val root = Towers().findRoot(File("src/main/resources/inputDay7").readText())
    println(root)
}
