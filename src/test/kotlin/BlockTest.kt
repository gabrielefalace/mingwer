import org.apache.commons.codec.binary.Hex
import org.junit.Assert.assertEquals
import org.junit.Test
import java.security.MessageDigest
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class BlockTest {

    @Test
    fun `test header`() {
        val b = Block(1)

        //TODO do it better: from the real transactions?
        val txIds = listOf(
                "51d37bdd871c9e1f4d5541be67a6ab625e32028744d7d4609d0c37747b40cd2d",
                "60c25dda8d41f8d3d7d5c6249e2ea1b05a25bf7ae2ad6d904b512b31f997e1a1",
                "01f314cdd8566d3e5dbdd97de2d9fbfbfd6873e916a00d48758282cbb81a45b9",
                "b519286a1040da6ad83c783eb2872659eaf57b1bec088e614776ffe7dc8f6d01"
        ).map { littleEndian(it) }

        b.hashMerkleRoot = "2b12fcf1b09288fcaff797d71e950e71ae42b91e8bdb2304758dfcffc2b620e3" // computeMerkleRoot(txIds, false)

        b.hashPrevBlock = "00000000000008a3a41b85b8b29ad444def299fee21793cd8b9e567eab02cd81"
        b.time = Instant.from(LocalDateTime.of(2011, 5, 21, 17, 26, 31).atZone(ZoneId.of("UTC"))).epochSecond
        b.bits = 0xf2b9441a
        b.nonce = 2504433986

        prettyPrintHeader(b)

        val hashMaker = MessageDigest.getInstance("SHA-256")
        val decodedHeader = Hex.decodeHex(b.headerHex().toCharArray())
        val hash = hashMaker.digest(hashMaker.digest(decodedHeader))

        val result = littleEndian(String(Hex.encodeHex(hash)))

        assertEquals("2b12fcf1b09288fcaff797d71e950e71ae42b91e8bdb2304758dfcffc2b620e3", b.hashMerkleRoot)
        assertEquals("00000000000000001e8d6829a8a21adc5d38d0a473b144b6765798e61f98bd1d", result)
    }

}