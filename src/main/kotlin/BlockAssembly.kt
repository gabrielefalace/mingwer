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




fun buildTransactionList(includedTransactions: List<String>): LinkedList<Transaction> {
    val txs = LinkedList<Transaction>()
    txs.addFirst(createCoinbaseTransacion())
    for (txId in includedTransactions) {
        txs.addLast(createTransaction(execBitcoinCommand("$GET_TRANSACTION $txId")))
    }
    return txs
}


private fun chooseNumberOfTransactions(txIds: List<String>): Int {
    //TODO how many TX? 511+1 conibase
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