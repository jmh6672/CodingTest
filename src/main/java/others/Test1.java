package others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


/**
 이벤트 기간 k 가 있고, 일자별로 방문자 수가 기록된 배열 accessors 가 있을 때 이벤트기간 동안 가장 많은 접속자를 return 하라

 >  ex) k = 3,  accessors = [4,2,3,6,1,1]
 >
 >  ```
 >  => 이벤트 기간이 3일 이므로,  4+2+3 = 9,  2+3+6 = 11,  3+6+1 = 10,  6+1+1 = 8 의 결과가 나온다.
 >  	이벤트 기간 동안 가장 많은 접속자 수 는 11 이 된다.
 >  ```
* */
public class Test1 {
    static final int EX_CNT = 100000;

    public static void main(String[] args) throws JsonProcessingException {
        Test1 test = new Test1();
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();


        List<Integer> accessors = new ArrayList<>();
        Integer k = random.nextInt(EX_CNT)+1;

        for(int i=0; i<EX_CNT; i++){
            accessors.add(random.nextInt(10)+1);
        }

        System.out.println("Test k : " + objectMapper.writeValueAsString(k));
        System.out.println("Test accessors : " + objectMapper.writeValueAsString(accessors));

        long beforeTime = System.currentTimeMillis();
        int result = test.solution1(
                k,
                accessors.stream().mapToInt(Integer::intValue).toArray()

        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }

    public int solution1(int k, int[] accessors) {
        int answer = 0;

        int len = accessors.length - k+1;

        int sum;
        for(int i=0; i<len; i++){
            sum = 0;
            for(int j=i; j<k+i; j++){
                sum += accessors[j];
            }
            if(sum > answer){
                answer = sum;
            }
        }

        return answer;
    }

}