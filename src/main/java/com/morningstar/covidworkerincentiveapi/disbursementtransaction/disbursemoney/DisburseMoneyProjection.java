package com.morningstar.covidworkerincentiveapi.disbursementtransaction.disbursemoney;

import com.morningstar.covidworkerincentiveapi.common.DisburseTransactionRepo;
import com.morningstar.covidworkerincentiveapi.common.DisburseTransactionView;
import com.morningstar.covidworkerincentiveapi.common.TransactionStatus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class DisburseMoneyProjection {
    private DisburseTransactionRepo disburseTransactionRepo;

    public DisburseMoneyProjection(DisburseTransactionRepo disburseTransactionRepo) {
        this.disburseTransactionRepo = disburseTransactionRepo;
    }

    @EventHandler
    public void on(MoneyDisbursedEvt moneyDisbursedEvt) {
        final DisburseTransactionView disburseTransactionView = new DisburseTransactionView(
            moneyDisbursedEvt.getTransactionId().toString(),
            TransactionStatus.DISBURSED);
        disburseTransactionRepo.save(disburseTransactionView);
    }
}
