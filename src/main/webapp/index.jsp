<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            text-align: center;
            margin-bottom: 30px;
            color: #333;
        }

        .section {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
        }

        .section h2 {
            margin-bottom: 15px;
            color: #667eea;
        }

        form {
            display: grid;
            gap: 15px;
        }

        label {
            font-weight: 600;
            margin-bottom: 5px;
        }

        input, select, textarea {
            padding: 10px;
            border-radius: 5px;
            border: 2px solid #ddd;
            font-size: 14px;
        }

        input:focus, select:focus, textarea:focus {
            outline: none;
            border-color: #667eea;
        }

        button {
            padding: 12px;
            background: #667eea;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 600;
        }

        button:hover {
            background: #5a67d8;
        }
    </style>
</head>

<body>

<div class="container">
    <h1>Blockchain Supply Chain Management System</h1>

    <div class="section">
        <h2>Add Transaction</h2>

        <!-- ✅ Client-side validation enabled -->
        <form method="post" action="transactions" onsubmit="return validateForm();">

            <label for="itemId">Item ID</label>
            <input type="text" id="itemId" name="itemId" placeholder="Enter Item ID">

            <label for="fromLocation">From Location</label>
            <input type="text" id="fromLocation" name="fromLocation" placeholder="Enter Source Location">

            <label for="toLocation">To Location</label>
            <input type="text" id="toLocation" name="toLocation" placeholder="Enter Destination Location">

            <label for="status">Status</label>
            <select id="status" name="status">
                <option value="">-- Select Status --</option>
                <option value="CREATED">Pending</option>
                <option value="IN_TRANSIT">In Transit</option>
                <option value="DELIVERED">Delivered</option>
            </select>

            <label for="description">Description</label>
            <textarea id="description" name="description" rows="3"
                placeholder="Optional description"></textarea>

            <button type="submit">Add Transaction</button>
        </form>
    </div>
</div>

<!-- ✅ CLIENT-SIDE VALIDATION SCRIPT -->
<script>
function validateForm() {

    const itemId = document.getElementById("itemId").value.trim();
    const fromLocation = document.getElementById("fromLocation").value.trim();
    const toLocation = document.getElementById("toLocation").value.trim();
    const status = document.getElementById("status").value.trim();

    if (itemId === "") {
        alert("Item ID is required");
        return false;
    }

    if (fromLocation === "") {
        alert("From Location is required");
        return false;
    }

    if (toLocation === "") {
        alert("To Location is required");
        return false;
    }

    if (fromLocation.toLowerCase() === toLocation.toLowerCase()) {
        alert("From Location and To Location cannot be same");
        return false;
    }

    if (status === "") {
        alert("Please select a transaction status");
        return false;
    }

    return true;
}
</script>

</body>
</html>