package com.morningstar.covidworkerincentiveapi.disbursementtransaction.disbursemoney;

import com.morningstar.covidworkerincentiveapi.disbursementtransaction.validateworkersdata.WorkersDataValidatedEvt;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
public class MoneyDisbursementService {
    private CommandGateway commandGateway;

    public MoneyDisbursementService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void handle(WorkersDataValidatedEvt workersDataValidatedEvt) {
        // TODO: call bank api to disburse money

        final DisburseMoneyCmd disburseMoneyCmd = new DisburseMoneyCmd(
            workersDataValidatedEvt.getTransactionId(),
            workersDataValidatedEvt.getWorkerDataList());

        // continue to next process
        commandGateway.send(disburseMoneyCmd);

    }
}
