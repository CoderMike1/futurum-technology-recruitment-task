package org.example.futurumtechnologyrecruitmenttask.controller;


import jakarta.validation.Valid;
import org.example.futurumtechnologyrecruitmenttask.dto.AddNewCampaignRequest;
import org.example.futurumtechnologyrecruitmenttask.dto.CampaignResponse;
import org.example.futurumtechnologyrecruitmenttask.dto.EditCampaignRequest;
import org.example.futurumtechnologyrecruitmenttask.service.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("api/campaigns")
public class CampaignController {

    private CampaignService campaignService;


    public CampaignController(CampaignService campaignService){
        this.campaignService = campaignService;
    }

    @GetMapping()
    public ResponseEntity<List<CampaignResponse>> getAllCampaigns(){

        List<CampaignResponse> records = campaignService.getAllCampaigns();
        System.out.println(records);
        return ResponseEntity.status(HttpStatus.OK).body(records);
    }

    @PostMapping("/add")
    public ResponseEntity<CampaignResponse> addNewCampaign(@Valid @RequestBody AddNewCampaignRequest newCampaignRequest){
        CampaignResponse c = campaignService.addCampaign(newCampaignRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CampaignResponse> editCampaign(@PathVariable Long id, @RequestBody @Valid EditCampaignRequest newCampaign){
        CampaignResponse cr = campaignService.editCampaign(id, newCampaign);

        return ResponseEntity.status(HttpStatus.OK).body(cr);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long id){
        campaignService.deleteCampaign(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
