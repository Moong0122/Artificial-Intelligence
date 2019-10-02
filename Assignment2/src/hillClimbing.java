import java.util.Random;

public class hillClimbing {
    private int n;

    public hillClimbing(int n){
        this.n = n;
    }
    /*
     * clone()은 원본 배열과는 별개의 주소값을 가진 새로운 배열을 만든다
     * 클론으로 만든 배열은 바꿔도 원본이 바뀌지 않는다
     */
    public int[] makeRandomQueens(){
        int[] randomQueens = new int[n];

        // 랜덤한 초기 위치 설정
        Random rand = new Random();
        for(int i=0;i<n;i++){
            randomQueens[i] = rand.nextInt(n);
        }
        return randomQueens;
    }

    public int calStateValue(int[] state){
        // low가 높다는 뜻은 더 낮다는 뜻이다
        // 이 함수를 통해 더 높은 상태를 구할 수 있다
        int low = 0;
        for(int i=0;i<n;i++){
            for(int j = i+1;j<n;j++){
                // 같은 row에 queen이 위치하거나
                if(state[i] == state[j])
                    low++;
                // 대각선에 queen이 있다면 low를 +1씩 해준다.
                if(Math.abs(state[j] - state[i]) == (j - i))
                    low++;
            }
        }
        return low;
    }

    public int[] climbing() {
        // 각 Column에 있는 Queens의 초기 위치를 랜덤하게 지정해주고 시작한다
        int[] start = makeRandomQueens();
        int state_value = calStateValue(start);

        int now_most = state_value;
        int[] now_most_state = new int[n];

        // state_value가 0이라는 뜻은 목표에 도달했다는 뜻이므로 while문을 탈출한다
        while (state_value != 0) {
            for (int i = 0; i < n; i++) {
                // 이제 각 column별로 queen의 위치를 이동시키면서 low가 가장 낮은 상태를 구한다
                int[] check = start.clone();
                for (int j = 0; j < n; j++) {
                    check[i] = j;
                    int value = calStateValue(check);
                    if (value < now_most) {
                        now_most = value;
                        now_most_state = check.clone();
                    }
                }
                /*
                 * 만약 now_most와 초기에 랜덤으로 설정한 배열에 대한 value가 동일하다면
                 * Local optima에 갇혔다는 의미이므로 다시 랜덤배열을 만들어준다
                 */
                if(now_most == state_value){
                    start = makeRandomQueens();
                    state_value = calStateValue(start);
                }
                /*
                 * 더 높은 state가 있다면 다음 state로 이동해준다
                 */
                else{
                    start = now_most_state.clone();
                    state_value = calStateValue(start);
                }
            }
        }
        return start;
    }
}