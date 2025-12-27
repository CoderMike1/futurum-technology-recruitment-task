package org.example.futurumtechnologyrecruitmenttask.dto;

import org.example.futurumtechnologyrecruitmenttask.model.STATUS;

import java.util.List;

public record CampaignResponse(Long id,
                               String name,
                               List<String> keywords,
                               double bidAmount,
                               double campaignFund,
                               STATUS status,
                               String town,
                               double radius ) {
}
