package com.supplychain.interfaces;

import com.supplychain.model.Block;
import java.util.List;

/**
 * Interface for blockchain operations
 * Demonstrates: Interfaces, OOP
 */
public interface IBlockchain {
    /**
     * Add a new block to the blockchain
     * @param data The transaction data
     * @return The newly created block
     */
    Block addBlock(String data);
    
    /**
     * Validate the integrity of the blockchain
     * @return true if blockchain is valid, false otherwise
     */
    boolean isValid();
    
    /**
     * Get all blocks in the blockchain
     * @return List of blocks
     */
    List<Block> getBlocks();
    
    /**
     * Get the latest block in the blockchain
     * @return The latest block
     */
    Block getLatestBlock();
    
    /**
     * Get the size of the blockchain
     * @return Number of blocks
     */
    int getSize();
}

