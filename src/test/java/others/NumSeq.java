package others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NumSeq {


    public int solution(int n) {
        int answer = -1;

        long temp = n;
        long target = 0;
        long numLen = 1;
        long decimalCnt = 9;

        while (temp > numLen * decimalCnt) {
            temp -= (numLen * decimalCnt);
            target += decimalCnt;

            numLen++;
            decimalCnt *= 10;
        }


        target = (target+1) + (temp-1) / numLen;
        int index = (int) ((temp-1) % numLen);
        answer = String.valueOf(target).charAt(index) - '0';


        return answer;
    }


    @Test
    public void 이어쓰기2_테스트(){
//        assertEquals(
//                1,
//                solution(1)
//        );
//        assertEquals(
//                5,
//                solution(5)
//        );
        assertEquals(
                2,
                solution(15)
        );
        assertEquals(
                3,
                solution(17)
        );
    }
}
