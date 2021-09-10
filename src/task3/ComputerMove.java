package task3;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.util.Formatter;

public class ComputerMove {
    int numberMoves;
    public ComputerMove(int a) {
        numberMoves = a;
    };

    String generationKey() throws NoSuchAlgorithmException {      //generating a key and convert it to string form
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[16];
        random.nextBytes(bytes);
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    int generationComputerMov(){
        SecureRandom rand = new SecureRandom();
        int computerMov =rand.nextInt(numberMoves) + 1;     //generate the computer's move
        return computerMov;
    }

     String generationHMAC(String data)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
    {
        SecretKeySpec secretKeySpec = new SecretKeySpec(data.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);

        Formatter formatter = new Formatter();
        for (byte b : mac.doFinal(data.getBytes())) {                          //calculate HMAC and convert it to string form
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}
