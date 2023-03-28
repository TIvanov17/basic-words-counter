import { useState }         from "react";
import { countByPageUrl }   from "../../../service/words-counter-service";
import { WordsList }        from "../../words-list/WordsList";

import "./UrlForm.css";

export function UrlForm(){
    
    const emptyWordsCountResult = {
        content: "",
        totalNumberOfWords: 0,
        topWordsRankedMap: {}
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
        countByPageUrl(wordsCountResult)
        .then(response  => {
            setWordsCountResult(response.data);
        })
        .catch(error => console.log(error.message))
    }

    return(
        <div>
            <div className="form-url">
                <form onSubmit= {onButtonClicked }>
                    <label>Enter Url:
                        <input  type = "url" className="url-input" onChange = { onTextAreaChange } 
                                name = "content" value = {wordsCountResult.content}/>
                    </label>
                    <input type="submit" value="Count"/>
                </form>
            </div>
            <h2>{ wordsCountResult.totalNumberOfWords } words </h2>
            <div className = "result">
                <h3>Most Common Words</h3>
                <WordsList topWordsCollection = { wordsCountResult.topWordsRankedMap }/>
            </div>
        </div>

    );
}