import java.util.ArrayList;

public class LRU extends ReplacementAlgorithm // LRU will extend ReplacementAlgorithm
{

    /**
     * @param pageFrameCount - the number of physical page frames
     */
    public LRU(int pageFrameCount) {
        super(pageFrameCount);
    }

    public int run(int[] refString, int numFrames) {
        ArrayList<Integer> ram = new ArrayList<>();
        ArrayList<Integer> lastUsedQueue = new ArrayList<>();
        int faultCount = 0;

        for (int i : refString) {
            if (!ram.contains(i)) {
                faultCount++;
                if (ram.size() == numFrames) {
                    int pageToRemove = lastUsedQueue.remove(0);
                    ram.remove(ram.indexOf(pageToRemove));
                }
                ram.add(i);
                lastUsedQueue.add(i);
            } else {
                lastUsedQueue.remove(lastUsedQueue.indexOf(i));
                lastUsedQueue.add(i);
            }
        }

        return faultCount;
    }

    @Override
    public void insert(int pageNumber) {

    }
}
