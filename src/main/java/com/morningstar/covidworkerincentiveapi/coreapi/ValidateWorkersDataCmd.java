package com.morningstar.covidworkerincentiveapi.coreapi;

import com.morningstar.covidworkerincentiveapi.domain.WorkerData;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class ValidateWorkersDataCmd {

    @TargetAggregateIdentifier
    private UUID transactionId;
    private List<WorkerData> workerDataList;
}
