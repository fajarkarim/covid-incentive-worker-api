package com.morningstar.covidworkerincentiveapi.controller;

import com.morningstar.covidworkerincentiveapi.coreapi.UploadWorkersDataCmd;
import com.morningstar.covidworkerincentiveapi.service.UploadWorkersDataService;
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
        final UploadWorkersDataCmd command = new UploadWorkersDataCmd(
            UUID.randomUUID(), workersData);
        uploadWorkersDataService.upload(workersData);

        return commandGateway.send(command);
    }
}
