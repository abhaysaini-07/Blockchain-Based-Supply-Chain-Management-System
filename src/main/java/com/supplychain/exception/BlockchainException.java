package com.supplychain.exception;

/**
 * Custom exception for blockchain operations
 * Demonstrates: Exception Handling, OOP
 */
public class BlockchainException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public BlockchainException(String message) {
        super(message);
    }
    
    public BlockchainException(String message, Throwable cause) {
        super(message, cause);
    }
}

