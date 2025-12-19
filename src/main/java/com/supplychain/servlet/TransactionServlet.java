package com.supplychain.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.supplychain.model.Block;
import com.supplychain.model.Transaction;
import com.supplychain.service.BlockchainService;

/**
 * Servlet for transaction operations
 * Demonstrates: Servlets, Web Integration
 */
@WebServlet("/api/transactions")
public class TransactionServlet extends HttpServlet {
    private BlockchainService blockchainService;
    private Gson gson;
    
    @Override
    public void init() throws ServletException {
        super.init();
        blockchainService = new BlockchainService(2);
        gson = new Gson();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

                 //IMPROVED ERROR HANDLING (SERVLET) - Review-2
        try {
            var transactions = blockchainService.getAllTransactions();
            out.print(gson.toJson(transactions));
        } catch (JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"Invalid JSON format\"}");
        }
        catch (BlockchainException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Blockchain processing failed\"}");
        }
        catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Unexpected server error\"}");
        }
        
        out.flush();
            }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            StringBuilder jsonBuffer = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                jsonBuffer.append(line);
            }
            
            Transaction transaction = gson.fromJson(jsonBuffer.toString(), Transaction.class);

            // -------- Server-side Validation (Review-2) --------
if (transaction.getItemId() == null || transaction.getItemId().isBlank()) {
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    out.print("{\"error\":\"Item ID cannot be empty\"}");
    return;
}

if (transaction.getFromLocation() == null || transaction.getFromLocation().isBlank()) {
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    out.print("{\"error\":\"From Location is required\"}");
    return;
}

if (transaction.getToLocation() == null || transaction.getToLocation().isBlank()) {
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    out.print("{\"error\":\"To Location is required\"}");
    return;
}

if (transaction.getStatus() == null || transaction.getStatus().isBlank()) {
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    out.print("{\"error\":\"Status is required\"}");
    return;
}
            
            // Generate transaction ID if not provided
            if (transaction.getTransactionId() == null || transaction.getTransactionId().isEmpty()) {
                transaction.setTransactionId(UUID.randomUUID().toString());
            }
            
            Block newBlock = blockchainService.addTransaction(transaction);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(newBlock));
        } catch (com.google.gson.JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"Invalid JSON format: " + e.getMessage() + "\"}");
        } catch (com.supplychain.exception.BlockchainException | IOException | RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
        
        out.flush();
    }
}

