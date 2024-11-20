/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, Xander Nguyen and Siddarth Saladi, this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 Xander Nguyen (Student whose Canvas account is being used)
 *  UTEID: xmn64
 *  email address: xmn64@my.utexas.edu
 *  Grader name: Brad
 *
 *  Student 2 Siddarth Saladi
 *  UTEID: ss229786
 *  email address: sidsaladi@utexas.edu
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleHuffProcessor implements IHuffProcessor {

    private HuffmanTree hTree;
    private IHuffViewer myViewer;

    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     * @param in is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind of
     * header to use, standard count format, standard tree format, or
     * possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     * Note, to determine the number of
     * bits saved, the number of bits written includes
     * ALL bits that will be written including the
     * magic number, the header format number, the header to
     * reproduce the tree, AND the actual data.
     * @throws IOException if an error occurs while reading from the input file.
     */
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {

        BitInputStream preProcess = new BitInputStream(in);
        int[] allBits = new int[ALPH_SIZE];
        int isNeg = preProcess.readBits(BITS_PER_WORD);
        // readBits gives -1 when file is done
        while (isNeg != -1) {
            // inc freq at that spot
            allBits[isNeg]++;
            isNeg = preProcess.readBits(BITS_PER_WORD);
        }
        preProcess.close();
        PriorityQueue314<TreeNode> ogQueue = new PriorityQueue314<>();
        int ogCharacters = 0;
        for (int x = 0; x < ALPH_SIZE; x++) {
            // adding node with val of character and freq
            ogQueue.add(new TreeNode(x, allBits[x]));
            ogCharacters += allBits[x];
        }
        // queue is now populated
        hTree = new HuffmanTree(ogQueue);

        int ogBits = ogCharacters * BITS_PER_WORD;
        int encBits = 0;
        for (int x = 0; x < ALPH_SIZE; x++) {
            encBits += allBits[x] * htree.get(x).length();
        }
        // for magic number and header
        encBits += BITS_PER_INT * 2;

        // check if store counts or tree
        if (headerFormat == STORE_COUNTS) {
            encBits += hTree.calculateCountHeader();
        }
        else if (STORE_TREE == headerFormat) {
            encBits += hTree.calculateTreeHeader();
        }
        else {
            throw new IllegalArgumentException("header format paramater is invalid");
        }
        // return diff between original and huffman bits
        return ogBits - encBits;
    }

    /**
	 * Compresses input to output, where the same InputStream has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br> pre: <code>preprocessCompress</code> must be called before this method
     * @param in is the stream being compressed (NOT a BitInputStream)
     * @param out is bound to a file/stream to which bits are written
     * for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than the input file.
     * If this is false do not create the output file if it is larger than the input file.
     * @return the number of bits written.
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        throw new IOException("compress is not implemented");
        //return 0;
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     * @param in is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {
	        throw new IOException("uncompress not implemented");
	        //return 0;
    }

    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    private void showString(String s){
        if (myViewer != null) {
            myViewer.update(s);
        }
    }
}
