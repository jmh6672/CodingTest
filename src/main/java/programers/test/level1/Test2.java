package programers.test.level1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

public class Test2 {
    static final int CASE_COUNT = 10;

    /**
     문제 설명
     자연수 n을 뒤집어 각 자리 숫자를 원소로 가지는 배열 형태로 리턴해주세요. 예를들어 n이 12345이면 [5,4,3,2,1]을 리턴합니다.
     * */
    public static void main(String[] args) throws JsonProcessingException {
        Test2 test1 = new Test2();

        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();

        int brown = 24;
        int yellow = 24;

        System.out.println("Test brown : " + objectMapper.writeValueAsString(brown));
        System.out.println("Test yellow : " + objectMapper.writeValueAsString(yellow));

        long startTime = System.currentTimeMillis();
        int[] result = test1.solution1(
                12345
        );
        System.out.println(String.format("Spent Time : %s(ms)",System.currentTimeMillis()-startTime));
        System.out.println(objectMapper.writeValueAsString(result));
    }

    public int[] solution1(long n) {
        int[] answer = {};

        int index=0;
        String str = String.valueOf(n);
        answer = new int[str.length()];
        for(int i=str.length()-1; i>=0; i--){
            answer[index]=str.charAt(i)-'0';
            index++;
        }

        return answer;
    }
}
