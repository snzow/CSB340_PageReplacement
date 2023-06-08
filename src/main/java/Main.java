import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        PageGenerator p = new PageGenerator();

        FIFO fifo = new FIFO(1);
        LRU lru = new LRU(1);
        OPT opt = new OPT(1);


        for(int refSize = 10; refSize <= 20; refSize += 5){
            int[] gen = p.getReferenceString(refSize);
            System.out.println("------------------------");
            System.out.println("REFSIZE: " + refSize);
            System.out.println(Arrays.toString(gen));
            for(int numFrames = 3; numFrames <= 7; numFrames += 2){
                int optFault = opt.run(gen,numFrames);
                int fifoFault = fifo.run(gen,numFrames);
                int lruFault = lru.run(gen,numFrames);
                System.out.println();
                System.out.println("Faults for " + numFrames + " page frames");
                System.out.println("Optimal: " + optFault);
                System.out.println("First In First Out: " + fifoFault);
                System.out.println("Least Recently Used: " + lruFault);
                System.out.println();
            }
        }
        System.out.printf("------------------------");
    }

}
