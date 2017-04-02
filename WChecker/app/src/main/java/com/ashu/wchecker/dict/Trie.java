package com.ashu.wchecker.dict;

import com.ashu.wchecker.model.Dictionary;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Trie implements Dictionary {

    Node rootNode;
    public Trie(){
        rootNode = new Node("", false);
    }

    @Override
    public void addWord(String word) {
        Node currentNode = rootNode;
        for (int i=0; i<word.length(); i++){
            char currentChar = word.charAt(i);
            Node nextNode = currentNode.getChild(currentChar);
            if (nextNode == null){
                nextNode = new Node(currentNode.name + currentChar, false);
                currentNode.addChild(currentChar, nextNode);
            }
            currentNode = nextNode;
        }
        currentNode.isWord = true;
    }

    public boolean containsWord(String word) {
        Node currentNode = rootNode;
        for (int i=0; i<word.length(); i++) {
            char currentChar = word.charAt(i);
            Node nextNode = currentNode.getChild(currentChar);
            if (nextNode == null) {
                return false;
            }
            currentNode = nextNode;
        }
        return currentNode.isWord;

    }


    @Override
    public List<String> getMatchingWords(String word, int listSize) {
        //get prefix node for input word.
        Node currentNode = rootNode;
        for (int i=0; i<word.length(); i++){
            char currentChar = word.charAt(i);
            Node nextNode = currentNode.getChild(currentChar);
            if (nextNode == null){
                break;
            }
            currentNode = nextNode;
        }
        List<String> resultList = new LinkedList<>();
        //now we'll do the BFS traversal starting from currentNode.
        Queue<Node> traversalQueue = new LinkedList<>();
        traversalQueue.add(currentNode);
        while (!traversalQueue.isEmpty() && listSize != resultList.size()){
            Node headNode = traversalQueue.remove();
            if (headNode.isWord){
                resultList.add(headNode.name);
            }

            for (Node childNode : headNode.children.values()){
                traversalQueue.add(childNode);
            }
        }

        return resultList;
    }
}
