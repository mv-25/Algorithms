package compression_assingment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Runner{


    public static void testHuffman(String orgStr, boolean show, String dotfilename){
        System.out.print("* Building Huffman Tree and Code Tables\n");
        Huffman h = new Huffman(orgStr,show,dotfilename);
        System.out.println(" DONE");

        if (show){
            System.out.println("\n============= Word Frequency =============");
            for (Map.Entry<Character, Integer> entry: h.hmapWC.entrySet()){
                String key = entry.getKey().toString();
                int val = entry.getValue();
                if (key.equals("\n"))
                    key = "\\n";
                System.out.println(key + " occurs " + val + " times");
            }

            System.out.println("\n========== Huffman Code for each character =============");
            for (Map.Entry<Character, String> entry: h.hmapCode.entrySet()){
                String key = entry.getKey().toString();
                String val = entry.getValue();
                if (key.equals("\n"))
                    key = "\\n";
                System.out.println(key + ": " + val);
            }
            System.out.println();
        }

        //compressing and decompressing the files
        //calculating the time for both the  operations
        final long startTime = System.currentTimeMillis();
        System.out.print("* Compressing the text\n");
        String e = h.compress();
        System.out.println(" DONE");
        final long elapsedTime = (System.currentTimeMillis() - startTime);
        System.out.println("Elapsed time: " + elapsedTime);

        final long startTime1 = System.currentTimeMillis();
        System.out.print("* Decompressing the encoded text\n");
        String d = h.decompress();
        myassert(orgStr.equals(d)) ;   // Check if original text and decoded text is exactly same
        System.out.println(" DONE");
        final long elapsedTime1 = (System.currentTimeMillis() - startTime1);
        System.out.println("Elapsed time: " + elapsedTime1);

        double orginalString = orgStr.length() * 7 ;
        double compressedString = e.length();
        System.out.println("\n========== RESULT ==========");
        System.out.println("Original string cost = " + (int)orginalString + " bits") ;
        System.out.println("Compressed string cost = " + (int)compressedString + " bits") ;
        double ratio = (compressedString/orginalString) ;
        System.out.println("Compression Ratio= " + ratio) ;
    }

    public static String readFile(String fname){
        StringBuilder sb = new StringBuilder();
        File filename = new File(fname);
        try (BufferedReader in = new BufferedReader(new FileReader(filename))){
            String line = in.readLine();
            while (line != null){
                sb.append(line + "\n");
                line = in.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
        return sb.toString();
    }

    public static void myassert(boolean  x) {
        if (!x) {
            throw new IllegalArgumentException("Assert fail") ;
        }
    }

    public static void main(String[] args)
    {
        System.out.println("----- START -----");
        boolean show = true ;
        String orgFile = "C:\\Users\\Mudita\\IdeaProjects\\Algorithm\\src\\compression_assingment\\medTale.txt";
        //String orgFile = args[0];
        String dotFile = "test.dot";

        System.out.print("* Loading the file\n");
        String orgString = readFile(orgFile);
        System.out.println("DONE");

        testHuffman(orgString, show, dotFile);
        System.out.println("\n----- DONE ----- ");

    }

}
