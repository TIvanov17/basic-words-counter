package com.springuni.wordscounter.providers;

import java.util.List;

public interface WordsProvider {

    String SPLIT_REGEX_SEPARATORS = "[ \n+=,.;:-]";
    List<String> provideWords(String input);
}
