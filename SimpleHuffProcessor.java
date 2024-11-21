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
    private int spaceSaved;
    private int headerFormat;

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

            //showString(isNeg + "");
            allBits[isNeg]++;
            isNeg = preProcess.readBits(BITS_PER_WORD);
        }
        // native array is populated
        preProcess.close();
        PriorityQueue314<TreeNode> ogQueue = new PriorityQueue314<>();
        int ogBits = 0;
        for (int x = 0; x < ALPH_SIZE; x++) {
            // adding node with val of character and freq
            if (allBits[x] != 0) {
                ogQueue.add(new TreeNode(x, allBits[x]));
                ogBits += allBits[x] * BITS_PER_WORD;
            }
        }
        // queue is now populated
        hTree = new HuffmanTree(ogQueue, allBits);

        int encBits = 0;
        // Counts bits in compressed
        for (int x = 0; x < ALPH_SIZE; x++) {
            if (!(hTree.get(x) == null)){
                encBits += allBits[x] * hTree.get(x).length();
            }
        }
        // for magic number and header
        encBits += BITS_PER_INT * 2;

        // check if store counts or tree
        encBits += hTree.calculateHeaderLength(headerFormat);
        // return diff between original and huffman bits
        spaceSaved = ogBits - encBits;
        this.headerFormat = headerFormat;
        return spaceSaved;
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

        if(spaceSaved < 0 && !force) {
            return -1;
        } else {
            //COMPRESSING
            int bitsWritten = 0;

            BitInputStream bitsIn = new BitInputStream(in);
            BitOutputStream bitsOut = new BitOutputStream(out);

            //Writing magic number and the number indicating the header format
            bitsOut.writeBits(BITS_PER_INT, MAGIC_NUMBER);
            bitsOut.writeBits(BITS_PER_INT, headerFormat);
            bitsWritten += BITS_PER_INT * 2;

            //Writing body of the header which changes depending on the headerFormat
            bitsWritten += hTree.writeTree(bitsOut, headerFormat);

            //Reading in BITS_PER_WORD bits in at a time
            int curCharacter = bitsIn.readBits(BITS_PER_WORD);
            while(curCharacter != -1){
                String huffCode = hTree.get(curCharacter);
                bitsOut.writeBits(huffCode.length(), Integer.parseInt(huffCode, 2));
                bitsWritten += huffCode.length();
                curCharacter = bitsIn.readBits(BITS_PER_WORD);
            }

            //Writing the PSEUDO_EOF value
            String PEOFcode = hTree.get(PSEUDO_EOF);
            bitsOut.writeBits(PEOFcode.length(), Integer.parseInt(PEOFcode, 2));
            bitsWritten += PEOFcode.length();
            bitsOut.close();
            bitsIn.close();
            showString(bitsWritten + "");
            return bitsWritten;
        }
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
	    BitInputStream bitIn = new BitInputStream(in);
        BitOutputStream bitOut = new BitOutputStream(out);
        int bitsWritten = 0;
    
        //Check that the file being decompressed is a Huffman coded file
        int magicNum = bitIn.readBits(BITS_PER_INT);
        if(magicNum != MAGIC_NUMBER){
            bitIn.close();
            bitOut.close();
            throw new IOException("This file is not a Huffman coded file");
        }

        //Getting format of header
        headerFormat = bitIn.readBits(BITS_PER_INT);
        
        //Reading header information and building the huffman tree
        HuffmanTree key = readHeader(bitIn, headerFormat);

        //decoding huffman file and counting the number of bits written
        bitsWritten += key.decodeAndWrite(bitIn, bitOut);
        bitIn.close();
        bitOut.close();
        return bitsWritten;
    }

    //Private helper method to read the header information and build the HuffmanTree 
    private static HuffmanTree readHeader(BitInputStream in, int headerFormat) throws IOException{
        if(headerFormat == STORE_COUNTS){
            PriorityQueue314<TreeNode> pq = new PriorityQueue314<>();
            for(int i = 0; i < ALPH_SIZE; i ++){
                int freq = in.readBits(BITS_PER_INT);
                if(freq != 0){
                    pq.add(new TreeNode(i, freq));
                }
            }
            //won't need a frequency array because this tree will be used exclusively for decoding
            return new HuffmanTree(pq, null);
        } else if(headerFormat == STORE_TREE){
            int[] numBits = {in.readBits(BITS_PER_INT)};
            HuffmanTree res = new HuffmanTree();
            res.buildTree(numBits, in);
            return res;
        }
        return null;
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
