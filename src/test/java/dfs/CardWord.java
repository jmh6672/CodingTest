package dfs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CardWord {
    public String[] solution(String[] card, String[] word) {
        List<String> answer = new ArrayList<>();

        int[] visits = { 0, 1, 2 }; // 1~3세트 방문 체크

        String[] it;
        int[] vit;
        boolean isFind = false;

        // 1. 총 단어 수
        for (int i=0; i<word.length; i++) {
            if(isFind){
                break;
            }
            // 2. 단어별 검색
            String[] cpCard = card;

            for (int j=0; j<word[i].length(); j++) {
                // 단어마다 카드를 새로 생성
                // 3. 카드별 탐색
                for (int k=0; k<3; k++) {
                    // 알파벳이 없을 때
                    if (k == 3)
                    {
                        isFind = false;
                        break;
                    }
                }
            }

            // 1~3세트 카드 모두 방문하였는지 체크
            if (visits.length != 0) {
                isFind = false;
            }

            if (isFind) {
                answer.add(word[i]);
            }
        }

        if (answer.size() == 0) {
            answer.add("-1");
        }

        return answer.stream().toArray(String[]::new);
    }

    @Test
    public void 카드에서단어찾기_테스트() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        assertEquals(
                objectMapper.writeValueAsString(new String[]{"GPQM,MMNA"}),
                objectMapper.writeValueAsString(solution(new String[]{"ABACDEFG","NOPQRSTY","HIJKLKMM"}, new String[]{"GPQM","GPMZ","EFU","MMNA"}))
        );
        assertEquals(
                objectMapper.writeValueAsString(new String[]{"-1"}),
                objectMapper.writeValueAsString(solution(new String[]{"AABBCCDD","KKKKJJJJ","MOMOMOMO"}, new String[]{"AAAAKKKKKMMMMM","ABCDKJ"}))

        );
//        assertEquals(
//                2+1+2,
//                solution(5, false)
//        );
    }
}
