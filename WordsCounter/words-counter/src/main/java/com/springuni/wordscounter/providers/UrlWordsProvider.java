package com.springuni.wordscounter.providers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Component
public class UrlWordsProvider implements WordsProvider{

    @Override
    public List<String> provideWords(String input) {
        try {
            Document document = Jsoup.connect(input).get();
            String extractedText = document.body().text();
            return Stream.of(extractedText.split(SPLIT_REGEX_SEPARATORS))
                    .map(String::toLowerCase)
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
