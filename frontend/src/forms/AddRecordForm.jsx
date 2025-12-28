import {useState} from "react";
import './Form.css'
const URL = "http://localhost:8080/api/campaigns"
const AddRecordForm = (props) =>{

    const [campaignName,setCampaignName] = useState("")
    const [keywords,setKeywords] = useState("")
    const [bidAmount,setBidAmount] = useState("")
    const [campaignFund,setCampaignFund] = useState("")
    const [status, setStatus] = useState("")
    const [town, setTown] = useState("")
    const [radius, setRadius] = useState("")

    const [errorMessage,setErrorMessage] = useState(null)

    const handleForm = async (e)=>{
        e.preventDefault()
        setErrorMessage(null)
        const keywords_arr = keywords.split(",");

        const payload = {
            name:campaignName,
            keywords:keywords_arr,
            bidAmount,
            campaignFund,
            status,
            town,
            radius
        }
        const resp = await fetch(`${URL}/add`,{
            method:"POST",
            body:JSON.stringify(payload),
            headers:{
                "Content-type":"application/json"
            }
        })
        if (!resp.ok){
            setErrorMessage("Error while creating new record...")
        }
        props.setAdding(false)
    }

    return (
        <div className="form-container">
            <div className="form-card">
                <p className="error-field">{errorMessage}</p>
                <h3 className="form-title">Add new campaign</h3>

                <form onSubmit={handleForm}>
                    <div className="form-item">
                        <label>Campaign name</label>
                        <input
                            type="text"
                            value={campaignName}
                            onChange={(e)=>setCampaignName(e.target.value)}
                            required={true}
                        />
                    </div>
                    <div className="form-item">
                        <label>Keywords ( separated by comma, ex. "large,scalably")</label>
                        <input
                            type="text"
                            value={keywords}
                            onChange={(e)=>setKeywords(e.target.value)}
                            required={true}
                        />
                    </div>
                    <div className="form-item">
                        <label>Bid amount</label>
                        <input
                            type="number"
                            min="0"
                            step="0.01"
                            value={bidAmount}
                            onChange={(e)=>setBidAmount(e.target.value)}
                            required={true}
                        />
                    </div>
                    <div className="form-item">
                        <label>Campaign fund</label>
                        <input
                            type="number"
                            min="0"
                            step="0.01"
                            value={campaignFund}
                            onChange={(e)=>setCampaignFund(e.target.value)}
                            required={true}
                        />
                    </div>
                    <div className="form-item">
                        <label>Status</label>
                        <select
                            value={status}
                            onChange={(e) => setStatus(e.target.value)}
                            required
                        >
                            <option value="" disabled>Choose...</option>
                            <option value="ON">ON</option>
                            <option value="OFF">OFF</option>
                        </select>
                    </div>
                    <div className="form-item">
                        <label>Town</label>
                        <input
                            type="text"
                            value={town}
                            onChange={(e)=>setTown(e.target.value)}
                            list="town-options"
                            required={true}
                        />

                        <datalist id="town-options">
                            {props.townsList.map((town,index)=>(
                                <option key={index} value={town}/>
                            ))}
                        </datalist>
                    </div>
                    <div className="form-item">
                        <label>Radius</label>
                        <input
                            type="number"
                            step="0.01"
                            value={radius}
                            onChange={(e)=>setRadius(e.target.value)}
                            required={true}
                        />
                    </div>

                    <div className="form-actions">
                        <button className="form-btn" onClick={()=>props.setAdding(false)}>Cancel</button>
                        <button className="form-btn primary" type="submit">Save</button>
                    </div>
                </form>

            </div>

        </div>
    )

}

export default AddRecordForm