import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.binary.Hex.*
import java.lang.StringBuilder
import java.security.MessageDigest
import java.util.*

const val CURRENT_VERSION = 2
const val SUBMIT_BLOCK_CMD = "bitcoin-cli submitblock"
const val GET_MINING_INFO = "bitcoin-cli getmininginfo"
const val GET_MEMPOOL_TRANSACTIONS_IDS = "getrawmempool"
const val GET_TRANSACTION = "gettransaction" // <txid>
const val GET_RAW_TRANSACTION = "getrawtransaction" // <txid>


fun toLittleEndianHexString(value: Int, numBytes: Int): String {
    require(numBytes.isEven())
    val c = Integer.toHexString(value).toCharArray()
    val paddingZerosAmount = (numBytes * 2) - c.size
    val padding = CharArray(paddingZerosAmount, { _ -> '0' })
    return littleEndian(String(padding + c))
}

fun littleEndian(s: String): String {
    val res = StringBuilder(s.length)
    for (i in (s.length - 1) downTo 0 step 2) {
        res.append(s[i - 1]).append(s[i])
    }
    return res.toString()
}

fun execBitcoinCommand(cmd: String): String {
    val str = StringBuilder()
    val proc = Runtime.getRuntime().exec(cmd)
    Scanner(proc.inputStream).use {
        while (it.hasNextLine()) {
            str.append(it.nextLine())
        }
    }
    return str.toString()
}


//TODO improve?
fun hash(hasher: MessageDigest, input: String, double: Boolean = true) : String {
    val firstPass = hasher.digest(input.toByteArray())
    val firstPassString = encodeHexString(firstPass)
    if (!double)
        return firstPassString

    val secondPass = hasher.digest(firstPassString.toByteArray())
    return encodeHexString(secondPass)
}


fun Int.isEven() = this % 2 == 0
