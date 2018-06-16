import org.junit.Test

class ExplorerTest {

    val target = "2b12fcf1b09288fcaff797d71e950e71ae42b91e8bdb2304758dfcffc2b620e3"
    val txIds = listOf(
            "51d37bdd871c9e1f4d5541be67a6ab625e32028744d7d4609d0c37747b40cd2d",
            "60c25dda8d41f8d3d7d5c6249e2ea1b05a25bf7ae2ad6d904b512b31f997e1a1",
            "01f314cdd8566d3e5dbdd97de2d9fbfbfd6873e916a00d48758282cbb81a45b9",
            "b519286a1040da6ad83c783eb2872659eaf57b1bec088e614776ffe7dc8f6d01"
    )

    val target2 = "a48025bde298b368ed0924d8f00dbf8a7e38933e7e0e684f61741d3db8908f73"
    val txIds2 = listOf("65ac1241c1ca1f2d91b3306dda433b1ea71371f530cc00c68c1700d46f5fa984",
            "ec858f7870ea9ccb22025b0e5ea8d8a3ccbd33e200208adb49042a03df92687d")

    @Test
    fun `exploring parameters combinations`() {
        for (algo in setOf("SHA-256", "SHA-1", "MD5")) {
            println("\n - - - $algo - - - \n")
            rootAndPrint(algo, txIds2, true, true, true, false)
            rootAndPrint(algo, txIds2, true, true, false, false)
            rootAndPrint(algo, txIds2, true, false, true, false)
            rootAndPrint(algo, txIds2, true, false, false, false)

            rootAndPrint(algo, txIds2, false, true, true, false)
            rootAndPrint(algo, txIds2, false, true, false, false)
            rootAndPrint(algo, txIds2, false, false, true, false)
            rootAndPrint(algo, txIds2, false, false, false, false)

            // with little endian every stage
            rootAndPrint(algo, txIds2, true, true, true, true)
            rootAndPrint(algo, txIds2, true, true, false, true)
            rootAndPrint(algo, txIds2, true, false, true, true)
            rootAndPrint(algo, txIds2, true, false, false, true)

            rootAndPrint(algo, txIds2, false, true, true, true)
            rootAndPrint(algo, txIds2, false, true, false, true)
            rootAndPrint(algo, txIds2, false, false, true, true)
            rootAndPrint(algo, txIds2, false, false, false, true)
        }
    }


    private fun rootAndPrint(algorithm: String,
                             initialData: List<String>,
                             firstHashRound: Boolean = true,
                             applyLittleEndian: Boolean = false,
                             doubleHash: Boolean = true,
                             applyLittleEndianEveryStage: Boolean = false) {

        val root = computeMerkleRoot(initialData, firstHashRound, applyLittleEndian, doubleHash, applyLittleEndianEveryStage, algorithm)

        val prefix = if (target2 in setOf(root, littleEndian(root))) ">>>> FOUND >>>" else ""

        println("combination [$firstHashRound, $applyLittleEndian, $doubleHash, $applyLittleEndianEveryStage]: \t $root")


    }


    @Test
    fun toLittleEnd() {
        val first = "65ac1241c1ca1f2d91b3306dda433b1ea71371f530cc00c68c1700d46f5fa984"
        val second = "ec858f7870ea9ccb22025b0e5ea8d8a3ccbd33e200208adb49042a03df92687d"
        val o = littleEndian(first)
        println(o)
        val q = littleEndian(second)
        println(q)
    }
}

