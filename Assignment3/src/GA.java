public class GA implements Comparable<GA>{
    // 하나의 개체
    public int fitness;
    public int[] chromosome;
    // 배열 chromosome을 이루는 수(queen의 위치) 하나하나는 gene라고 생각!

    public GA(int n){
        chromosome = new int[n];
    }

    // sort를 위해 필요하다
    @Override
    public int compareTo(GA o){
        return fitness - o.fitness;
    }
}
