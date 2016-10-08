import java.util.Arrays;

public class UnsortedArrayPriorityQueue<QType> implements PQueue<QType>{

	public int arraySize = 10;
	@SuppressWarnings("unchecked")
	private QType[] elms = (QType[]) new Object[arraySize];
	public Integer[] priorities = new Integer[arraySize];

	public QType dequeue() {
		int highlander = 0;
		int newChallenger = 0;
		// keeps track of highest priority
		for (int i = 0; i < arraySize; i++) {
			if ((priorities[i] > priorities[highlander]) && (priorities[i] != null) && (priorities[highlander] != null)) {
				highlander = i;
			}
			// try catching with NPE didn't work here. why?
			else if ((priorities[i] == priorities[highlander]) && (priorities[i] != null)) {
				newChallenger = i;
				if (newChallenger < highlander) {
					highlander = newChallenger;
				} else {
					newChallenger = 0;
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
				doubleQ(elms);
				doublePri(priorities);
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

	public QType[] doubleQ(QType[] array) {
		int doubleSize = array.length * 2;
		array = Arrays.copyOf(array, doubleSize);

		return array;
	}

	public Integer[] doublePri(Integer[] array) {
		int doubleSize = array.length * 2;
		array = Arrays.copyOf(array, doubleSize);

		return array;
	}
}