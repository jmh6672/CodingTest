package others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


/**
 # 랭킹 페이지

 랭킹페이지 1의 표기 수량 k 가 있고, "{userName} {score}" 형식의 문자열이 나열된 배열 user_scores 가 있을 때, 랭킹 페이지가 1의 랭킹이 변하는 횟수를 return 하라

 - 랭킹페이지에 점수 순으로 저장된다.
 - 같은 유저가 이전에 기록한 점수 보다 낮은 점수를 기록했을 때는 무시한다.
 - 저장된 순위의 점수와 동일한 점수를 기록했을 때는 이전에 저장된 순위가 우선이다.

 > ex) k = 3,  user_scores = ["A 100", "B 200", "C 120", "D 110", "B 160", "A 300"]
 >
 > ```
 > => 랭킹페이지 표기 수량은 3이므로 처음 3개의 유저가 순위대로 저장된다. ["B","C","A"]
 > 	이 후, D 유저가 110점을 기록했으므로 랭킹페이지가 1회 변동된다. ["B","C","D"]
 > 	이 후, B 유저가 160점을 기록하여 2위보다 높은 점수지만 자신의 이전 기록보다 낮으므로 랭킹 페이지 변동은 없다.
 > 	이 후, A 유저가 300점을 기록하여 랭킹페이지가 1회 변동된다. ["A","B","C"]
 > 	랭킹페이지는 총 5회 변동되었다.
 > ```
* */
public class Test2 {
    static final int EX_CNT = 5;

    public static void main(String[] args) throws JsonProcessingException {
        Test2 test = new Test2();
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();


        List<String> user_scores = new ArrayList<>();
        Integer k = random.nextInt(EX_CNT)+1;

        for(int i=0; i<EX_CNT; i++){
            String user = random.ints('A','D')
                            .limit(1)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();

            user_scores.add(user+" "+random.nextInt(100)+1);
        }

        System.out.println("Test k : " + objectMapper.writeValueAsString(k));
        System.out.println("Test user_scores : " + objectMapper.writeValueAsString(user_scores));

        long beforeTime = System.currentTimeMillis();
        int result = test.solution1(
                k,
                user_scores.toArray(String[]::new)
        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }

    public int solution1(int k, String[] user_scores) {
        int answer = 0;

        HashMap<String,Integer> maxScoreMap = new HashMap<>();
        List<String> rankList = new ArrayList<>(k){{
            add("");
        }};

        for(String userScore:user_scores) {
            String[] split = userScore.split(" ");
            String userId = split[0];
            int score = Integer.parseInt(split[1]);

            //user의 개인기록이 갱신되었을 때만 수행한다.
            if(score > maxScoreMap.getOrDefault(userId,0)){
                maxScoreMap.put(userId,score);

                for(int i=0; i<k; i++){
                    String ranker = rankList.get(i);
                    if (!ranker.equals(userId) && maxScoreMap.getOrDefault(ranker,0) < score) {
                        rankList.add(i, userId);
                        answer++;
                        break;
                    }
                }

            }
        }


        return answer;
    }


}