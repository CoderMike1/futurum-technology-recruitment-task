package org.example.futurumtechnologyrecruitmenttask;


import org.example.futurumtechnologyrecruitmenttask.dto.AddNewCampaignRequest;
import org.example.futurumtechnologyrecruitmenttask.dto.EditCampaignRequest;
import org.example.futurumtechnologyrecruitmenttask.model.Campaign;
import org.example.futurumtechnologyrecruitmenttask.model.STATUS;
import org.example.futurumtechnologyrecruitmenttask.repository.CampaignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    CampaignRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void beforeTest(){
        repository.deleteAll();
    }

    @Test
    public void shouldReturnEmptyCampaignsList() throws Exception {

        mvc.perform(get("/api/campaigns")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(0));


    }

    @Test
    public void shouldReturnNotEmptyCampaignsList() throws Exception {

        Campaign c1 = new Campaign(
                "Summer Promo",
                List.of("fine", "cool"),
                0.9,
                1200.0,
                STATUS.ON,
                "Gdansk",
                45.0
        );
        Campaign c2 = new Campaign(
                "Winter Promo",
                List.of("ski", "winter"),
                1.25,
                1500.0,
                STATUS.ON,
                "Krakow",
                12.5
        );
        repository.saveAll(List.of(c1,c2));

        mvc.perform(get("/api/campaigns")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Summer Promo"))
                .andExpect(jsonPath("$[1].town").value("Krakow"));


    }

    @Test
    public void shouldAddNewCampaignRecord() throws Exception {

        AddNewCampaignRequest req = new AddNewCampaignRequest("Summer Promo",List.of("fine","cool"),0.9,1200.0,STATUS.ON,"Krakow",45.0);

        String json = objectMapper.writeValueAsString(req);

        mvc.perform(post("/api/campaigns/add").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Summer Promo"));


    }

    @Test
    public void shouldEditCampaignRecord() throws Exception{

        Campaign c1 = new Campaign(
                "Summer Promo",
                List.of("fine", "cool"),
                0.9,
                1200.0,
                STATUS.ON,
                "Gdansk",
                45.0
        );
        Campaign saved = repository.save(c1);

        Long id = saved.getId();
        EditCampaignRequest req = new EditCampaignRequest(null,null,null,900.0,null,"Berlin",null);

        String json = objectMapper.writeValueAsString(req);

        mvc.perform(put("/api/campaigns/edit/"+id).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Summer Promo"))
                .andExpect(jsonPath("$.campaignFund").value(900.0))
                .andExpect(jsonPath("$.town").value("Berlin"));
    }

    @Test
    public void shouldDeleteCampaignRecord() throws Exception{

        Campaign c1 = new Campaign(
                "Summer Promo",
                List.of("fine", "cool"),
                0.9,
                1200.0,
                STATUS.ON,
                "Gdansk",
                45.0
        );
        Campaign saved = repository.save(c1);

        Long id = saved.getId();

        mvc.perform(delete("/api/campaigns/delete/"+id))
                .andExpect(status().isNoContent());

        assertEquals(Optional.empty(),repository.findById(id));


    }




}
