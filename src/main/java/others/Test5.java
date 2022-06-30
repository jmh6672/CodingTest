package others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**

 문제 설명
 문자열에서 일정한 간격으로 같은 문자열이 반복해서 나타난다면 이를 문자열의 주기라고 합니다. 예를 들어 문자열 "abababab"에서 문자열의 주기는 다음과 같이 세 가지가 있습니다.

 문자열 "ab"가 네 번 반복해서 나타납니다.
 문자열 "abab"가 두 번 반복해서 나타납니다.
 문자열 "abababab"가 한 번 반복해서 나타납니다.
 이때, 가능한 짧은 주기를 문자열의 주기로 정합니다. 따라서 위 문자열의 주기는 2가 됩니다.
 문자열 s가 매개변수로 주어질 때, 문자열의 주기를 return 하도록 solution 함수를 완성해주세요.

 제한사항
 s는 알파벳 소문자로만 이루어진 문자열입니다.
 s의 길이는 1 이상 1,000,000 이하입니다.
 입출력 예
 s   result
 "abababab"   2
 "abcabcabd"   9
 입출력 예 설명
 입출력 예 #1
 문제의 예시와 같습니다.

 입출력 예 #2
 "abc"가 s[3]~ s[5]에서 한번 반복해서 나타나지만, s[6]~s[8]에서는 나타나지 않으므로 가장 짧은 주기는 전체 문자열의 길이인 9가 됩니다.
* */
public class Test5 {
    static final int EX_CNT = 4;

    public static void main(String[] args) throws JsonProcessingException {
        Test5 test = new Test5();
        ObjectMapper objectMapper = new ObjectMapper();

        StringBuffer sb = new StringBuffer();

        Random random = new Random();
        for(int i=0; i<EX_CNT; i++){
            sb.append(random.ints('A','C')
                            .limit(1)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString());
        }

        System.out.println("Test s : " + objectMapper.writeValueAsString(sb.toString()));

        long beforeTime = System.currentTimeMillis();
        int result = test.solution1(
                sb.toString()
        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }

    public int solution1(String s) {
        int answer = 0;

        answer = recursive(s, 1);

        return answer;
    }

    public int recursive(String s, int cycle){
        int tryLen = s.length()/cycle;
        if(tryLen == 1) {
            return s.length();
        }

        String subStr = s.substring(0,cycle);
        for(int i=1; i<tryLen; i++){
            String nextStr = s.substring(i*cycle, (i+1)*cycle);
            if(!subStr.equals(nextStr)) {
                return recursive(s, cycle+1);
            }
        }

        return cycle;
    }
}