
public interface PQueue<QType> {
	public void enqueue(QType elm, int p);
	public QType dequeue();
	public int size();
}
