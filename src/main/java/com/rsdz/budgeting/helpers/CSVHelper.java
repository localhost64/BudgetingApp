package com.rsdz.budgeting.helpers;

import com.rsdz.budgeting.entity.AmexTransaction;
import com.rsdz.budgeting.entity.CCTransaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERS =  {"Date", "Description", "Amount"};

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<? extends CCTransaction> csvToTransactions(InputStream is) {
        System.out.println("Trying to import file...");
        try(BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(fileReader,
                                            CSVFormat.Builder.create().setHeader().setSkipHeaderRecord(true).setIgnoreHeaderCase(true).setTrim(true).build());
        ) {
            System.out.println("Getting accounts from file...");
            List<AmexTransaction> transactions = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for(CSVRecord record : csvRecords) {
                AmexTransaction transaction = new AmexTransaction(
                        record.get("Amount"),
                        record.get("Category"),
                        record.get("Date")
                );
                transactions.add(transaction);
            }

            return transactions;
        } catch(IOException e) {
            throw new RuntimeException("Failed to parse CSV File. " + e.getMessage());
        }
    }
}
