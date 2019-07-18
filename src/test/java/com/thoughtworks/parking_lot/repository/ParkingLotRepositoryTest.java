package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotRepositoryTest {
    @Autowired
    private MockMvc mockMvc;

    private List<ParkingLot> parkingLots = new ArrayList<>();

    @Test
    @Transactional
    public void should_return_the_new_parking_lot_when_add_a_new_parking_lot() throws Exception {
        mockMvc.perform(post("/parking-lots")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("{\n" +
                "    \"name\": \"parkingLotTwo\",\n" +
                "    \"capacity\": 100,\n" +
                "    \"position\": \"positionTwo\"\n" +
                "}"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("parkingLotTwo"));
    }

    @Test
    @Transactional
    public void should_return_error_when_add_a_same_name_parking_lot() throws Exception {
        mockMvc.perform(post("/parking-lots")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "    \"name\": \"parkingLotOne\",\n" +
                        "    \"capacity\": 100,\n" +
                        "    \"position\": \"positionOne\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void should_return_the_success_code_when_delete_a_parking_lot() throws Exception {
        mockMvc.perform(delete("/parking-lots/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void should_return_all_parking_lot_when_delete_a_parking_lot() throws Exception {
        mockMvc.perform(get("/parking-lots"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"parkingLotOne\",\n" +
                        "        \"capacity\": 100,\n" +
                        "        \"position\": \"positionOne\"\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    @Transactional
    public void should_return_the_parking_lot_when_query_a_parking_lot_by_id() throws Exception {
        mockMvc.perform(get("/parking-lots/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"parkingLotOne\",\n" +
                        "    \"capacity\": 100,\n" +
                        "    \"position\": \"positionOne\"\n" +
                        "}"));
    }

    @Test
    @Transactional
    public void should_return_the_parking_lot_when_query_a_parking_lot_by_name() throws Exception {
        mockMvc.perform(get("/parking-lots?name=parkingLotOne"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"parkingLotOne\",\n" +
                        "    \"capacity\": 100,\n" +
                        "    \"position\": \"positionOne\"\n" +
                        "}]"));
    }

}