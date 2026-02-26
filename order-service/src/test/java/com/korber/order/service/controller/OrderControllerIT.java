package com.korber.order.service.controller;

import org.junit.jupiter.api.Test;
import com.korber.order.service.client.InventoryClient;
import com.korber.order.service.dto.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrderControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private InventoryClient inventoryClient;

    @Test
    void shouldCreateOrderSuccessfully() throws Exception {

        doNothing().when(inventoryClient)
                .updateInventory(anyLong(), anyInt());

        OrderRequest request = new OrderRequest();
        request.setProductId(1002L);
        request.setQuantity(2);

        mockMvc.perform(post("/order")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").exists())
                .andExpect(jsonPath("$.status").value("PLACED"))
                .andExpect(jsonPath("$.productId").value(1002))
                .andExpect(jsonPath("$.quantity").value(2));
    }

}
