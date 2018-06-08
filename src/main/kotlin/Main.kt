import kotlinx.coroutines.experimental.launch
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.MINUTES


fun main(args: Array<String>) {

  val targetDifficulty: Long = 0x1212
  val numProcessors = 8
  val latch = CountDownLatch(numProcessors)
  val candidateBlock = assembleCandidateBlock()

  for (i in 0 until numProcessors) {
    launch {
      seek(latch, candidateBlock, targetDifficulty)
    }
  }
  latch.await(10, MINUTES)
}

fun seek(latch: CountDownLatch, block: Block, targetDifficulty: Long) {
  for (nonce in 0 until 3_000_000L) {
    block.nonce = guessNonce(block)
    if (verifyBlock(block, targetDifficulty)) {
      submitBlock(block)
    }
  }
  latch.countDown()
}

