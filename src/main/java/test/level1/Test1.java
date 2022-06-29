package test.level1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Random;

public class Test1 {
    static final int CASE_COUNT = 10;

    /**
     문제 설명
     배열 arr가 주어집니다. 배열 arr의 각 원소는 숫자 0부터 9까지로 이루어져 있습니다. 이때, 배열 arr에서 연속적으로 나타나는 숫자는 하나만 남기고 전부 제거하려고 합니다. 단, 제거된 후 남은 수들을 반환할 때는 배열 arr의 원소들의 순서를 유지해야 합니다. 예를 들면,

     arr = [1, 1, 3, 3, 0, 1, 1] 이면 [1, 3, 0, 1] 을 return 합니다.
     arr = [4, 4, 4, 3, 3] 이면 [4, 3] 을 return 합니다.
     배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들을 return 하는 solution 함수를 완성해 주세요.
     * */
    public static void main(String[] args) throws JsonProcessingException {
        Test1 test1 = new Test1();

        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();

        int brown = 24;
        int yellow = 24;

        System.out.println("Test brown : " + objectMapper.writeValueAsString(brown));
        System.out.println("Test yellow : " + objectMapper.writeValueAsString(yellow));

        long startTime = System.currentTimeMillis();
        int[] result = test1.solution1(
               new int[]{1,1,3,3,0,1,1}
        );
        System.out.println(String.format("Spent Time : %s(ms)",System.currentTimeMillis()-startTime));
        System.out.println(objectMapper.writeValueAsString(result));
    }

    public int[] solution1(int []arr) {
        int[] answer = {};

        answer = new int[arr.length];
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        int index = 0;
        for(int i=0; i<arr.length; i++){

            for(int j=i+1; j<arr.length; j++){
                if(arr[i]!=arr[j]){
                    answer[index] = arr[i] ;
                    index++;
                    break;
                }
            }
        }

        return answer;
    }
}
