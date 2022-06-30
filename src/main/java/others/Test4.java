package others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


/**
 문제 설명
 유저가 주문한 음식 데이터를 이용해 음식을 가장 다양하게 주문한 유저는 누구인지 알아보려 합니다. 유저는 주문 한 번당 음식 여러 종류를 주문하며, 같은 음식을 여러 번 주문할 수도 있습니다.

 예를 들어 음식 주문 데이터가 다음과 같은 경우

 ["alex pizza pasta", "alex pizza pizza", "alex noodle", "bob pasta", "bob noodle sandwich pasta", "bob steak noodle"]

 "alex"는 "pizza", "pasta", "noodle"을 주문했습니다.
 "bob"은 "pasta", "noodle", "sandwich", "steak"를 주문했습니다.
 따라서 "bob"이 주문한 음식이 총 네 종류로 가장 많습니다.

 유저 ID와 각 유저가 주문한 음식이 문자열 형태로 들어있는 배열 orders가 매개변수로 주어질 때, 가장 많은 종류의 음식을 주문한 유저의 아이디를 배열에 담아 return 하도록 solution 함수를 완성해주세요. 만약, 그런 유저가 여러 명이면 해당 유저들을 오름차순으로 정렬해 return 하면 됩니다.

 제한사항
 1 ≤ orders의 길이 ≤ 200,000
 orders의 원소는 음식 주문 데이터가 "유저ID 음식1 음식2 ..." 순서로 들어있습니다.
 유저는 한 번에 최대 5개까지 음식을 주문합니다.
 유저 ID와 음식 이름은 공백(스페이스 바) 하나로 구분해서 주어집니다.
 음식과 음식도 공백(스페이스 바) 하나로 구분해서 주어집니다.
 유저 ID와 음식 이름은 길이가 1 이상 10 이하인 문자열이며, 알파벳 소문자로만 이루어져 있습니다.
 입출력 예
 orders   result
 ["alex pizza pasta", "alex pizza pizza", "alex noodle", "bob pasta", "bob noodle sandwich pasta", "bob steak noodle"]   ["bob"]
 ["alex pizza pasta steak", "bob noodle sandwich pasta", "choi pizza sandwich pizza", "alex pizza pasta steak"]   ["alex", "bob"]
 입출력 예 설명
 입출력 예 #1
 문제 예시와 같습니다.

 입출력 예 #2

 "alex"와 "bob"은 음식 세 종류를 주문했으며, "choi"는 두 종류를 주문했습니다. 따라서 오름차순 정렬하여 ["alex", "bob"]을 return하면 됩니다.

* */
public class Test4 {
    static final int EX_CNT = 10;

    public static void main(String[] args) throws JsonProcessingException {
        Test4 test = new Test4();
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();

        List<String> orders = new ArrayList<>();

        for(int i=0; i<EX_CNT; i++){
            String user = random.ints('A','D')
                            .limit(1)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();

            for(int j=0; j < (random.nextInt(5)+1); j++) {
                user = user+" "+random.ints('a', 'z')
                        .limit(1)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
            }

            orders.add(user);
        }

        System.out.println("Test coffee_times : " + objectMapper.writeValueAsString(orders));

        long beforeTime = System.currentTimeMillis();
        String[] result = test.solution(
                orders.toArray(String[]::new)
        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }

    public String[] solution(String[] orders) {
        String[] answer = {};

        int max = 0;
        HashMap<String,HashSet<String>> userMap = new HashMap<>();
        for (String order:orders){
            String[] split = order.split(" ");

            String userName = split[0];
            HashSet<String> value = userMap.getOrDefault(userName,new HashSet<>());
            for(int i=1; i< split.length; i++){
                value.add(split[i]);
            }

            userMap.put(userName,value);

            max = Math.max(max,value.size());
        }

        int finalMax = max;
        answer = userMap.keySet().stream()
                .filter(userName -> finalMax == userMap.get(userName).size())
                .toArray(String[]::new);

        return answer;
    }


}