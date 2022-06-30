package programers.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 문제 설명
 일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다. 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다. 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.

 1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
 2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
 3. 그렇지 않으면 J를 인쇄합니다.
 예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.

 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.

 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.

 제한사항
 현재 대기목록에는 1개 이상 100개 이하의 문서가 있습니다.
 인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.
 location은 0 이상 (현재 대기목록에 있는 작업 수 - 1) 이하의 값을 가지며 대기목록의 가장 앞에 있으면 0, 두 번째에 있으면 1로 표현합니다.
* */
public class Test2 {
    static final int EX_CNT = 5;

    public static void main(String[] args) throws JsonProcessingException {
        Test2 test = new Test2();
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();

        List<Integer> priorities = new ArrayList<>();
        int location = 0;

        for(int i=0; i<EX_CNT; i++){
            priorities.add(1+random.nextInt(10));
        }
        location = random.nextInt(EX_CNT);

        System.out.println("Test progresses : " + objectMapper.writeValueAsString(priorities));
        System.out.println("Test location : " + location);


        long beforeTime = System.currentTimeMillis();
//        int result = test.solution1(
//                priorities.stream().mapToInt(Integer::intValue).toArray(),
//                location
//        );
        int result = test.solution1(
                new int[] {2, 1, 3, 2},
                2
        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }




    public int solution1(int[] priorities, int location) {
        int answer = 0;

        Queue<Map.Entry<Integer,Integer>> queue = new LinkedList<>();
        for(int i=0; i<priorities.length; i++){
            queue.offer(new AbstractMap.SimpleEntry<>(i,priorities[i]));
        }

        int cnt = 0;
        while(!queue.isEmpty()) {
            boolean print = true;
            Map.Entry<Integer,Integer> map = queue.poll();
            for(Map.Entry<Integer,Integer> entry:queue) {
                if(map.getValue() < entry.getValue()) {
                    queue.offer(map);
                    print = false;
                    break;
                }
            }
            if(print){
                cnt++;
                if(location == map.getKey()){
                    answer = cnt;
                }
            }
        }

        return answer;
    }


}