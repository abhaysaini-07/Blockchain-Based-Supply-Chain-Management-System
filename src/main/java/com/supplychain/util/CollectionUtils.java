package com.supplychain.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.supplychain.model.Block;
import com.supplychain.model.Transaction;

/**
 * Utility class demonstrating Collections & Generics
 * Demonstrates: Collections, Generics, Stream API
 */
public class CollectionUtils {
    
    /**
     * Generic method to filter a list based on a predicate
     * Demonstrates: Generics
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
    
    /**
     * Generic method to map a list to another type
     * Demonstrates: Generics
     */
    public static <T, R> List<R> map(List<T> list, Mapper<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(mapper.apply(item));
        }
        return result;
    }
    
    /**
     * Group transactions by status using Map
     * Demonstrates: Collections (Map)
     */
    public static Map<String, List<Transaction>> groupTransactionsByStatus(
            List<Transaction> transactions) {
        Map<String, List<Transaction>> grouped = new HashMap<>();
        for (Transaction transaction : transactions) {
            String status = transaction.getStatus();
            grouped.computeIfAbsent(status, k -> new ArrayList<>()).add(transaction);
        }
        return grouped;
    }
    
    /**
     * Get unique statuses from transactions using Set
     * Demonstrates: Collections (Set)
     */
    public static Set<String> getUniqueStatuses(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getStatus)
                .collect(Collectors.toSet());
    }
    
    /**
     * Sort blocks by index using Collections.sort
     * Demonstrates: Collections (List sorting)
     */
    public static void sortBlocksByIndex(List<Block> blocks) {
        Collections.sort(blocks, Comparator.comparingInt(Block::getIndex));
    }
    
    /**
     * Find block by hash using Stream API
     * Demonstrates: Collections, Stream API
     */
    public static Optional<Block> findBlockByHash(List<Block> blocks, String hash) {
        return blocks.stream()
                .filter(block -> block.getHash().equals(hash))
                .findFirst();
    }
    
    /**
     * Count blocks by data pattern
     * Demonstrates: Collections, Stream API
     */
    public static long countBlocksContaining(List<Block> blocks, String pattern) {
        return blocks.stream()
                .filter(block -> block.getData().contains(pattern))
                .count();
    }
    
    /**
     * Functional interface for predicates
     * Demonstrates: Generics, Functional Interfaces
     */
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }
    
    /**
     * Functional interface for mappers
     * Demonstrates: Generics, Functional Interfaces
     */
    @FunctionalInterface
    public interface Mapper<T, R> {
        R apply(T t);
    }
}

