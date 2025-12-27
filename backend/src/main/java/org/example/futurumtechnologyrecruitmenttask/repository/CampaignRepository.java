package org.example.futurumtechnologyrecruitmenttask.repository;

import org.example.futurumtechnologyrecruitmenttask.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
