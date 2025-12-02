package com.supplychain.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.supplychain.exception.DatabaseException;
import com.supplychain.interfaces.IDatabaseOperations;
import com.supplychain.model.Block;
import com.supplychain.model.Transaction;

/**
 * Data Access Object for Block operations
 * Demonstrates: JDBC, Database Operations, Classes for DB operations
 */
public class BlockDAO implements IDatabaseOperations {
    private final DatabaseConnection dbConnection;
    
    public BlockDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    
    @Override
    public boolean saveBlock(Block block) {
        String sql = "INSERT INTO blocks (block_index, timestamp, data, previous_hash, hash, nonce) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, block.getIndex());
            pstmt.setLong(2, block.getTimestamp());
            pstmt.setString(3, block.getData());
            pstmt.setString(4, block.getPreviousHash());
            pstmt.setString(5, block.getHash());
            pstmt.setInt(6, block.getNonce());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException | DatabaseException e) {
            System.err.println("Error saving block: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Block> getAllBlocks() {
        List<Block> blocks = new ArrayList<>();
        String sql = "SELECT * FROM blocks ORDER BY block_index";
        
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Block block = new Block(
                    rs.getInt("block_index"),
                    rs.getString("data"),
                    rs.getString("previous_hash")
                );
                block.setTimestamp(rs.getLong("timestamp"));
                block.setHash(rs.getString("hash"));
                block.setNonce(rs.getInt("nonce"));
                blocks.add(block);
            }
            
        } catch (SQLException | DatabaseException e) {
            System.err.println("Error retrieving blocks: " + e.getMessage());
        }
        
        return blocks;
    }
    
    @Override
    public Block getBlockByHash(String hash) {
        String sql = "SELECT * FROM blocks WHERE hash = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, hash);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Block block = new Block(
                    rs.getInt("block_index"),
                    rs.getString("data"),
                    rs.getString("previous_hash")
                );
                block.setTimestamp(rs.getLong("timestamp"));
                block.setHash(rs.getString("hash"));
                block.setNonce(rs.getInt("nonce"));
                return block;
            }
            
        } catch (SQLException | DatabaseException e) {
            System.err.println("Error retrieving block by hash: " + e.getMessage());
        }
        
        return null;
    }
    
    @Override
    public boolean saveTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (transaction_id, item_id, from_location, " +
                     "to_location, status, timestamp, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, transaction.getTransactionId());
            pstmt.setString(2, transaction.getItemId());
            pstmt.setString(3, transaction.getFromLocation());
            pstmt.setString(4, transaction.getToLocation());
            pstmt.setString(5, transaction.getStatus());
            pstmt.setTimestamp(6, Timestamp.valueOf(transaction.getTimestamp()));
            pstmt.setString(7, transaction.getDescription());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException | DatabaseException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY timestamp DESC";
        
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getString("transaction_id"));
                transaction.setItemId(rs.getString("item_id"));
                transaction.setFromLocation(rs.getString("from_location"));
                transaction.setToLocation(rs.getString("to_location"));
                transaction.setStatus(rs.getString("status"));
                transaction.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
                transaction.setDescription(rs.getString("description"));
                transactions.add(transaction);
            }
            
        } catch (SQLException | DatabaseException e) {
            System.err.println("Error retrieving transactions: " + e.getMessage());
        }
        
        return transactions;
    }
    
    @Override
    public void close() {
        // Connection pooling handled by DatabaseConnection
        // Individual DAO doesn't need to close connections
    }
}

