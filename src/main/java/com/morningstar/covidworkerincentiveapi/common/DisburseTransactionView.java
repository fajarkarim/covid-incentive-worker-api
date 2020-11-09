package com.morningstar.covidworkerincentiveapi.common;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name =  "DISB_TRX")
public class DisburseTransactionView {
    @Id
    private String transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}
