package others;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AveragePair {
    /**
     * 두 개의 양수를 입력했을 떄, 자릿수가 같은지 확인해라
     * */


    public boolean averagePair(int[] numbers, double avg) {
        boolean answer = false;

        for(int i=1; i<numbers.length; i++){
            double totalAvg = (numbers[i-1] + numbers[i]) / 2.0;
            if(totalAvg >= avg){
                return true;
            }
        }

        return answer;
    }


    @Test
    public void averagePairTest(){
        assertEquals(
                true,
                averagePair(new int[]{1, 2, 3},2.5)
        );
        assertEquals(
                true,
                averagePair(new int[]{1,3,3,5,6,7,10,12,19},8)

        );
        assertEquals(
                false,
                averagePair(new int[]{-1,0,3,4,5,6},4.1)
        );
        assertEquals(
                false,
                averagePair(new int[]{},4)
        );
    }
}
