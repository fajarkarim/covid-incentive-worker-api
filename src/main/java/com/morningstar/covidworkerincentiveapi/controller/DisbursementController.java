package com.morningstar.covidworkerincentiveapi.controller;

import com.morningstar.covidworkerincentiveapi.coreapi.UploadWorkersDataCmd;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DisbursementController {

    private final CommandGateway commandGateway;

    public DisbursementController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/disburse")
    public CompletableFuture<UUID> disburse(MultipartFile workersData) {
        final UploadWorkersDataCmd command = new UploadWorkersDataCmd(
            UUID.randomUUID(), workersData);

        return commandGateway.send(command);
    }
}
