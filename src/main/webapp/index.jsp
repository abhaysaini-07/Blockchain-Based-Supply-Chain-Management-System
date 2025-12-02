<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blockchain Supply Chain Management</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            padding: 30px;
        }
        
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
            font-size: 2.5em;
        }
        
        .section {
            margin-bottom: 30px;
            padding: 20px;
            background: #f8f9fa;
            border-radius: 8px;
        }
        
        .section h2 {
            color: #667eea;
            margin-bottom: 15px;
        }
        
        form {
            display: grid;
            gap: 15px;
        }
        
        .form-group {
            display: flex;
            flex-direction: column;
        }
        
        label {
            margin-bottom: 5px;
            color: #555;
            font-weight: 600;
        }
        
        input, select, textarea {
            padding: 10px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }
        
        input:focus, select:focus, textarea:focus {
            outline: none;
            border-color: #667eea;
        }
        
        button {
            padding: 12px 30px;
            background: #667eea;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 600;
            transition: background 0.3s;
        }
        
        button:hover {
            background: #5568d3;
        }
        
        .button-group {
            display: flex;
            gap: 10px;
        }
        
        .results {
            margin-top: 20px;
            padding: 15px;
            background: white;
            border-radius: 5px;
            border: 1px solid #ddd;
            max-height: 400px;
            overflow-y: auto;
        }
        
        .block-item, .transaction-item {
            padding: 10px;
            margin-bottom: 10px;
            background: #f0f0f0;
            border-left: 4px solid #667eea;
            border-radius: 4px;
        }
        
        .status-valid {
            color: green;
            font-weight: bold;
        }
        
        .status-invalid {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🔗 Blockchain Supply Chain Management System</h1>
        
        <div class="section">
            <h2>Add Transaction</h2>
            <form id="transactionForm">
                <div class="form-group">
                    <label>Item ID:</label>
                    <input type="text" name="itemId" required>
                </div>
                <div class="form-group">
                    <label>From Location:</label>
                    <input type="text" name="fromLocation" required>
                </div>
                <div class="form-group">
                    <label>To Location:</label>
                    <input type="text" name="toLocation" required>
                </div>
                <div class="form-group">
                    <label>Status:</label>
                    <select name="status" required>
                        <option value="Pending">Pending</option>
                        <option value="In Transit">In Transit</option>
                        <option value="Delivered">Delivered</option>
                        <option value="Cancelled">Cancelled</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Description:</label>
                    <textarea name="description" rows="3"></textarea>
                </div>
                <button type="submit">Add Transaction</button>
            </form>
        </div>
        
        <div class="section">
            <h2>Blockchain Operations</h2>
            <div class="button-group">
                <button onclick="getAllBlocks()">Get All Blocks</button>
                <button onclick="getAllTransactions()">Get All Transactions</button>
                <button onclick="validateBlockchain()">Validate Blockchain</button>
            </div>
            <div id="results" class="results"></div>
        </div>
    </div>
    
    <script>
        document.getElementById('transactionForm').addEventListener('submit', async (e) => {
            e.preventDefault();
            const formData = new FormData(e.target);
            const transaction = {
                itemId: formData.get('itemId'),
                fromLocation: formData.get('fromLocation'),
                toLocation: formData.get('toLocation'),
                status: formData.get('status'),
                description: formData.get('description')
            };
            
            try {
                const response = await fetch('/blockchain-supply-chain/api/transactions', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(transaction)
                });
                
                const result = await response.json();
                document.getElementById('results').innerHTML = 
                    '<pre>' + JSON.stringify(result, null, 2) + '</pre>';
            } catch (error) {
                document.getElementById('results').innerHTML = 
                    '<p style="color: red;">Error: ' + error.message + '</p>';
            }
        });
        
        async function getAllBlocks() {
            try {
                const response = await fetch('/blockchain-supply-chain/api/blockchain/');
                const blocks = await response.json();
                document.getElementById('results').innerHTML = 
                    '<pre>' + JSON.stringify(blocks, null, 2) + '</pre>';
            } catch (error) {
                document.getElementById('results').innerHTML = 
                    '<p style="color: red;">Error: ' + error.message + '</p>';
            }
        }
        
        async function getAllTransactions() {
            try {
                const response = await fetch('/blockchain-supply-chain/api/transactions');
                const transactions = await response.json();
                document.getElementById('results').innerHTML = 
                    '<pre>' + JSON.stringify(transactions, null, 2) + '</pre>';
            } catch (error) {
                document.getElementById('results').innerHTML = 
                    '<p style="color: red;">Error: ' + error.message + '</p>';
            }
        }
        
        async function validateBlockchain() {
            try {
                const response = await fetch('/blockchain-supply-chain/api/blockchain/validate');
                const result = await response.json();
                const isValid = result.valid;
                document.getElementById('results').innerHTML = 
                    '<p class="' + (isValid ? 'status-valid' : 'status-invalid') + '">' +
                    'Blockchain is ' + (isValid ? 'VALID' : 'INVALID') + '</p>';
            } catch (error) {
                document.getElementById('results').innerHTML = 
                    '<p style="color: red;">Error: ' + error.message + '</p>';
            }
        }
    </script>
</body>
</html>

