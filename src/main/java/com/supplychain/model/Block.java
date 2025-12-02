package com.supplychain.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * Block class representing a single block in the blockchain
 * Demonstrates: Core Java Concepts, OOP
 */
public class Block implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int index;
    private long timestamp;
    private String data; // Transaction data
    private String previousHash;
    private String hash;
    private int nonce;
    
    public Block(int index, String data, String previousHash) {
        this.index = index;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
        this.previousHash = previousHash;
        this.nonce = 0;
        this.hash = calculateHash();
    }
    
    public final String calculateHash() {
        String dataToHash = index + timestamp + data + previousHash + nonce;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(dataToHash.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
    
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
    }
    
    // Getters and Setters
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public String getPreviousHash() {
        return previousHash;
    }
    
    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
    
    public String getHash() {
        return hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public int getNonce() {
        return nonce;
    }
    
    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return index == block.index &&
               timestamp == block.timestamp &&
               nonce == block.nonce &&
               Objects.equals(data, block.data) &&
               Objects.equals(previousHash, block.previousHash) &&
               Objects.equals(hash, block.hash);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(index, timestamp, data, previousHash, hash, nonce);
    }
    
    @Override
    public String toString() {
        return "Block{" +
               "index=" + index +
               ", timestamp=" + timestamp +
               ", data='" + data + '\'' +
               ", previousHash='" + previousHash + '\'' +
               ", hash='" + hash + '\'' +
               ", nonce=" + nonce +
               '}';
    }
}

