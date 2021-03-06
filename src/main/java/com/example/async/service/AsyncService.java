package com.example.async.service;

import com.example.async.model.EmployeeAddresses;
import com.example.async.model.EmployeeNames;
import com.example.async.model.EmployeePhone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {
    private static Logger log = LoggerFactory.getLogger(AsyncService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Async("asyncExecutor")
    public CompletableFuture<EmployeeNames> getEmployeeName() throws InterruptedException{
        log.info("getEmployeeName status");
        EmployeeNames employeeNameData = restTemplate.getForObject("http://localhost:8080/name", EmployeeNames.class);
        log.info("employeeNameData, {}", employeeNameData);
        Thread.sleep(1000l);
        log.info("employeeNameData completed");
        return CompletableFuture.completedFuture(employeeNameData);
    }

    @Async("asyncExecutor")
    public CompletableFuture<EmployeeAddresses> getEmployeeAddress() throws InterruptedException {
        log.info("getEmployeeAddress Starts");
        EmployeeAddresses employeeAddressData = restTemplate.getForObject("http://localhost:8080/address", EmployeeAddresses.class);
        log.info("employeeAddressData, {}", employeeAddressData);
        Thread.sleep(1000l);
        return CompletableFuture.completedFuture(employeeAddressData);
    }

    @Async("asyncExecutor")
    public CompletableFuture<EmployeePhone> getEmployeePhone() throws InterruptedException {
        log.info("getEmployeePhone Starts");
        EmployeePhone employeePhoneData = restTemplate.getForObject("http://localhost:8080/phone", EmployeePhone.class);

        log.info("employeePhoneData, {}", employeePhoneData);
        Thread.sleep(1000L);	//Intentional delay
        log.info("employeePhoneData completed");
        return CompletableFuture.completedFuture(employeePhoneData);
    }
}
