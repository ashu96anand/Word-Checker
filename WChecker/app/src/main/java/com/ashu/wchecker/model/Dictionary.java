package com.ashu.wchecker.model;

import java.util.List;


public interface Dictionary {

    public boolean containsWord(String inp);
    public void addWord(String word);
    public List<String> getMatchingWords(String inp, int listSize);
}
