import org.apache.commons.codec.binary.Hex
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.util.*

data class Block(val version: Int = CURRENT_VERSION) {
  var hashPrevBlock: String = ""
  var hashMerkleRoot: String = ""
  var time: Long = Instant.now(Clock.system(ZoneId.of("UTC"))).epochSecond
  var bits: Long = 0x0004
  var nonce: Long = 0x0005

  var transactions: LinkedList<Transaction> = LinkedList()

  fun headerHex(): String {
    val versionHex = toLittleEndianHexString(version, 4)
    val hashPrevBlockLittleEndian = littleEndian(hashPrevBlock)
    val hashMerkleRootLittleEndian = littleEndian(hashMerkleRoot)
    val timestampHex = littleEndian(java.lang.Long.toHexString(time))
    val bitsHex = java.lang.Long.toHexString(bits)
    val nonceHex = littleEndian(java.lang.Long.toHexString(nonce))

    return buildString {
      append(versionHex)
      append(hashPrevBlockLittleEndian)
      append(hashMerkleRootLittleEndian)
      append(timestampHex)
      append(bitsHex)
      append(nonceHex)
    }
  }

  fun serializeAsHex(): String {
    //TODO implement: how to serialize for block submission?
    return Hex.encodeHexString("the entire block!".toByteArray())
  }

}
