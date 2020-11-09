package com.morningstar.covidworkerincentiveapi.common;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisburseTransactionRepo extends JpaRepository<DisburseTransactionView, UUID> {

}
