import java.util.ArrayList;

public class FIFO extends ReplacementAlgorithm// FIFO will extend ReplacementAlgorithm
{


    public FIFO(int pageFrameCount) {
        super(pageFrameCount);
    }

    public int run(int[] refString, int numFrames){
        ArrayList<Integer> ram = new ArrayList<>();
        int faultCount = 0;
        for(int i : refString){
            //System.out.println(ram);
            //System.out.println(faultCount + " fault(s)");
            //System.out.println("number to add: " + i);
            if(!ram.contains(i)){
                faultCount++;
                if(ram.size() == numFrames){
                    ram.remove(0);
                }
                ram.add(i);
            }
        }
        return faultCount;
    }


    @Override
    public void insert(int pageNumber) {

    }
}
