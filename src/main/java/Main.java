import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        PageGenerator p = new PageGenerator();

        FIFO fifo = new FIFO(1);
        LRU lru = new LRU(1);
        OPT opt = new OPT(1);

        int[] rStr1 = {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1};
        int[] fStr2 = {8,1,0,7,3,0,3,4,5,3,5,2,0,6,8,4,8,1,5,3};
        int[] rStr3 = {4,6,4,8,6,3,6,0,5,9,2,1,0,4,6,3,0,6,8,4};
        ArrayList<int[]> given = new ArrayList<>();
        given.add(rStr1);
        given.add(fStr2);
        given.add(rStr3);

        for(int[] i : given){
            System.out.println("Optimal: " + opt.run(i,3));
            System.out.println("First In First Out: " + fifo.run(i,3));
            System.out.println("Least Recently Used: " + lru.run(i,3));
        }


        System.out.println();
        int optTotal = 0;
        int fifoTotal = 0;
        int lruTotal = 0;

        for(int refSize = 10; refSize <= 20; refSize += 5){
            int[] gen = p.getReferenceString(refSize);
            System.out.println("------------------------");
            System.out.println("REFSIZE: " + refSize);
            System.out.println(Arrays.toString(gen));
            for(int numFrames = 3; numFrames <= 7; numFrames += 2){
                int optFault = opt.run(gen,numFrames);
                int fifoFault = fifo.run(gen,numFrames);
                int lruFault = lru.run(gen,numFrames);
                optTotal += optFault;
                fifoTotal += fifoFault;
                lruTotal += lruFault;
                System.out.println();
                System.out.println("Faults for " + numFrames + " page frames");
                System.out.println("Optimal: " + optFault);
                System.out.println("First In First Out: " + fifoFault);
                System.out.println("Least Recently Used: " + lruFault);
                System.out.println();
            }
        }

        double optAvg = optTotal / 27.0;
        double fifoAvg = fifoTotal / 27.0;
        double lruAvg = lruTotal / 27.0;
        System.out.println("-Averages-");
        System.out.println("Optimal : " + Math.round(optAvg * 100.0) / 100.0);
        System.out.println("FIFO : " + Math.round(fifoAvg * 100.0) / 100.0);
        System.out.println("LRU : " + Math.round(lruAvg * 100.0) / 100.0);

        System.out.printf("------------------------");
    }

}
