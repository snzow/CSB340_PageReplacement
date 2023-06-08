import java.util.Random;

public class PageGenerator
{

	public int[] getReferenceString(int size){
		Random rand = new Random();
		int[] toReturn = new int[size];
		for(int i = 0; i < size; i++){
			toReturn[i] = rand.nextInt(9);
		}
		return toReturn;
	}


}
