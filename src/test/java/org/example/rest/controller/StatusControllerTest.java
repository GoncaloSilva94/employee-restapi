package org.example.rest.controller;

import org.example.domain.entity.Status;
import org.example.domain.repository.StatusRep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StatusController.class)
public class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StatusRep statusRep;

    @Test
    public void listAllPositionsShouldReturnOkTest() throws Exception{
        Status status = new Status(1, "description");
        List<Status> allStatus = new ArrayList<>();
        allStatus.add(status);

        given(statusRep.findAll()).willReturn(allStatus);

        mockMvc.perform(get("/status").
                contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
