package com.morningstar.covidworkerincentiveapi.disbursementtransaction;

import com.morningstar.covidworkerincentiveapi.common.WorkerData;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.disbursemoney.DisburseMoneyCmd;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.disbursemoney.MoneyDisbursedEvt;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.uploadworkersdata.UploadWorkersDataCmd;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.uploadworkersdata.WorkersDataUploadedEvt;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.validateworkersdata.ValidateWorkersDataCmd;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.validateworkersdata.WorkersDataValidatedEvt;
import java.util.List;
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

    @CommandHandler
    public void handle(ValidateWorkersDataCmd validateWorkersDataCmd) {
        if (validated) {
            return;
        }

        final List<WorkerData> workerDataList = validateWorkersDataCmd.getWorkerDataList();
        final Integer MAXIMUM_SALARY_CRITERIA = 1000000;

        for (final WorkerData workerData : workerDataList) {
            if (workerData.getSalary() > MAXIMUM_SALARY_CRITERIA) {
                return;
            }
        }

        AggregateLifecycle.apply(new WorkersDataValidatedEvt(transactionId,
            validateWorkersDataCmd.getWorkerDataList()));
    }

    @CommandHandler
    public void handle(DisburseMoneyCmd disburseMoneyCmd) {
        if (disbursed) {
            return;
        }

        AggregateLifecycle.apply(new MoneyDisbursedEvt(disburseMoneyCmd.getTransactionId()));
    }

    @EventSourcingHandler
    public void on(WorkersDataUploadedEvt workersDataUploadedEvt) {
        transactionId = workersDataUploadedEvt.getTransactionId();
        uploaded = true;
        validated = false;
        disbursed = false;
    }

    @EventSourcingHandler
    public void on(WorkersDataValidatedEvt workersDataValidatedEvt) {
        validated = true;
    }

    @EventSourcingHandler
    public void on(MoneyDisbursedEvt moneyDisbursedEvt) {
        disbursed = true;
    }
}
