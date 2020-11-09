package com.morningstar.covidworkerincentiveapi.disbursementtransaction.uploadworkersdata;

import com.morningstar.covidworkerincentiveapi.common.DisburseTransactionRepo;
import com.morningstar.covidworkerincentiveapi.common.DisburseTransactionView;
import com.morningstar.covidworkerincentiveapi.common.TransactionStatus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class UploadWorkersDataProjection {
    private DisburseTransactionRepo disburseTransactionRepo;

    public UploadWorkersDataProjection(DisburseTransactionRepo disburseTransactionRepo) {
        this.disburseTransactionRepo =  disburseTransactionRepo;
    }

    @EventHandler
    public void on(WorkersDataUploadedEvt workersDataUploadedEvt) {
        final DisburseTransactionView disburseTransactionView = new DisburseTransactionView(
            workersDataUploadedEvt.getTransactionId().toString(),
            TransactionStatus.UPLOADED);
        disburseTransactionRepo.save(disburseTransactionView);
    }
}
