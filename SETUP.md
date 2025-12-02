# Setup Guide

## Quick Start

### 1. Database Setup

```bash
# Login to MySQL
mysql -u root -p

# Run the schema script
source database/schema.sql
```

Or directly:
```bash
mysql -u root -p < database/schema.sql
```

### 2. Update Database Credentials

Edit `src/main/java/com/supplychain/database/DatabaseConnection.java`:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/supplychain_db";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "your_password_here";
```

### 3. Build the Project

```bash
mvn clean compile
```

### 4. Package as WAR

```bash
mvn clean package
```

This creates: `target/blockchain-supply-chain-1.0.0.war`

### 5. Deploy to Tomcat

Copy the WAR file to Tomcat's webapps directory:

```bash
cp target/blockchain-supply-chain-1.0.0.war $CATALINA_HOME/webapps/
```

Or if using Tomcat directly:
```bash
cp target/blockchain-supply-chain-1.0.0.war /path/to/tomcat/webapps/
```

### 6. Start Tomcat

```bash
$CATALINA_HOME/bin/startup.sh
```

### 7. Access the Application

Open your browser and navigate to:
```
http://localhost:8080/blockchain-supply-chain-1.0.0/
```

## Running the Main Class

To test the core functionality without a web server:

```bash
mvn exec:java -Dexec.mainClass="com.supplychain.Main"
```

## Testing the API

### Using cURL

**Get all blocks:**
```bash
curl http://localhost:8080/blockchain-supply-chain-1.0.0/api/blockchain/
```

**Get all transactions:**
```bash
curl http://localhost:8080/blockchain-supply-chain-1.0.0/api/transactions
```

**Validate blockchain:**
```bash
curl http://localhost:8080/blockchain-supply-chain-1.0.0/api/blockchain/validate
```

**Add a transaction:**
```bash
curl -X POST http://localhost:8080/blockchain-supply-chain-1.0.0/api/transactions \
  -H "Content-Type: application/json" \
  -d '{
    "itemId": "ITEM001",
    "fromLocation": "Warehouse A",
    "toLocation": "Warehouse B",
    "status": "In Transit",
    "description": "Test transaction"
  }'
```

## Troubleshooting

### Database Connection Issues
- Ensure MySQL is running: `sudo systemctl status mysql` (Linux) or `brew services list` (Mac)
- Verify database credentials in `DatabaseConnection.java`
- Check if database `supplychain_db` exists

### Port Conflicts
- If port 8080 is in use, change Tomcat port in `server.xml`
- Or use a different port when accessing the application

### Maven Build Issues
- Ensure Java 11+ is installed: `java -version`
- Ensure Maven is installed: `mvn -version`
- Clean and rebuild: `mvn clean install`

