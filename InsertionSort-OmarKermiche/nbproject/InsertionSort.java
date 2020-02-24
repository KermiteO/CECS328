/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertionsort;

public class InsertionSort {

    Node head = null;
    int listSize = 0;
    class Node
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
              
        while(!found && count <= listSize)
        {
            if(count == n)
            {
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
        for(int i = 1; i <= doubleLinkList.listSize; i++)
        {
            System.out.print(doubleLinkList.getNthNode(i).text + " ");
        }
        System.out.println();
    }   
}
