package com.morningstar.covidworkerincentiveapi.coreapi;

import com.morningstar.covidworkerincentiveapi.domain.WorkerData;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkersDataValidatedEvt {

    private UUID transactionId;
    private List<WorkerData> workerDataList;
}
