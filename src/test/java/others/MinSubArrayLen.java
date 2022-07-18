package others;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MinSubArrayLen {
    /**
     * 두 개의 양수를 입력했을 떄, 자릿수가 같은지 확인해라
     * */


    public boolean minSubArrayLen(int array[], int target) {
        boolean answer = false;

        return answer;
    }


    @Test
    public void minSubArrayLenTest(){
        assertEquals(
                7,
                minSubArrayLen(new int[]{2,3,1,2,4,3},7)
        );
        assertEquals(
                9,
                minSubArrayLen(new int[]{2,1,6,5,4},9)
        );
        assertEquals(
                52,
                minSubArrayLen(new int[]{3,1,7,11,2,9,8,21,62,33,19},52)
        );
        assertEquals(
                39,
                minSubArrayLen(new int[]{1,4,16,22,5,7,8,9,10},39)
        );
        assertEquals(
                55,
                minSubArrayLen(new int[]{1,4,16,22,5,7,8,9,10},55)
        );
        assertEquals(
                11,
                minSubArrayLen(new int[]{4,3,3,8,1,2,3},11)
        );
        assertEquals(
                95,
                minSubArrayLen(new int[]{1,4,16,22,5,7,8,9,10},95)
        );
    }
}
