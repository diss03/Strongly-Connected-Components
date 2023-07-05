import org.junit.jupiter.api.Test
import com.example.practice.GraphGenerator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class GraphGeneratorTest {

    @Test
    @DisplayName("Discharged graph with five vertexes")
    fun generateGraphTest1() {
        var generator = GraphGenerator()
        val nodeArray = generator.generateGraph(5, 0)
        val expected = "1: {}; 2: {}; 3: {}; 4: {}; 5: {}; "
        var actual = ""
        for(vertex in nodeArray){
            actual += "${vertex.name}: {"
            for(i in vertex.adjacents)
                actual += "${i.name} "
            actual += "}; "
        }
        println(actual)
        assertEquals(expected, actual)
    }
    @Test
    @DisplayName("2-full graph")
    fun generateGraphTest2() {
        var generator = GraphGenerator()
        val nodeArray = generator.generateGraph(2, 2)
        val expected = "1: { 2 }; 2: { 1 }; "
        var actual = ""
        for(vertex in nodeArray){
            actual += "${vertex.name}: {"
            for(i in vertex.adjacents)
                actual += " ${i.name}"
            actual += " }; "
        }
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Graph with 1 vertex and 12 ages")
    fun generateGraphTest3() {
        var generator = GraphGenerator()
        val nodeArray = generator.generateGraph(1, 12)
        var actual = ""
        val expected = "1: { }; "
        for(vertex in nodeArray){
            actual += "${vertex.name}: {"
            for(i in vertex.adjacents)
                actual += " ${i.name}"
            actual += " }; "
        }
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Graph with 5 vertexes and 12 ages")
    fun generateGraphTest4() {
        var generator = GraphGenerator()
        val nodeArray = generator.generateGraph(5, 12)
        val nodeAmount = nodeArray.size
        var vertexAmount = 0
        var actual = ""
        val expected = "5 12"
        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Graph with 0 vertex")
    fun generateGraphTest6() {
        var generator = GraphGenerator()
        val nodeArray = generator.generateGraph(0, 0)
        val nodeAmount = nodeArray.size
        var vertexAmount = 0
        var actual = ""
        val expected = "0 0"
        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Graph with a negative number of vertexes")
    fun generateGraphTest5() {
        var generator = GraphGenerator()
        val nodeArray = generator.generateGraph(-10, 0)
        val nodeAmount = nodeArray.size
        var vertexAmount = 0
        var actual = ""
        val expected = "0 0"
        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Graph with one vertex")
    fun generateGraphTest7() {
        var generator = GraphGenerator()
        val nodeArray = generator.generateGraph(1, 0)
        val expected = "1: {}; "
        var actual = ""
        for(vertex in nodeArray){
            actual += "${vertex.name}: {"
            for(i in vertex.adjacents)
                actual += "${i.name} "
            actual += "}; "
        }
        println(actual)
        assertEquals(expected, actual)
    }
    @Test
    @DisplayName("Graph with a negative number of ages")
    fun generateGraphTest8() {
        var generator = GraphGenerator()
        val nodeArray = generator.generateGraph(3, -10)
        val nodeAmount = nodeArray.size
        var vertexAmount = 0
        var actual = ""
        val expected = "3 0"
        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Graph with a negative number of vertexes and ages")
    fun generateGraphTest9() {
        var generator = GraphGenerator()
        val nodeArray = generator.generateGraph(-10, -10)
        val nodeAmount = nodeArray.size
        var vertexAmount = 0
        var actual = ""
        val expected = "0 0"
        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Graph with a negative number of vertexes & 3 ages")
    fun generateGraphTest10() {
        var generator = GraphGenerator()
        val nodeArray = generator.generateGraph(-10, 3)
        val nodeAmount = nodeArray.size
        var vertexAmount = 0
        var actual = ""
        val expected = "0 0"
        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Graph with a 2 vertexes and 12 ages")
    fun generateGraphTest11() {
        var generator = GraphGenerator()
        val nodeArray = generator.generateGraph(2, 12)
        val nodeAmount = nodeArray.size
        var vertexAmount = 0
        var actual = ""
        val expected = "2 2"
        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }
}