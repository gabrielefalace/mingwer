import org.apache.commons.codec.binary.Hex
import java.security.MessageDigest
import java.util.*

fun assembleCandidateBlock(): Block {
    val block = Block()
    val txIds = retrieveTransactionsIds()
    val numTransaction = chooseNumberOfTransactions(txIds)
    val includedTransactions = txIds.subList(0, numTransaction)

    block.transactions = buildTransactionList(includedTransactions)
    //TODO get raw transaction ... block.hashMerkleRoot = computeMerkleRoot(block.transactions)
    return block
}


fun computeMerkleRoot(transactionIds: List<String>): String {
    val sha256 = MessageDigest.getInstance("SHA-256")

    var current: LinkedList<String> = LinkedList(transactionIds.map { doubleHash(sha256, it) })
    var next: LinkedList<String> = LinkedList()
    while (current.size > 1) {
        for (i in 0 until current.size step 2) {
            val element = when {
                i+1 >= current.size -> current[i]
                else -> doubleHash(sha256, "${current[i]}${current[i+1]}")
            }
            next.addLast(element)
            println("added $element")
        }
        println("-----")
        current = LinkedList(next.map { ""+it })
        next = LinkedList()
    }

    return current.first()
}


fun buildTransactionList(includedTransactions: List<String>): LinkedList<Transaction> {
    val txs = LinkedList<Transaction>()
    txs.addFirst(createCoinbaseTransacion())
    for (txId in includedTransactions) {
        txs.addLast(createTransaction(execBitcoinCommand("$GET_TRANSACTION $txId")))
    }
    return txs
}


private fun chooseNumberOfTransactions(txIds: List<String>): Int {
    //TODO how many TX?
    return 511
}

private fun createCoinbaseTransacion(): Transaction {
    //TODO Implement construction of the COINBASE TX
    return Transaction("6df54b081e28161b707832e3f95d85f064a4a38a8cc7cf62cbad74e46840b429")
}

private fun createTransaction(rawTxData: String): Transaction {
    //TODO Implement construction of MemPool TX
    return Transaction("BLABLABLA")
}

private fun retrieveTransactionsIds(): List<String> {
    val rawIds = execBitcoinCommand(GET_MEMPOOL_TRANSACTIONS_IDS)
    //TODO Implement
    return listOf("123", "345")
}


data class Transaction(val id: String)