package com.morningstar.covidworkerincentiveapi.disbursementtransaction.validateworkersdata;

import com.morningstar.covidworkerincentiveapi.common.DisburseTransactionRepo;
import com.morningstar.covidworkerincentiveapi.common.DisburseTransactionView;
import com.morningstar.covidworkerincentiveapi.common.TransactionStatus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ValidateWorkersDataProjection {
    DisburseTransactionRepo disburseTransactionRepo;

    public ValidateWorkersDataProjection(DisburseTransactionRepo disburseTransactionRepo)  {
        this.disburseTransactionRepo = disburseTransactionRepo;
    }

    @EventHandler
    public void on(WorkersDataValidatedEvt workersDataValidatedEvt) {
        final DisburseTransactionView disburseTransactionView = new DisburseTransactionView(
            workersDataValidatedEvt.getTransactionId().toString(),
            TransactionStatus.VALIDATED);
        disburseTransactionRepo.save(disburseTransactionView);
    }
}
