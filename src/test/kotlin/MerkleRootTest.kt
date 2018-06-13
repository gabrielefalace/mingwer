import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class MerkleRootTest {

    @Test
    fun `merkle root computed with power-of-2 inputs`() {

        val root = computeMerkleRoot(listOf("a", "b", "c", "d"))

        assertEquals("a3712fb404bdd9a9155501160b342ae8e4e92b108e5bba4078a14a7ab619b295", root)
    }

    @Test
    fun `merkle root computed with a odd number of inputs - bubble-up odds policy`() {

        val root = computeMerkleRoot(listOf("a", "b", "c", "d", "e"))

        assertEquals("e987b5c601e87b0e65519dd58caa9226cb9acc85136ff796dd7f1a837530c847", root)
    }

    @Test
    fun `merkle root computed with even (not power-of-2) inputs `() {

        val root = computeMerkleRoot(listOf("a", "b", "c", "d", "e", "f"))

        assertEquals("5343ac5720005a8f7972351aa08fcb04558eda7aea81bdb40a1d5326d33f7edd", root)
    }


    @Test
    fun `merkle computed with a odd number of inputs - duplicate odds policy`() {
        //TODO implement
    }
}