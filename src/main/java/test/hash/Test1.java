package test.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Test1 {
    static final int CASE_COUNT = 100000;

    /**
     * 문제 설명
     * 수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
     *
     * 마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
     *
     * 제한사항
     * 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
     * completion의 길이는 participant의 길이보다 1 작습니다.
     * 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
     * 참가자 중에는 동명이인이 있을 수 있습니다.
     * */
    public static void main(String[] args) {
        Test1 test1 = new Test1();

        List<String> participant = new ArrayList<>();
        List<String> completion = new ArrayList<>();

        Random random = new Random();
        for(int i=0; i<CASE_COUNT; i++){
            String name = random.ints('a','z')
                    .limit(20)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            participant.add(name);
        }
        completion.addAll(participant);
        completion.remove(random.nextInt(participant.size()));

        long startTime = System.currentTimeMillis();
        String result = test1.solution1(
                participant.toArray(new String[participant.size()]),
                completion.toArray(new String[completion.size()])
        );
        System.out.println(String.format("Spent Time : %s(ms)",System.currentTimeMillis()-startTime));
        System.out.println(result);
    }

    public String solution1(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String,Integer> map = new HashMap<>();
        for(String name:participant) {
            if (name.matches("^[a-z]{1,20}")) {
                map.put(name, map.getOrDefault(name, 0) + 1);
            } else{
                System.out.println(String.format("[%s] is wrong name formmat",name));
            }
        }
        for(String name:completion) {
            if (map.containsKey(name)){
                map.put(name, map.get(name) - 1);
            } else {
                System.out.println(String.format("[%s] was not exist in participant list",name));
            }
        }
        for(String name:participant){
            if(map.get(name) > 0){
                answer = name;
            }
        }

        return answer;
    }

}
