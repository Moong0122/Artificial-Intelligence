import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    private static PrintWriter pw;

    public static void printResult(ArrayList<Integer> result){
        pw.print("Location :");
        for(int i : result){
            pw.print(" "+i);
        }
        pw.println();
    }

    public static void main(String[] args) {
        if(args.length < 2){
            System.out.println("input: [number] [path]");
            System.exit(1);
        }
        long start, end = 0;
        int input = Integer.parseInt(args[0]);

        try {
            // 윈도우 및 맥에서 모두 파일을 제대로 읽기 위해서 System.getProperty("file.separator")을 추가
            pw = new PrintWriter(args[1] +System.getProperty("file.separator")+"result" + args[0] + ".txt");
            DFS dfs = new DFS(input);
            BFS bfs = new BFS(input);
            DFID dfid = new DFID(input);

            // DFS
            pw.println(">DFS");
            start = System.currentTimeMillis();
            if (dfs.findCase()) {
                printResult(dfs.printCase());
            } else {
                pw.println("No solution");
            }
            end = System.currentTimeMillis();
            pw.println("실행 시간 : " + (double) (end - start) / 1000.0);
            pw.println();

            // BFS
            pw.println(">BFS");
            start = System.currentTimeMillis();
            if (bfs.findCase()) {
                printResult(bfs.printCase());
            } else {
                pw.println("No solution");
            }
            end = System.currentTimeMillis();
            pw.println("실행 시간 : " + (double) (end - start) / 1000.0);
            pw.println();

            // DFID
            pw.println(">DFID");
            start = System.currentTimeMillis();
            if (dfid.findCase()) {
                printResult(dfid.printCase());
            } else {
                pw.println("No solution");
            }
            end = System.currentTimeMillis();
            pw.println("실행 시간 : " + (double) (end - start) / 1000.0);
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}