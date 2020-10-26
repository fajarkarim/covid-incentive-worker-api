package com.morningstar.covidworkerincentiveapi.command;

import com.morningstar.covidworkerincentiveapi.coreapi.UploadWorkersDataCmd;
import com.morningstar.covidworkerincentiveapi.coreapi.WorkersDataUploadedEvt;
import java.util.UUID;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class DisbursementTransaction {
    @AggregateIdentifier
    private UUID transactionId;
    boolean disbursed;

    public DisbursementTransaction() {

    }

    @CommandHandler
    public void handle(UploadWorkersDataCmd uploadWorkersDataCmd) {
        AggregateLifecycle.apply(new WorkersDataUploadedEvt(uploadWorkersDataCmd.getTransactionId(),
            uploadWorkersDataCmd.getWorkersData()));
    }
}
