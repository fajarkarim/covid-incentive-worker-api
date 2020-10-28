package com.morningstar.covidworkerincentiveapi.disbursementtransaction;

import com.morningstar.covidworkerincentiveapi.disbursementtransaction.uploadworkersdata.UploadWorkersDataCmd;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.uploadworkersdata.UploadWorkersDataService;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DisbursementController {

    private final CommandGateway commandGateway;
    private final UploadWorkersDataService uploadWorkersDataService;

    public DisbursementController(CommandGateway commandGateway, UploadWorkersDataService uploadWorkersDataService) {
        this.commandGateway = commandGateway;
        this.uploadWorkersDataService = uploadWorkersDataService;
    }

    @PostMapping("/disburse")
    public CompletableFuture<UUID> disburse(@RequestParam("data") MultipartFile workersData) {
        final UUID transactionId = UUID.randomUUID();
        final UploadWorkersDataCmd command = new UploadWorkersDataCmd(
            transactionId, workersData);
        uploadWorkersDataService.upload(transactionId, workersData);

        return commandGateway.send(command);
    }
}
