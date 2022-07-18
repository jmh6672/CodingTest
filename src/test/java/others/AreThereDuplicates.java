package others;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AreThereDuplicates {
    /**
     * 같은 값이 있는지 확
     * */


    public boolean areThereDuplicates(String ...chars) {
        boolean answer = false;

        HashMap<String,Integer> map = new HashMap();
        for(String c:chars){
            int cnt = map.getOrDefault(c,0)+1;
            if(cnt > 1){
                return true;
            }
            map.put(c,cnt);
        }
        return answer;
    }

    @Test
    public void areThereDuplicatesTest(){
        assertEquals(
                false,
                areThereDuplicates("1","2","3")
        );
        assertEquals(
                true,
                areThereDuplicates("1","2","2")
        );
        assertEquals(
                true,
                areThereDuplicates("a","b","c","a")
        );
    }
}
