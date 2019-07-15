import java.security.MessageDigest;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class MessageDigestExample {

public static String getSHA256(String message) {

        try {
                //Creating the MessageDigest object
                MessageDigest md = MessageDigest.getInstance("SHA-256");

                //Passing data to the created MessageDigest Object
                md.update(message.getBytes());

                //Compute the message digest
                byte[] digest = md.digest();
                System.out.println(digest);

                //Converting the byte array in to HexString format
                StringBuffer hexString = new StringBuffer();

                for (int i = 0; i<digest.length; i++) {
                        hexString.append(Integer.toHexString(0xFF & digest[i]));
                }
                return hexString.toString();
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
                System.out.println("Exception thrown" + " for incorrect algorithm: " + e);
                return null;
        }
}

public static void main(String args[]) throws Exception {
        // Reading data from user
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        System.out.println(getSHA256(message));
}
}
