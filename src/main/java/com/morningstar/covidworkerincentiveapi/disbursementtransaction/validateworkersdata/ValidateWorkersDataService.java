package com.morningstar.covidworkerincentiveapi.disbursementtransaction.validateworkersdata;

import com.morningstar.covidworkerincentiveapi.common.WorkerData;
import java.util.List;
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
        final List<WorkerData> workerDataList = workersDataValidatedEvt.getWorkerDataList();
        final Integer MAXIMUM_SALARY_CRITERIA = 1000000;

        for (final WorkerData workerData : workerDataList) {
            if (workerData.getSalary() > MAXIMUM_SALARY_CRITERIA) {
                return "ERROR";
            }
        }
        return "OK";
    }
}
