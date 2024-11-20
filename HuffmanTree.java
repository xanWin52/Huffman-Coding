import java.util.HashMap;
import java.util.Map;

public class HuffmanTree implements IHuffConstants {
    private TreeNode root;
    private Map<Integer, String> codes;
    private int numLeaves;
    private int numInternal;
    private int[] freqArr;

    /*
     * Constructor that builds the Huffman tree using a given PriorityQueue of TreeNodes
     */
    public HuffmanTree(PriorityQueue314<TreeNode> frequencies, int[] arr){
        frequencies.add(new TreeNode(PSEUDO_EOF, 1));
        numLeaves = frequencies.size();
        while(frequencies.size() >= 2){
            TreeNode first = frequencies.dequeue();
            TreeNode second = frequencies.dequeue();
            TreeNode temp = new TreeNode(first, -1, second);
            frequencies.add(temp);
            numInternal ++;
        }
        root = frequencies.dequeue();
        codes = new HashMap<>();
        freqArr = arr;
        createMap(root, codes, "");
    }

    /*
     * Recursive map creation algorithm using preorder traversal
     */
    private void createMap(TreeNode node, Map<Integer, String> codes, String code){
        if(node.isLeaf()){
            codes.put(node.getValue(), code);
        } else {
            createMap(node.getLeft(), codes, code + "0");
            createMap(node.getRight(), codes, code + "1");
        }
    }

    /**
     * Method to calculate the number of bits needed to store the tree information using the 
     * STORE_TREE method.
     * @return The number of bits needed to write the header, not including the magic number.
     */
    public int calculateTreeHeaderLength(){
        //header consists of 32-bit integer that indicates number of bits to store the tree,
        //a 0 for each internal node, and (BITS_PER_WORD + 1) bits per number of leaves,
        // and the length of the PEOF.
        return BITS_PER_INT + numInternal + numLeaves * (BITS_PER_WORD + 1) 
            + codes.get(PSEUDO_EOF).length();
    }

    /**
     * Method to calculate the number of bits needed to store the tree information using the counts
     * @return The total number of bits needed to write the header, not including the magic number
     */
    public int calculateCountHeaderLength(){
        return BITS_PER_INT * ALPH_SIZE + codes.get(PSEUDO_EOF).length();
    }

    public int writeTree(BitOutputStream out, int headerFormat){
        int bitsWritten = 0;
        if(headerFormat == STORE_COUNTS){
            for(int i = 0; i < ALPH_SIZE; i ++){
                bitsWritten += BITS_PER_INT;
                out.writeBits(BITS_PER_INT, freqArr[i]);
            }
        } else if(headerFormat == STORE_TREE){
            bitsWritten = writeTreeHelper(root, out);
        }
        return bitsWritten;
    }

    private int writeTreeHelper(TreeNode cur, BitOutputStream out){
        if(cur == null){
            return 0;
        } else if(cur.isLeaf()){
            out.writeBits(1, 1);
            out.writeBits(BITS_PER_WORD + 1, cur.getValue());
            return 1 + BITS_PER_WORD + 1;
        } else {
            out.writeBits(1, 0);
            int result = 0;
            result += writeTreeHelper(cur.getLeft(), out) + writeTreeHelper(cur.getRight(), out); 
            return result;
        }
    }

    /**
     * Method to get the code associated with the given key
     * @param key The integer representation of the value that needs to be encoded
     * @return The string representation of the Huffman code or null if the key is nonexistant
     */
    public String get(int key){
        return codes.get(key);
    }

}