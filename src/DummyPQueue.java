import java.util.ArrayList;

public class DummyPQueue<QType> implements PQueue<QType>{
	
	private ArrayList<QType> elms = new ArrayList<QType>();

	public QType dequeue() {
		return elms.remove(0);
	}

	public int size() {
		return elms.size();
	}

	public void enqueue(QType elm, int p) {
		elms.add(elm);
	}

}
