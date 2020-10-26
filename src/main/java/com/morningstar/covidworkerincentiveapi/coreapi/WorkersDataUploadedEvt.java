package com.morningstar.covidworkerincentiveapi.coreapi;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class WorkersDataUploadedEvt {
    UUID transactionId;
    MultipartFile workersData;
}
