package com.morningstar.covidworkerincentiveapi.disbursementtransaction;

import com.morningstar.covidworkerincentiveapi.disbursementtransaction.uploadworkersdata.UploadWorkersDataService;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DisbursementController {
    private final UploadWorkersDataService uploadWorkersDataService;

    public DisbursementController(UploadWorkersDataService uploadWorkersDataService) {
        this.uploadWorkersDataService = uploadWorkersDataService;
    }

    @PostMapping("/disburse")
    public String disburse(@RequestParam("data") MultipartFile workersData) {
        final UUID transactionId = UUID.randomUUID();
        uploadWorkersDataService.upload(transactionId, workersData);

        return transactionId.toString();
    }
}
