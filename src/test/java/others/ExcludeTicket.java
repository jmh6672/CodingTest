package others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExcludeTicket {

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 중복된 번호는 제외하는 프로그램 작성
     * 결과는 오름차순으로 조회
     * */


    public int[] excludeTicket(int N, int[] M) {
        int[] answer = {};

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++){
            map.put(M[i], map.getOrDefault(M[i],0)+1);
        }

        List<Integer> list = new ArrayList<>();
        for(int key:map.keySet()) {
            if(map.get(key) < 2){
                list.add(key);
            }
        }
        list.sort((o1, o2) -> o1-o2);
        answer = list.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }


    @Test
    public void sameFrequencyTest() throws JsonProcessingException {
        assertEquals(
                objectMapper.writeValueAsString(new int[]{2, 4, 6}),
                objectMapper.writeValueAsString(excludeTicket(5,new int[]{4,3,3,2,6}))
        );
        assertEquals(
                objectMapper.writeValueAsString(new int[]{5}),
                objectMapper.writeValueAsString(excludeTicket(10,new int[]{1,2,3,4,5,4,3,2,1,2}))
        );
    }
}
