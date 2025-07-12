package com.abner.playground.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueSolution {
    public static void main(String[] args) {

    }

    //No.649 Dota2参议院
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        //if(n == 1) return senate;

        //定义两个队列分别存储R和D

        Queue<Integer> rQueue = new LinkedList<>();
        Queue<Integer> dQueue = new LinkedList<>();

        //首次循环string把相应阵营入队
        for(int i=0; i<n; i++){
            if(senate.charAt(i)  == 'R'){
                rQueue.offer(i);
            }else{
                dQueue.offer(i);
            }
        }


        //每次比较两个队列的首元素, 序号小的，加n后再次放入队列，另一个queue直接出队
        while(!rQueue.isEmpty() && !dQueue.isEmpty()){
            int rFront = rQueue.peek();
            int dFront = dQueue.peek();
            if(rFront < dFront){
                dQueue.poll();
                rQueue.poll();
                rQueue.offer(rFront+n);
            }else{
                rQueue.poll();
                dQueue.poll();
                dQueue.offer(dFront+n);
            }
        }

        return rQueue.isEmpty()? "Dire":"Radiant";
    }
}
