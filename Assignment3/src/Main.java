import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    private static PrintWriter pw;

    public static void printState(int[] resultState){
        int len = resultState.length;
        for(int i=0;i<len;i++){
            pw.print(resultState[i] + " ");
        }
        pw.println();
    }

    public static void main(String[] args) {
        if(args.length < 2){
            // n의 개수와 경로, 2가지를 다 입력받아야 한다
            System.out.println("input : [number] [path]");
            System.exit(1);
        }

        int input = Integer.parseInt(args[0]);

        if(input == 2 || input == 3){
            // n이 2와 3일 경우에는 올바른 답을 구할 수 없으므로 예외처리 해준다
            System.out.println("input another number!");
            System.exit(1);
        }
        Genetic assignment3 = new Genetic(input);
        long start, end = 0;

        try {
            pw = new PrintWriter(args[1] +System.getProperty("file.separator")+"result"+args[0]+".txt");
            pw.println(">Genetic Algorithm");
            start = System.currentTimeMillis();
            printState(assignment3.GeneticAlgorithm());
            end = System.currentTimeMillis();
            pw.println("Total Elapsed Time: " + (double) (end - start) / 1000.0);
            pw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
