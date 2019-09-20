import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    private static int n;

    Queue<ArrayList<Integer>> q = new LinkedList<>();
    Queue<Boolean> possible = new LinkedList<>();
    ArrayList<Integer> chess = new ArrayList<>();
    /*
    arrqyList를 저장하는 queue를 만들어준다
    arrayList는 각 col마다의 row 위치가 들어있다
    */
    public BFS(int n){
        this.n = n;
    }

    public boolean findCase(){
        for(int i=0;i<n;i++){
            ArrayList<Integer> array = new ArrayList<>();
            array.add(i);
            q.offer(array);
            possible.offer(true);
        }
        // queue를 poll하면서 올바른 경우가 있는지 찾는다
        while(!q.isEmpty()){
            ArrayList<Integer> pollArray = new ArrayList<>();
            pollArray = q.poll();
            boolean save = possible.poll();

            if(pollArray.size() < n){
                for(int i=0;i<n;i++) {
                    ArrayList<Integer> newArray = new ArrayList<>();
                    newArray.addAll(pollArray);
                    newArray.add(i);
                    q.offer(newArray);
                    // 다음 col에 있는 row를 추가해서 q에 넣어준다
                    if(!save)
                        possible.offer(save);
                    else
                        possible.offer(isCheck(newArray));
                }
            }
            else{
                if(save) {
                    chess.addAll(pollArray);
                    return true;
                }
            }
        }
        return false;
    }

    // 길이가 n인 arraylist가 true인지 확인해준다
    public boolean isCheck(ArrayList<Integer> a) {
        int len = a.size();
        int col = len - 1;
        int row = a.get(col);
        for (int i = 0; i < len-1; i++) {
            if(a.get(i) == row)
                return false;
            if(Math.abs(a.get(i)-row) == Math.abs(i-col))
                return false;
        }
        return true;
    }
    // 답을 가지고 있는 chess ArrayList를 출력 -> return
    public ArrayList<Integer> printCase() {
        return chess;
    }
}
