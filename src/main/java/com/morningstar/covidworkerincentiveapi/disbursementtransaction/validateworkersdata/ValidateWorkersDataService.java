package com.morningstar.covidworkerincentiveapi.disbursementtransaction.validateworkersdata;

import com.morningstar.covidworkerincentiveapi.common.WorkerData;
import java.util.List;
import java.util.UUID;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
public class ValidateWorkersDataService {
    private CommandGateway commandGateway;

    public ValidateWorkersDataService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @CommandHandler
    public String handle(ValidateWorkersDataCmd validateWorkersDataCmd) {
        final UUID transactionId = validateWorkersDataCmd.getTransactionId();
        final List<WorkerData> workerDataList = validateWorkersDataCmd.getWorkerDataList();
        final Integer MAXIMUM_SALARY_CRITERIA = 1000000;

        for (final WorkerData workerData : workerDataList) {
            if (workerData.getSalary() > MAXIMUM_SALARY_CRITERIA) {
                return "ERROR";
            }
        }
        return "OK";
    }
}
