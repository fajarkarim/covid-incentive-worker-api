package com.morningstar.covidworkerincentiveapi.disbursementtransaction;

import com.morningstar.covidworkerincentiveapi.disbursementtransaction.disbursemoney.DisburseMoneyCmd;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.disbursemoney.MoneyDisbursedEvt;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.uploadworkersdata.UploadWorkersDataCmd;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.uploadworkersdata.WorkersDataUploadedEvt;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.validateworkersdata.ValidateWorkersDataCmd;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.validateworkersdata.WorkersDataValidatedEvt;
import java.util.UUID;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class DisbursementTransaction {
    @AggregateIdentifier
    private UUID transactionId;
    private boolean uploaded;
    private boolean validated;
    private boolean disbursed;

    public DisbursementTransaction() {

    }

    @CommandHandler
    public DisbursementTransaction(UploadWorkersDataCmd uploadWorkersDataCmd) {
        AggregateLifecycle.apply(new WorkersDataUploadedEvt(uploadWorkersDataCmd.getTransactionId(),
            uploadWorkersDataCmd.getWorkersData()));
    }

    @EventSourcingHandler
    public void on(WorkersDataUploadedEvt workersDataUploadedEvt) {
        transactionId = workersDataUploadedEvt.getTransactionId();
        uploaded = true;
        validated = false;
        disbursed = false;
    }

    @CommandHandler
    public void handle(ValidateWorkersDataCmd validateWorkersDataCmd) {
        if (validated) {
            return;
        }
        AggregateLifecycle.apply(new WorkersDataValidatedEvt(validateWorkersDataCmd.getTransactionId(),
            validateWorkersDataCmd.getWorkerDataList()));
    }

    @EventSourcingHandler
    public void on(WorkersDataValidatedEvt workersDataValidatedEvt) {
        validated = true;
    }

    @CommandHandler
    public void handle(DisburseMoneyCmd disburseMoneyCmd) {
        if (disbursed) {
            return;
        }

        AggregateLifecycle.apply(new MoneyDisbursedEvt(disburseMoneyCmd.getTransactionId()));
    }

    @EventSourcingHandler
    public void on(MoneyDisbursedEvt moneyDisbursedEvt) {
        disbursed = true;
    }
}
