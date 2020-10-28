package com.morningstar.covidworkerincentiveapi.disbursementtransaction.disbursemoney;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoneyDisbursedEvt {
    private UUID transactionId;
}
