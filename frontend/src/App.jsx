import './App.css'
import {useEffect, useState} from "react";

const URL = "http://localhost:8080/api/campaigns"

function App() {

    const [campaignsList,setCampaignsList] = useState([])

    const [loading,setLoading] = useState(true);
    const [errorMessage,setErrorMessage] = useState(null);



    useEffect(() => {

        (async ()=>{

            const resp = await fetch(URL, {
                method:"GET"
            })
            if (!resp.ok){
                setErrorMessage("Error while loading the data.")
            }
            else{
                const data = await resp.json()
                setCampaignsList(data)
            }
        })()

    }, []);


  return (
    <div>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Keywords</th>
                    <th>Bid amount</th>
                    <th>Campaign fund</th>
                    <th>Status</th>
                    <th>Town</th>
                    <th>Radius</th>
                </tr>

            </thead>
            <tbody>
                {campaignsList.map((campaign)=>(
                    <tr key={campaign.id}>
                        <td>{campaign.id}</td>
                        <td>{campaign.name}</td>
                        <td>{campaign.keywords.join(",")}</td>
                        <td>{campaign.bidAmount}</td>
                        <td>{campaign.campaignFund}</td>
                        <td>{campaign.status}</td>
                        <td>{campaign.town}</td>
                        <td>{campaign.radius}</td>
                    </tr>
                ))}
            </tbody>


        </table>


    </div>
  )
}

export default App
