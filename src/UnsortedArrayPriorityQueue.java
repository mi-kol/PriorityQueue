import java.util.*

public class UnsortedArrayPriorityQueue<QType> implements PQueue<QType>() {

	public int arraySize = 10;
	public QType[] elms = new QType[arraySize];
	public Integer[] priorities = new Integer[arraySize];
	public Integer[] timeWaiting = new Integer[arraySize];

	public QType dequeue() {
		int highlander = -1;
		int newChallenger = -1;
		// keeps track of highest priority
		
		for (int i = 0; i < arraySize; i++) {
			if (priorities[i] > currentHighest) {
				currentHighest = i;
			} else if (priorities[i].equals(currentHighest)) {
				newChallenger = i;
				if (timeWaiting[newChallenger] > timeWaiting[currentHighest]) {
					currentHighest = newChallenger;
				} else if (timeWaiting[newChallenger].equals(timeWaiting[currentHighest]) {
					System.out.println("What did you DO?");
				} else {
					newChallenger = -1;
				}
			}
		}
		// Finds the highest priority
		
		QType ret_val = elms[highlander];
		elms[highlander] = null;
		ps[highlander] = null;
		timeWaiting = null;

		for (int i = highlander; i < elms.length - 1; i++) {
			elms[i] = elms[i+1];
			priorities[i] = priorities[i+1];
			timeWaiting[i] = timeWaiting[i+1];
		}

		elms[arraySize - 1] = null;
		priorities[arraySize - 1] = null;
		timeWaiting[arraySize - 1] = null;
		
		reutn ret_val;
	}
}
		
