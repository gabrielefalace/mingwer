import org.junit.Assert.*
import org.junit.Ignore
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

    //TODO fix this
    @Ignore
    @Test
    fun `merkle root computed with even inputs (example from internet)`() {
        val first = "65ac1241c1ca1f2d91b3306dda433b1ea71371f530cc00c68c1700d46f5fa984"
        val second = "ec858f7870ea9ccb22025b0e5ea8d8a3ccbd33e200208adb49042a03df92687d"
        val root = computeMerkleRoot(listOf(first, second), false)
        assertEquals("a48025bde298b368ed0924d8f00dbf8a7e38933e7e0e684f61741d3db8908f73", littleEndian(root))
    }


    @Test
    fun `merkle computed with a odd number of inputs - duplicate odds policy`() {
        //TODO implement
    }


}