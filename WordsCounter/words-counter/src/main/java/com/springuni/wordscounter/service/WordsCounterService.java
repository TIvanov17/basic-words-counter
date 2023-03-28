package com.springuni.wordscounter.service;

import com.springuni.wordscounter.result.WordsCounterResult;
import com.springuni.wordscounter.providers.TextWordsProvider;
import com.springuni.wordscounter.providers.UrlWordsProvider;
import com.springuni.wordscounter.providers.WordsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordsCounterService {

    private final List<WordsProvider> wordsProviders;

    private final int numberOfTopWords;
    private final List<String> commonWordsCollection;

    @Autowired
    public WordsCounterService(List<WordsProvider> wordsProviders) {
        this.wordsProviders = wordsProviders;
        this.numberOfTopWords = 10;
        this.commonWordsCollection =  List.of(
                "","the","of","and","a","to","in","is", "you","that","it","he","was",
                    "for","on","are","as","with", "his","they","at","be", "this", "not",
                    "have","from","or","had", "by","but","what","were","we","when","your",
                    "can","said","there","an", "each","which","she","do","how", "their","if",
                    "will","up","other","out","many","then","them", "these",
                    "so","her","would","him","into", "has","more","no","way","could",
                    "my","than", "who","now","did","get","come",
                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "^", "#", "@", "$", "*"
        );
    }

    public WordsCounterResult countWords(WordsCounterResult wordsCounterResult) {

        Optional<WordsProvider> textWordsProvider = wordsProviders.stream()
                .filter(wordsProvider -> wordsProvider instanceof TextWordsProvider)
                .findFirst();

        if(textWordsProvider.isEmpty()){
            return wordsCounterResult;
        }

        return this.countWordsProcess(textWordsProvider.get(), wordsCounterResult);
    }

    public WordsCounterResult countUrlWords(WordsCounterResult wordsCounterResult) {

        WordsProvider urlWordsProviderOptional = wordsProviders.stream()
                .filter(wordsProvider -> wordsProvider instanceof UrlWordsProvider)
                .findFirst().get();

        return this.countWordsProcess(urlWordsProviderOptional, wordsCounterResult);
    }

    private WordsCounterResult countWordsProcess(WordsProvider wordsProvider,
                                                 WordsCounterResult wordsCounterResult){

        List<String> allWords =  wordsProvider.provideWords(wordsCounterResult.getContent()).stream()
                .filter( w -> !w.equals(""))
                .toList();

        wordsCounterResult.setTotalNumberOfWords(allWords.size());

        allWords.stream()
                .filter( word -> !this.commonWordsCollection.contains(word))
                .forEach( word -> wordsCounterResult.getTopWordsRankedMap()
                        .merge(word, 1, Integer::sum)
                );

        Map<String, Integer> timeAppears = wordsCounterResult.getTopWordsRankedMap().entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(this.numberOfTopWords)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        wordsCounterResult.setTopWordsRankedMap(timeAppears);

        return wordsCounterResult;
    }
}