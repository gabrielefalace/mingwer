import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.binary.Hex.*
import org.junit.Test
import java.security.MessageDigest

class MerkleTest {

    @Test
    fun `merkle computed with a even number of Tx`() {
        //val sha256 = MessageDigest.getInstance("SHA-256")
        //val first = sha256.digest("51d37bdd871c9e1f4d5541be67a6ab625e32028744d7d4609d0c37747b40cd2d".toByteArray())
        //val second = sha256.digest("60c25dda8d41f8d3d7d5c6249e2ea1b05a25bf7ae2ad6d904b512b31f997e1a1".toByteArray())
        //val third = sha256.digest("01f314cdd8566d3e5dbdd97de2d9fbfbfd6873e916a00d48758282cbb81a45b9".toByteArray())
        //val fourth = sha256.digest("b519286a1040da6ad83c783eb2872659eaf57b1bec088e614776ffe7dc8f6d01".toByteArray())
        //val input = listOf(encodeHexString(first), encodeHexString(second), encodeHexString(third), encodeHexString(fourth))

        val first = "51d37bdd871c9e1f4d5541be67a6ab625e32028744d7d4609d0c37747b40cd2d"
        val second = "60c25dda8d41f8d3d7d5c6249e2ea1b05a25bf7ae2ad6d904b512b31f997e1a1"
        val third = "01f314cdd8566d3e5dbdd97de2d9fbfbfd6873e916a00d48758282cbb81a45b9"
        val fourth = "b519286a1040da6ad83c783eb2872659eaf57b1bec088e614776ffe7dc8f6d01"

        //TODO use concatenated values from tx fields
        // eg see here https://blockchain.info/rawtx/51d37bdd871c9e1f4d5541be67a6ab625e32028744d7d4609d0c37747b40cd2d

        val output = computeMerkleRoot(listOf(first, second, third,fourth))

        println("supposed Merkle root: $output")
        println("expected Merkle root: 2b12fcf1b09288fcaff797d71e950e71ae42b91e8bdb2304758dfcffc2b620e3")
    }

}