import './App.css'
import {useEffect, useState} from "react";
import AddRecordForm from "./forms/AddRecordForm.jsx";
import EditRecordForm from "./forms/EditRecordForm.jsx";

const URL = "http://localhost:8080/api"

function App() {

    const [campaignsList,setCampaignsList] = useState([])
    const [townsList,setTownsList] = useState([])

    const [loading,setLoading] = useState(true);
    const [errorMessage,setErrorMessage] = useState(null);
    const [adding,setAdding] = useState(false)
    const [editing,setEditing] = useState(false)

    const [selectedRecord,setSelectedRecord] = useState(null)


    const loadTownsData = async () =>{
        setErrorMessage(null)
        const resp = await fetch(URL+"/towns",{
            method:"GET"
        })
        if (!resp.ok){
            setErrorMessage("Error while loading towns data.")
        }
        else{
            const data = await resp.json();
            setTownsList(data);
        }
    }

    const loadData = async ()=>{
        setErrorMessage(null)
        const resp = await fetch(URL+"/campaigns", {
            method:"GET"
        })
        if (!resp.ok){
            setErrorMessage("Error while loading the data.")
        }
        else{
            const data = await resp.json()
            setCampaignsList(data)
        }
    }

    const deleteRecord = async (id) =>{
        setErrorMessage(null)
        const resp = await fetch(`${URL}/campaigns/delete/${id}`,{
            method:"DELETE"
        })
        if(resp.status !== 204){
            setErrorMessage("Error while deleting record.")
        }
        else{
            await loadData()
        }

    }

    const handleEdit = (c)=>{
        setEditing(true)
        setSelectedRecord(c)
    }

    useEffect(() => {

        (async ()=>{
            console.log("test")
            await loadData()
            setLoading(false)
        })()

    }, [adding,editing]);

    useEffect(()=>{
        (async ()=>{
            await loadTownsData()
        })()
    },[])





  return (
      <div className="main-container">
          {editing && <EditRecordForm selectedRecord={selectedRecord} setEditing={setEditing} townsList={townsList}/>}
          {adding && <AddRecordForm setAdding={setAdding} townsList={townsList}/>}
          <div className="table-container">
              <p className="loading-field">{loading && "Loading..."}</p>
              <p className="error-field">{errorMessage}</p>
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
                      <th>Actions</th>
                  </tr>

                  </thead>
                  <tbody>
                  {campaignsList.map((campaign)=>(
                      <tr key={campaign.id} className="table-item">
                          <td>{campaign.id}</td>
                          <td>{campaign.name}</td>
                          <td>{campaign.keywords.join(",")}</td>
                          <td>{campaign.bidAmount}</td>
                          <td>{campaign.campaignFund}</td>
                          <td>{campaign.status}</td>
                          <td>{campaign.town}</td>
                          <td>{campaign.radius}</td>
                          <td className="actions-column">
                              <button onClick={()=>handleEdit(campaign)}>Edit</button>
                              <button onClick={()=>deleteRecord(campaign.id)}>Delete</button>
                          </td>
                      </tr>
                  ))}
                  </tbody>


              </table>
          </div>
          <button className="add-record-button" onClick={()=>setAdding(!adding)}>Add</button>
      </div>

  )
}

export default App
