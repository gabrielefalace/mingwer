fun submitBlock(block: Block) {
  execBitcoinCommand(SUBMIT_BLOCK_CMD + " " + block.serializeAsHex())
}
