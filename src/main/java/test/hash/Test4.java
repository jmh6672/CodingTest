package test.hash;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;


/**
 문제 설명
 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

 제한사항
 genres[i]는 고유번호가 i인 노래의 장르입니다.
 plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
 genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
 장르 종류는 100개 미만입니다.
 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
 모든 장르는 재생된 횟수가 다릅니다.

* */
public class Test4 {
    static final int EX_CNT = 1000;

    public static void main(String[] args) throws JsonProcessingException {
        Test4 test4 = new Test4();
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();

        List<String> genres = new ArrayList<>();
        List<Integer> plays = new ArrayList<>();
        for(int i=0; i<EX_CNT; i++){
            String randomString = random.ints(97, 100+1) //a-d
                    .limit(1)
                    .collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append)
                    .toString();
            genres.add(randomString);
            plays.add(random.nextInt(1000));
        }

        System.out.println("Test genres : " + objectMapper.writeValueAsString(genres));
        System.out.println("Test plays : " + objectMapper.writeValueAsString(plays));

        long beforeTime = System.currentTimeMillis();
        int[] result = test4.solution1(genres.stream().toArray(String[]::new),plays.stream().mapToInt(Integer::intValue).toArray());
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));
    }

    public class Music implements Comparable<Music>{
        private int play;
        private int id;
        private String genre;

        public Music(String genre, int play, int id) {
          this.genre = genre;
          this.play = play;
          this.id = id;
        }

        public int getId() {return id;}
        public int getPlay() {return play;}
        public String getGenre() {return genre;}

        @Override
        public int compareTo(Music music) {
          if(this.play == music.play) {
              return this.id - music.id;
          }else {
              return music.play - this.play;
          }
        }
    }

    public int[] solution1(String[] genres, int[] plays){
        List<Integer> bestPlay = new ArrayList<>();

        HashMap<String,HashMap<Integer,Integer>> musicMap = new HashMap<>();

        HashMap<String,Integer> sumMap = new HashMap();
        for(int i=0; i<genres.length; i++){
            if(musicMap.get(genres[i]) == null){
                int finalI = i;
                musicMap.put(genres[i],new HashMap<>(){{put(finalI,plays[finalI]);}});
            }else{
                musicMap.get(genres[i]).put(i,plays[i]);
            }
            sumMap.put(genres[i],sumMap.getOrDefault(genres[i],0) + plays[i]);
        }
        //장르 정렬
        sumMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.mapping(Map.Entry::getKey,Collectors.toList()))
                .forEach(genre -> {
                    bestPlay.addAll(
                        musicMap.get(genre).entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(2)
                            .collect(Collectors.mapping(Map.Entry::getKey,Collectors.toList()))
                    );
                });


        return bestPlay.stream().mapToInt(Integer::intValue).toArray();
    } 
}