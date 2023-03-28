package com.springuni.wordscounter.result;

import java.util.HashMap;
import java.util.Map;

public class WordsCounterResult {

    private String content;
    private long totalNumberOfWords;
    private Map<String, Integer> topWordsRankedMap;

    public WordsCounterResult(){
        this.topWordsRankedMap = new HashMap<>();
    }

    public String getContent() {
        return this.content;
    }

    public long getTotalNumberOfWords() {
        return this.totalNumberOfWords;
    }

    public void setTotalNumberOfWords(long totalNumberOfWords) {
        this.totalNumberOfWords = totalNumberOfWords;
    }

    public Map<String, Integer> getTopWordsRankedMap() {
        return this.topWordsRankedMap;
    }

    public void setTopWordsRankedMap(Map<String, Integer> topWordsRankedMap) {
        this.topWordsRankedMap = topWordsRankedMap;
    }
}
