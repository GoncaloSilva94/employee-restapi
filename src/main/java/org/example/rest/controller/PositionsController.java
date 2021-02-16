package org.example.rest.controller;



import org.example.domain.entity.Positions;
import org.example.domain.repository.PositionsRep;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/positions")
public class PositionsController {

    private PositionsRep positionsRep;

    public PositionsController(PositionsRep positionsRep){
        this.positionsRep = positionsRep;
    }

    @GetMapping
    @ResponseStatus(OK)   //Request http for finding all positions
    public List<Positions> getAllPositions (){

        return positionsRep.findAll();
    }
}
