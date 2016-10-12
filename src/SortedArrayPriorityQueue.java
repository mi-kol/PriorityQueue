import java.util.Arrays;

public class SortedArrayPriorityQueue<QType> implements PQueue<QType> {
    public int arraySize = 10;
    private Q[] objs = new Q[arraySize];
    public QType[] elms = (QType[]) new Object[arraySize];

    public QType dequeue() {
        int highestPriorityLevel = 0;
        int forRemoval = 0;
        int noContradictions = 0;

        Integer[] highestPriorityElmIndexes = new Integer[objs.length];

        for (int i = 0; i < objs.length; i++) {
            if ((objs[i] != null) && (objs[i].getPriority() > highestPriorityLevel)) {
                highestPriorityLevel = objs[i].getPriority();
            }
        }

        for (int i = 0; i < objs.length; i++) {
            if ((objs[i] != null) && (objs[i].getPriority() == highestPriorityLevel)) {
                highestPriorityElmIndexes[noContradictions] = i;
                noContradictions++;
            }
        }

        forRemoval = highestPriorityElmIndexes[0];

        QType removed = (QType) objs[forRemoval].getElm();
        objs[forRemoval] = null;

        for (int i = forRemoval; i < objs.length - 1; i++) {
            objs[i] = objs[i + 1];
        }
    }

    public void enqueue(QType q, int pri) {
        Q newQ = new Q;
        newQ.setElm(q);
        newQ.setPriority(pri);
        for (int i = 0; i < arraySize; i++) {
            if (objs[i] == null) {
                newQ.setIndex(i);
                objs[i] = newQ;
            } else {
                doubleQ(objs);
                doubleE(elms);
                arraySize *= 2;
                enqueue(q, pri);
            }
        }
        Arrays.sort(objs);
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

    public void elmRipper(Q[] array) {
        for (int i = 0; i < objs.length; i++) {
            elms[i] = (QType) objs[i].getElm();
        }
    }


}

class Q<QType> implements Comparable<Q> {
    public QType elm;
    private int index;
    private int priority;
    private int timeWaiting = 0;

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

    public void ticker() {
        timeWaiting += 1;
    }

    @Override
    public int compareTo(Q compareQ) {
        int comparePriority = ((Q) compareQ).getPriority();

        return comparePriority - this.priority;
    }
}