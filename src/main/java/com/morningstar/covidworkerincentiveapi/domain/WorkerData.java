package com.morningstar.covidworkerincentiveapi.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkerData {
    @CsvBindByName
    private String nik;

    @CsvBindByName
    private String fullName;

    @CsvBindByName
    private String address;

    @CsvBindByName
    private Integer salary;
}
