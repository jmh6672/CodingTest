import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test1 {
    /**
     * [2,1,3]
     * [1,2,3]
     * [3,2,3,6,4,5]
     * [1,0,1,1,1]
     * [1,0,1,1,1]
     *
     * */


    public int solution(int[] grade) {
        int answer = 0;

        for(int i=0; i<grade.length; i++){
            // 현재 점수 보다 낮은 점수가 있는지 찾는다.
            int min = grade[i];
            for(int j=i+1; j<grade.length; j++){
                if(min > grade[j]){
                    min = grade[j];
                }
            }

            if(min < grade[i]){
                answer += (grade[i]-min);
            }

        }

        return answer;
    }


    @Test
    public void 테스트(){
        assertEquals(
                2+1+2,
                solution(new int[]{3,2,3,1,4,5})
        );
    }
}
