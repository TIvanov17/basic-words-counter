package com.springuni.wordscounter.providers;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Stream;

@Component
public class TextWordsProvider implements WordsProvider{

    @Override
    public List<String> provideWords(String input) {

        return Stream.of(input.split(SPLIT_REGEX_SEPARATORS))
                .map(String::toLowerCase)
                .toList();
    }

}
