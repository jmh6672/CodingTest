package others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ThreePow {


    public long solution(long n) {
        long answer = 0;

        int count = 0;
        while (n > 0) {
            //2의 제곱의 갯수만큼 수열이 나올 수 있다. 그래서 n을 이진수로 계산해본다.
            if ((n & 1) == 1) {
                answer += pow(3, count);
            }
            count++;
            n = (n >> 1);
        }

        return answer;
    }

    private static long pow(long a, long b) {
        if(b == 0) return 1;
        if(b == 1) return a;

        long temp = pow(a, b / 2);

        if(b % 2 == 0) {
            return (temp * temp);
        } else {
            return ((temp * temp) * a);
        }
    }


    @Test
    public void 거듭제곱수의n번_테스트(){

        //27.28,30,31,40,41,43,44
        //81,82.84,85,90,91,93,94

        //0==1 ,1==2, 2==4, 4==8
        assertEquals(
                1,
                solution(1)
        );
        assertEquals(
                3,//3
                solution(2)
        );
        assertEquals(
                9,//9
                solution(4)
        );
        assertEquals(
                31,//11
                solution(11)
        );
    }
}
