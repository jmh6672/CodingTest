package programers.hash;

import java.util.*;

public class Test2 {
    static final int CASE_COUNT = 1000000;

    /**
     전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
     전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.

     구조대 : 119
     박준영 : 97 674 223
     지영석 : 11 9552 4421
     전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.

     제한 사항
     phone_book의 길이는 1 이상 1,000,000 이하입니다.
     각 전화번호의 길이는 1 이상 20 이하입니다.
     같은 전화번호가 중복해서 들어있지 않습니다.
     * */
    public static void main(String[] args) {
        Test2 test2 = new Test2();

        List<String> phoneBook = new ArrayList<>();

        Random random = new Random();
        for(int i=0; i<CASE_COUNT; i++){
            String name = random.ints(0,9)
                    .limit(20)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            phoneBook.add(name);
        }

        String[] a = {"119", "97674223", "1195524421"};

        long startTime = System.currentTimeMillis();
        boolean result = test2.solution1(
                phoneBook.toArray(new String[phoneBook.size()])
        );
        System.out.println(String.format("Spent Time : %s(ms)",System.currentTimeMillis()-startTime));
        System.out.println(result);
    }

    public boolean solution1(String[] phone_book) {
        boolean answer = true;

        HashSet<String> set = new HashSet<>();
        for(String phoneNum:phone_book){
            if(phoneNum.length() <= 20) {
                set.add(phoneNum);
            }else{
                System.out.println(String.format("[%s] is too much long number",phoneNum));
            }
        }
        for (String phoneNum : phone_book) {
            for (String s : set) {
                if (phoneNum.startsWith(s) || s.startsWith(phoneNum))
                    return false;
            }
        }

        return answer;
    }

    public boolean solution(String[] phone_book) {

        Arrays.sort(phone_book);
        for (int i = 0; i <phone_book.length-1; i++) {
            if (phone_book[i+1].startsWith(phone_book[i])) return false;
        }
        return true;

    }

}
