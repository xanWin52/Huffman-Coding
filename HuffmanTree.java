import java.util.Map;
import java.util.HashMap;

public class HuffmanTree {
    private TreeNode root;
    private Map<Integer, String> codes;

    /*
     * Constructor that builds the Huffman tree using a given PriorityQueue of TreeNodes
     */
    public HuffmanTree(PriorityQueue314<TreeNode> frequencies){
        while(frequencies.size() >= 2){
            TreeNode first = frequencies.dequeue();
            TreeNode second = frequencies.dequeue();
            TreeNode temp = new TreeNode(first, -1, second);
            frequencies.add(temp);
        }
        root = frequencies.dequeue();
        codes = new HashMap<>();
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
     * Method to get the code associated with the given key
     * @param key The integer representation of the value that needs to be encoded
     * @return The string representation of the Huffman code or null if the key is nonexistant
     */
    public String get(int key){
        return codes.get(key);
    }

}