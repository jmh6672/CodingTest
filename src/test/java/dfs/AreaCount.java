package dfs;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AreaCount {
    /**
     * [1,1,1,0,1]
     * [1,1,0,1,1]
     * [0,0,0,0,0]
     * [1,0,1,1,1]
     * [1,0,1,1,1]
     *
     * return [4,8]
     * */

    ObjectMapper objectMapper = new ObjectMapper();

    int[] dx = {0,1,0,-1};
    int[] dy = {-1,0,1,0};
    int[][] numbers;
    int N;
    boolean[][] checkd;
    int cnt;

    PriorityQueue<Integer> pq = new PriorityQueue(Comparator.reverseOrder());

    public int[] solution(int[][] numbers) {
        int[] answer = new int[2];

        this.numbers = numbers;
        this.N = numbers.length;
        this.checkd = new boolean[N][N];

        for(int y=0; y<N; y++){
            for(int x=0; x<N; x++){
                if(checkd[y][x]){
                    continue;
                }
                if(numbers[y][x]==1) {
                    cnt = 1;
                    checkd[y][x] = true;
                    recursive(x, y);
                    pq.offer(cnt);
                }
            }
        }

        answer[0] = pq.size();
        answer[1] = pq.peek();

        return answer;
    }

    void recursive(int x, int y){
        checkd[y][x] = true;

        for(int i=0; i<4; i++){
            int nextX = x+dx[i];
            int nextY = y+dy[i];

            if((nextX > -1 && nextX < N) && (nextY > -1 && nextY < N)) {
                if(!checkd[nextY][nextX] && numbers[nextY][nextX]==1){
                    cnt++;
                    recursive(nextX,nextY);
                }
            }
        }
    }


    @Test
    public void 테스트() throws JsonProcessingException {
        assertEquals(
                objectMapper.writeValueAsString(new int[]{4,6}),
                objectMapper.writeValueAsString(solution(new int[][]{
                        {1,1,1,0,1},
                        {1,1,0,1,1},
                        {0,0,0,0,0},
                        {1,0,1,1,1},
                        {1,0,1,1,1}
                }))
        );
    }
}
