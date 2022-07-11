package programers.brute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MathImpossing {
    static final int CASE_COUNT = 1000000000;
    static final int CASE_COUNT_ARRAY = 100000;

    /**
     문제 설명
     수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.

     1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
     2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
     3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...

     1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.

     제한 조건
     시험은 최대 10,000 문제로 구성되어있습니다.
     문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
     가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
     * */
    public static void main(String[] args) {
        MathImpossing test4 = new MathImpossing();

        List<Integer> times = new ArrayList<>();

        Random random = new Random();
        for(int i=0; i<CASE_COUNT_ARRAY; i++){
            int pick = random.nextInt(5)+1;

            times.add(pick);
        }

        long startTime = System.currentTimeMillis();
        long result = test4.solution(
                random.nextInt(CASE_COUNT)+1,
                times.stream().mapToInt(Integer::intValue).toArray()
        );
        System.out.println(String.format("Spent Time : %s(ms)",System.currentTimeMillis()-startTime));
        System.out.println(result);
    }

    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);

        long left = 0;
        long right = (long) n * times[times.length - 1]; //가장 최악의 경우의(오래걸리는) 시간
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0; // 총 심사한 인원
            for (int i = 0; i < times.length; i++) { //빨리 심사하는 심사관 순으로 심사처리
                sum += mid / times[i];
            }
            if (sum < n) { // 해야할 인원보다 심사처리 못함 -> 시간 더 필요
                left = mid + 1;
            } else { // 해야할 인원보다 심사처리 많이함 -> 시간을 줄여서 더 최고 경우의 시간을 만든다.
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }

}
