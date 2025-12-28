# TECHNICAL TASK FOR FUTURUM TECHNOLOGY RECRUITMENT PROCESS

## Overview
REST API for managing campaign information.

### RUN APPLICATION
 1) Using Docker, run ```docker compose up``` in main directory , then open ```http://localhost:5173/```
2) Locally, run ```npm run dev``` in frontend directory and run ```FuturumTechnologyRecruitmentTaskApplication.java```, then open ```http://localhost:5173/```

Base URL:
- Local: http://localhost:8080

Content-Type:
- Requests with body: application/json
- ---

## Create a campaign


POST /api/campaigns/add

Request body:
```json
{
  "name":"Promo1",
  "keywords":["toys","winter","christmas"],
  "status":"OFF",
  "campaignFund":5,
  "radius":0,
  "town":"Warsaw",
  "bidAmount":1.5
}
```

Response:
- 201 Created

---
## Get all campaigns

GET /api/campaigns

Response:
- 200 OK

---
## Update campaign
PUT /api/campaigns/edit/{id}

Request body:
```json
{
  "name":"Promo1",
  "keywords":["toys","winter","christmas"],
  "status":"OFF",
  "campaignFund":5,
  "radius":0,
  "town":"Warsaw",
  "bidAmount":1.5
}
```
Notes: All fields are optional.

Response:
- 200 OK

---

## Delete campaign

DELETE /api/campaigns/delete/{id}

Response:
- 204 No Content
