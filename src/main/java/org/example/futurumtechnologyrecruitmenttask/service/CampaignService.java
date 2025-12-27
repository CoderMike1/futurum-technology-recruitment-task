package org.example.futurumtechnologyrecruitmenttask.service;

import org.example.futurumtechnologyrecruitmenttask.dto.AddNewCampaignRequest;
import org.example.futurumtechnologyrecruitmenttask.dto.CampaignResponse;
import org.example.futurumtechnologyrecruitmenttask.model.Campaign;
import org.example.futurumtechnologyrecruitmenttask.repository.CampaignRepository;
import org.example.futurumtechnologyrecruitmenttask.utils.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    private CampaignRepository campaignRepository;


    public CampaignService(CampaignRepository campaignRepository){
        this.campaignRepository = campaignRepository;
    }

    public List<CampaignResponse> getAllCampaigns(){

        List<CampaignResponse> records = campaignRepository.findAll().stream().map(c-> ObjectMapper.mapToResponse(c)).toList();

        return records;

    }

    public CampaignResponse addCampaign(AddNewCampaignRequest newCampaign){

        Campaign c = new Campaign(
                newCampaign.name(),
                newCampaign.keywords(),
                newCampaign.bidAmount(),
                newCampaign.fund(),
                newCampaign.status(),
                newCampaign.town(),
                newCampaign.radius()
        );

        Campaign p = campaignRepository.save(c);

        return ObjectMapper.mapToResponse(p);


    }

    public void editCampaign(){

    }

    public void deleteCampaign(){
    }





}
