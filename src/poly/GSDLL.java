package poly;

import java.util.Iterator;

/**
 * GSDLL : Generic Sorted Doubly Linked List public methods: <br>
 * void insert(T val) bool <br>
 * contains(T val) bool <br>
 * remove(T val) (still missing)<br>
 * T getHead() <br>
 * T getTail() <br>
 * String toString()
 *
 * @author MCO264
 * @param <T> T extends Comparable
 */
public class GSDLL<T extends Comparable<? super T>>
{

    //<editor-fold defaultstate="collapsed" desc="Node">
    private class Node<T extends Comparable<? super T>>
    {
        private T info;
        private Node next = null;
        private Node prev = null;

        private Node(T val)
        {
            info = val;
        }

    }
    //</editor-fold>

    private Node head;
    private Node tail;

    public GSDLL()
    {
        head = tail = null;
    }

    /**
     * insert:  inserts T value in its proper place in the list
     * @param value of generic type T to be inserted
     */
    public void insert(T value)
    {
        Node node = new Node(value);
        if (head == null)
        {
            head = tail = node;
        }
        else
        {
            Node curr = head;
            int cmprCurr2Value = curr.info.compareTo(value);
            if (head == tail)
            {
                if (cmprCurr2Value <= 0)
                {
                    insertBetween(tail, node, null);
                    tail = node;
                }
                else
                {
                    insertBetween(null, node, head);
                    head = node;
                }
            }
            else
            {
                while (curr != tail && cmprCurr2Value < 0)
                {
                    curr = curr.next;
                    cmprCurr2Value = curr.info.compareTo(value);
                }
                if (curr == tail)
                {
                    if (cmprCurr2Value < 0)
                    {
                        insertBetween(tail, node, null);
                        tail = node;
                    }
                    else
                    {
                        insertBetween(tail.prev, node, tail);
                    }

                }
                else if (curr == head)
                {
                    insertBetween(null, node, head);
                    head = node;

                }
                else
                {
                    insertBetween(curr.prev, node, curr);
                }

            }

        }

    }

    public T getHead()
    {
        T retVal = null;
        if (head != null) retVal = (T)head.info;
        return retVal;
    }
    public T getTail()
    {
        T retVal = null;
        if (tail != null) retVal = (T)tail.info;
        return retVal;
    }
    /**
     * locate a node in a sorted doubly linked list.
     *
     * @param value: T - value to locate using compareTo method
     * @return boolean true on success, false on failure
     */
    public boolean locate(T value)
    {
        boolean isLocated = false;
        if (head != null && value != null)
        {
            Node curr = traverse(value);
            if (curr != null && curr.info.compareTo(value) == 0)
            {
                isLocated = true;
            }
        }
        return isLocated;
    }

    // return the largest node with a value less than or equal to "value"
    //        or null, if value < head.info
    private Node traverse(T value)
    {
        Node curr = null;
        if (head != null && head.info.compareTo(value) <= 0)
        {
            curr = head;
            while (curr.next != null && curr.next.info.compareTo(value) <= 0)
            {
                curr = curr.next;
            }
        }
        return curr;
    }

    // insert the given Node between other two nodes (either of which could be null)
    private void insertBetween(Node prevNode, Node thisNode, Node nextNode)
    {
        thisNode.next = nextNode;
        thisNode.prev = prevNode;
        if (prevNode != null)
        {
            prevNode.next = thisNode;
        }
        if (nextNode != null)
        {
            nextNode.prev = thisNode;
        }
    }

    public String toString()
    {
        String str = "<-";
        Node curr = head;
        while (curr != null)
        {
            str += ("|" + curr.info + "|<->");
            curr = curr.next;
        }
        return str.substring(0, str.length());
    }
    //     public boolean remove(T value)
//    {
//        boolean found = false;
//        if (head != null)
//        {
//            if (head.info.equals(value))
//            {
//                found = true;
//                head = head.next;
//            }
//            else
//            {
//                Node<T> curr = head;
//                while (curr.next != null)
//                {
//                    if (curr.next.info.equals(value))
//                    {
//                        /*if (curr.next.info instanceof Monomial)
//                        {
//                            Monomial currM = (Monomial) curr.next.info;
//
//                            if (currM.coeffEquals(value))
//                            {
//                                found = true;
//                        curr.next = curr.next.next;
//                        curr.next.prev = curr.prev;
//                        break;
//                            }
//                        }
//                        else
//                        {**/
//                        found = true;
//                        curr.next = curr.next.next;
//                        curr.next.prev = curr.prev;
//                        break;
//                        //}
//                    }
//                    curr = curr.next;
//                }
//            }
//        }
//        return found;
//    }
    public boolean remove(T element) {

        boolean retVal = false;
        Node n = head;

        if (head.info.equals(element)) {
            head = head.next;
        } else if (tail.info.equals(element)) {
            tail.prev.next = null;
        } else {
            while (n != tail) {
                if (n.info.equals(element)) {
                    n.prev.next = n.next;
                    n.next.prev = n.prev;
                    retVal = true;
                    break;
                }
                n = n.next;
            }
        }
        return retVal;
    }


    //<editor-fold defaultstate="collapsed" desc="iterator">
    public Iterator<T> iterator()
    {
        return new LocalIterator(this);
    }

    private class LocalIterator<T extends Comparable<? super T>> implements Iterator<T>
    {

        private GSDLL<T> localList;
        private Node curr; // = head;

        public LocalIterator(GSDLL<T> list)
        {
            localList = list;
            curr = localList.head;
        }

        public boolean hasNext()
        {
            return curr != null;
        }

        public T next()
        {
            T retVal = (T) curr.info;
            curr = curr.next;
            return retVal;
        }

    }
    //</editor-fold>
}
