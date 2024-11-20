package com.rsdz.budgeting.controllers;

import com.rsdz.budgeting.dao.CCTransactionDao;
import com.rsdz.budgeting.entity.AmexTransaction;
import com.rsdz.budgeting.entity.CCTransaction;
import com.rsdz.budgeting.services.CSVServiceImpl;
import com.rsdz.budgeting.services.TransactionsServiceImpl;
import com.rsdz.budgeting.helpers.CSVHelper;
import com.rsdz.budgeting.messages.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionRESTController {
    CSVServiceImpl csvService;
    TransactionsServiceImpl transactionsService;

    @Autowired
    public TransactionRESTController(CSVServiceImpl csvService, TransactionsServiceImpl transactionsService) {
        this.csvService = csvService;
        this.transactionsService = transactionsService;
    }

    @PostMapping("/statements")
    public ResponseEntity<ResponseMessage> processStatement(@RequestParam("file") MultipartFile file) {
        String message = "";
        if(CSVHelper.hasCSVFormat(file)){
            try {

                csvService.upload(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a CSV file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PostMapping("/transactions")
    public ResponseEntity<CCTransaction> addTransaction(@RequestBody AmexTransaction transaction){
        try {
            transaction.setId(0);
            CCTransaction postTransaction = transactionsService.save(transaction);
            return new ResponseEntity<>(postTransaction, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<? extends CCTransaction>> getTransactions(){
        try {
            List<? extends CCTransaction> transactions = transactionsService.findAll();
            if (transactions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/transactions")
    public ResponseEntity<ResponseMessage> deleteTransactions() {
        try {
            transactionsService.deleteTransactions();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Deleted Transaction Database"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Could Not Delete Transactions"));
        }
    }
}
