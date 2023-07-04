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
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Incorrect data")
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
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Check node & edges amount")
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
        assertEquals(expected, actual)
    }
}