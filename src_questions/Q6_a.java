//Question 6
//        a)	Implement Huffman encoding and decoding.


import java.util.*;

//Huffmantreenode
class MinHeapNode{
    char data;
    int freq;
    MinHeapNode left,right;

    MinHeapNode(char data,int freq){
        left=right=null;
        this.data=data;
        this.freq=freq;
    }
}

//In order to compare two nodes.
class Compare implements Comparator<MinHeapNode>{
    public int compare(MinHeapNode l,MinHeapNode r){
        return l.freq-r.freq;
    }

}
public class Q6_a {
    //Print
    static void printCodes(MinHeapNode root,String str){
        //If root is Null then return.
        if(root==null){
            return;
        }
        //Print the text if the node's data does not begin with "$," which indicates that it is not an internal node.
        if(root.data!='$'){
            System.out.println(root.data+": "+str);
        }

        printCodes(root.left,str+"0");
        printCodes(root.right,str+"1");
    }

    //Build Huffman Tree
    static void HuffmanCodes(char data[],int freq[],int size){

        MinHeapNode left,right,top,tmp;

        //Min heap is created
        PriorityQueue<MinHeapNode> minheap = new PriorityQueue<>(size,new Compare());

        // For each character, make a leaf node, and then add it to the heap.
        for(int i=0;i<size;i++){
            minheap.add(new MinHeapNode(data[i],freq[i]));
        }

        //Repeat as long as the size of the minimum heap doesn't reach 1.
        while(minheap.size()!=1){
            //Extract two nodes from the heap.
            left = minheap.poll();

            right = minheap.poll();

            tmp = new MinHeapNode('$',left.freq+right.freq);
            tmp.left = left;
            tmp.right = right;

            minheap.add(tmp);
        }
        //Calling function to print
        printCodes(minheap.peek()," ");
    }

    public static void main(String[] args) {

        char arr[] = {'a','b','c','d','e'};
        int freq[] = {10,5,2,14,15};

        int size=5;
        HuffmanCodes(arr,freq,size);

    }

}

