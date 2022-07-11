package others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


/**
 # 커피

 커피숍에서 한 번에 만들수 있는 커피 수량과 커피 배출구의 수량 k 가 있고, 주문순서대로 커피를 제조하는 시간이 있는 배열 coffee_times 가 있을 때, 커피가 나오는 주문번호를 순서대로 나열한 배열을 return 하라

 - 주문번호는 1번 부터 시작한다.
 - 동시에 k개 까지의 커피만 만들 수 있다.
 - 한 번에 커피는 k개 까지만 배출된다.
 - 커피가 동시에 완성되면 주문순서대로 내보낸다.

 > ex) k = 2,  coffee_times = [2,4,1,1,3]
 >
 > ```
 > => 처음에 주문번호 1,2번 커피를 만들기 시작하면 1번이 먼저 완성된다. [1]
 > 	이 후, 주문번호 2번은 제조중이고 주문번호 3번이 먼저 배출된다. [1,3]
 > 	이 후, 주문번호 2번과 4번이 동시에 완성되고 배출된다. [1,3,2,4]
 > 	이 후, 주문번호 5번을 만들고 배출한다. [1,3,2,4,5]
 > ```

* */
public class CoffeeOrders {
    static final int EX_CNT = 5;

    public static void main(String[] args) throws JsonProcessingException {
        CoffeeOrders test = new CoffeeOrders();
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();


        List<Integer> coffee_times = new ArrayList<>();
        int k = random.nextInt(3)+1;

        for(int i=0; i<EX_CNT; i++){
            coffee_times.add(random.nextInt(10)+1);
        }

        System.out.println("Test k : " + objectMapper.writeValueAsString(k));
        System.out.println("Test coffee_times : " + objectMapper.writeValueAsString(coffee_times));

        long beforeTime = System.currentTimeMillis();
        int[] result = test.solution(
                k,
                coffee_times.stream().mapToInt(Integer::intValue).toArray()
        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }

    public class CoffeeOrder {
        private int order;
        private int endTime;

        CoffeeOrder(int order, int end){
            this.order = order;
            this.endTime = end;
        }
    }

    public int[] solution(int k, int[] coffee_times) {
        int[] answer = {};

        List<Integer> endOrders = new ArrayList();


        PriorityQueue<CoffeeOrder> readyCoffee = new PriorityQueue<>((a, b) -> a.endTime - b.endTime);
        int nextOrder = 1;
        int time = 0;
        do {

            while(readyCoffee.size() < k && nextOrder <= coffee_times.length) {
                readyCoffee.offer(new CoffeeOrder(nextOrder, time + coffee_times[nextOrder-1]));
                nextOrder++;
            }

            CoffeeOrder endCoffee = readyCoffee.poll();
            time = endCoffee.endTime;
            endOrders.add(endCoffee.order);

        }while (readyCoffee.size() > 0);

        answer = endOrders.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }


}