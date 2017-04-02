package com.ashu.wchecker.dict;

import java.util.HashMap;

/**
 * Created by ashu on 30/08/16.
 */
class Node{

    boolean isWord;
    String name;
    HashMap<Character, Node> children;

    public Node(String naam, boolean wordHai){
        name = naam;
        isWord = wordHai;
        children = new HashMap<>();
    }

    public Node getChild(char child){
        Node value = children.get(child);
        return value;
    }

    public void addChild(Character c, Node n){
        children.put(c, n);
    }
}
