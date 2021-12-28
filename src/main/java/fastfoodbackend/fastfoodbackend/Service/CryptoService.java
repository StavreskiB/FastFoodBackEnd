package fastfoodbackend.fastfoodbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

@Service
public class CryptoService {

    @Autowired
    private TokenService tokenService;

    public static String secret = "unagi";

    //decrypt with CryptoJS
    public static String decryptJS(String text)
    {
        String decryptedText = "";
        try {
            byte[] cipherData = Base64.getDecoder().decode(text);
            byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secret.getBytes(StandardCharsets.UTF_8), md5);
            SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
            IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

            byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
            Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
            aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] decryptedData = aesCBC.doFinal(encrypted);
            decryptedText = new String(decryptedData, StandardCharsets.UTF_8);

            //System.out.println("decryptedText: " + decryptedText);
        }catch (Exception e) {
            //System.out.println("error " + e);
        }

        return decryptedText;
    }

    //decrypt text with Base64 and call fn:decryptJS
    //povik vo APIs
    public static String decryptText(String text)
    {
        String decryptText = null;
        byte[] decodedBytes = Base64.getDecoder().decode(text);
        String decodedString = new String(decodedBytes);
        //System.out.println("decodedString---" + decodedString);
        decryptText = decryptJS(decodedString);
        //System.out.println("decryptJS(cipherText)!: " +  decryptJS(decodedString));
        //System.out.println(decryptText);
        return decryptText;
    }

    public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) {
        int digestLength = md.getDigestLength();
        int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
        byte[] generatedData = new byte[requiredLength];
        int generatedLength = 0;

        try {
            md.reset();

            // Repeat process until sufficient data has been generated
            while (generatedLength < keyLength + ivLength) {

                // Digest data (last digest if available, password data, salt if available)
                if (generatedLength > 0)
                    md.update(generatedData, generatedLength - digestLength, digestLength);
                md.update(password);
                if (salt != null)
                    md.update(salt, 0, 8);
                md.digest(generatedData, generatedLength, digestLength);

                // additional rounds
                for (int i = 1; i < iterations; i++) {
                    md.update(generatedData, generatedLength, digestLength);
                    md.digest(generatedData, generatedLength, digestLength);
                }

                generatedLength += digestLength;
            }

            // Copy key and IV into separate byte arrays
            byte[][] result = new byte[2][];
            result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
            if (ivLength > 0)
                result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

            return result;

        } catch (DigestException e) {
            throw new RuntimeException(e);

        } finally {
            // Clean out temporary data
            Arrays.fill(generatedData, (byte)0);
        }
    }

    public static final String DEFAULT_ENCODING = "UTF-8";

    public String encript(String text){
        String encriptStr = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(text.getBytes());

        return encriptStr;
    }

    public String decript(String text){
        byte[] decriptByte = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(text.getBytes());
        String decriptStr = new String(decriptByte);

        return decriptStr;
    }

    public String GetUserName(HttpServletRequest request){
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = tokenService.extractUsername(jwt);
            System.out.println("exp. " + tokenService.extractExpiration(jwt));
        }
        return username;
    }
}
