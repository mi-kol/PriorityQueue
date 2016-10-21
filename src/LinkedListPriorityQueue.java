/**
 * Created by start on 10/17/2016.
 */

import java.util.LinkedList;

public class LinkedListPriorityQueue<QType> implements PQueue<QType> {
    public int possibleIndex = 0;
    private LinkedList<Node> objs = new LinkedList<Node>();

    public void enqueue(QType q, int pri) {
        Node newNode = new Node();
        newNode.setElm(q);
        newNode.setPriority(pri);
        objs.add(possibleIndex, newNode);
        possibleIndex++;
    }

    public int size() {
        return objs.size();
    }

    public QType dequeue() {
        return (QType) objs.removeFirst().getElm();
    }
}

class Node<QType> implements Comparable<Q> {
    public QType elm;
    private int index;
    private int priority;
    private int timeWaiting = 0;
    public int head;
    public int tail;

    public void setIndex(int fnIndex) { index = fnIndex; }

    public void setElm(QType fnElm) { this.elm = fnElm; }

    public QType getElm() { return this.elm; }

    public int getIndex() {return index; }

    public void setPriority(int fnPri) { priority = fnPri; }

    public int getPriority() {return priority; }

    public void ticker() {timeWaiting++; }

    @Override
    public int compareTo(Q compareQ) {
        int comparePriority = ((Q) compareQ).getPriority();

        return comparePriority - this.priority;
    }
}
