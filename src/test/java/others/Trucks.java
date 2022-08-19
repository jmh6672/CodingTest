package others;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Trucks {


   public int solution(int M, int[] load) {
       int answer = 1;

       PriorityQueue<Integer> pq = new PriorityQueue();
       for (int i=0; i<load.length; i++) {
           pq.offer(load[i]);
       }

       Deque<Integer> deque = new ArrayDeque();
       while (!pq.isEmpty()) {
           deque.add(pq.poll());
       }


       int weight = 0;
       while (!deque.isEmpty()){
           int minWeight = deque.pollFirst();

           //작은 숫자를 먼저 더한다.
           weight += minWeight;
           if(M < weight){
               answer += 1;
               weight = minWeight;
           }
           //이 후 큰 수 부터 더해본다.
           Deque<Integer> tempQueue = new ArrayDeque<>(deque);
           while (!tempQueue.isEmpty()){
               int tempWeight = weight + tempQueue.pollLast();
               if(M > tempWeight) {
                   weight = tempWeight;
                   deque.removeLast();
               }
           }
       }

       return answer;
   }


    @Test
    public void 트럭개수_테스트(){

        assertEquals(
                2,
                solution(10,new int[]{2,3,7,8})
        );
        assertEquals(
                3,
                solution(5,new int[]{2,2,2,2,2})
        );
        assertEquals(
                4,
                solution(20,new int[]{16,15,9,17,1,3})
        );
    }
}
