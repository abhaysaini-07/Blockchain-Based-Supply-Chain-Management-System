package com.supplychain.interfaces;

import java.time.LocalDateTime;

/**
 * Interface for supply chain items
 * Demonstrates: Interfaces, OOP
 */
public interface ISupplyChainItem {
    /**
     * Get the unique identifier of the item
     * @return Item ID
     */
    String getItemId();
    
    /**
     * Get the current status of the item
     * @return Status string
     */
    String getStatus();
    
    /**
     * Update the status of the item
     * @param newStatus The new status
     */
    void updateStatus(String newStatus);
    
    /**
     * Get the timestamp of the last update
     * @return LocalDateTime of last update
     */
    LocalDateTime getLastUpdated();
    
    /**
     * Get the location of the item
     * @return Location string
     */
    String getLocation();
}

