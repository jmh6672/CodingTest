package others;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PerOfVictory {
    /**
     * 승률은 소수부를 내림한 정수부로 표시가 됩니다. (58.893 -> 58)
     * 현재까지 진행한 게임 횟수 M, 승리 N
     *
     * 승률을 1%를 올리는게 목표일 때 최소 게임 횟수를 구하라
     *
     * 100,000,000 회 미만 가능
     * */


    public int perOfVictory(double M, double N) {
        int answer = 0;

        double winRate = winRate(M,N);
        double target = winRate + 1;

        //승률이 99이상일 때 더 이상 승률을 올 릴 수 없으므로 0으로 리턴
        if(target >= 100){
            return answer;
        }

        //승률이 1오를 떄 까지 반복
        while(winRate < target){
            answer++;
            M += 1;
            N += 1;
            winRate = winRate(M,N);
        }

        return answer;
    }

    double winRate(double M, double N){
        return Math.floor(N/M * 100);
    }


    @Test
    public void sameFrequencyTest(){
        assertEquals(
                6,
                perOfVictory(100,80)
        );
        assertEquals(
                0,
                perOfVictory(888,887)
        );
    }
}
