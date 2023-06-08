import com.sun.security.jgss.GSSUtil;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class OPT extends ReplacementAlgorithm {
    /**
     * @param pageFrameCount - the number of physical page frames
     */
    public OPT(int pageFrameCount) {
        super(pageFrameCount);
    }

    public int run(int[] refString, int numFrames) {
        ArrayList<Integer> ram = new ArrayList<>();
        int faultCount = 0;
        int cur = 0;


        for (int i : refString) {
            if (!ram.contains(i)) {
                faultCount++;
                if (ram.size() == numFrames) {
                    int maxNum = -1;
                    int max = -1;
                    for (int y : ram) {
                        int tmp = getNextInstance(refString,y,cur);
                        if(tmp == Integer.MAX_VALUE){
                            maxNum = y;
                            break;
                        }
                        if(tmp > max){
                            max = tmp;
                            maxNum = y;
                        }
                    }
                    ram.remove(ram.indexOf(maxNum));
                }
                ram.add(i);

            }
            cur++;
        }

        return faultCount;
    }

    private int getNextInstance(int[] arr, int num, int start){
        int nextInstance = 0;
        for(int i = start; i < arr.length; i++){
            if(arr[i] == num){
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public void insert(int pageNumber) {

    }
    // OPT will extend ReplacementAlgorithm
}
