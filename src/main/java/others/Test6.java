package others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 문제 설명
 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.

 예를 들어 array가 [1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3이라면

 array의 2번째부터 5번째까지 자르면 [5, 2, 6, 3]입니다.
 1에서 나온 배열을 정렬하면 [2, 3, 5, 6]입니다.
 2에서 나온 배열의 3번째 숫자는 5입니다.
 배열 array, [i, j, k]를 원소로 가진 2차원 배열 commands가 매개변수로 주어질 때, commands의 모든 원소에 대해 앞서 설명한 연산을 적용했을 때 나온 결과를 배열에 담아 return 하도록 solution 함수를 작성해주세요.

 제한사항
 array의 길이는 1 이상 100 이하입니다.
 array의 각 원소는 1 이상 100 이하입니다.
 commands의 길이는 1 이상 50 이하입니다.
 commands의 각 원소는 길이가 3입니다.

* */
public class Test6 {
    static final int EX_CNT = 100;

    public static void main(String[] args) throws JsonProcessingException {
        Test6 test = new Test6();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Integer> array = new ArrayList<>();
        List<List<Integer>> commands = new ArrayList<>();

        Random random = new Random();
        for(int i=0; i<EX_CNT; i++){
            array.add(random.nextInt(EX_CNT+1));
        }
        for(int i=0; i< random.nextInt(50); i++) {
            int start = random.nextInt(EX_CNT+1);
            int end = start + random.nextInt(EX_CNT-start+1);
            int position = random.nextInt(end-start+1);
            commands.add(Arrays.asList(start,end,position));
        }

        System.out.println("Test array : " + objectMapper.writeValueAsString(array));
        System.out.println("Test commands : " + objectMapper.writeValueAsString(commands));

        long beforeTime = System.currentTimeMillis();
        int[] result = test.solution1(
                array.stream().mapToInt(Integer::intValue).toArray(),
                commands.stream().map(list -> list.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new)
        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }

    public int[] solution1(int[] array, int[][] commands) {
        int[] answer = {};
        return answer;
    }
}