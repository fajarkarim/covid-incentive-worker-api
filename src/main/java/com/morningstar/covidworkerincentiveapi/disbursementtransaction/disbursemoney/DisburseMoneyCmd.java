package com.morningstar.covidworkerincentiveapi.disbursementtransaction.disbursemoney;

import com.morningstar.covidworkerincentiveapi.disbursementtransaction.domain.WorkerData;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class DisburseMoneyCmd {
    @TargetAggregateIdentifier
    private UUID transactionId;
    private List<WorkerData> workerDataList;
}
