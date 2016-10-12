import java.util.Arrays;

public class UnsortedArrayPriorityQueue<QType> implements PQueue<QType>{

	public int arraySize = 10;
	@SuppressWarnings("unchecked")
	private QType[] elms = (QType[]) new Object[arraySize];
	public Integer[] priorities = new Integer[arraySize];
	// Initializing vars

	public QType dequeue() {
		int highestPriorityLevel = 0;
		// keeps track of the highest priority level
		int forRemoval = 0;
		// keeps track of which elm will be removed
		int noContradictions = 0;
		// makes sure there's no contradictions when adding to the highestPriorityElmIndexes

		Integer[] highestPriorityElmIndexes = new Integer[priorities.length];  // Start with this length so we always have enough space
		// Keeps track of all indexes that match the highestPriorityLevel

		// Finds highest priority
		for (int i = 0; i < priorities.length; i++) {
			if ((priorities[i] != null) && (priorities[i] > highestPriorityLevel)) {
				// Why can't I try/catch this? Also, why is this even a problem? Is there a workaround?
				highestPriorityLevel = priorities[i];
			}
		}

		// Finds all elms that match the highest priority and push them in highestPriorityElmIndexes
		for (int i = 0; i < elms.length; i++) {
			if ((priorities[i] != null) && (priorities[i] == highestPriorityLevel)) {
				highestPriorityElmIndexes[noContradictions] = i;
				noContradictions++;
			}
		}

		forRemoval = highestPriorityElmIndexes[0];

		QType removed = elms[forRemoval];
		elms[forRemoval] = null;
		priorities[forRemoval] = null;

		for (int i = forRemoval; i < elms.length - 1; i++) {
			elms[i] = elms[i + 1];
			priorities[i] = priorities[i + 1];
		}
		// Shifts everything to accommodate the removal

		elms[elms.length - 1] = null;
		priorities[priorities.length - 1] = null;

		return removed;
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
	// Pushes things to the priority queue. Doubles sizes of arrays if needs more space.

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
	// Here I made two different functions, because of typing issues. Using a statically typed language like Java, how should I do this?
	public Integer[] doublePri(Integer[] array) {
		int doubleSize = array.length * 2;
		array = Arrays.copyOf(array, doubleSize);

		return array;
	}

}