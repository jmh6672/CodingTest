import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test2 {
    /**
     * [2,1,3]
     * [1,2,3]
     * [3,2,3,6,4,5]
     * [1,0,1,1,1]
     * [1,0,1,1,1]
     *
     * */

    int[][] answer;
    int count;

    public int[][] solution(int n, boolean horizontal) {
        this.answer = new int[n][n];
        this.count = 0;

        recursive(0,0, horizontal, horizontal, true);

//        for(int i=0; i<n; i++){
//            //오른쪽으로 이동
//            int cnt = i;
//            int cor = i+1;
//            for(int j=0; j<n; j++){
//                int num=0;
//                if(cor==0){
//                    num += 1;
//                    cor=i;
//                }else{
//                    num=cnt*j;
//                    cnt++;
//                    cor--;
//                }
//                answer[i][j] = num;
//            }
//        }


        return answer;
    }
    void recursive(int x, int y, boolean horizontal, boolean right, boolean forward){
        if(count > Math.sqrt(answer.length)){
            return;
        }
        answer[y][x] = ++count;

        int i = forward ? 1 : -1;
        int chk = 0;
        if(horizontal){
            if(x+i < 0 || x+i >= answer.length){
                right = !right;
                forward = !forward;
            }else{
                x += i;
            }

            if (right) {
                chk = answer[y+i][x];
            } else {
                chk = answer[y-i][x];
            }
        }else{
            if(y+i < 0 || y+i >= answer.length){
                right = !right;
                forward = !forward;
            }else{
                y += i;
            }

            if (right) {
                chk = answer[y][x+i];
            } else {
                chk = answer[y][x-i];
            }
        }

        if (chk < 1) {
            horizontal = !horizontal;
        }

        recursive(x,y,horizontal,right,forward);
    }


    @Test
    public void 테스트2(){
        assertEquals(
                new int[][]{{1,2,9,10},{4,3,8,11},{5,6,7,12},{16,15,14,13}},
                solution(4, true)
        );
//        assertEquals(
//                2+1+2,
//                solution(5, false)
//        );
    }
}
