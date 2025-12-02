package com.supplychain.blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.supplychain.interfaces.IBlockchain;
import com.supplychain.model.Block;

/**
 * Blockchain implementation
 * Demonstrates: Interfaces, Collections, Generics, Multithreading
 */
public class Blockchain implements IBlockchain {
    private final List<Block> chain;
    private int difficulty;
    private static final String GENESIS_PREV_HASH = "0";
    
    public Blockchain(int difficulty) {
        this.chain = new CopyOnWriteArrayList<>(); // Thread-safe collection
        this.difficulty = difficulty;
        // Create genesis block
        createGenesisBlock();
    }
    
    private void createGenesisBlock() {
        Block genesis = new Block(0, "Genesis Block", GENESIS_PREV_HASH);
        genesis.mineBlock(difficulty);
        chain.add(genesis);
    }
    
    @Override
    public Block addBlock(String data) {
        Block previousBlock = getLatestBlock();
        Block newBlock = new Block(previousBlock.getIndex() + 1, data, previousBlock.getHash());
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
        return newBlock;
    }
    
    @Override
    public boolean isValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);
            
            // Check if current block's hash is correct
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }
            
            // Check if current block points to previous block
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public List<Block> getBlocks() {
        return new ArrayList<>(chain); // Return a copy for thread safety
    }
    
    @Override
    public Block getLatestBlock() {
        if (chain.isEmpty()) {
            return null;
        }
        return chain.get(chain.size() - 1);
    }
    
    @Override
    public int getSize() {
        return chain.size();
    }
    
    public int getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}

