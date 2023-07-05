import com.example.practice.Kosaraju
import com.example.practice.OrientedGraph
import org.junit.jupiter.api.Assertions.*
import com.example.practice.Loader
import com.example.practice.Node

class KosarajuTest {

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Ordinary Graph")
    fun startTest1() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_1.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 8
        kosaraju.m = 13
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start(1)
        val expected = "1: [1, 5, 2] 2: [3, 4] 3: [7, 6] 4: [8] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with one vertex")
    fun startTest2() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_2.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 1
        kosaraju.m = 0
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start(1)
        val expected = "1: [] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Full Graph with one component")
    fun startTest3() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_3.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3
        kosaraju.m = 6
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start(1)
        val expected = "1: [1, 2, 3] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with two component")
    fun startTest4() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_4.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3
        kosaraju.m = 1
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start(1)
        val expected = "1: [3] 2: [1, 2] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with three component and one age")
    fun startTest5() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_5.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3
        kosaraju.m = 2
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start(1)
        val expected = "1: [3] 2: [1] 3: [2] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Generated graph with one vertex")
    fun startTest6() {
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 1
        kosaraju.m = 0
        kosaraju.graph = OrientedGraph()

        val actual = kosaraju.start(0)
        val expected = "1: [] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Generated graph with three component")
    fun startTest7() {
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 3
        kosaraju.m = 0
        kosaraju.graph = OrientedGraph()

        val actual = kosaraju.start(0)
        val expected = "1: [3] 2: [2] 3: [1] "
        assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Graph with 0 vertex")
    fun startTest8() {
        var node_list: ArrayList<Node> = Loader().loadFromFile("test_graph_6.json")
        var kosaraju: Kosaraju = Kosaraju()
        kosaraju.n = 0
        kosaraju.m = 0
        kosaraju.graph = OrientedGraph()
        kosaraju.graph.graph = node_list

        val actual = kosaraju.start(1)
        val expected = ""
        assertEquals(expected, actual)
    }
}