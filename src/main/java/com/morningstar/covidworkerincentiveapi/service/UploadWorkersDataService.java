package com.morningstar.covidworkerincentiveapi.service;

import com.morningstar.covidworkerincentiveapi.domain.WorkerData;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadWorkersDataService {
    StorageService storageService;

    public UploadWorkersDataService (StorageService storageService) {
        this.storageService = storageService;
    }

    public void upload(MultipartFile workersData) {
        // store file
        this.storageService.storeFile(workersData);

        // parse file
        try (Reader reader = new BufferedReader(new InputStreamReader(workersData.getInputStream()))) {
            final CsvToBean csvBean = new CsvToBeanBuilder(reader)
                .withType(WorkerData.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

            List<WorkerData> csv = csvBean.parse();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        // pass the object to next process
    }
}
