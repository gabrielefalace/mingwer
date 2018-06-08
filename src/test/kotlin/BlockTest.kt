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
    b.hashPrevBlock = "00000000000008a3a41b85b8b29ad444def299fee21793cd8b9e567eab02cd81"
    b.hashMerkleRoot = "2b12fcf1b09288fcaff797d71e950e71ae42b91e8bdb2304758dfcffc2b620e3"
    b.time = Instant.from(LocalDateTime.of(2011, 5, 21, 17, 26, 31).atZone(ZoneId.of("UTC"))).epochSecond
    b.bits = 0xf2b9441a
    b.nonce = 2504433986

    prettyPrintHeader(b)

    val hashMaker = MessageDigest.getInstance("SHA-256")
    val decodedHeader = Hex.decodeHex(b.headerHex().toCharArray())
    val hash = hashMaker.digest(hashMaker.digest(decodedHeader))

    val result = littleEndian(String(Hex.encodeHex(hash)))

    assertEquals("00000000000000001e8d6829a8a21adc5d38d0a473b144b6765798e61f98bd1d", result)
  }

}