package test.brute;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test1 {
    static final int CASE_COUNT = 10000;

    /**
     문제 설명
     n명이 입국심사를 위해 줄을 서서 기다리고 있습니다. 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다.

     처음에 모든 심사대는 비어있습니다. 한 심사대에서는 동시에 한 명만 심사를 할 수 있습니다. 가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있습니다. 하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.

     모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶습니다.

     입국심사를 기다리는 사람 수 n, 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times가 매개변수로 주어질 때, 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하도록 solution 함수를 작성해주세요.

     제한사항
     입국심사를 기다리는 사람은 1명 이상 1,000,000,000명 이하입니다.
     각 심사관이 한 명을 심사하는데 걸리는 시간은 1분 이상 1,000,000,000분 이하입니다.
     심사관은 1명 이상 100,000명 이하입니다.
     * */
    public static void main(String[] args) {
        Test1 test1 = new Test1();

        List<Integer> answer = new ArrayList<>();

        Random random = new Random();
        for(int i=0; i<CASE_COUNT; i++){
            int pick = random.nextInt(5)+1;

            answer.add(pick);
        }

        long startTime = System.currentTimeMillis();
        int[] result = test1.solution1(
                answer.stream().mapToInt(Integer::intValue).toArray()
        );
        System.out.println(String.format("Spent Time : %s(ms)",System.currentTimeMillis()-startTime));
        System.out.println(result);
    }

    public int[] solution1(int[] answers) {
        int[] answer;

        int[] a = new int[]{1,2,3,4,5};
        int[] b = new int[]{2,1,2,3,2,4,2,5};
        int[] c = new int[]{3,3,1,1,2,2,4,4,5,5};

        int[] score = new int[3];

        // score[]에 수포자별 맞힌 갯수
        for(int i=0; i<answers.length; i++){
            if(answers[i] == a[i%5])  score[0]++;
            if(answers[i] == b[i%8])  score[1]++;
            if(answers[i] == c[i%10]) score[2]++;
        }

        // 가장 높은 점수
        int max = 0;
        for(int i=0; i<3; i++){
            if(score[i] > max){ max = score[i]; }
        }

        // 가장 높은 점수를 받은 사람 수
        int maxCount = 0;
        for(int i=0; i<3; i++){
            if(score[i] == max){ maxCount++; }
        }

        answer = new int[maxCount];
        int idx = 0;
        for(int i=0; i<3; i++){
            if(score[i] == max) answer[idx++] = i+1;
        }
        return answer;
    }

}
