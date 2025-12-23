package com.supplychain.service;

import java.util.Set;

import com.supplychain.exception.BlockchainException;
import com.supplychain.model.Transaction;

public final class TransactionService {

    private static final Set<String> ALLOWED_STATUSES = Set.of(
            "CREATED",
            "IN_TRANSIT",
            "DELIVERED"
    );

    private TransactionService() {
        // Utility class
    }

    /**
     * Centralized transaction validation (Review-2 compliant)
     */
    public static void validateTransaction(Transaction transaction) throws BlockchainException {

        if (transaction == null) {
            throw new BlockchainException("Transaction cannot be null");
        }

        if (isBlank(transaction.getTransactionId())) {
            throw new BlockchainException("Transaction ID is required");
        }

        if (isBlank(transaction.getItemId())) {
            throw new BlockchainException("Item ID is required");
        }

        if (isBlank(transaction.getFromLocation()) || isBlank(transaction.getToLocation())) {
            throw new BlockchainException("From and To locations are required");
        }

        if (transaction.getFromLocation().equalsIgnoreCase(transaction.getToLocation())) {
            throw new BlockchainException("From and To locations cannot be same");
        }

        if (isBlank(transaction.getStatus())) {
            throw new BlockchainException("Transaction status is required");
        }

        // Normalize status
        String normalizedStatus = normalizeStatus(transaction.getStatus());

        // Validate allowed values
        if (!ALLOWED_STATUSES.contains(normalizedStatus)) {
            throw new BlockchainException(
                    "Invalid status. Allowed values: CREATED, IN_TRANSIT, DELIVERED"
            );
        }

        // Save normalized value
        transaction.setStatus(normalizedStatus);
    }

    private static String normalizeStatus(String status) {
        return status.trim().toUpperCase().replace(" ", "_");
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}