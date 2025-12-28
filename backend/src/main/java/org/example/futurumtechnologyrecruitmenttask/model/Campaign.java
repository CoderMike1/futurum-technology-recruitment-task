package org.example.futurumtechnologyrecruitmenttask.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private List<String> keywords;

    private double bidAmount;

    private double campaignFund;

    private STATUS status;

    private String town;

    private double radius;

    public Campaign(Long id, String name, List<String> keywords, double bidAmount, double campaignFund, STATUS status, String town, double radius) {
        this.id = id;
        this.name = name;
        this.keywords = keywords;
        this.bidAmount = bidAmount;
        this.campaignFund = campaignFund;
        this.status = status;
        this.town = town;
        this.radius = radius;
    }

    public Campaign(String name, List<String> keywords, double bidAmount, double campaignFund, STATUS status, String town, double radius) {
        this.name = name;
        this.keywords = keywords;
        this.bidAmount = bidAmount;
        this.campaignFund = campaignFund;
        this.status = status;
        this.town = town;
        this.radius = radius;
    }

    public Campaign() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public double getCampaignFund() {
        return campaignFund;
    }

    public void setCampaignFund(double campaignFund) {
        this.campaignFund = campaignFund;
    }

    public STATUS isStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
