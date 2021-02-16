package org.example.rest.controller;


import org.example.domain.entity.Positions;
import org.example.domain.repository.PositionsRep;
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

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PositionsController.class)
public class PositionsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PositionsRep positionsRep;

    @Test
    public void listAllPositionsShouldReturnOkTest() throws Exception{
        Positions position = new Positions(1, "description");
        List<Positions> allPositions = new ArrayList<>();
        allPositions.add(position);

        given(positionsRep.findAll()).willReturn(allPositions);

        mockMvc.perform(get("/positions").
                contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
