public class PriorityQueue314<E extends Comparable<? super E>> {

    private Node<E> front;
    private int size;
    
    public PriorityQueue314(){
        front = null;
    }

    public PriorityQueue314(E front){
        this.front = new Node<E>(front, null);
        size = 1;
    }


    /**
     * Add to queue based on node weight
     */
    public void add(E val){
        if(front == null || front.data.compareTo(val) > 0){
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
    public int size(){
        return size;
    }

    /**
     * Method to remove from the front of the priority queue
     * @return
     */
    public E dequeue(){
        Node<E> temp = front;
        front = temp.next;
        temp.next = null;
        size --;
        return temp.data;
    }
    
    /*
     * Wrote for testing purposes
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PriorityQueue314: ");
        Node<E> current = front;

        while (current != null) {
            if (current.data instanceof TreeNode) {
                TreeNode treeNode = (TreeNode) current.data;

                sb.append("[Freq: ").append(treeNode.getFrequency());
                sb.append(", Left: ").append(treeNode.getLeft() != null ? treeNode.getLeft().getFrequency() : "null");
                sb.append(", Right: ").append(treeNode.getRight() != null ? treeNode.getRight().getFrequency() : "null");
                sb.append("]");
            } else {
                sb.append(current.data.toString());
            }

            if (current.next != null) {
                sb.append(" -> ");
            }

            current = current.next;
        }

        if (front == null) {
            sb.append("Empty");
        }

        return sb.toString();
    }



    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(){
            data = null;
            next = null;
        }
    
        public Node(E val, Node<E> next){
            data = val;
            this.next = next;
        }
    }

    
}