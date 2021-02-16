package org.example.rest.controller;

import org.example.domain.entity.Status;
import org.example.domain.repository.StatusRep;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/status")
public class StatusController {

    private StatusRep statusRep;

    public StatusController(StatusRep statusRep){
        this.statusRep = statusRep;
    }

    @GetMapping
    @ResponseStatus(OK)               //Request http for finding all status
    public List<Status> getAllStatus (){

        return statusRep.findAll();
    }
}
