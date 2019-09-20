import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
    private static int n;
    private static int chess[];
    // 체스의 각 col에서 queen이 어느 위치에 있는지 알려주는 배열

    Pair<Integer,Integer> pair;
    Stack<Pair> s = new Stack<>();
    Stack<Boolean> possible = new Stack<Boolean>();
    // DFS에서는 마지막 한꺼번에 검사하지 않고, stack에 넣을 때마다 미리 검사를 한다

    public DFS(int n){
        this.n = n;
        chess = new int[n]; // n개의 배열이 생성된다
    }
    /*
     Search를 할 때 Pruning을 하지 않고, 모두 expanding을 시켜준다는 조건
     DFS(backtracking, 재귀 사용하는 방법도 있음)
     */
    public boolean findCase(){
        for(int i=0;i<n;i++){
            pair = Pair.createPair(0,i);
            s.push(pair);
            possible.push(true);
        }
        // 처음 시작에는 0번째 col에서 row의 0부터 n-1까지 모두 stack에 넣어준다

        while(!s.empty()){
            int col = (int) s.peek().getElement0();
            int row = (int) s.peek().getElement1();
            s.pop();
            Boolean save = possible.pop();
            chess[col] = row;
            // 그리고 stack을 pop해주면서 chess배열도 채워준다

            if(col < n-1){
                pushStack(save,col);
                // 윗줄에 이미 false면 이 줄도 자연스럽게 false이므로 따로 계산 하지 않아도 된다
            }else{
                if(save)
                    return true;
            }
        }
        return false;
    }
    public void pushStack(Boolean before, int popCol){
        int r = popCol + 1;
        for(int i=0;i<n;i++){
            pair = Pair.createPair(r,i);
            s.push(pair);
            if(!before)
                possible.push(before);
            else
                possible.push(isCheck(r,i));
        }
    }

    // 새로 들어온 row,col은 기존에 chess배열과 비교를 통해 true인지 확인
    public boolean isCheck(int col, int row) {
        for (int i = 0; i < col; i++) {
            if(chess[i] == row)
                return false;
            if(Math.abs(chess[i]-row) == Math.abs(i-col))
                return false;
        }
        return true;
    }

    public ArrayList<Integer> printCase() {
        ArrayList<Integer> sendArray = new ArrayList<>();
        for(int i=0;i<n;i++){
         sendArray.add(chess[i]);
        }
        return sendArray;
    }
}
