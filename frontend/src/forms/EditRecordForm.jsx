import {useEffect, useState} from "react";

const URL = "http://localhost:8080/api/campaigns"
const EditRecordForm = (props) =>{

    const [form,setForm] = useState({})

    useEffect(() => {
        setForm({
            name: props.selectedRecord.name,
            keywords: props.selectedRecord.keywords,
            bidAmount: props.selectedRecord.bidAmount,
            campaignFund: props.selectedRecord.campaignFund ,
            status: props.selectedRecord.status,
            town: props.selectedRecord.town ,
            radius: props.selectedRecord.radius
        });
    }, [props.selectedRecord]);

    const update = (key)=> (e) => setForm(prevState => ({...prevState, [key]:e.target.value}))

    const update_keywords = (key)=> (e) => setForm(prevState => ({...prevState, [key]:e.target.value.split(",")}))


    const [errorMessage,setErrorMessage] = useState(null)

    const handleForm = async (e)=>{
        e.preventDefault();
        const id = props.selectedRecord.id
        const resp = await fetch(`${URL}/edit/${id}`,{
            method:"PUT",
            body:JSON.stringify(form),
            headers:{
                "Content-type":"application/json"
            }
        })
        if (!resp.ok){
            setErrorMessage("Error while editing the record..")
        }
        else{
            props.setEditing(false)
        }
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
                            value={form.name}
                            onChange={update("name")}
                            required={true}
                        />
                    </div>
                    <div className="form-item">
                        <label>Keywords ( separated by comma, ex. "large,scalably")</label>
                        <input
                            type="text"
                            value={form.keywords}
                            onChange={update_keywords("keywords")}
                            required={true}
                        />
                    </div>
                    <div className="form-item">
                        <label>Bid amount</label>
                        <input
                            type="number"
                            min="0"
                            step="0.01"
                            value={form.bidAmount}
                            onChange={update("bidAmount")}
                            required={true}
                        />
                    </div>
                    <div className="form-item">
                        <label>Campaign fund</label>
                        <input
                            type="number"
                            min="0"
                            step="0.01"
                            value={form.campaignFund}
                            onChange={update("campaignFund")}
                            required={true}
                        />
                    </div>
                    <div className="form-item">
                        <label>Status</label>
                        <select
                            value={form.status}
                            onChange={update("status")}
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
                            value={form.town}
                            onChange={update("town")}
                            required={true}
                        />
                    </div>
                    <div className="form-item">
                        <label>Radius</label>
                        <input
                            type="number"
                            step="0.01"
                            value={form.radius}
                            onChange={update("radius")}
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

export default EditRecordForm