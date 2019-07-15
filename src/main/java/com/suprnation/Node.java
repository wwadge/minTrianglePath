package com.suprnation;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.min;

class Node {
    private int value;
    private List<Integer> path = new LinkedList<>();

    private static Node of(int value){
        Node n = new Node();
        n.value = value;
        return n;
    }

    static List<Node> of(String str) {
        List<Node> result = Lists.newArrayList();
        for (String s: str.split(" ")){
            result.add(of(Integer.parseInt(s)));
        }
        return result;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    List<Integer> getPath() {
        return path;
    }

    int minOf(Node node) {
        return min(value, node.getValue());
    }
}
