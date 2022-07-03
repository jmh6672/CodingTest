package dfs;


import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test2 {
    /**
     * [1,1,1,0,1]
     * [1,1,0,1,1]
     * [0,0,0,0,0]
     * [1,0,1,1,1]
     * [1,0,1,1,1]
     *
     * return [4,8]
     * */

    int[][] numbers;
    int section = 0;
    int max = 0;
    PriorityQueue<Integer> pq = new PriorityQueue();

    public int[] solution(int[][] numbers) {
        int[] answer = {};

        this.numbers = numbers;
        this.section = 0;
        this.max = 0;

        System.out.println();

        answer[0] = pq.size();
        answer[1] = pq.peek();

        return answer;
    }

    void dfs(int i, int a){

    }


    @Test
    public void 테스트(){
        assertEquals(
                solution(new int[][]{
                        {1,1,1,0,1},
                        {1,1,0,1,1},
                        {0,0,0,0,0},
                        {1,0,1,1,1},
                        {1,0,1,1,1}
                }),
                new int[]{4,6}
        );
    }
}
