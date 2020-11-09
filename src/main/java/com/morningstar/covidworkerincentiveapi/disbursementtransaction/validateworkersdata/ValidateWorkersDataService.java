package com.morningstar.covidworkerincentiveapi.disbursementtransaction.validateworkersdata;

import com.morningstar.covidworkerincentiveapi.common.WorkerData;
import com.morningstar.covidworkerincentiveapi.disbursementtransaction.disbursemoney.DisburseMoneyCmd;
import java.util.List;
import java.util.UUID;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
public class ValidateWorkersDataService {
    private CommandGateway commandGateway;

    public ValidateWorkersDataService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public String on(WorkersDataValidatedEvt workersDataValidatedEvt) {
        final UUID transactionId = workersDataValidatedEvt.getTransactionId();
        final List<WorkerData> workerDataList = workersDataValidatedEvt.getWorkerDataList();
        final Integer MAXIMUM_SALARY_CRITERIA = 1000000;

        for (final WorkerData workerData : workerDataList) {
            if (workerData.getSalary() > MAXIMUM_SALARY_CRITERIA) {
                return "ERROR";
            }
        }
        commandGateway.send(new DisburseMoneyCmd(transactionId, workerDataList));
        return "OK";
    }
}
