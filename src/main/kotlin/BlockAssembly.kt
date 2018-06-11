import java.security.MessageDigest
import java.util.*

fun assembleCandidateBlock(): Block {
    val block = Block()
    val txIds = retrieveTransactionsIds()
    val numTransaction = chooseNumberOfTransactions(txIds)
    val includedTransactions = txIds.subList(0, numTransaction)

    block.transactions = buildTransactionList(includedTransactions)
    block.hashMerkleRoot = computeMerkleRoot(block.transactions)
    return block
}


//TODO manage odd number of Tx
fun computeMerkleRoot(transactions: List<Transaction>): String {
    val hash = MessageDigest.getInstance("SHA-256")
    val txHashes = transactions.map { hash.digest(it.id.toByteArray()) }

    var previous: LinkedList<ByteArray> = LinkedList(txHashes)
    val current: LinkedList<ByteArray> = LinkedList<ByteArray>()
    while (previous.size > 1) {
        for (i in 0 until txHashes.size step 2) {
            val element = when {
                i+1 >= txHashes.size -> txHashes[i]
                else -> hash.digest(txHashes[i] + txHashes[i + 1])
            }
            current.addLast(element)
        }
        previous = LinkedList(current.map { it.copyOf() })
        current.clear()
    }

    return String(previous.first())
}




fun buildTransactionList(includedTransactions: List<String>): LinkedList<Transaction> {
    val txs = LinkedList<Transaction>()
    txs.addFirst(createCoinbaseTransacion())
    for (txId in includedTransactions) {
        txs.addLast(createTransaction(execBitcoinCommand(GET_TRANSACTION + " " + txId)))
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