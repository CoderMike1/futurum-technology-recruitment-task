package org.example.futurumtechnologyrecruitmenttask.controller;


import jakarta.validation.Valid;
import org.example.futurumtechnologyrecruitmenttask.dto.AddNewCampaignRequest;
import org.example.futurumtechnologyrecruitmenttask.dto.CampaignResponse;
import org.example.futurumtechnologyrecruitmenttask.dto.UpdateCampaignRequest;
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

    @PostMapping()
    public ResponseEntity<CampaignResponse> addNewCampaign(@Valid @RequestBody AddNewCampaignRequest newCampaignRequest){
        CampaignResponse c = campaignService.addCampaign(newCampaignRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CampaignResponse> updateCampaign(@PathVariable Long id, @RequestBody @Valid UpdateCampaignRequest updatedCampaign){
        CampaignResponse cr = campaignService.updateCampaign(id, updatedCampaign);

        return ResponseEntity.status(HttpStatus.OK).body(cr);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long id){
        campaignService.deleteCampaign(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
