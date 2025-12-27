package org.example.futurumtechnologyrecruitmenttask.utils;

import org.example.futurumtechnologyrecruitmenttask.dto.CampaignResponse;
import org.example.futurumtechnologyrecruitmenttask.model.Campaign;

public class ObjectMapper {

    public static CampaignResponse mapToResponse(Campaign c){

        return new CampaignResponse(
                c.getId(),
                c.getName(),
                c.getKeywords(),
                c.getBidAmount(),
                c.getFund(),
                c.isStatus(),
                c.getTown(),
                c.getRadius()
        );

    }

}
