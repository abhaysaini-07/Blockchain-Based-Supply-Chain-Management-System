package com.supplychain;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.supplychain.blockchain.Blockchain;
import com.supplychain.model.Block;
import com.supplychain.model.Transaction;
import com.supplychain.util.CollectionUtils;

/**
 * Test class for Blockchain functionality
 * Demonstrates: Testing, Core Java Concepts
 */
public class BlockchainTest {
    private Blockchain blockchain;
    
    @BeforeEach
    public void setUp() {
        blockchain = new Blockchain(2);
    }
    
    @Test
    public void testBlockchainCreation() {
        assertNotNull(blockchain);
        assertEquals(1, blockchain.getSize()); // Genesis block
    }
    
    @Test
    public void testAddBlock() {
        Block newBlock = blockchain.addBlock("Test transaction data");
        assertNotNull(newBlock);
        assertEquals(2, blockchain.getSize());
        assertEquals("Test transaction data", newBlock.getData());
    }
    
    @Test
    public void testBlockchainValidity() {
        blockchain.addBlock("Transaction 1");
        blockchain.addBlock("Transaction 2");
        assertTrue(blockchain.isValid());
    }
    
    @Test
    public void testGetLatestBlock() {
        Block block1 = blockchain.addBlock("First transaction");
        Block latest = blockchain.getLatestBlock();
        assertEquals(block1.getHash(), latest.getHash());
    }
    
    @Test
    public void testCollectionsAndGenerics() {
        // Create sample transactions
        Transaction t1 = new Transaction("T1", "ITEM1", "A", "B", "Pending", "Test");
        Transaction t2 = new Transaction("T2", "ITEM2", "B", "C", "In Transit", "Test");
        Transaction t3 = new Transaction("T3", "ITEM1", "C", "D", "Pending", "Test");
        
        List<Transaction> transactions = List.of(t1, t2, t3);
        
        // Test filtering with generics
        List<Transaction> pending = CollectionUtils.filter(
            transactions,
            t -> "Pending".equals(t.getStatus())
        );
        assertEquals(2, pending.size());
        
        // Test grouping
        var grouped = CollectionUtils.groupTransactionsByStatus(transactions);
        assertEquals(2, grouped.size());
        assertTrue(grouped.containsKey("Pending"));
        assertTrue(grouped.containsKey("In Transit"));
    }
}

