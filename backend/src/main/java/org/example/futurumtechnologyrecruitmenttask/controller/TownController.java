package org.example.futurumtechnologyrecruitmenttask.controller;


import org.example.futurumtechnologyrecruitmenttask.service.TownService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("api/towns")
public class TownController {

    private TownService townService;

    public TownController(TownService townService){
        this.townService = townService;
    }

    @GetMapping()
    public ResponseEntity<List<String>> getCitiesList(){

        return ResponseEntity.status(HttpStatus.OK).body(townService.getCities());
    }


}
