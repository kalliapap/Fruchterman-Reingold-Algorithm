//package graphimplementation;

public class myList {
    
    private Node head;
    static int size = 0;
    private int v;
    public myList(){}
    
    public void add(int value){
        if(head == null){ //if list is empty 
            Node tmp = new Node(value);
            tmp.setNext(null);
            this.head = tmp;
        }else{    // else add at the end of list    
            Node tmp = this.head;
            Node newNode = new Node(value);
            while(tmp.getNext()!=null){
                tmp = tmp.getNext();
            }
            tmp.setNext(newNode);
            newNode.setNext(null);
        }
        size++;
    }

    /*search value in list, if found return index else -1*/
    public int searchNode(int value){
        Node tmp = head;
        int count = 0;
        if(tmp == null){
            count = -1;
            System.out.println("List is empty.");
        }
        while(tmp != null){
            if(tmp.getNode() == value)
                break;
            count++;
            tmp = tmp.getNext();      
        }  
       return count;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }
    
    public void printList(){
        Node tmp = this.head;
        while(tmp != null){
            System.out.print(tmp.getNode()+" ");
            tmp = tmp.getNext();
        }
    }
    @Override
    public String toString(){
       
        return v+" ";
 
   }
}
