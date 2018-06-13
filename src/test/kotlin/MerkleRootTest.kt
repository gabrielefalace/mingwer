import org.junit.Assert
import org.junit.Test

class MerkleRootTest {

    @Test
    fun `merkle computed with power-of-2 inputs`() {
        val first = "a"
        val second = "b"
        val third = "c"
        val fourth = "d"

        val root = computeMerkleRoot(listOf(first, second, third, fourth))

        Assert.assertEquals("a3712fb404bdd9a9155501160b342ae8e4e92b108e5bba4078a14a7ab619b295", root)
    }

    @Test
    fun `merkle computed with a odd number of inputs - bubble-up odds policy`() {
        val first = "a"
        val second = "b"
        val third = "c"
        val fourth = "d"
        val fifth = "e"

        val root = computeMerkleRoot(listOf(first, second, third, fourth, fifth))

        Assert.assertEquals("e987b5c601e87b0e65519dd58caa9226cb9acc85136ff796dd7f1a837530c847", root)
    }

    @Test
    fun `merkle computed with even (not power-of-2) inputs `() {
        val first = "a"
        val second = "b"
        val third = "c"
        val fourth = "d"
        val fifth = "e"
        val sixth = "f"

        val root = computeMerkleRoot(listOf(first, second, third, fourth, fifth, sixth))

        Assert.assertEquals("5343ac5720005a8f7972351aa08fcb04558eda7aea81bdb40a1d5326d33f7edd", root)
    }


    @Test
    fun `merkle computed with a odd number of inputs - duplicate odds policy`() {
        //TODO implement
    }
}