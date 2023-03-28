import { useState }                 from "react";
import { countTextWordsResult }     from "../../../service/words-counter-service";
import { WordsList }                from "../../words-list/WordsList";

import "./TextForm.css";

export function TextForm(){

    const emptyWordsCountResult = {
        content             : "",
        totalNumberOfWords  : 0,
        topWordsRankedMap   : {}
    }

    const [wordsCountResult, setWordsCountResult] = useState(emptyWordsCountResult);
    
    const onTextAreaChange = ( event ) => {
        setWordsCountResult({
            ...wordsCountResult, 
            [event.target.name] : event.target.value
        });
    }

    const onButtonClicked = ( event ) => {
        event.preventDefault();
        countTextWordsResult(wordsCountResult)
        .then(response  => {
            setWordsCountResult(response.data);
        })
        .catch(error => console.log(error));
    }

    const clearTextArea = ( event ) => {
        event.preventDefault();
        setWordsCountResult(emptyWordsCountResult);
    }

    return(
        <div className="main-container">
            
            <div className="page-title">
                <h2> Welcome to Words Counter </h2>
            </div>
            
            <div>    
                <form>
                    <textarea className="text-input"
                            placeholder="Write Something..."
                            rows = "15" cols = "100" 
                            name = "content" value = { wordsCountResult.content }
                            onChange = { onTextAreaChange }>      
                            Write something
                    </textarea>
                    <div className="action-buttons">
                        <button type = "submit" onClick = { onButtonClicked }>Count</button>   
                        <button onClick = { clearTextArea }>Clear</button>                      
                   </div>
                </form>
            </div>
            
            <div className="result">
                <h3>{ wordsCountResult.totalNumberOfWords } words </h3>
                <div>
                    <h4>Most Common Words</h4>
                    <WordsList topWordsCollection = { wordsCountResult.topWordsRankedMap } />
                </div>
            </div>

        </div>
    )
}