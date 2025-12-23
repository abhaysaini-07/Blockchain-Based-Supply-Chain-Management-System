package com.supplychain.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.supplychain.exception.BlockchainException;
import com.supplychain.model.Transaction;
import com.supplychain.service.BlockchainService;
import com.supplychain.service.TransactionService;

@WebServlet("/api/transactions")
public class TransactionServlet extends HttpServlet {

    private BlockchainService blockchainService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        blockchainService = new BlockchainService(2);
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            List<Transaction> transactions = blockchainService.getAllTransactions();
            out.print(gson.toJson(transactions));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Failed to fetch transactions\"}");
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
            Transaction tx = gson.fromJson(request.getReader(), Transaction.class);

            // Generate ID if missing
            if (tx.getTransactionId() == null || tx.getTransactionId().isBlank()) {
                tx.setTransactionId(UUID.randomUUID().toString());
            }

            // Central validation (Review-2)
            TransactionService.validateTransaction(tx);

            blockchainService.addTransaction(tx);

            out.print("{\"message\":\"Transaction added successfully\"}");

        } catch (JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"Invalid JSON format\"}");
        } catch (BlockchainException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Server error\"}");
        }

        out.flush();
    }
}