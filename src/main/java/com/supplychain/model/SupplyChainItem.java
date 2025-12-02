package com.supplychain.model;

import com.supplychain.interfaces.ISupplyChainItem;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * SupplyChainItem class implementing ISupplyChainItem interface
 * Demonstrates: Inheritance, Interfaces, OOP
 */
public class SupplyChainItem implements ISupplyChainItem {
    private String itemId;
    private String name;
    private String status;
    private String location;
    private LocalDateTime lastUpdated;
    private String category;
    private double quantity;
    
    public SupplyChainItem() {
        this.lastUpdated = LocalDateTime.now();
    }
    
    public SupplyChainItem(String itemId, String name, String status, 
                          String location, String category, double quantity) {
        this.itemId = itemId;
        this.name = name;
        this.status = status;
        this.location = location;
        this.category = category;
        this.quantity = quantity;
        this.lastUpdated = LocalDateTime.now();
    }
    
    @Override
    public String getItemId() {
        return itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String getStatus() {
        return status;
    }
    
    @Override
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        this.lastUpdated = LocalDateTime.now();
    }
    
    @Override
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
        this.lastUpdated = LocalDateTime.now();
    }
    
    @Override
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public double getQuantity() {
        return quantity;
    }
    
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplyChainItem that = (SupplyChainItem) o;
        return Objects.equals(itemId, that.itemId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }
    
    @Override
    public String toString() {
        return "SupplyChainItem{" +
               "itemId='" + itemId + '\'' +
               ", name='" + name + '\'' +
               ", status='" + status + '\'' +
               ", location='" + location + '\'' +
               ", lastUpdated=" + lastUpdated +
               ", category='" + category + '\'' +
               ", quantity=" + quantity +
               '}';
    }
}

