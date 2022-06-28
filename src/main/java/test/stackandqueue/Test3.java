package test.stackandqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 문제 설명
 트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.

 예를 들어, 트럭 2대가 올라갈 수 있고 무게를 10kg까지 견디는 다리가 있습니다. 무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.

 경과 시간	다리를 지난 트럭	다리를 건너는 트럭	대기 트럭
 0	[]	[]	[7,4,5,6]
 1~2	[]	[7]	[4,5,6]
 3	[7]	[4]	[5,6]
 4	[7]	[4,5]	[6]
 5	[7,4]	[5]	[6]
 6~7	[7,4,5]	[6]	[]
 8	[7,4,5,6]	[]	[]
 따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.

 solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다. 이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

 제한 조건
 bridge_length는 1 이상 10,000 이하입니다.
 weight는 1 이상 10,000 이하입니다.
 truck_weights의 길이는 1 이상 10,000 이하입니다.
 모든 트럭의 무게는 1 이상 weight 이하입니다.
* */
public class Test3 {
    static final int EX_CNT = 5;

    public static void main(String[] args) throws JsonProcessingException {
        Test3 test = new Test3();
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();

        int bridge_length = 1+random.nextInt(EX_CNT);
        int weight = 1+random.nextInt(EX_CNT);
        List<Integer> truck_weights = new ArrayList<>();

        for(int i=0; i<EX_CNT; i++){
            truck_weights.add(1+random.nextInt(weight));
        }

        System.out.println("Test bridge_length : " + objectMapper.writeValueAsString(bridge_length));
        System.out.println("Test weight : " + objectMapper.writeValueAsString(weight));
        System.out.println("Test truck_weights : " + objectMapper.writeValueAsString(truck_weights));


        long beforeTime = System.currentTimeMillis();
        int result = test.solution1(
                bridge_length,
                weight,
                truck_weights.stream().mapToInt(Integer::intValue).toArray()
        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }


    public class Bridge extends HashMap<Integer,Integer> {
        private int totalWeight = 0;

        //다리의 총중량을 리턴
        @Override
        public Integer put(Integer key, Integer value) {
            super.put(key, value);
            this.totalWeight += value != null ? value : 0;
            return this.totalWeight;
        }

        //다리의 총중량을 리턴
        public Integer remove(Integer key){
            Integer result = super.remove(key);
            this.totalWeight -= result != null ? result : 0;
            return this.totalWeight;
        }

        //다리의 총중량을 리턴
        public int getTotalWeight() {
            return this.totalWeight;
        }
    }

    public int solution1(int bridge_length, int weight, int[] truck_weights) {
       int answer = 0;

       Queue<Integer> readyTrucks = new LinkedList<>();
       for(int i=0; i<truck_weights.length; i++){
           readyTrucks.offer(truck_weights[i]);
       }

       Bridge bridge = new Bridge();
       bridge.put(++answer+bridge_length,readyTrucks.poll());
       while(!bridge.isEmpty()){
           bridge.remove(++answer);
           //현재 타이밍에 다리 위의 총중량과 다음에 다리를 건널 트럭의 중량을 더해서 비교
           Integer nextTruck = readyTrucks.peek();
           if(
                   nextTruck!=null
                           &&
                   bridge.getTotalWeight() + nextTruck <= weight
                           &&
                   bridge.size() < bridge_length
           ) {
               //key에 지금 건너기 시작한 트럭이 다리를 완전히 지나는 타이밍을 기록
               bridge.put(answer+bridge_length,readyTrucks.poll());
           }
       }

       return answer;
    }

}