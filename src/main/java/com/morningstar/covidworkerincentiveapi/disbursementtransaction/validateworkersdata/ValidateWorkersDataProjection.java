package com.morningstar.covidworkerincentiveapi.disbursementtransaction.validateworkersdata;

import com.morningstar.covidworkerincentiveapi.common.DisburseTransactionRepo;
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
        System.out.println("----======== VALIDATED PROJECTION ==========-----------");
    }
}
