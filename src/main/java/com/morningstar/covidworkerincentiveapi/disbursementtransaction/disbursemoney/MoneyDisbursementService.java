package com.morningstar.covidworkerincentiveapi.disbursementtransaction.disbursemoney;

import com.morningstar.covidworkerincentiveapi.common.WorkerData;
import java.util.List;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public class MoneyDisbursementService {

    @CommandHandler
    public void handle(DisburseMoneyCmd disburseMoneyCmd) {
        final List<WorkerData> workerDataList = disburseMoneyCmd.getWorkerDataList();
    }
}
