package prova.main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.IntStream;

/**
 * This class is an example of how to use some api of java.io package, to read and write files.
 * In particular, we've considered the case of a large file and how performance is varied with use
 * of two different API.
 *
 * The file used is generated from us with method prepFile().
 */
public class ReadingFile {
    public final static String TARGET_FILE="C:\\Users\\rmaisto\\Desktop\\prova\\prova.txt";
    public final static String TARGET_FILE_SMALL="C:\\Users\\rmaisto\\Desktop\\prova\\prova.txt";
    public final static String TARGET_FILE_DEST="C:\\Users\\rmaisto\\Desktop\\prova\\prova_cpy.txt";

    /**
     * creates a large file
     * @throws IOException
     */
    public static void prepFile() throws IOException {
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(TARGET_FILE), StandardOpenOption.CREATE);
        IntStream.rangeClosed(1,100000000)
                .forEach(a-> write(bufferedWriter, a));
        bufferedWriter.flush();
    }

    public static void write(BufferedWriter bufferedWriter, int a) {
        try {
            bufferedWriter.write(a + "\n");
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception{

        /*First of all, we create large file that we'll use in this case
        prepFile();
        */

        /*At this point we have large file prova.txt on our file system at path TARGET_FILE.
        * Now we want to access it and copy to another file prova_copy.txt
        * So we create an input stream on TARGET_FILE from wich we'll read
        * and an outputstream to wich we'll write.
        * */
/*
        InputStream input=new FileInputStream(TARGET_FILE);
        OutputStream output=new FileOutputStream(TARGET_FILE_DEST);
*/
        
        /*We read the file and every byte returning we write it on output stream
        int dato_letto;
        while((dato_letto=input.read())!=-1){
            output.write(dato_letto);
        }
        input.close();output.close();
        */
        /*Doing so, we spent a lot of time and resources.So we can conclude that this method
        * works well for small file*/

        /*So to enhance performance, we can use Buffer*/

        InputStream bufIn=new BufferedInputStream(new FileInputStream(TARGET_FILE));
        OutputStream bufOut=new BufferedOutputStream(new FileOutputStream(TARGET_FILE_DEST));

        /*Now that we have our buffered stream we can call the same methods called above and see advantages*/

        int data_read;
        while((data_read=bufIn.read())!=-1){
            bufOut.write(data_read);
        }
        bufIn.close();bufOut.close();
        /*as we can see , application saves a lot amount of time returning the same result*/
    }
}
