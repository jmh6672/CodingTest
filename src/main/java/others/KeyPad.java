package others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**

* */
public class KeyPad {
    static final int EX_CNT = 10;

    public static void main(String[] args) throws JsonProcessingException {
        KeyPad test = new KeyPad();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Integer> array = new ArrayList<>();
        List<List<Integer>> commands = new ArrayList<>();

        StringBuffer sb = new StringBuffer();


        Random random = new Random();
        for(int i=0; i<EX_CNT; i++) {
            sb.append(random.ints('0','9')
                    .limit(1)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString());
        }

        sb = new StringBuffer("2220281");

        System.out.println("Test sb : " + objectMapper.writeValueAsString(sb));

        long beforeTime = System.currentTimeMillis();
        String result = test.solution(
                sb.toString()
        );
        long secDiffTime = (System.currentTimeMillis() - beforeTime);

        System.out.println(String.format("time-lapse : %s(ms)",secDiffTime));
        System.out.println("Test result : " + objectMapper.writeValueAsString(result));

    }

    String[] key1 = {".","Q","Z"};
    String[] key2 = {"A","B","C"};
    String[] key3 = {"D","E","F"};
    String[] key4 = {"G","H","I"};
    String[] key5 = {"J","K","L"};
    String[] key6 = {"M","N","O"};
    String[] key7 = {"P","R","S"};
    String[] key8 = {"T","U","V"};
    String[] key9 = {"W","X","Y"};

    public String solution(String s) {
        String answer = "";


        StringBuffer sb = new StringBuffer();

        int cnt = 0;
        int tmp = s.charAt(0);
        for(int i=1; i<=s.length(); i++){
            int chk = (i<s.length() ? s.charAt(i) : -1);
            if(tmp == chk){
                cnt++;
                continue;
            }else{
                sb.append(output(tmp-'0',cnt));
                tmp = chk;
                cnt=0;
            }
        }

        return sb.toString();
    }

    String output(int c, int cnt){
        String out;
        switch (c) {
            case 1 -> out = key1[cnt];
            case 2 -> out = key2[cnt];
            case 3 -> out = key3[cnt];
            case 4 -> out = key4[cnt];
            case 5 -> out = key5[cnt];
            case 6 -> out = key6[cnt];
            case 7 -> out = key7[cnt];
            case 8 -> out = key8[cnt];
            case 9 -> out = key9[cnt];
            default -> out = "";
        }

        return out;
    }
}