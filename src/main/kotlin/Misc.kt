fun prettyPrintHeader(b: Block) {
    val versionHex = toLittleEndianHexString(b.version, 4)
    val hashPrevBlockLittleEndian = littleEndian(b.hashPrevBlock)
    val hashMerkleRootHex = b.hashMerkleRoot

    val timeAsHex = java.lang.Long.toHexString(b.time)

    val timeOk = littleEndian(timeAsHex) // it's usually already 4B, just apply little-endian

    val bitsHex = java.lang.Long.toHexString(b.bits)
    val nonceHex = littleEndian(java.lang.Long.toHexString(b.nonce))

    val printableBlock = "version: $versionHex \nprev hash: $hashPrevBlockLittleEndian \nmerkle root. $hashMerkleRootHex \ntime: $timeOk \nbits: $bitsHex \nnonce: $nonceHex"

    println("---- Block ---- \n$printableBlock \n---- ---- ----")
}