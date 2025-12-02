-- Database schema for Blockchain Supply Chain Management System
-- Demonstrates: Database Design, JDBC

CREATE DATABASE IF NOT EXISTS supplychain_db;
USE supplychain_db;

-- Table for storing blockchain blocks
CREATE TABLE IF NOT EXISTS blocks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    block_index INT NOT NULL,
    timestamp BIGINT NOT NULL,
    data TEXT NOT NULL,
    previous_hash VARCHAR(255) NOT NULL,
    hash VARCHAR(255) NOT NULL UNIQUE,
    nonce INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_hash (hash),
    INDEX idx_index (block_index)
);

-- Table for storing transactions
CREATE TABLE IF NOT EXISTS transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_id VARCHAR(255) NOT NULL UNIQUE,
    item_id VARCHAR(255) NOT NULL,
    from_location VARCHAR(255) NOT NULL,
    to_location VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_transaction_id (transaction_id),
    INDEX idx_item_id (item_id),
    INDEX idx_status (status)
);

-- Table for supply chain items
CREATE TABLE IF NOT EXISTS supply_chain_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    item_id VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    location VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    quantity DECIMAL(10, 2),
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_item_id (item_id),
    INDEX idx_status (status)
);

