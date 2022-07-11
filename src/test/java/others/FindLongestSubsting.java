package others;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FindLongestSubsting {
    /**
     * ""
     * "rithmschool"    hh oo
     * "thisisawesome"
     * "thecatinthehat"
     * "bbbbbb"
     * "longestsubstring"
     * "thisishowwedoit"
     * */


    public int findLongestSubsting(String in) {
        int answer = 0;

        int start = 0;
        HashMap<Integer, Integer> seen = new HashMap();

        for(int i=0; i<in.length(); i++){
            int c = in.charAt(i);
            start = Math.max(start,seen.getOrDefault(c,0));

            answer = Math.max(answer,i-start+1);

            seen.put(c,i+1);
        }

        return answer;
    }


    @Test
    public void findLongestSubsting_테스트(){
        assertEquals(
                0,
                findLongestSubsting("")
        );
        assertEquals(
                7,
                findLongestSubsting("rithmschool")
        );
        assertEquals(
                6,
                findLongestSubsting("thisisawesome")
        );
        assertEquals(
                7,
                findLongestSubsting("thecatinthehat")
        );
        assertEquals(
                1,
                findLongestSubsting("bbbbbb")
        );
        assertEquals(
                8,
                findLongestSubsting("longestsubstring")
        );
        assertEquals(
                6,
                findLongestSubsting("thisishowwedoit")
        );
    }
}
