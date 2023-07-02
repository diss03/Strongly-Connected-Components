class Node(val name: Int){

    var visited: Boolean = false
    var timeout: Int = 0
    var adjacents = ArrayList<Node>()
    var revadjacents = ArrayList<Node>()

}