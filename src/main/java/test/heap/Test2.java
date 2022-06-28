package test.heap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


/**
 문제 설명
 하드디스크는 한 번에 하나의 작업만 수행할 수 있습니다. 디스크 컨트롤러를 구현하는 방법은 여러 가지가 있습니다. 가장 일반적인 방법은 요청이 들어온 순서대로 처리하는 것입니다.

 예를들어

 - 0ms 시점에 3ms가 소요되는 A작업 요청
 - 1ms 시점에 9ms가 소요되는 B작업 요청
 - 2ms 시점에 6ms가 소요되는 C작업 요청
 와 같은 요청이 들어왔습니다. 이를 그림으로 표현하면 아래와 같습니다.
 Screen Shot 2018-09-13 at 6.34.58 PM.png

 한 번에 하나의 요청만을 수행할 수 있기 때문에 각각의 작업을 요청받은 순서대로 처리하면 다음과 같이 처리 됩니다.
 Screen Shot 2018-09-13 at 6.38.52 PM.png

 - A: 3ms 시점에 작업 완료 (요청에서 종료까지 : 3ms)
 - B: 1ms부터 대기하다가, 3ms 시점에 작업을 시작해서 12ms 시점에 작업 완료(요청에서 종료까지 : 11ms)
 - C: 2ms부터 대기하다가, 12ms 시점에 작업을 시작해서 18ms 시점에 작업 완료(요청에서 종료까지 : 16ms)
 이 때 각 작업의 요청부터 종료까지 걸린 시간의 평균은 10ms(= (3 + 11 + 16) / 3)가 됩니다.

 하지만 A → C → B 순서대로 처리하면
 Screen Shot 2018-09-13 at 6.41.42 PM.png

 - A: 3ms 시점에 작업 완료(요청에서 종료까지 : 3ms)
 - C: 2ms부터 대기하다가, 3ms 시점에 작업을 시작해서 9ms 시점에 작업 완료(요청에서 종료까지 : 7ms)
 - B: 1ms부터 대기하다가, 9ms 시점에 작업을 시작해서 18ms 시점에 작업 완료(요청에서 종료까지 : 17ms)
 이렇게 A → C → B의 순서로 처리하면 각 작업의 요청부터 종료까지 걸린 시간의 평균은 9ms(= (3 + 7 + 17) / 3)가 됩니다.

 각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원 배열 jobs가 매개변수로 주어질 때, 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면 평균이 얼마가 되는지 return 하도록 solution 함수를 작성해주세요. (단, 소수점 이하의 수는 버립니다)

 제한 사항
 jobs의 길이는 1 이상 500 이하입니다.
 jobs의 각 행은 하나의 작업에 대한 [작업이 요청되는 시점, 작업의 소요시간] 입니다.
 각 작업에 대해 작업이 요청되는 시간은 0 이상 1,000 이하입니다.
 각 작업에 대해 작업의 소요시간은 1 이상 1,000 이하입니다.
 하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.
* */
public class Test2 {
    static final int EX_CNT = 10;

    public static void main(String[] args) throws JsonProcessingException {
        Test2 test = new Test2();
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();

        int[][] jobs = new int[EX_CNT][2];

        for(int i=0; i<EX_CNT; i++){
            jobs[i]=(new int[]{random.nextInt(1000 + 1),random.nextInt(1000)+1});
        }

        System.out.println("Test jobs : " + objectMapper.writeValueAsString(jobs));

        long beforeTime = System.currentTimeMillis();
        int result = test.solution1(
                jobs
        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }

    public int solution1(int[][] jobs) {

        //작업 요청시점이 빠른것 순으로 정렬
        Arrays.sort(jobs,Comparator.comparingInt(a -> a[0]));
        //수행시간이 짧은 순으로 넣는 heap큐
        PriorityQueue<int[]> diskQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));


        //시간, 인덱스, 답 초기화
        int time =0, idx=0, answer=0;
        //인덱스가 범위내에 있거나, pq가 비어있지 않다면 while진행
        while(idx<jobs.length||!diskQueue.isEmpty()) {
            //인덱스가 범위내에 있고, 시작시간이 시간 이내라면 pq에 집어넣기
            while(idx<jobs.length&&jobs[idx][0]<=time) {
                diskQueue.add(jobs[idx++]);
            }
            //pq가 비어있다면 (시간 이내에 시작하는 task가 없다면)
            if(diskQueue.isEmpty()) {
                //해당 시작 시간을 time으로 지정
                time=jobs[idx][0];
                continue;
            }
            //pq가 비어있지 않다면
            //시간에 pq에 들어간 작은 수행시간을 더함
            int[] job = diskQueue.poll();
            time+=job[1];
            //종료 시간 - 시작시간
            answer += time-job[0];
        }

        return answer/jobs.length;
    }


}