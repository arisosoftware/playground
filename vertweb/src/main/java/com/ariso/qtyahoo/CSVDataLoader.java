package com.ariso.qtyahoo;

import java.io.FileReader;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class CSVDataLoader {

    public static void main(String[] args) throws Exception {
       
        readVariableColumnsWithCsvListReader();
    }

    static String VARIABLE_CSV_FILENAME = "C:\\Users\\Redirection\\yanga4\\Downloads\\0777M5A-activity-13-Sep-2016.csv";

    class Data {
        public String TradeDate;
        public String SettleDate;
        public String Description;
        public String Action;
        public float Quantity;
        public float Price;
        public float Commission;
        public float NetAmount;
        public String Remark;
    }

    private static void readVariableColumnsWithCsvListReader() throws Exception {

        final CellProcessor[] allProcessors = new CellProcessor[] {
                null, // TradeDate
                null, // SettleDate
                new org.supercsv.cellprocessor.Optional(),// Description
                new NotNull(),// Action
                new NotNull(),// Quantity
                new Optional(new org.supercsv.cellprocessor.ParseDouble()),// Price
                new Optional(new org.supercsv.cellprocessor.ParseDouble()),// Commission
                new Optional(new org.supercsv.cellprocessor.ParseDouble()),// NetAmount
                new Optional(new org.supercsv.cellprocessor.ParseDouble())
        };

        ICsvListReader listReader = null;
        try {

            listReader = new CsvListReader(
                    new FileReader(VARIABLE_CSV_FILENAME),
                    CsvPreference.STANDARD_PREFERENCE);

            //listReader.getHeader(true); // skip the header (can't be used with
                                        // CsvListReader)
             
            while ((listReader.read()) != null) {

                // use different processors depending on the number of columns
                final CellProcessor[] processors;
                
                
//              System.out.println(String.format(
//                      "lineNo=%s, rowNo=%s, length=%s",
//                      listReader.getLineNumber(),
//                      listReader.getRowNumber(), 
//                      listReader.length()));
                
                if (listReader.length() == allProcessors.length) {

                    processors = allProcessors;
                 
                     
                      List<Object> customerList = listReader
                            .executeProcessors(processors);

                    System.out.println(String.format(
                            "lineNo=%s, rowNo=%s, columns=%s, customerList=%s",
                            listReader.getLineNumber(),
                            listReader.getRowNumber(), customerList.size(),
                            customerList));
                }
            }

        } finally {
            if (listReader != null) {
                listReader.close();
            }
        }
    }

    
}
