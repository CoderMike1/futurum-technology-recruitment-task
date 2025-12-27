package org.example.futurumtechnologyrecruitmenttask.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateCampaignRequest(
        @Size(min=1, message="Name must have at least 1 letter.")
        String name,


        List<@NotBlank(message="Keyword can not be empty.") String> keywords,

        @Min(value=0)
        Double bidAmount,
        @Min(value=0)
        Double campaignFund,
        Boolean status,
        @Size(min=1, message="Name must have at least 1 letter.")
        String town,
        @Min(value=0, message="Radius field value must be greater than or equal to 0.")
        Double radius


) {
}
