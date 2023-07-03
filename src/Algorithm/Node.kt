class Node(val name: Int){
    //От класса Edge отказались, потому что в классе Node храним вершины к которым направлено ребро
    //И вершин от которых направлено ребро
    var visited: Boolean = false
    var timeout: Int = 0
    var adjacents = ArrayList<Node>() //Вершины к которым направлено ребро от данной
    var revadjacents = ArrayList<Node>() //Вершины от которых направлено ребро
}