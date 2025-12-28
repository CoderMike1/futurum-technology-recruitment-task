package org.example.futurumtechnologyrecruitmenttask;


import org.example.futurumtechnologyrecruitmenttask.dto.AddNewCampaignRequest;
import org.example.futurumtechnologyrecruitmenttask.dto.CampaignResponse;
import org.example.futurumtechnologyrecruitmenttask.dto.EditCampaignRequest;
import org.example.futurumtechnologyrecruitmenttask.model.Campaign;
import org.example.futurumtechnologyrecruitmenttask.model.STATUS;
import org.example.futurumtechnologyrecruitmenttask.repository.CampaignRepository;
import org.example.futurumtechnologyrecruitmenttask.service.CampaignService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UnitTests {

    @Mock
    private CampaignRepository repository;

    @InjectMocks
    private CampaignService service;


    @Test
    public void getAllCampaignsTest(){

        Campaign c1 = new Campaign(
                1L,
                "Winter Promo",
                List.of("ski", "winter"),
                1.25,
                1500.0,
                STATUS.ON,
                "Krakow",
                12.5
        );

        Campaign c2 = new Campaign(
                2L,
                "Spring Launch",
                List.of("spring", "launch"),
                0.95,
                800.0,
                STATUS.OFF,
                "Warsaw",
                8.0
        );


        when(repository.findAll()).thenReturn(List.of(c1,c2));

        List<CampaignResponse> result = service.getAllCampaigns();

        assertEquals(2,result.size());
        assertEquals(1L,result.get(0).id());
        assertEquals("Winter Promo",result.get(0).name());
        assertEquals("Spring Launch",result.get(1).name());
    }

    @Test
    public void addNewCampaignRecordTest(){

        AddNewCampaignRequest newCampaign = new AddNewCampaignRequest("Summer Promo",List.of("fine","cool"),0.9,1200.0,STATUS.ON,"Krakow",45.0);
        Campaign c = new Campaign(
                newCampaign.name(),
                newCampaign.keywords(),
                newCampaign.bidAmount(),
                newCampaign.campaignFund(),
                newCampaign.status(),
                newCampaign.town(),
                newCampaign.radius()
        );
        when(repository.save(any(Campaign.class))).thenReturn(c);

        CampaignResponse result = service.addCampaign(newCampaign);

        assertNotNull(result);
        assertEquals("Summer Promo",result.name());
        assertEquals(1200.0,result.campaignFund());
        assertEquals("Krakow",result.town());
        assertEquals(List.of("fine","cool"),result.keywords());

    }

    @Test
    public void editCampaignRecordTest(){
        Long id = 1L;
        EditCampaignRequest ecr = new EditCampaignRequest("Summer Promo",List.of("fine","cool"),0.9,1200.0,STATUS.ON,"Krakow",45.0);
        Campaign c = new Campaign(
                "Autumn day",
                List.of("rain"),
                3.1,
                290,
                STATUS.OFF,
               "Warsaw",
                ecr.radius()
        );
        when(repository.findById(id)).thenReturn(Optional.of(c));

        when(repository.save(any(Campaign.class))).thenReturn(null);

        CampaignResponse result = service.editCampaign(id,ecr);

        assertNotNull(result);
        assertEquals("Summer Promo",result.name());
        assertEquals(0.9,result.bidAmount());
        assertEquals("Krakow",result.town());
    }




}
