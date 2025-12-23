# Blockchain-Based Supply Chain Management System

A comprehensive Java-based blockchain implementation for supply chain management, demonstrating advanced Java concepts including OOP, Collections, Generics, Multithreading, JDBC, and Servlets.

## 📋 Project Overview

This project implements a blockchain-based supply chain management system that tracks products through various stages of the supply chain. Each transaction is recorded as a block in the blockchain, ensuring immutability and traceability.

## ✨ Features Implemented

### 1. OOP Implementation
- **Polymorphism**: Demonstrated through interface implementations (`IBlockchain`, `ISupplyChainItem`, `IDatabaseOperations`)
- **Inheritance**: `SupplyChainItem` implements `ISupplyChainItem` interface
- **Exception Handling**: Custom exceptions (`BlockchainException`, `DatabaseException`) with proper error handling
- **Interfaces**: Multiple interfaces defining contracts for blockchain, supply chain items, and database operations

### 2. Collections & Generics
- **Collections**: Extensive use of `List`, `Map`, `Set` from Java Collections Framework
- **Generics**: Generic methods in `CollectionUtils` class (`filter`, `map`)
- **Thread-safe Collections**: `CopyOnWriteArrayList` for concurrent access
- **Stream API**: Used for filtering, mapping, and processing collections

### 3. Multithreading & Synchronization 
- **Multithreading**: `ExecutorService` for asynchronous block processing
- **Synchronization**: `synchronized` blocks and `lock` objects for thread safety
- **CompletableFuture**: Asynchronous operations for blockchain validation and transaction addition
- **Thread Pool**: Fixed thread pool executor for managing concurrent tasks

### 4. Database Operations Classes 
- **DAO Pattern**: `BlockDAO` class implementing `IDatabaseOperations` interface
- **Database Connection**: `DatabaseConnection` singleton class for connection management
- **CRUD Operations**: Complete CRUD for blocks and transactions


## 🚀 Deployment & Testing (Review‑2 Ready)

### Tomcat Deployment
1. Build the WAR file:
   ```bash
   mvn clean package
   ```

2. Copy the generated WAR to Tomcat:
   ```bash
   cp target/blockchain-supply-chain-1.0.0.war $CATALINA_HOME/webapps/
   ```

3. Start Tomcat:
   ```bash
   $CATALINA_HOME/bin/startup.sh
   ```

4. Open in browser:
   ```
   http://localhost:8080/blockchain-supply-chain-1.0.0/
   ```

---

### 🌐 Application URLs

| Purpose | URL |
|------|----|
| Home UI (JSP) | http://localhost:8080/blockchain-supply-chain-1.0.0/ |
| Add / View Transactions (API) | http://localhost:8080/blockchain-supply-chain-1.0.0/api/transactions |
| View Blockchain Blocks | http://localhost:8080/blockchain-supply-chain-1.0.0/api/blockchain |
| Validate Blockchain | http://localhost:8080/blockchain-supply-chain-1.0.0/api/blockchain/validate |

---

### 🧪 How to Test the Project

#### 1️⃣ UI Testing
- Open the home page
- Fill the **Add Transaction** form
- Submit → transaction is validated, added to blockchain, and stored in DB

#### 2️⃣ API Testing (cURL)
```bash
curl -X POST http://localhost:8080/blockchain-supply-chain-1.0.0/api/transactions \
-d "itemId=ITEM100&fromLocation=Warehouse A&toLocation=Warehouse B&status=In Transit&description=Test"
```

```bash
curl http://localhost:8080/blockchain-supply-chain-1.0.0/api/transactions
```

---

### ⚠️ Database Note (Important for Review)

- This project uses **MySQL (JDBC)**
- Database connection depends on **local MySQL credentials**
- If DB is not running, blockchain logic still works, but persistence will fail
- Schema is provided in:
  ```
  database/schema.sql
  ```

---

## ✅ Review‑2 Compliance Checklist

- ✔ OOP (Interfaces, Inheritance, Polymorphism)
- ✔ Exception Handling (Custom Exceptions)
- ✔ Collections & Generics
- ✔ Multithreading & Synchronization
- ✔ JDBC + DAO Pattern
- ✔ Servlets & JSP Integration
- ✔ Client‑side + Server‑side Validation
- ✔ Blockchain Integrity Validation
- ✔ Maven + Tomcat Deployment Ready

---

## 👤 Author

**Abhay Saini**  
GitHub: https://github.com/abhaysaini-07