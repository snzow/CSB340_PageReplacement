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
        HashMap<Integer, ArrayList<Integer>> nextAppMap = new HashMap<>();

        for (int i : refString) {
            if (!nextAppMap.containsKey(i)) {
                ArrayList<Integer> a = new ArrayList<>();
                a.add(cur);
                nextAppMap.put(i, a);
            } else {
                nextAppMap.get(i).add(cur);
            }
            cur++;
        }

        cur = 0;
        for (int i : refString) {
            System.out.println(ram);
            System.out.println(faultCount);
            System.out.println(i);
            if (!ram.contains(i)) {
                faultCount++;
                if (ram.size() == numFrames) {
                    int maxNum = -1;
                    int max = -1;
                    for (int y : ram) {
                        if(nextAppMap.get(y).size() == 0){
                            maxNum = y;
                            break;
                        }
                        int nextAppearance = nextAppMap.get(y).get(0);
                        if (nextAppearance > max) {
                            max = nextAppearance;
                            maxNum = y;
                        }
                    }
                    ram.remove(Integer.valueOf(maxNum));
                    nextAppMap.get(maxNum).remove(0);
                }
                ram.add(i);
            }
            cur++;
        }

        return faultCount;
    }

    @Override
    public void insert(int pageNumber) {

    }
    // OPT will extend ReplacementAlgorithm
}
