package com.fileUploadbatch2.csv_to_Db2.service;

import com.fileUploadbatch2.csv_to_Db2.helper.CSVHelper;
import com.fileUploadbatch2.csv_to_Db2.model.PMDealsDetails;
import com.fileUploadbatch2.csv_to_Db2.repository.CMSRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    CMSRepo repository;

    public void save(MultipartFile file) {
        try {
            List<PMDealsDetails> pmDealsDetails = CSVHelper.csvToPM(file.getInputStream());
            repository.saveAll(pmDealsDetails);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<PMDealsDetails> tutorials = repository.findAll();

        ByteArrayInputStream in = CSVHelper.PMDetailsToCSV(tutorials);
        return in;
    }

    public List<PMDealsDetails> getAllTutorials() {
        return repository.findAll();
    }
}