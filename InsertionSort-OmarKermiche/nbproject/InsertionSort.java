/*************************************************************************
 * NAME         : OMAR KERMICHE
 * CLASS/SECTION: CECS 328/TU/THUR 5:00-5:50
 * ASSIGNMENT   : LAB 1-INSERTION SORT
 * DATE         : 2/3/20
 *************************************************************************/
package insertionsort;

/*
steps for passing through cmd:
C:\Users\kermi>cd C:\MyFolder

C:\MyFolder>javac -d . InsertionSort.java

C:\MyFolder>java insertionsort.InsertionSort apple test going zebra aardvark
*/
class InsertionSort 
{
    Node head = null;
    int listSize = 0;
    public class Node
    {
        Node prev = null;
        String text;
        Node next = null;
        
        Node(String t){text = t;}      
    }
   
    InsertionSort() {}
    public void push(String newText)
    {
        //create new node with new text in it
        Node newNode = new Node(newText);
        
        //append new node to the front of the doubly linked list      
        newNode.next = head;
        newNode.prev = null;
        
        //point the previous head node if there is one to the new head node
        if(head != null)
            head.prev = newNode;
        
        //set head to new node
        head = newNode;
        listSize++;
    }
    
    
    public Node getNthNode(int n)
    {
        int     count = 1;
        boolean found = false;
        Node    ptr   = head;
        Node    foundedNode = null;
              
        //search double link list until nth node is found
        while(!found && count <= listSize)
        {
            if(count == n)
            {
                //once found, set return node to pointer
                found = true;
                foundedNode = ptr;
            }
            
            ptr = ptr.next;
            count++;
        }
        
        return foundedNode;
    }
    
    public void reverseList()
    {
        //Purpose of this function is to reverse the contents of link list to 
        //match the input exactly the way it was passed 
        Node ptr = head;
        int j = 0;
        String[] arr = new String[listSize];
        for(int i = listSize; i > 0; i--)
        {
            arr[j] = getNthNode(i).text;
            
            j++;
        }
        
        for(int i = 0; i<listSize;i++)
        {
            ptr.text = arr[i];
            ptr = ptr.next;
        }
    }
    
    public void shiftKey(String key)
    {
        //shift the key to its new position after all the other nodes have been
        //shifted to the right
        boolean found = false;
        Node ptr = head;
        
        while(!found && ptr != null)
        {
            if(ptr.text.equals(""))
            {
                found = true;
                ptr.text = key;
            }
            
            ptr = ptr.next;
        }
    }
    
    public void shiftReplace(String    key, 
                             String newText)
    {
        Node ptr = head;
        int count = 1;
        boolean found = false;
         
        while(!found && ptr != null)
        {
            if(ptr.next != null)
            {
                if(ptr.next.text.equals(key))
                {
                    found = true;
                    ptr.next.text = newText;
                    ptr.text = key;
                }
            }
            ptr = ptr.next;          
        }
    }
    
    public void sort()
    {
        //where the actual insertion sort happens and every other method is
        //used just for the purpose of making this function work
        String [] keys = new String[listSize];
        
        for(int i = 0; i < listSize; i++)
        {
            keys[i] = getNthNode(i + 1).text;
        }
        
        for(int j = 2; j <= listSize; j++)
        {    
            Node key = new Node(keys[j-1]);
            
            //insert A[j] into sorted sequence
            int i = j - 1;
            
            while(i >= 1 && getNthNode(i).text.compareTo(key.text) > 0)
            {
                shiftReplace(key.text,getNthNode(i).text);
                i--;               
            }
            shiftKey(key.text);             
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        InsertionSort doubleLinkList = new InsertionSort();
        
        for(int i = 0; i< args.length; i++)
        {
            doubleLinkList.push(args[i]);
        }
        
        doubleLinkList.reverseList();

        doubleLinkList.sort();
        System.out.println();
        for(int i = 1; i <= doubleLinkList.listSize; i++)
        {
            System.out.print(doubleLinkList.getNthNode(i).text + " ");
        }
        System.out.println();
    }   
}
