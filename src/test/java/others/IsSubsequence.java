package others;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IsSubsequence {
    /**
     * 두 개의 양수를 입력했을 떄, 자릿수가 같은지 확인해라
     * */


    public boolean isSubsequence(String seq, String str) {
        boolean answer = false;

        PriorityQueue<Character> pq = new PriorityQueue();
        for(Character c:str.toCharArray()) {
            if(pq.peek() == c){
                pq.poll();
            }
        }

        if(pq.size()<1){
            answer = true;
        }


        return answer;
    }


    @Test
    public void isSubsequenceTest(){
        assertEquals(
                true,
                isSubsequence("hello","hello world")
        );
        assertEquals(
                true,
                isSubsequence("sing","string")

        );
        assertEquals(
                true,
                isSubsequence("abc","abracadanbra")
        );
        assertEquals(
                true,
                isSubsequence("abc","acb")
        );
    }
}
