import MerkleOddsPolicy.*
import java.security.MessageDigest
import java.util.*


fun computeMerkleRoot(initialData: List<String>,
                      firstHashRound: Boolean = true,
                      applyLittleEndian: Boolean = false,
                      doubleHash: Boolean = true,
                      applyLittleEndianEveryStage: Boolean = false,
                      oddsPolicy: MerkleOddsPolicy = BUBBLE_UP_ODD): String {

    val sha256 = MessageDigest.getInstance("SHA-256")

    val leaves = initialData
            .map { if (firstHashRound) hash(sha256, it, doubleHash) else it }
            .map { if (applyLittleEndian) littleEndian(it) else it }

    var current = LinkedList<String>(leaves)
    var next = LinkedList<String>()

    while (current.size > 1) {
        current = createNextLevel(current, oddsPolicy, sha256, doubleHash, applyLittleEndianEveryStage)
        next = LinkedList()
    }
    return current.first()
}

private fun createNextLevel(current: LinkedList<String>,
                            oddsPolicy: MerkleOddsPolicy,
                            hashMethod: MessageDigest,
                            doubleHash: Boolean,
                            applyLittleEndianEveryStage: Boolean): LinkedList<String> {
    val next = LinkedList<String>()
    for (i in 0 until current.size step 2) {
        val element = when (i) {
            current.lastIndex -> when (oddsPolicy) {
                BUBBLE_UP_ODD -> current[i]
                DUPLICATE_ODD -> hash(hashMethod, "${current[i]}${current[i]}", doubleHash)
            }
            else -> hash(hashMethod, "${current[i]}${current[i + 1]}", doubleHash)
        }
        val newElement = if (applyLittleEndianEveryStage) littleEndian(element) else element
        next.addLast(newElement)
    }
    return next
}

enum class MerkleOddsPolicy {
    BUBBLE_UP_ODD, DUPLICATE_ODD
}
