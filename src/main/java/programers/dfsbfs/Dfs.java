package programers.dfsbfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dfs {
    static final int CASE_COUNT = 10;
    /**
     문제 설명
     n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.

     -1+1+1+1+1 = 3
     +1-1+1+1+1 = 3
     +1+1-1+1+1 = 3
     +1+1+1-1+1 = 3
     +1+1+1+1-1 = 3
     사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.

     제한사항
     주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
     각 숫자는 1 이상 50 이하인 자연수입니다.
     타겟 넘버는 1 이상 1000 이하인 자연수입니다.
     * */
    public static void main(String[] args) {
        Dfs test1 = new Dfs();

        List<Integer> numbers = new ArrayList<>();

        Random random = new Random();
        for(int i=0; i<CASE_COUNT; i++){
            int pick = random.nextInt(50)+1;

            numbers.add(pick);
        }

        long startTime = System.currentTimeMillis();
        long result = test1.solution(
                numbers.stream().mapToInt(Integer::intValue).toArray(),
                random.nextInt(1000)+1
        );
        System.out.println(String.format("Spent Time : %s(ms)",System.currentTimeMillis()-startTime));
        System.out.println(result);
    }
    
    int answer;
    int target;
    int[] numbers;

    public int solution(int[] numbers, int target) {
        answer = 0;
        this.target = target;
        this.numbers = numbers;

        dfs(0, 0);

        return answer;
    }

    // 깊이 우선 탐색
    public void dfs(int depth, int sum){
        // 마지막 노드 까지 탐색한 경우
        if(depth == numbers.length){
            if(sum == target) {
                answer++;
            }
            return;
        } else {
            dfs(depth + 1, sum + numbers[depth]);
            dfs(depth + 1, sum - numbers[depth]);
        }
    }
}
