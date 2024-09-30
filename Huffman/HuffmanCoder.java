/* Notes from https://www.youtube.com/watch?v=XLfgeaYHinM
 * Huffman encoding : It's a lossless data compression algorithm , folder to zip, jpeg , fax machines
 * How does huffman coding work : any info is in form of 0's and 1's in the machine world . We will use 2 Hashmaps -> encoder (char to string) and decoder(string to char)
 * Step 1: pass the string (data aka feeder)
 * Step 2: Make a frequency map 
 * Step 3: For every key in freq map , create a node(char,freq,Node left right) and insert that node in a min heap (Priority Queue)
 * Step 4: Go through the heap , remove two elements and combine them make them left and right of new heap (interchangable)
 * Step 5: Make a tree and create a mapping from the heap for encoder and decoder
 * Step 6: How to encode and decode? eg string = abbccda (112 bits)
 * replace the string with encoder map , you will definitely get lesser bits
 * Tree makes sure no value is prefix of another value
 * 
 * Space complexity : O(N) ; Time complexity : 2*(n-1) * log n = O(n log n)
 */

 package Huffman;
 import java.util.*;

 class HuffmanCoder {
   HashMap<Character, String> encoder;
   HashMap<String, Character> decoder;
 
   private class Node implements Comparable<Node> {
     Character data;
     int cost; // frequency
     Node left;
     Node right;
 
     public Node(Character data, int cost) {
       this.data = data;
       this.cost = cost;
       this.left = null;
       this.right = null;
     }
 
     @Override
     public int compareTo(Node other) {
       return this.cost - other.cost;
     }
   }
 
   public HuffmanCoder(String feeder) throws Exception {
     HashMap<Character, Integer> fmap = new HashMap<>();
 
     for(int i=0; i < feeder.length(); i++) {
       char cc = feeder.charAt(i);
       if(fmap.containsKey(cc)) {
         int ov = fmap.get(cc);
         ov += 1;
         fmap.put(cc, ov);
       } else {
         fmap.put(cc, 1);
       }
     }
 
     Heap<Node> minHeap = new Heap<>();
     Set<Map.Entry<Character, Integer>> entrySet = fmap.entrySet();
 
     for(Map.Entry<Character, Integer> entry : entrySet) {
       Node node = new Node(entry.getKey(), entry.getValue());
       minHeap.insert(node);
     }
 
     while(minHeap.size() != 1) {
       Node first = minHeap.remove();
       Node second = minHeap.remove();
 
       Node newNode = new Node('\0', first.cost + second.cost);
       newNode.left = first;
       newNode.right = second;
 
       minHeap.insert(newNode);
     }
 
     Node ft = minHeap.remove();
 
     this.encoder = new HashMap<>();
     this.decoder = new HashMap<>();
 
     this.initEncoderDecoder(ft, "");
   }
 
   private void initEncoderDecoder(Node node, String osf) {
     if(node == null) {
       return;
     }
     if(node.left == null && node.right == null) {
       this.encoder.put(node.data, osf);
       this.decoder.put(osf, node.data);
     }
     initEncoderDecoder(node.left, osf+"0");
     initEncoderDecoder(node.right, osf+"1");
   }
 
   public String encode(String source) {
     String ans = "";
 
     // Bitset can be used: like an array but with a bit at each index
 
     for(int i=0; i<source.length(); i++) {
       ans = ans + encoder.get(source.charAt(i));
     }
     
     return ans;
   }
 
   public String decode(String codedString) {
     String key = "";
     String ans = "";
     for(int i=0; i<codedString.length(); i++) {
       key = key + codedString.charAt(i);
       if(decoder.containsKey(key)) {
         ans = ans + decoder.get(key);
         key = "";
       }
     }
     return ans;
   }
 }
