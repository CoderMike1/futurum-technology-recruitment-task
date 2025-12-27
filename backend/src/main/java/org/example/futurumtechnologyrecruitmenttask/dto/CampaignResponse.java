package org.example.futurumtechnologyrecruitmenttask.dto;

import java.util.List;

public record CampaignResponse(Long id,
                               String name,
                               List<String> keywords,
                               double bidAmount,
                               double fund,
                               boolean status,
                               String town,
                               double radius ) {
}
