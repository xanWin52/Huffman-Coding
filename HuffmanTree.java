import java.io.IOException;
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
        numInternal = 0;
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

    public HuffmanTree(){
        root = null;
        codes = null;
        freqArr = null;
    }

    /*
     * Recursive map creation algorithm using preorder traversal
     */
    private void createMap(TreeNode node, Map<Integer, String> codes, String code){
        if(node != null){
            if(node.isLeaf()){
                codes.put(node.getValue(), code);
            } else {
                createMap(node.getLeft(), codes, code + "0");
                createMap(node.getRight(), codes, code + "1");
            }
        }
    }

    /**
     * Method to build the tree given the preorder traversal
     * @param count Counter for the number of bits in the flattened tree
     * @param in The file being written
     * @return The HuffmanTree built
     */
    public void buildTree(int[] count, BitInputStream in) throws IOException{
        root = buildTreeHelper(count, in);
        codes = new HashMap<>();
        createMap(root, codes, "");
    }

    private TreeNode buildTreeHelper(int[] count, BitInputStream in) throws IOException{
        if(count[0] < 0){
            return null;
        }
        int curChar = in.readBits(1);
        if(curChar == 0){
            count[0]--;
            TreeNode temp = new TreeNode(-1, -1);
            temp.setLeft(buildTreeHelper(count, in));
            temp.setRight(buildTreeHelper(count, in));
            return temp;
        } else if(curChar == 1){
            count[0] --;
            return new TreeNode(in.readBits(BITS_PER_WORD + 1), 0);
        }
        return null;
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
        return BITS_PER_INT + numInternal + numLeaves * (BITS_PER_WORD + 2) 
            + codes.get(PSEUDO_EOF).length();
    }

    /**
     * Method to calculate the number of bits needed to store the tree information using the counts
     * @return The total number of bits needed to write the header, not including the magic number
     */
    public int calculateCountHeaderLength(){
        return BITS_PER_INT * ALPH_SIZE + codes.get(PSEUDO_EOF).length();
    }

    /**
     * Method to write the STORE_COUNTS and STORE_TREE headers
     * @param out The file to be written to
     * @param headerFormat Either STORE_COUNTS or STORE_TREE
     * @return The number of bits written
     */
    public int writeTree(BitOutputStream out, int headerFormat){
        int bitsWritten = 0;
        if(headerFormat == STORE_COUNTS){
            for(int i = 0; i < ALPH_SIZE; i ++){
                bitsWritten += BITS_PER_INT;
                out.writeBits(BITS_PER_INT, freqArr[i]);
            }
        } else if(headerFormat == STORE_TREE){
            out.writeBits(BITS_PER_INT, numInternal + numLeaves * (BITS_PER_WORD + 2));
            bitsWritten += BITS_PER_INT;
            bitsWritten += writeTreeHelper(root, out);
        }
        return bitsWritten;
    }

    //Recursive method to write the flattened Tree
    private int writeTreeHelper(TreeNode cur, BitOutputStream out){
        if(cur == null){
            return 0;
        } else if(cur.isLeaf()){
            out.writeBits(1, 1);
            out.writeBits(BITS_PER_WORD + 1, cur.getValue());
            return 1 + BITS_PER_WORD + 1;
        } else {
            out.writeBits(1, 0);
            int result = 1;
            result += writeTreeHelper(cur.getLeft(), out) + writeTreeHelper(cur.getRight(), out); 
            return result;
        }
    }

    /**
     * Method to decode an encoded Huffman file and write the result to out
     * @param in The file being read
     * @param out The file being written to
     * @return The number of bits written
     */
    public int decodeAndWrite(BitInputStream in, BitOutputStream out) throws IOException{
        TreeNode cur = root;
        int bitsWritten = 0;
        boolean done = false;
        while(!done){
            int dir = in.readBits(1);
            if(dir == -1){
                throw new IOException("File terminated without reaching the EOF character");
            }

            if(dir == 0){
                cur = cur.getLeft();
            } else if(dir == 1){
                cur = cur.getRight();
            }

            if(cur.isLeaf()){
                if(cur.getValue() == PSEUDO_EOF){
                    done = true;
                } else {
                    out.writeBits(BITS_PER_WORD, cur.getValue());
                    bitsWritten += BITS_PER_WORD;
                    cur = root;
                }
            }

        }
        return bitsWritten;
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