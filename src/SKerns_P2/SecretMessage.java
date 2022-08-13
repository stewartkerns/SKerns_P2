package SKerns_P2;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;

public class SecretMessage {

    public static void main(String[] args) throws IOException {
        final String FILENAME = "C:\\Users\\Stewart\\Desktop\\CPSC5002\\SKerns_P2\\src\\SKerns_P2\\rain.dat";
        final String FILENAME2 = "C:\\Users\\Stewart\\Desktop\\CPSC5002\\SKerns_P2\\src\\SKerns_P2\\hello-1.dat";
        final String FILENAME3 = "SKerns_P2/rain.dat";
//        char space = 32;
//        char a = 'a';
//        System.out.println("Is this"+ space +"a space?");
        MessageDecoder newMessage = new MessageDecoder();
        newMessage.testPrint(FILENAME);
    }
}
