package programers.heap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


/**
 문제 설명
 이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.

 명령어	수신 탑(높이)
 I 숫자	큐에 주어진 숫자를 삽입합니다.
 D 1	큐에서 최댓값을 삭제합니다.
 D -1	큐에서 최솟값을 삭제합니다.
 이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.

 제한사항
 operations는 길이가 1 이상 1,000,000 이하인 문자열 배열입니다.
 operations의 원소는 큐가 수행할 연산을 나타냅니다.
 원소는 “명령어 데이터” 형식으로 주어집니다.- 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.
 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
* */
public class Heap {
    static final int EX_CNT = 10;

    public static void main(String[] args) throws JsonProcessingException {
        Heap test = new Heap();
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();

        String[] insertDelete = {"I","D"};
        String[] operations = new String[EX_CNT];

        for(int i=0; i<EX_CNT; i++){
            operations[i]=insertDelete[random.nextInt(1)]+random.nextInt(EX_CNT);
        }

        System.out.println("Test operations : " + objectMapper.writeValueAsString(operations));

        long beforeTime = System.currentTimeMillis();
        int[] result = test.solution1(
                operations
        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }

    public int[] solution1(String[] operations) {
        int[] answer = {};

        //최소 힙, 최대 힙
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());

        for (String op : operations) {
            StringTokenizer st = new StringTokenizer(op);
            String judge = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            //빈 큐에 데이터를 삭제 요청 경우 연산 무시
            if (pq.size() < 1 && judge.equals("D"))
                continue;

            //삽입 시 최소 힙, 최대 힙에 value 넣기
            if (judge.equals("I")) {
                pq.offer(value);
                maxPq.offer(value);
                continue;
            }

            //나머지 경우는 D이면서 value값이 1인지 -1인지 이므로
            //0보다 작은 경우 최소 힙에서 poll후 최대힙에서 해당 원소 삭제
            if(value < 0) {
                int min = pq.poll();
                maxPq.remove(min);
                continue;
            }

            //최대 힙에서 poll후 최소힙에서 해당 원소 삭제
            int max = maxPq.poll();
            pq.remove(max);
        }
        if(pq.size() > 0 ) {
            answer[0] = maxPq.poll();
            answer[1] = pq.poll();
        }
        return answer;
    }


}