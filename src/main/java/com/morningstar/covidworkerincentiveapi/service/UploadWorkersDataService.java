package com.morningstar.covidworkerincentiveapi.service;

import com.morningstar.covidworkerincentiveapi.coreapi.ValidateWorkersDataCmd;
import com.morningstar.covidworkerincentiveapi.domain.WorkerData;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.UUID;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadWorkersDataService {
    private StorageService storageService;
    private CommandGateway commandGateway;

    public UploadWorkersDataService (StorageService storageService, CommandGateway commandGateway) {
        this.storageService = storageService;
        this.commandGateway = commandGateway;
    }

    public void upload(UUID transactionId, MultipartFile workersData) {
        // store file
        this.storageService.storeFile(workersData);

        // parse file
        try (Reader reader = new BufferedReader(new InputStreamReader(workersData.getInputStream()))) {
            final CsvToBean csvBean = new CsvToBeanBuilder(reader)
                .withType(WorkerData.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

            List<WorkerData> workerDataList = csvBean.parse();

            // pass continue to next process
            final String result = commandGateway
                .sendAndWait(new ValidateWorkersDataCmd(transactionId, workerDataList));
            System.out.println(result);

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

    }
}
