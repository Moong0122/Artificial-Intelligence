import java.util.ArrayList;
import java.util.Stack;
/*
   Iterative deeping depth-first search
   depth-limit을 가지고 있다
*/
public class DFID {
    private static int n;
    Stack<ArrayList<Integer>> s;
    Stack<Boolean> possible;
    ArrayList<Integer> chess = new ArrayList<>();
    boolean result = false;

    public DFID(int n){
        this.n = n;
    }
    /*
    depth가 0일때부터 Stack을 통해서 계속 확인해줘야 하므로 이중 for문 사용
     */
    public boolean findCase() {
        boolean save = true;
        for (int depth = 0; depth <= n; depth++){
            s = new Stack<>();
            possible = new Stack<>();
            for (int i = 0; i < n ; i++) {
                ArrayList<Integer> array = new ArrayList<>();
                array.add(i);
                s.push(array);
                possible.push(true);
            }
            // 이제 usingDfid 함수를 재귀로 방문하면서 depth가 n이 되면 true/false를 반환
            usingDfid(depth);
        }
        return result;
    }

    public void usingDfid(int depthLimit){ // depth-limit
        // 이미 올바른 결과를 찾았으므로 돌아간다
        if(result)
            return;

        while(!s.empty()){
            ArrayList<Integer> popArray = new ArrayList<>();
            popArray = s.pop();
            boolean save = possible.pop();

            if(popArray.size() < depthLimit){
                for(int i=0;i<n;i++){
                    ArrayList<Integer> newArray = new ArrayList<>();
                    newArray.addAll(popArray);
                    newArray.add(i);
                    s.push(newArray);
                    if(!save)
                        possible.push(save);
                    else
                        possible.push(isCheck(newArray));
                }
            }
            if(popArray.size() == n){
                if(save) {
                    chess.addAll(popArray);
                    result = true;
                    return;
                }
            }
        }
    }

    public boolean isCheck(ArrayList<Integer> array) {
        int len = array.size();
        int col = len - 1;
        int row = array.get(col);
        for (int i = 0; i < len-1; i++) {
            if(array.get(i) == row)
                return false;
            if(Math.abs(array.get(i)-row) == Math.abs(i-col))
                return false;
        }
        return true;
    }

    public ArrayList<Integer> printCase() {
        return chess;
    }
}
