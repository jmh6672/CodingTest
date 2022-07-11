package programers.hash;

import java.util.*;

public class SpyCloth {
    static final int CASE_COUNT = 30;

    /**
     문제 설명
     스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.

     예를 들어 스파이가 가진 옷이 아래와 같고 오늘 스파이가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면 다음날은 청바지를 추가로 입거나 동그란 안경 대신 검정 선글라스를 착용하거나 해야 합니다.

     종류	이름
     얼굴	동그란 안경, 검정 선글라스
     상의	파란색 티셔츠
     하의	청바지
     겉옷	긴 코트
     스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.

     제한사항
     clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
     스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
     같은 이름을 가진 의상은 존재하지 않습니다.
     clothes의 모든 원소는 문자열로 이루어져 있습니다.
     모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
     스파이는 하루에 최소 한 개의 의상은 입습니다.
     * */
    public static void main(String[] args) {
        SpyCloth test3 = new SpyCloth();

        String[] categories = {"headgear","accessory","shoes","bottom","top"};
        List<String[]> clothes = new ArrayList<>();

        Random random = new Random();
        for(int i=0; i<CASE_COUNT; i++){
            String category = categories[random.nextInt(categories.length)];
            String cloth = random.ints('a','z')
                    .limit(20)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            String[] set = {cloth,category};

            clothes.add(set);
        }


        String[][] a = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println("");
        clothes.stream().forEach(strings ->
                System.out.println(Arrays.toString(strings))
        );
        long startTime = System.currentTimeMillis();
        int result = test3.solution1(
                clothes.toArray(new String[clothes.size()][2])
        );
        int result2 = test3.solution1(
                a
        );
        System.out.println(String.format("Spent Time : %s(ms)",System.currentTimeMillis()-startTime));
        System.out.println(result);
        System.out.println(result2);
    }


    public int solution1(String[][] clothes) {
        int answer = 1;

        //중복제거를 위해 hashset으로 데이터 저장
        HashSet<String[]> set = new HashSet<>();
        for(String[] clothCategory:clothes){
            set.add(clothCategory);
        }

        //카테고리별 옷의 수량만 담는 hashmap
        HashMap<String, Integer> clothMap = new HashMap<>();
        for(String[] clothCategory : set){
            String category = clothCategory[1];
            clothMap.put(category,clothMap.getOrDefault(category,0) + 1);
        }

        //카테고리별 옷의 수를 곱해서 모든 경우의 수를 구한다.
        //카테고리 내에서 고르지 않는 경우도 있어서 +1 한 후 곱한다.
        for(Integer cnt : clothMap.values()){
            answer *= (cnt+1);
        }

        //위의 계산에는 옷을 선택하지 않는 경우의 수도 포함되므로 -1 해준다.
        return answer-1;
    }

}
