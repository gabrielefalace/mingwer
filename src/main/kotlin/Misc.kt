fun prettyPrintHeader(b: Block): String {
  val versionHex = toLittleEndianHexString(b.version, 4)
  val hashPrevBlockLittleEndian = littleEndian(b.hashPrevBlock)
  val hashMerkleRootHex = littleEndian(b.hashMerkleRoot)

  val timeAsHex = java.lang.Long.toHexString(b.time)

  val timeOk = littleEndian(timeAsHex) // it's usually already 4B, just apply little-endian

  val bitsHex = java.lang.Long.toHexString(b.bits)
  val nonceHex = littleEndian(java.lang.Long.toHexString(b.nonce))

  return """Block:
              version: $versionHex
              prev hash: $hashPrevBlockLittleEndian
              merkle root. $hashMerkleRootHex
              time: $timeOk
              bits: $bitsHex
              nonce: $nonceHex"""
}