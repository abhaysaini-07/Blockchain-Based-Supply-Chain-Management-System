package com.supplychain.interfaces;

import java.util.List;

import com.supplychain.model.Block;
import com.supplychain.model.Transaction;

/**
 * Interface for database operations
 * Demonstrates: Interfaces, Database Operations
 */
public interface IDatabaseOperations {
    /**
     * Save a block to the database
     * @param block The block to save
     * @return true if successful, false otherwise
     */
    boolean saveBlock(Block block);
    
    /**
     * Retrieve all blocks from the database
     * @return List of blocks
     */
    List<Block> getAllBlocks();
    
    /**
     * Save a transaction to the database
     * @param transaction The transaction to save
     * @return true if successful, false otherwise
     */
    boolean saveTransaction(Transaction transaction);
    
    /**
     * Retrieve all transactions from the database
     * @return List of transactions
     */
    List<Transaction> getAllTransactions();
    
    /**
     * Get a block by its hash
     * @param hash The hash of the block
     * @return The block if found, null otherwise
     */
    Block getBlockByHash(String hash);
    
    /**
     * Close database connections
     */
    void close();
}

