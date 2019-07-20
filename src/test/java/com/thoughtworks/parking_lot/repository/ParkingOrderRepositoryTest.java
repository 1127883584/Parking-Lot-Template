package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingOrderRepositoryTest {
    @Autowired
    private MockMvc mockMvc;

//    @MockBean
    @Autowired
    private ParkingOrderService parkingOrderService;

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Test
    @Transactional
    public void should_return_the_new_parking_order_when_add_a_new_parking_order() throws Exception {

//        Mockito.when(mockParkingOrderService.createParkingOrder(Mockito.any())).thenReturn(new ParkingOrder("'A12345",null,null,true))

        mockMvc.perform(post("/parking-orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "\t\"parkingLot\": {\n" +
                        "\t\t\"id\": 1\n" +
                        "\t},\n" +
                        "    \"carNumber\": \"659821\",\n" +
                        "    \"status\": \"open\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carNumber").value("659821"));
    }

    @Test
    @Transactional
    public void should_return_the_updated_order_when_a_car_exit() throws Exception {
        mockMvc.perform(put("/parking-orders/33"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("close"));
    }

    @Test
    @Transactional
    public void should_return_error_when_add_a_car_come_and_the_parking_lot_is_full() throws Exception {
        ParkingLot parkingLot = parkingLotRepository.findById(1);
        parkingOrderRepository.save(new ParkingOrder(parkingLot, "4564654", (new Date()).getTime(),"open"));
        mockMvc.perform(post("/parking-orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "\t\"parkingLot\": {\n" +
                        "\t\t\"id\": 1\n" +
                        "\t},\n" +
                        "    \"carNumber\": \"659821\",\n" +
                        "    \"status\": \"open\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(null));
    }
}