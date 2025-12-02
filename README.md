# Blockchain-Based Supply Chain Management System

A comprehensive Java-based blockchain implementation for supply chain management, demonstrating advanced Java concepts including OOP, Collections, Generics, Multithreading, JDBC, and Servlets.

## 📋 Project Overview

This project implements a blockchain-based supply chain management system that tracks products through various stages of the supply chain. Each transaction is recorded as a block in the blockchain, ensuring immutability and traceability.

## ✨ Features Implemented

### 1. OOP Implementation (10 marks)
- **Polymorphism**: Demonstrated through interface implementations (`IBlockchain`, `ISupplyChainItem`, `IDatabaseOperations`)
- **Inheritance**: `SupplyChainItem` implements `ISupplyChainItem` interface
- **Exception Handling**: Custom exceptions (`BlockchainException`, `DatabaseException`) with proper error handling
- **Interfaces**: Multiple interfaces defining contracts for blockchain, supply chain items, and database operations

### 2. Collections & Generics (6 marks)
- **Collections**: Extensive use of `List`, `Map`, `Set` from Java Collections Framework
- **Generics**: Generic methods in `CollectionUtils` class (`filter`, `map`)
- **Thread-safe Collections**: `CopyOnWriteArrayList` for concurrent access
- **Stream API**: Used for filtering, mapping, and processing collections

### 3. Multithreading & Synchronization (4 marks)
- **Multithreading**: `ExecutorService` for asynchronous block processing
- **Synchronization**: `synchronized` blocks and `lock` objects for thread safety
- **CompletableFuture**: Asynchronous operations for blockchain validation and transaction addition
- **Thread Pool**: Fixed thread pool executor for managing concurrent tasks

### 4. Database Operations Classes (7 marks)
- **DAO Pattern**: `BlockDAO` class implementing `IDatabaseOperations` interface
- **Database Connection**: `DatabaseConnection` singleton class for connection management
- **CRUD Operations**: Complete Create, Read, Update operations for blocks and transactions
- **Prepared Statements**: Secure database queries using prepared statements

### 5. Database Connectivity (JDBC) (3 marks)
- **JDBC Driver**: MySQL JDBC connector integration
- **Connection Management**: Proper connection handling with try-with-resources
- **SQL Operations**: Execute queries, updates, and result set processing

### 6. JDBC Implementation (3 marks)
- **Connection Pooling**: Singleton pattern for connection management
- **Transaction Management**: Proper transaction handling
- **Error Handling**: Database exception handling with custom exceptions

### 7. Problem Understanding & Solution Design (8 marks)
- **Architecture**: Well-structured MVC-like architecture
- **Design Patterns**: Singleton, DAO, Factory patterns
- **Separation of Concerns**: Clear separation between model, service, and data access layers
- **Scalability**: Designed for concurrent access and scalability

### 8. Core Java Concepts (10 marks)
- **Classes & Objects**: Multiple classes with proper encapsulation
- **Encapsulation**: Private fields with public getters/setters
- **Method Overriding**: Interface method implementations
- **Static Methods**: Utility methods in `CollectionUtils`
- **Serialization**: Serializable classes for data transfer
- **String Manipulation**: Hash calculation and string operations
- **Date/Time API**: `LocalDateTime` for timestamps

### 9. Database Integration (JDBC) (8 marks)
- **Schema Design**: Comprehensive database schema with proper indexing
- **Data Persistence**: Blocks and transactions stored in MySQL database
- **Data Retrieval**: Efficient querying with indexes
- **Data Integrity**: Foreign key relationships and constraints

### 10. Servlets & Web Integration (7 marks)
- **Servlets**: `BlockchainServlet` and `TransactionServlet` for REST API
- **Web Interface**: JSP page with modern UI for interaction
- **REST API**: JSON-based API endpoints
- **Request Handling**: GET and POST request handling
- **Response Formatting**: JSON response formatting

## 🏗️ Project Structure

```
Blockchain-Supply-Chain/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── supplychain/
│   │   │           ├── blockchain/
│   │   │           │   └── Blockchain.java
│   │   │           ├── database/
│   │   │           │   ├── BlockDAO.java
│   │   │           │   └── DatabaseConnection.java
│   │   │           ├── exception/
│   │   │           │   ├── BlockchainException.java
│   │   │           │   └── DatabaseException.java
│   │   │           ├── interfaces/
│   │   │           │   ├── IBlockchain.java
│   │   │           │   ├── IDatabaseOperations.java
│   │   │           │   └── ISupplyChainItem.java
│   │   │           ├── model/
│   │   │           │   ├── Block.java
│   │   │           │   ├── SupplyChainItem.java
│   │   │           │   └── Transaction.java
│   │   │           ├── service/
│   │   │           │   └── BlockchainService.java
│   │   │           ├── servlet/
│   │   │           │   ├── BlockchainServlet.java
│   │   │           │   └── TransactionServlet.java
│   │   │           └── util/
│   │   │               └── CollectionUtils.java
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       └── index.jsp
├── database/
│   └── schema.sql
├── pom.xml
└── README.md
```

## 🚀 Setup Instructions

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- MySQL 8.0+
- Apache Tomcat 9.0+ or similar servlet container

### Database Setup

1. Create MySQL database:
```sql
mysql -u root -p < database/schema.sql
```

2. Update database credentials in `DatabaseConnection.java`:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/supplychain_db";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "your_password";
```

### Build and Deploy

1. Build the project:
```bash
mvn clean package
```

2. Deploy the WAR file to Tomcat:
```bash
cp target/blockchain-supply-chain-1.0.0.war $CATALINA_HOME/webapps/
```

3. Start Tomcat:
```bash
$CATALINA_HOME/bin/startup.sh
```

4. Access the application:
```
http://localhost:8080/blockchain-supply-chain-1.0.0/
```

## 📡 API Endpoints

### Get All Blocks
```
GET /api/blockchain/
```

### Get All Transactions
```
GET /api/transactions
```

### Validate Blockchain
```
GET /api/blockchain/validate
```

### Add Transaction
```
POST /api/transactions
Content-Type: application/json

{
  "itemId": "ITEM001",
  "fromLocation": "Warehouse A",
  "toLocation": "Warehouse B",
  "status": "In Transit",
  "description": "Product shipment"
}
```

## 🧪 Testing

### Manual Testing
1. Use the web interface at `http://localhost:8080/blockchain-supply-chain-1.0.0/`
2. Add transactions through the form
3. View blocks and transactions
4. Validate the blockchain

### Programmatic Testing
Create a test class to verify:
- Block creation and validation
- Transaction processing
- Database operations
- Multithreading safety

## 📊 Key Classes and Their Roles

1. **Block**: Represents a single block in the blockchain
2. **Blockchain**: Implements the blockchain data structure
3. **Transaction**: Represents a supply chain transaction
4. **BlockDAO**: Handles all database operations
5. **BlockchainService**: Service layer with multithreading support
6. **BlockchainServlet**: REST API endpoint for blockchain operations
7. **CollectionUtils**: Utility class demonstrating Collections & Generics

## 🔒 Security Considerations

- Prepared statements prevent SQL injection
- Input validation in servlets
- Thread-safe operations for concurrent access
- Proper exception handling

## 📝 Notes

- The blockchain uses SHA-256 hashing algorithm
- Mining difficulty is configurable (default: 2)
- All transactions are persisted to MySQL database
- The system supports concurrent transaction processing

## 👨‍💻 Author

Blockchain-Based Supply Chain Management System

## 📄 License

This project is created for educational purposes.

