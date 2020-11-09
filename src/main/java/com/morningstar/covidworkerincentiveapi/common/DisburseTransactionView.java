package com.morningstar.covidworkerincentiveapi.common;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name =  "DISB_TRX")
public class DisburseTransactionView {
    @Id
    private String transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}
