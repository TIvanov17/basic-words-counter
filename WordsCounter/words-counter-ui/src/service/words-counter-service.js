import axios from "axios";

const API_COUNT_TEXT_WORDS_BY_PAGE_URL  = "http://localhost:8080/count/url";


export function countTextWordsResult( wordsCountResult ){
    if(Object.keys(wordsCountResult).length > 0){
        wordsCountResult.topWordsRankedMap = {};
    }
    return axios.post("count/text", wordsCountResult);
}

export function countByPageUrl( urlWordsCountResult ){
    if(Object.keys(urlWordsCountResult).length > 0){
        urlWordsCountResult.topWordsRankedMap = {};
    }
    return axios.post(API_COUNT_TEXT_WORDS_BY_PAGE_URL, urlWordsCountResult);
}