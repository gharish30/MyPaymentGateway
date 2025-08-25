package com.paymentapp.main;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {
	
	@Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private String apiKeyHeader = "X-API-KEY";
    private String apiKeyValue = "changeme";

    @BeforeEach
    void setup() {}

    @Test
    void createGetListFlow() throws Exception {
        var body = Map.of("method","A_PAY","amount",10.25,"reference","INV-2001","description","desc");
        mockMvc.perform(post("/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .header(apiKeyHeader, apiKeyValue)
                .content(mapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PENDING"));

        mockMvc.perform(get("/payments/1").header(apiKeyHeader, apiKeyValue))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reference").value("INV-2001"));

        mockMvc.perform(get("/payments?page=0&size=5").header(apiKeyHeader, apiKeyValue))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists());
    }

    @Test
    void approveWrongCodeReturnsFailed() throws Exception {
        var body = Map.of("method","B_PAY","amount",9.99,"reference","INV-2002");
        mockMvc.perform(post("/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .header(apiKeyHeader, apiKeyValue)
                .content(mapper.writeValueAsString(body)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/payments/2/approve")
                .contentType(MediaType.APPLICATION_JSON)
                .header(apiKeyHeader, apiKeyValue)
                .content(mapper.writeValueAsString(Map.of("code","000000"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("FAILED"));
    }

    @Test
    void missingApiKeyIsUnauthorized() throws Exception {
        mockMvc.perform(get("/payments"))
                .andExpect(status().isUnauthorized());
    }


}
