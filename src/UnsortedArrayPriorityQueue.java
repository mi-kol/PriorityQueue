public class UnsortedArrayPriorityQueue<QType> implements PQueue<QType>{

	public int arraySize = 10;
	public QType[] elms = new QType[arraySize];
	public Integer[] priorities = new Integer[arraySize];
	public Integer[] timeWaiting = new Integer[arraySize];

	public QType dequeue() {
		int highlander = -1;
		int newChallenger = -1;
		// keeps track of highest priority
		
		for (int i = 0; i < arraySize; i++) {
			if (priorities[i] > highlander) {
				highlander = i;
			} else if (priorities[i].equals(highlander)) {
				newChallenger = i;
				if (timeWaiting[newChallenger] > timeWaiting[highlander]) {
					highlander = newChallenger;
				} else if (timeWaiting[newChallenger].equals(timeWaiting[highlander])) {
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
		timeWaiting = null;

		for (int i = highlander; i < elms.length - 1; i++) {
			elms[i] = elms[i+1];
			priorities[i] = priorities[i+1];
			timeWaiting[i] = timeWaiting[i+1];
		}

		elms[arraySize - 1] = null;
		priorities[arraySize - 1] = null;
		timeWaiting[arraySize - 1] = null;
		
		return ret_val;
	}

	public void enqueue(QType q, int pri) {
		;;
	}

	public int size() {
		return elms.length;
		// TODO: Fix this, waiting for answer from Christian
	}
}
		
