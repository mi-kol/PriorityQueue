import java.util.Arrays;

public class UnsortedArrayPriorityQueue<QType> implements PQueue<QType>{

	public int arraySize = 10;
	public QType[] elms = (QType[]) new Object[arraySize];
	public Integer[] priorities = new Integer[arraySize];

	public QType dequeue() {
		int highlander = -1;
		int newChallenger = -1;
		// keeps track of highest priority
		
		for (int i = 0; i < arraySize; i++) {
			if (priorities[i] > highlander) {
				highlander = i;
			} else if (priorities[i].equals(highlander)) {
				newChallenger = i;
				if (newChallenger < highlander) {
					highlander = newChallenger;
				} else if (newChallenger == highlander) {
					System.out.println("What did you DO?");
				} else {
					newChallenger = -1;
				}
			}
		}
		// Finds the highest priority, breaks ties between same priorities
		
		QType ret_val = elms[highlander];
		elms[highlander] = null;
		priorities[highlander] = null;

		for (int i = highlander; i < elms.length - 1; i++) {
			elms[i] = elms[i+1];
			priorities[i] = priorities[i+1];
		}

		elms[arraySize - 1] = null;
		priorities[arraySize - 1] = null;
		
		return ret_val;
	}

	public void enqueue(QType q, int pri) {
		for (int i = 0; i < arraySize; i++) {
			if ((elms[i] == null) && (priorities[i] == null)) {
				elms[i] = q;
				priorities[i] = pri;
			} else {
				doubleDown(elms);
				doubleDown(priorities);
			}
		}
	}

	public int size() {
		// This also doubles as data integrity verification to make sure all three arrays are working in tandem.
		int elmsize = 0;
		int psize = 0;
		
		for (int i = 0; i < arraySize; i++) {
			if (elms[i] != null) {
				elmsize++;
			}
			if (priorities[i] != null) {
				psize++;
			}
		}

		if (elmsize == psize) {
			return elmsize;
		} else {
			System.out.println("All these values should be the same!");
			System.exit(0);
			return -1;
		}
	}

	private <T>T[] doubleDown(T[] myArray) {
		int new_size = myArray.length *2;
		myArray = Arrays.copyOf(myArray, new_size);

		return myArray;
	}
}