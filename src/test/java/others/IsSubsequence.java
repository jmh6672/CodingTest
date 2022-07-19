package others;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IsSubsequence {
    /**
     * 문자열을 입력했을 때 문자가 순서대로 모두 포함되는지 확인
     * */


    public boolean isSubsequence(String seq, String str) {
        boolean answer = false;


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
