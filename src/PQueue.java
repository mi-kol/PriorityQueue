
public interface PQueue<T> {
	public void enqueue(T elm, int p);
	public T dequeue();
	public int size();
}
