package com.supplychain.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.supplychain.blockchain.Blockchain;
import com.supplychain.database.BlockDAO;
import com.supplychain.exception.BlockchainException;
import com.supplychain.model.Block;
import com.supplychain.model.Transaction;

/**
 * Service class for blockchain operations
 * Demonstrates: Multithreading, Synchronization, Service Layer
 */
public class BlockchainService {

    private final Blockchain blockchain;
    private final BlockDAO blockDAO;
    private final ExecutorService executorService;
    private final Object lock = new Object(); // synchronization lock

    public BlockchainService(int difficulty) {
        this.blockchain = new Blockchain(difficulty);
        this.blockDAO = new BlockDAO();
        this.executorService = Executors.newFixedThreadPool(5);
    }

    /**
     * Add a transaction asynchronously
     * Demonstrates: Multithreading + Synchronization
     */
    public CompletableFuture<Block> addTransactionAsync(Transaction transaction) {
        return CompletableFuture.supplyAsync(() -> {
            synchronized (lock) {
                try {
                    // ✅ Centralized validation (Review-2 compliant)
                    TransactionService.validateTransaction(transaction);

                    Gson gson = new Gson();
                    String transactionJson = gson.toJson(transaction);
                    Block newBlock = blockchain.addBlock(transactionJson);

                    // Save block & transaction asynchronously
                    executorService.submit(() -> {
                        blockDAO.saveBlock(newBlock);
                        blockDAO.saveTransaction(transaction);
                    });

                    return newBlock;

                } catch (BlockchainException e) {
                    throw new RuntimeException(
                            "Transaction validation failed: " + e.getMessage(), e
                    );
                } catch (Exception e) {
                    throw new RuntimeException(
                            "Error adding transaction asynchronously", e
                    );
                }
            }
        }, executorService);
    }

    /**
     * Add a transaction synchronously
     */
    public Block addTransaction(Transaction transaction) throws BlockchainException {
        synchronized (lock) {

            // ✅ Centralized validation
            TransactionService.validateTransaction(transaction);

            try {
                Gson gson = new Gson();
                String transactionJson = gson.toJson(transaction);
                Block newBlock = blockchain.addBlock(transactionJson);

                blockDAO.saveBlock(newBlock);
                blockDAO.saveTransaction(transaction);

                return newBlock;

            } catch (Exception e) {
                throw new BlockchainException(
                        "Error adding transaction", e
                );
            }
        }
    }

    /**
     * Validate blockchain asynchronously
     */
    public CompletableFuture<Boolean> validateBlockchainAsync() {
        return CompletableFuture.supplyAsync(() -> {
            synchronized (lock) {
                return blockchain.isValid();
            }
        }, executorService);
    }

    /**
     * Get all blocks
     */
    public List<Block> getAllBlocks() {
        synchronized (lock) {
            return blockchain.getBlocks();
        }
    }

    /**
     * Get all transactions from database
     */
    public List<Transaction> getAllTransactions() {
        return blockDAO.getAllTransactions();
    }

    /**
     * Shutdown executor service
     */
    public void shutdown() {
        executorService.shutdown();
    }
}