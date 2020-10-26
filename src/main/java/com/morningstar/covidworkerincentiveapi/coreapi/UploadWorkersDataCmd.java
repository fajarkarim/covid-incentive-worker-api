package com.morningstar.covidworkerincentiveapi.coreapi;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class UploadWorkersDataCmd {
    @TargetAggregateIdentifier
    UUID transactionId;
    MultipartFile workersData;
}
