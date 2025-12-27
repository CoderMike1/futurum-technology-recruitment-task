package org.example.futurumtechnologyrecruitmenttask.service;

import org.example.futurumtechnologyrecruitmenttask.dto.AddNewCampaignRequest;
import org.example.futurumtechnologyrecruitmenttask.dto.CampaignResponse;
import org.example.futurumtechnologyrecruitmenttask.dto.UpdateCampaignRequest;
import org.example.futurumtechnologyrecruitmenttask.model.Campaign;
import org.example.futurumtechnologyrecruitmenttask.repository.CampaignRepository;
import org.example.futurumtechnologyrecruitmenttask.utils.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
                newCampaign.campaignFund(),
                newCampaign.status(),
                newCampaign.town(),
                newCampaign.radius()
        );

        Campaign p = campaignRepository.save(c);

        return ObjectMapper.mapToResponse(p);


    }

    public CampaignResponse updateCampaign(Long id, UpdateCampaignRequest updatedCampaign){

        Campaign c = campaignRepository.findById(id).orElseThrow(()->new NoSuchElementException("Campaign with id '"+id+"' was not found."));

        if(updatedCampaign.name() != null){
            c.setName(updatedCampaign.name());
        }
        if(updatedCampaign.keywords() != null){
            c.setKeywords(updatedCampaign.keywords());
        }
        if(updatedCampaign.bidAmount() != null){
            c.setBidAmount(updatedCampaign.bidAmount());
        }
        if(updatedCampaign.campaignFund() != null){
            c.setCampaignFund(updatedCampaign.campaignFund());
        }
        if(updatedCampaign.status() != null){
            c.setStatus(updatedCampaign.status());
        }
        if(updatedCampaign.town() != null){
            c.setTown(updatedCampaign.town());
        }
        if(updatedCampaign.radius() != null){
            c.setRadius(updatedCampaign.radius());
        }


         campaignRepository.save(c);

        return ObjectMapper.mapToResponse(c);


    }

    public void deleteCampaign(Long id){

        Campaign c = campaignRepository.findById(id).orElseThrow(()->new NoSuchElementException("Campaign with id '"+id+"' was not found."));

        campaignRepository.delete(c);
    }





}
