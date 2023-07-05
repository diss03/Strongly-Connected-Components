import com.example.practice.Kosaraju
import com.example.practice.OrientedGraph
import org.junit.jupiter.api.Assertions.*
import com.example.practice.Loader
import com.example.practice.Node

class KosarajuTest {

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with one vertex")
    fun startTest1() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_1.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 1
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("2-full graph")
    fun startTest2() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_2.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 2

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 2] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("3-full graph")
    fun startTest3() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_3.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 2, 3] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Disconnected graph with three component")
    fun startTest4() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_4.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [3] 2: [2] 3: [1] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Ordinary Graph")
    fun startTest5() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_5.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 8

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 5, 2] 2: [3, 4] 3: [7, 6] 4: [8] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with one component (3 vertexes, 3 ages)")
    fun startTest6() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_6.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 3, 2] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with one component (3 vertexes, 4 ages)")
    fun startTest7() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_7.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 2, 3] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with two components (3 vertexes, 2 ages)")
    fun startTest8() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_8.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [3] 2: [1, 2] "
        assertEquals(expected, actual)
    }
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with three components (3 vertexes, 2 ages)")
    fun startTest9() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_9.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3

        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1] 2: [2] 3: [3] "
        assertEquals(expected, actual)
    }




    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with three components (3 vertexes, 1 age)")
    fun startTest10() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_10.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [3] 2: [1] 3: [2] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with 0 vertex")
    fun startTest11() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_6.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 0
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = ""
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with n vertexes & n(n-1)+1 ages")
    fun startTest12() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_12.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start()

        val expected = "1: [1, 2, 3] "
        assertEquals(expected, actual)
    }

}