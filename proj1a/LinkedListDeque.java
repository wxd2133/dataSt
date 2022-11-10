public class LinkedListDeque<type> {
    public class node {
        public type data;
        public node next;
        public node last;
        public node (type a,node next0,node last0){
            data=a;
            next=next0;
            last=last0;
        }
    }
    private node head;
    private int size;
    public  LinkedListDeque(){
        this.head =new node(null,null,null);
        node t=new node(null,head,head);
        head.next=t;
        head.last=t;

        this.size=0;
    }

    public int size() {
        return size;
    }
    public void addFirst(type a){

        node temp= new node(a,head.next,head);

        head.next.last=temp;
        head.next =temp;
        size++;
    }
    public void addLast(type a){
        node temp= new node(a,head.last,head.last.last);
        head.last.last.next=temp;
        temp.next=head.last;
        size++;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void printDeque(){
        node temp=head.next;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
    }
    public type removeFirst(){
        if(size!=0){
        type a=head.next.data;
        head.next=head.next.next;
        head.next.last=head;
        size--;
        return a;
        }
        else
            return null;
    }
    public type removeLast(){
        if(size!=0){
        type a=head.last.last.data;
        head.last.last=head.last.last.last;
        head.last.last.next=head.last;
        size--;
        return a;
        }
        else
            return null;
    }
    public type get(int n){
        node t=head.next;
        for (int i = 0; i < n; i++) {
            if(t.next!=null)
                t=t.next;
            else
                return null;
        }
        return t.data;
    }
    public type getRecursive(int index){
        node t=head.next;
        if(index==0)
            return t.data;
        else{
            t=t.next;
            return getRecursive(index-1);
        }

    }

}
