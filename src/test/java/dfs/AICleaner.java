package dfs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AICleaner {
    /**
     * */

    int[] dx = {0,1,0,-1};
    int[] dy = {-1,0,1,0};
    int[][] answer;
    int count;
    int N;


    public int[][] solution(int n, boolean horizontal) {
        this.answer = new int[n][n];
        this.count = 0;
        this.N = n;

        recursive(0,0, (horizontal ? 0 : 1), horizontal);

        return answer;
    }
    void recursive(int x, int y, int direction, boolean right){
        answer[y][x] = ++count;

        for(int i=1; i<=4; i++){
            // 오른쪽으로 먼저 돌 떄 +1 , 왼쪽으로 먼저 돌 떄 +3
            int d = rotation(direction, right);
            int nextX = x+dx[d];
            int nextY = y+dy[d];

            if((nextX > -1 && nextX < N) && (nextY > -1 && nextY < N)){
                if(answer[nextY][nextX] == 0) {
                    if(i==2){
                        right = !right;
                    }
                    recursive(nextX, nextY, d, right);
                    break;
                }
            }
            // 처음 회전하는 방향에서 진행방향이 틀리면 반대로 돌아본다.
            direction = d;
            if(i==1){
                right = !right;
            }
        }
    }

    int rotation(int direction, boolean right){
        return (direction + (right ? 1 : 3)) % 4;
    }

    @Test
    public void 청소기문제_테스트() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        assertEquals(
                objectMapper.writeValueAsString(new int[][]{{1,2,9,10},{4,3,8,11},{5,6,7,12},{16,15,14,13}}),
                objectMapper.writeValueAsString(solution(4, true))
        );
//        assertEquals(
//                2+1+2,
//                solution(5, false)
//        );
    }
}
