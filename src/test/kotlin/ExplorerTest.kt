import org.junit.Test

class ExplorerTest {

    val target = "2b12fcf1b09288fcaff797d71e950e71ae42b91e8bdb2304758dfcffc2b620e3"

    val txIds = listOf(
            "51d37bdd871c9e1f4d5541be67a6ab625e32028744d7d4609d0c37747b40cd2d",
            "60c25dda8d41f8d3d7d5c6249e2ea1b05a25bf7ae2ad6d904b512b31f997e1a1",
            "01f314cdd8566d3e5dbdd97de2d9fbfbfd6873e916a00d48758282cbb81a45b9",
            "b519286a1040da6ad83c783eb2872659eaf57b1bec088e614776ffe7dc8f6d01"
    )

    @Test
    fun `exploring parameters combinations`() {
        rootAndPrint(txIds, true, true, true, false)
        rootAndPrint(txIds, true, true, false, false)
        rootAndPrint(txIds, true, false, true, false)
        rootAndPrint(txIds, true, false, false, false)
        rootAndPrint(txIds, false, true, true, false)
        rootAndPrint(txIds, false, true, false, false)
        rootAndPrint(txIds, false, false, true, false)
        rootAndPrint(txIds, false, false, false, false)

        // with little endian every stage
        rootAndPrint(txIds, true, true, true, true)
        rootAndPrint(txIds, true, true, false, true)
        rootAndPrint(txIds, true, false, true, true)
        rootAndPrint(txIds, true, false, false, true)
        rootAndPrint(txIds, false, true, true, true)
        rootAndPrint(txIds, false, true, false, true)
        rootAndPrint(txIds, false, false, true, true)
        rootAndPrint(txIds, false, false, false, true)
    }


    private fun rootAndPrint(initialData: List<String>,
                             firstHashRound: Boolean = true,
                             applyLittleEndian: Boolean = false,
                             doubleHash: Boolean = true,
                             applyLittleEndianEveryStage: Boolean = false) {

        val root = computeMerkleRoot(initialData, firstHashRound, applyLittleEndian, doubleHash)

        val prefix = if (target in setOf(root, littleEndian(root))) ">>>> FOUND >>>" else ""

        println("combination [$firstHashRound, $applyLittleEndian, $doubleHash, $applyLittleEndianEveryStage]: \t${root}")


    }
}