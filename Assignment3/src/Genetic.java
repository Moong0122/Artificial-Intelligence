import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genetic {
    private int n;

    public Genetic(int n){ this.n = n; }
    /*
     * 개체수만 확정짓
     * 초기 population -> 2000
     * parent -> 500개
     * crossover -> 4500개
     * mutation ->  10개
     */
    ArrayList<GA> population = new ArrayList<>();

    public int[] GeneticAlgorithm(){ // main 계산
        // 초기 population 2000개 설정
        for(int i=0;i<2000;i++) {
            GA tmp = new GA(n);
            tmp.chromosome = makeRandom();
            tmp.fitness = checkfitness(tmp.chromosome);
            population.add(tmp);
        }
        // fitness가 작은 것부터 차례대로 정렬!
        Collections.sort(population);

        // fitness가 만약에 0이라면 정답이라는 뜻이므로 while에 들어가지 않는다
        while(population.get(0).fitness != 0){
            // parents 선정 방법 -> fitness가 가장 좋은 500개를 고르고 나머지는 다 삭제
            while (population.size() > 500){
                population.remove(population.size()-1);
            }
            // 500개의 parents중에서 2개씩 골라서 crossover 해준다 -> 4500개 생성
            for(int i=0;i<4500;i++){
                crossover();
            }
            // 500개 fitness가 좋은 parents중에서 2개씩 골라서 mutation 해준다 -> 10개 생성
            for(int i=0;i<10;i++){
                mutation();
            }
            // fitness가 좋은 순서대로 정렬해준다
            Collections.sort(population);
        }
        return population.get(0).chromosome;
    }

    public int[] makeRandom(){
        int[] temp = new int[n];
        Random rand = new Random();
        for(int i=0;i<n;i++){
            temp[i] = rand.nextInt(n);
        }
        return temp;
    }

    public int checkfitness(int[] arr){
        // cnt가 0에 가깝다는 뜻은 정답에 가깝다는 뜻이다
        int cnt = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(arr[i] == arr[j])
                    cnt++;
                if((j-i) == Math.abs(arr[j] - arr[i]))
                    cnt++;
            }
        }
        return cnt;
    }

    public void crossover(){
        GA t = new GA(n);
        Random rn = new Random();

        // gene을 넘겨줄 서로 다른 부모(parent1,parent2)를 고른다
        int parent1 = rn.nextInt(500);
        int parent2 = rn.nextInt(500);

        while(parent2 == parent1){
            parent2 = rn.nextInt(500);
        }

        // crossoverPoint을 랜덤하 받아서 crossover해주는 방법
        int crossoverPoint = rn.nextInt(n);
        for(int i=0;i<crossoverPoint;i++){
            t.chromosome[i] = population.get(parent1).chromosome[i];
        }
        for(int i=crossoverPoint;i<n;i++){
            t.chromosome[i] = population.get(parent2).chromosome[i];
        }

        // 좌,우 위치를 랜덤하게 받아서 crossover 해주는 방법
//        int leftPoint = rn.nextInt(n/2);
//        int rightPoint = rn.nextInt(n/2) + n/2;
//        for(int i=0;i<leftPoint;i++){
//            t.chromosome[i] = population.get(parent2).chromosome[i];
//        }
//        for(int i=leftPoint;i<rightPoint;i++){
//            t.chromosome[i] = population.get(parent1).chromosome[i];
//        }
//        for(int i=rightPoint;i<n;i++){
//            t.chromosome[i] = population.get(parent2).chromosome[i];
//        }

        // crossover된 chromosome을 population에 넣어준다
        t.fitness = checkfitness(t.chromosome);
        population.add(t);
    }

    public void mutation(){
        GA tp = new GA(n);
        Random rn = new Random();
        int mutationLocation = rn.nextInt(500);

        // parent 중에서 랜덤하게 하나를 받아 mutation해준다
        tp.chromosome = population.get(mutationLocation).chromosome;

        // 위치 2개(left,right)를 선정해서 값들을 바꿔준다
        int leftPoint = rn.nextInt(n/2);
        int rightPoint = rn.nextInt(n/2) + n/2;

        int value = tp.chromosome[leftPoint];
        tp.chromosome[leftPoint] = tp.chromosome[rightPoint];
        tp.chromosome[rightPoint] = value;

        // mutation된 chromosome을 population에 넣어준다
        tp.fitness = checkfitness(tp.chromosome);
        population.add(tp);
    }
}
