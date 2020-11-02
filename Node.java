//package graphimplementation;

public class Node {
    
    private int node;
    private Node next;
    
    public Node(){}
    public Node(int node){
        this.node = node;
        this.next = null;
    }
    
    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    
    
}
