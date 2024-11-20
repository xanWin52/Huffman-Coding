import java.util.HashMap;
import java.util.Map;

public class HuffmanTree implements IHuffConstants {
    private TreeNode root;
    private Map<Integer, String> codes;
    private int numLeaves;
    private int numInternal;

    /*
     * Constructor that builds the Huffman tree using a given PriorityQueue of TreeNodes
     */
    public HuffmanTree(PriorityQueue314<TreeNode> frequencies){
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
        createMap(root, codes, "");
        for (int g : codes.keySet()) {
            System.out.println( g + ": " + codes.get(g));
        }
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

    public int calculateTreeHeader(){
        //header consists of 32-bit integer that indicates number of bits to store the tree,
        //a 0 for each internal node, and (BITS_PER_WORD + 1) bits per number of leaves
        return BITS_PER_INT + numInternal + numLeaves * (BITS_PER_WORD + 1);
    }

    public int calculateCountHeader(){
        //header consists of a 32 bit integer for each of the possible alpha values.
        return BITS_PER_INT * ALPH_SIZE;
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