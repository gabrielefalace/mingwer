import MerkleOddsPolicy.*
import org.apache.commons.codec.binary.Hex
import java.security.MessageDigest
import java.util.*


fun computeMerkleRoot(transactionIds: List<String>, oddsPolicy: MerkleOddsPolicy = BUBBLE_UP_ODD): String {
    val sha256 = MessageDigest.getInstance("SHA-256")

    //should the first round of hashes be performed or are input already hashed "enough"?
    var current: LinkedList<String> = LinkedList(transactionIds.map { doubleHash(sha256, it) })
    var next: LinkedList<String> = LinkedList()
    while (current.size > 1) {
        current = createNextLevel(current, oddsPolicy, sha256)
        next = LinkedList()
    }
    return current.first()
}

private fun createNextLevel(current: LinkedList<String>, oddsPolicy: MerkleOddsPolicy, hashMethod: MessageDigest): LinkedList<String> {
    val next = LinkedList<String>()
    for (i in 0 until current.size step 2) {
        val element = when (i) {
            current.lastIndex -> when (oddsPolicy) {
                BUBBLE_UP_ODD -> current[i]
                DUPLICATE_ODD -> doubleHash(hashMethod, "${current[i]}${current[i]}")
            }
            else -> doubleHash(hashMethod, "${current[i]}${current[i + 1]}")
        }
        next.addLast(element)
    }
    return next
}

enum class MerkleOddsPolicy {
    BUBBLE_UP_ODD, DUPLICATE_ODD
}
