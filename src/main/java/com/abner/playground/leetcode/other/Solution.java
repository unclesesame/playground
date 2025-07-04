package com.abner.playground.leetcode.other;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Solution {
    public static void main(String[] args) {

    }
}


class RecentCounter {

    Queue<Integer> queue = new ConcurrentLinkedQueue<>();


    public RecentCounter(){

    }

    public int ping(int t){
        queue.add(t);
        for(int n: queue){
            if(n < t - 3000){
                queue.poll();
            }
        }
        return queue.size();
    }
}