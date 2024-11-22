/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, Xander Nguyen and Siddarth Saladi, this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 Siddharth Saladi (Student whose Canvas account is being used)
 *  UTEID: ss229786
 *  email address: sidsaladi@utexas.edu
 *  Grader name: Brad
 *
 *  Student 2 Xander Nguyen
 *  UTEID: xmn64
 *  email address: xmn64@my.utexas.edu
 *
 */
public class PriorityQueue314<E extends Comparable<? super E>> {

    private Node<E> front;
    private int size;
    
    public PriorityQueue314() {
        front = null;
    }

    public PriorityQueue314(E front) {
        this.front = new Node<E>(front, null);
        size = 1;
    }


    /**
     * Add to queue based on node weight
     */
    public void add(E val) {
        if(front == null || front.data.compareTo(val) > 0) {
            Node<E> temp = new Node<E>(val, front);
            front = temp;
            size ++;
        } else {
            Node<E> temp = front;
            while ((temp.next != null) && (temp.next.data.compareTo(val) <= 0)) {
                temp =  temp.next;
            }
            temp.next = new Node<E>(val, temp.next);
            size ++;
        }
    }

    /**
     * Size getter method
     * @return the size of the priority queue
     */
    public int size() {
        return size;
    }

    /**
     * Method to remove from the front of the priority queue
     * @return the data in the removed node
     */
    public E dequeue() {
        Node<E> temp = front;
        front = temp.next;
        temp.next = null;
        size --;
        return temp.data;
    }
    


    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node() {
            data = null;
            next = null;
        }
    
        public Node(E val, Node<E> next) {
            data = val;
            this.next = next;
        }
    }

    
}