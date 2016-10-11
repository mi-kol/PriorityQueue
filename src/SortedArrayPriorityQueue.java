import java.util.Arrays;

public class SortedArrayPriorityQueue<QType> implements PQueue<QType> {
    public int arraySize = 10;
    private Q[] objs = new Q[arraySize];
    @SuppressWarnings("unchecked")
    private QType[] elms = (QType[]) new Object[arraySize];

    public QType dequeue() {
        for(;;);
    }

    public void enqueue(QType q, int pri) {
        Q newQ = new Q;
        newQ.setElm(q);
        newQ.setPriority(pri);
        for (int i = 0; i < arraySize; i++) {
            if (objs[i] == null) {
                newQ.setIndex(i);
                objs[i] = newQ;
                elms[i] = (QType) newQ.elm;
            } else {
                doubleQ(objs);
                doubleE(elms);
                arraySize *= 2;
                enqueue(q, pri);
            }
        }

    }

    public int size() {
        for(;;);
    }

    public Q[] doubleQ(Q[] array) {
        int doubleSize = array.length * 2;
        array = Arrays.copyOf(array, doubleSize);

        return array;
    }

    public QType[] doubleE(QType[] array) {
        int doubleSize = array.length * 2;
        array = Arrays.copyOf(array, doubleSize);

        return array;
    }

    public void sort() {
        Arrays.sort(objs);
    }


}

class Q<QType> implements Comparable<Q> {
    QType elm;
    int index;
    int priority;
    int timeWaiting = 0;

    public void setIndex(int fnIndex) {
        index = fnIndex;
    }

    public void setElm(QType fnElm) {
        this.elm = fnElm;
    }

    public QType getElm() {
        return this.elm;
    }

    public int getIndex() {
        return index;
    }

    public void setPriority(int fnPri) {
        priority = fnPri;
    }

    public int getPriority() {
        return priority;
    }

    public int ticker() {
        timeWaiting += 1;
    }

    @Override
    public int compareTo(Q compareQ) {
        int comparePriority = ((Q) compareQ).getPriority();

        return comparePriority - this.priority;
    }
}
