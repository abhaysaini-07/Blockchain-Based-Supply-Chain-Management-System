package com.supplychain;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.supplychain.model.Block;
import com.supplychain.model.Transaction;
import com.supplychain.service.BlockchainService;
import com.supplychain.util.CollectionUtils;

/**
 * Main class demonstrating the blockchain supply chain system
 * Demonstrates: Core Java Concepts, Multithreading
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Blockchain Supply Chain Management System ===\n");
        
        // Create blockchain service
        BlockchainService service = new BlockchainService(2);
        
        // Create sample transactions
        Transaction t1 = new Transaction(
            "TXN001",
            "ITEM001",
            "Manufacturer",
            "Warehouse A",
            "In Transit",
            "Initial shipment"
        );
        
        Transaction t2 = new Transaction(
            "TXN002",
            "ITEM001",
            "Warehouse A",
            "Warehouse B",
            "Pending",
            "Transfer between warehouses"
        );
        
        Transaction t3 = new Transaction(
            "TXN003",
            "ITEM002",
            "Warehouse B",
            "Retail Store",
            "Delivered",
            "Final delivery"
        );
        
        try {
            // Add transactions synchronously
            System.out.println("Adding transactions...");
            Block block1 = service.addTransaction(t1);
            System.out.println("Block 1 created: " + block1.getHash());
            
            Block block2 = service.addTransaction(t2);
            System.out.println("Block 2 created: " + block2.getHash());
            
            // Add transaction asynchronously
            System.out.println("\nAdding transaction asynchronously...");
            CompletableFuture<Block> future = service.addTransactionAsync(t3);
            Block block3 = future.get();
            System.out.println("Block 3 created: " + block3.getHash());
            
            // Get all blocks
            System.out.println("\n=== All Blocks ===");
            List<Block> blocks = service.getAllBlocks();
            for (Block block : blocks) {
                System.out.println("Block " + block.getIndex() + ": " + block.getHash());
            }
            
            // Validate blockchain
            System.out.println("\n=== Validating Blockchain ===");
            CompletableFuture<Boolean> validationFuture = service.validateBlockchainAsync();
            boolean isValid = validationFuture.get();
            System.out.println("Blockchain is " + (isValid ? "VALID" : "INVALID"));
            
            // Demonstrate Collections & Generics
            System.out.println("\n=== Collections & Generics Demo ===");
            List<Transaction> transactions = service.getAllTransactions();
            System.out.println("Total transactions: " + transactions.size());
            
            // Filter transactions by status
            List<Transaction> inTransit = CollectionUtils.filter(
                transactions,
                t -> "In Transit".equals(t.getStatus())
            );
            System.out.println("In Transit transactions: " + inTransit.size());
            
            // Group by status
            var grouped = CollectionUtils.groupTransactionsByStatus(transactions);
            System.out.println("Transactions grouped by status:");
            grouped.forEach((status, list) -> 
                System.out.println("  " + status + ": " + list.size())
            );
            
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (java.util.concurrent.ExecutionException e) {
            System.err.println("Execution Error: " + e.getMessage());
        } catch (com.supplychain.exception.BlockchainException e) {
            System.err.println("Blockchain Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            service.shutdown();
        }
    }
}

