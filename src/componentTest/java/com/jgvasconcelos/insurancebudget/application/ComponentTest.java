package com.jgvasconcelos.insurancebudget.application;

import com.jgvasconcelos.insurancebudget.InsuranceBudgetApplication;
import org.flywaydb.test.annotation.FlywayTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@FlywayTest
@ActiveProfiles("component-test")
@SpringBootTest(
        classes = InsuranceBudgetApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class ComponentTest {
    @LocalServerPort
    protected Integer localServerPort;
    @Autowired
    protected RestTemplate restTemplate;
}
