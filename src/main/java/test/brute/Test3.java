package test.brute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;

public class Test3 {
    static final int CASE_COUNT = 10;

    /**
     Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.

     Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.

     Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.

     제한사항
     갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
     노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
     카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.

     10	2	[4, 3]
     8	1	[3, 3]
     24	24	[8, 6]
     * */
    public static void main(String[] args) throws JsonProcessingException {
        Test3 test1 = new Test3();

        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();

        int brown = 24;
        int yellow = 24;

        System.out.println("Test brown : " + objectMapper.writeValueAsString(brown));
        System.out.println("Test yellow : " + objectMapper.writeValueAsString(yellow));

        long startTime = System.currentTimeMillis();
        int[] result = test1.solution1(
                brown,
                yellow
        );
        System.out.println(String.format("Spent Time : %s(ms)",System.currentTimeMillis()-startTime));
        System.out.println(objectMapper.writeValueAsString(result));
    }

    public int[] solution1(int brown, int yellow) {
        int[] answer = {};

        //yellow 경우의 수 만큼 반복
        for(int j=yellow; j>=1; j--){
            if(yellow % j == 0) {
                int widthYellow = j;
                int heightYellow = yellow / j;

                int half = (widthYellow+heightYellow+2);
                if( half*2 == brown){
                    answer = new int[]{widthYellow+2, heightYellow+2};
                    return answer;
                }
            }
        }


        return answer;
    }

}
