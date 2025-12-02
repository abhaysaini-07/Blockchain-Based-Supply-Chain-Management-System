package com.supplychain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Transaction class representing a supply chain transaction
 * Demonstrates: Core Java Concepts, OOP
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String transactionId;
    private String itemId;
    private String fromLocation;
    private String toLocation;
    private String status;
    private LocalDateTime timestamp;
    private String description;
    
    public Transaction() {
        this.timestamp = LocalDateTime.now();
    }
    
    public Transaction(String transactionId, String itemId, String fromLocation, 
                      String toLocation, String status, String description) {
        this.transactionId = transactionId;
        this.itemId = itemId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.status = status;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getItemId() {
        return itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    
    public String getFromLocation() {
        return fromLocation;
    }
    
    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }
    
    public String getToLocation() {
        return toLocation;
    }
    
    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
               "transactionId='" + transactionId + '\'' +
               ", itemId='" + itemId + '\'' +
               ", fromLocation='" + fromLocation + '\'' +
               ", toLocation='" + toLocation + '\'' +
               ", status='" + status + '\'' +
               ", timestamp=" + timestamp +
               ", description='" + description + '\'' +
               '}';
    }
}

