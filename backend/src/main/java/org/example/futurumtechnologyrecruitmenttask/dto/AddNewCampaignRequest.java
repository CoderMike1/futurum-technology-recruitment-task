package org.example.futurumtechnologyrecruitmenttask.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.example.futurumtechnologyrecruitmenttask.model.STATUS;

import java.util.List;

public record AddNewCampaignRequest(
               @NotBlank(message="Campaign name can not be null or empty.")
               String name,

               @NotEmpty(message="Keywords field is mandatory.")
               List< @NotBlank(message = "Keyword can not be null or empty.")String> keywords,

               @NotNull(message="Bid amount field is mandatory.")
               @Min(value=0, message="Bid amount value must be greater than or equal to 0.")
               Double bidAmount,

               @NotNull(message="Campaign fund field is mandatory.")
               @Min(value=0, message="Campaign fund value must be greater than or equal to 0.")
               Double campaignFund,

               @NotNull(message="Status field is mandatory and can not be null.")
               STATUS status,

               @NotBlank(message="Town field can not be null or empty.")
               String town,

               @NotNull(message="Radius field is mandatory.")
               @Min(value=0,message="Radius value must be greater than or equal to 0.")
               Double radius
) {
}
