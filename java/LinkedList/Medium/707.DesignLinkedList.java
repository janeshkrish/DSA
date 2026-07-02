class Node{
    int data;
    Node next;
    Node(int val){
        this.data = val;
        this.next = null;
    }
}
class MyLinkedList {
    int c = -1;
    Node head;
    public MyLinkedList() {
         head = null;
    }
    
    public int get(int index) {
        if (index < 0 || index > c || head == null) {
            return -1;
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }   
    
    public void addAtHead(int val) {
        Node newnode = new Node(val);
        newnode.next = head;
        head = newnode;
        c++;
    }
    
    public void addAtTail(int val) {
        if(c <= -1){
            addAtHead(val);
            return;
        }
        Node newnode = new Node(val);
        Node temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = newnode;
        c++;
    }
    
    public void addAtIndex(int index, int val) {
        if(index < 0 || index > c+1){
            System.out.println("invalid index");
            return;
        }
        if(index == 0){
            addAtHead(val);
            return;
        }
        if(index == c + 1){
            addAtTail(val);
            return;
        }
        Node newnode = new Node(val);
        Node temp = head;
        for(int i=0;i<index-1;i++){
            temp = temp.next;
        }
        newnode.next = temp.next;
        temp.next = newnode;
        c++;
    }
    
    public void deleteAtIndex(int index) {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        if (index < 0|| index > c) {
            System.out.println("Invalid index");
            return;
        }
        if (index == 0) {
            head = head.next;
            c--;
            return;
        }
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        c--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */