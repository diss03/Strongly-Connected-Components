import com.example.practice.Kosaraju
import com.example.practice.Loader
import com.example.practice.Node
import com.example.practice.OrientedGraph
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach


class KosarajuTest {

    var kosaraju: Kosaraju = Kosaraju()

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: Graph with one vertex")
    fun startTest1() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_1.json")
        kosaraju.n = 1
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: 2-full graph")
    fun startTest2() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_2.json")
        kosaraju.n = 2

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 2] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: 3-full graph")
    fun startTest3() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_3.json")
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 2, 3] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: Disconnected graph with three component")
    fun startTest4() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_4.json")
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [3] 2: [2] 3: [1] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: Ordinary Graph")
    fun startTest5() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_5.json")
        kosaraju.n = 8

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 5, 2] 2: [3, 4] 3: [7, 6] 4: [8] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: Graph with one component (3 vertexes, 3 ages)")
    fun startTest6() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_6.json")
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 3, 2] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: Graph with one component (3 vertexes, 4 ages)")
    fun startTest7() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_7.json")
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 2, 3] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: Graph with two components (3 vertexes, 2 ages)")
    fun startTest8() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_8.json")
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [3] 2: [1, 2] "
        assertEquals(expected, actual)
    }
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: Graph with three components (3 vertexes, 2 ages)")
    fun startTest9() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_9.json")
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1] 2: [2] 3: [3] "
        assertEquals(expected, actual)
    }




    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: Graph with three components (3 vertexes, 1 age)")
    fun startTest10() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_10.json")
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [3] 2: [1] 3: [2] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: Graph with 0 vertex")
    fun startTest11() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_6.json")
        kosaraju.n = 0
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = ""
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: Graph with n vertexes & n(n-1)+1 ages")
    fun startTest12() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_12.json")
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 2, 3] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Kosaraju: Graph with two components (4 vertexes, 4 ages)")
    fun startTest13() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_13.json")
        kosaraju.n = 4
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [3, 4] 2: [1, 2] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: Graph with one vertex")
    fun DFSTest1() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_1.json")
        kosaraju.n = 1
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        kosaraju.dfs(kosaraju.graph, node_list[0])
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "2 "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: 2-full graph")
    fun DFSTest2() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_2.json")
        kosaraju.n = 2
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        kosaraju.dfs(kosaraju.graph, node_list[0])
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "4 3 "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: 3-full graph")
    fun DFSTest3() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_3.json")
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        kosaraju.dfs(kosaraju.graph, node_list[0])
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "6 5 4 "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: Disconnected graph with three components")
    fun DFSTest4() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_4.json")
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list
        for (vertex in 0 until kosaraju.n) {
            if (!kosaraju.graph.graph[vertex].visited) {
                kosaraju.dfs(kosaraju.graph, kosaraju.graph.graph[vertex])
            }
        }
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "2 4 6 "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: Ordinary Graph")
    fun DFSTest5() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_5.json")
        kosaraju.n = 8
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        kosaraju.dfs(kosaraju.graph, node_list[0])
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "16 15 12 7 14 10 11 6 "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: Graph with one component (3 vertexes, 3 ages)")
    fun DFSTest6() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_6.json")
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        for (vertex in 0 until kosaraju.n) {
            if (!kosaraju.graph.graph[vertex].visited) {
                kosaraju.dfs(kosaraju.graph, kosaraju.graph.graph[vertex])
            }
        }
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "6 5 4 "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: Graph with one component (3 vertexes, 4 ages)")
    fun DFSTest7() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_7.json")
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        for (vertex in 0 until kosaraju.n) {
            if (!kosaraju.graph.graph[vertex].visited) {
                kosaraju.dfs(kosaraju.graph, kosaraju.graph.graph[vertex])
            }
        }
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "6 5 4 "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: Graph with two components (3 vertexes, 2 ages)")
    fun DFSTest8() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_8.json")
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        for (vertex in 0 until kosaraju.n) {
            if (!kosaraju.graph.graph[vertex].visited) {
                kosaraju.dfs(kosaraju.graph, kosaraju.graph.graph[vertex])
            }
        }
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "4 3 6 "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: Graph with three components (3 vertexes, 2 ages)")
    fun DFSTest9() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_9.json")
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        for (vertex in 0 until kosaraju.n) {
            if (!kosaraju.graph.graph[vertex].visited) {
                kosaraju.dfs(kosaraju.graph, kosaraju.graph.graph[vertex])
            }
        }
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "6 5 4 "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: Graph with three components (3 vertexes, 1 age)")
    fun DFSTest10() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_10.json")
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        for (vertex in 0 until kosaraju.n) {
            if (!kosaraju.graph.graph[vertex].visited) {
                kosaraju.dfs(kosaraju.graph, kosaraju.graph.graph[vertex])
            }
        }
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "4 3 6 "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: Graph with 0 vertex")
    fun DFSTest11() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_11.json")
        kosaraju.n = 0
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        for (vertex in 0 until kosaraju.n) {
            if (!kosaraju.graph.graph[vertex].visited) {
                kosaraju.dfs(kosaraju.graph, kosaraju.graph.graph[vertex])
            }
        }
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "0 "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("DFS: Graph with n vertexes & n(n-1)+1 ages")
    fun DFSTest12() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_12.json")
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        for (vertex in 0 until kosaraju.n) {
            if (!kosaraju.graph.graph[vertex].visited) {
                kosaraju.dfs(kosaraju.graph, kosaraju.graph.graph[vertex])
            }
        }
        var actual = ""
        for(i in 0 until kosaraju.graph.graph.size){
            actual += "${kosaraju.graph.graph[i].timeout} "
        }
        println(actual)
        val expected = "6 5 4 "
        assertEquals(expected, actual)
    }
}