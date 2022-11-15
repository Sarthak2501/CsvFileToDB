package com.fileUploadbatch2.csv_to_Db2.helper;

import com.fileUploadbatch2.csv_to_Db2.model.PMDealsDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.*;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "PM Deal Id", "Game ID", "Area ID", "PMS Purchased" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<PMDealsDetails> csvToPM(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<PMDealsDetails> pmDealsDetails = new ArrayList<PMDealsDetails>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                try{
                PMDealsDetails pmDealsDetail = new PMDealsDetails(
                        UUID.fromString(csvRecord.get("PM Deal Id")),
                        UUID.fromString(csvRecord.get("Game ID")),
                        UUID.fromString(csvRecord.get("Area ID")),
                        Integer.parseInt(csvRecord.get("PMS Purchased")));
                    pmDealsDetails.add(pmDealsDetail);
                }catch(IdentifierGenerationException i){
                    log.error(i.getMessage());
                }
            }

            return pmDealsDetails;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream PMDetailsToCSV(List<PMDealsDetails> tutorials) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (PMDealsDetails tutorial : tutorials) {
                List<String> data = Arrays.asList(
                        tutorial.getId().toString(),
                        tutorial.getpMDealId().toString(),
                        tutorial.getGameId().toString(),
                        tutorial.getAreaId().toString(),
                        String.valueOf(tutorial.getpMSPurchased())
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

}