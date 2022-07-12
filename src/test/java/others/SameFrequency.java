package others;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SameFrequency {
    /**
     * 두 개의 양수를 입력했을 떄, 자릿수가 같은지 확인해라
     * */


    public boolean sameFrequency(int numX, int numY) {
        boolean answer = true;

        String strX = String.valueOf(numX);
        String strY = String.valueOf(numY);

        if(strX.length() != strY.length()){
            return false;
        }

        HashMap<Integer,Integer> map = new HashMap();
        for(int num:strX.toCharArray()){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        for(int num:strY.toCharArray()){
            if(map.getOrDefault(num,0)<1){
                return false;
            }
            map.put(num,map.getOrDefault(num,0)-1);
        }

        return answer;
    }


    @Test
    public void sameFrequencyTest(){
        assertEquals(
                true,
                sameFrequency(182,281)
        );
        assertEquals(
                false,
                sameFrequency(34,14)
        );
        assertEquals(
                true,
                sameFrequency(3589578,5879385)
        );
        assertEquals(
                false,
                sameFrequency(22,222)
        );
    }
}
