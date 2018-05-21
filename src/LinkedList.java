/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author csc190
 */
public class LinkedList
{    
    protected Node start;
    protected Node end ;

    public LinkedList(Node n)
    {
        start = n;
        end = findEnd();
    }

    public Node findEnd(){
    Node current = start;
    
    do{
        current=current.link;
    }while(current.link!=null);
    
    return current;
    }
    
    public boolean isEmpty()
    {
        return start == null;
    }

    public int size() {
    int size = 0;
    for(Node n = start; n.link != null; n = n.link){
       size++;
    }
    return size;
    }   

    public void insertAtStart(int val)
    {
        Node nptr = new Node(val, null);       
        if(start == null) 
        {
            start = nptr;
            end = start;
        }
        else 
        {
            nptr.setLink(start);
            start = nptr;
        }
    }

    public void insertAtEnd(int val)
    {
        Node nptr = new Node(val,null);       
        if(start == null) 
        {
            start = nptr;
            end = start;
        }
        else 
        {
            end.setLink(nptr);
            end = nptr;
        }
    }

    public void insertAtPos(int val , int pos)
    {
        Node nptr = new Node(val, null);                
        //Node ptr = end;
        Node ptr = start;
        pos = pos - 1 ;
        int size = size();
        for (int i = 1; i < size; i++) 
        {
            if (i == pos) 
            {
                Node tmp = ptr.getLink() ;
                ptr.setLink(nptr);
                nptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
    }

    public void deleteAtPos(int pos)
    {        
        int size = size();
        if (pos == 1) 
        {
            start = start.getLink(); 
            return ;
        }
        if (pos == size) 
        {
            Node s = start;
            Node t = start;
            while (s != end)
            {
                t = s;
                s = s.getLink();
            }
            end = t;
            end.setLink(null);
            size --;
            return;
        }
        Node ptr = start;
        pos = pos - 1 ;
        for (int i = 1; i < size - 1; i++) 
        {
            if (i == pos) 
            {
                Node tmp = ptr.getLink();
                tmp = tmp.getLink();
                ptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
    }    

    public void display()
    {
        int size = size();
        System.out.print("\nSingly Linked List = ");
        if (size == 0) 
        {
            System.out.print("empty\n");
            return;
        }    
        if (start.getLink() == null) 
        {
            System.out.println(start.getData() );
            return;
        }
        Node ptr = start;
        System.out.print(start.getData()+ "->");
        ptr = start.getLink();
        while (ptr.getLink() != null)
        {
            System.out.print(ptr.getData()+ "->");
            ptr = ptr.getLink();
        }
        System.out.print(ptr.getData()+ "\n");
    }
    
    /*public void tostring(){
        if(start!=null){
            Node current = start;
            System.out.print(current.data+"-->");
            while(current.link!=null){
                current = current.link;
                System.out.print(current.data+"-->");
            }
            System.out.println("NULL");
        }
        else{
            System.out.println("list empty");
        }
    }*/
}


