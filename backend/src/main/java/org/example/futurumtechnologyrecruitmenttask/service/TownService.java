package org.example.futurumtechnologyrecruitmenttask.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownService {

    private static final List<String> CITIES = List.of(
            "Paris","London","Rome","Barcelona","Amsterdam","Warsaw","Prague","Madrid","New York"
    );


    public List<String> getCities(){
        return CITIES;
    }

}
