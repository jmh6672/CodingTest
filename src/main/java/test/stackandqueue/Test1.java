package test.stackandqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 문제 설명
 프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.

 또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.

 제한 사항
 작업의 개수(progresses, speeds 배열의 길이)는 100개 이하입니다.
 작업 진도는 100 미만의 자연수입니다.
 작업 속도는 100 이하의 자연수입니다.
 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.

* */
public class Test1 {
    static final int EX_CNT = 10;

    public static void main(String[] args) throws JsonProcessingException {
        Test1 test = new Test1();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Integer> progresses = new ArrayList<>();
        List<Integer> speeds = new ArrayList<>();

        Random random = new Random();
        for(int i=0; i<EX_CNT; i++){
            progresses.add(random.nextInt(100+1));
            speeds.add(1+random.nextInt(100));
        }

        System.out.println("Test progresses : " + objectMapper.writeValueAsString(progresses));
        System.out.println("Test speeds : " + objectMapper.writeValueAsString(speeds));

        long beforeTime = System.currentTimeMillis();
        int[] result = test.solution2(
                progresses.stream().mapToInt(Integer::intValue).toArray(),
                speeds.stream().mapToInt(Integer::intValue).toArray()
        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }

    public int[] solution1(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        //완료 일을 큐에 넣는다
        Queue<Integer> deploys = new LinkedList<>();
        for(int i=0; i<progresses.length; i++){
            deploys.offer( (int)Math.ceil((100.0-progresses[i]) / (0.0+speeds[i])) );
        }

        while (!deploys.isEmpty()) {
            int deployCnt = 1;
            int day = deploys.poll();

            //완료 큐 컬렉션을 돌면서 이전의 배포 일자보다 작을경우 배포 카운터 증가
            while (!deploys.isEmpty() && deploys.peek() <= day) {
                deployCnt++;
                deploys.poll();
            }
            answer.add(deployCnt);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] solution2(int[] progresses, int[] speeds) {
        return new int[1];
    }

}