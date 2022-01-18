    package gt.gob.mineco.diaco.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Singleton;

@Singleton
public class Crypto {

    private static final Logger LOGGER = Logger.getLogger(Crypto.class.getName());

    public static final String ALG_SHA256 = "SHA-256";
    public static final String ALG_MD5 = "MD5";
    public static final String ALG_SHA1 = "SHA-1";
    public static String NUMEROS = "0123456789";
    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    public static String ESPECIALES = "._";

    public String encrypt(String str, String algorithm) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest mdigest = MessageDigest.getInstance(algorithm);
            try {
                mdigest.update(str.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                LOGGER.info("No esta soportado el encoding UTF-8");
                mdigest.update(str.getBytes());
            }
            byte[] digest = mdigest.digest();
            for (int i = 0; i < digest.length; i++) {
                sb.append(String.format("%02x", digest[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            //LOGGER.info("No existe el algoritmo " + algorithm);
            sb.append(str);
            sb = sb.reverse();
        }

        return sb.toString();
    }

    public String getPinNumber() {
        int length = 4;
        String key = NUMEROS;
        String pswd = "";
        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }
        return pswd;

    }

    public String getPassword() {
        int length = 8;
        String key = NUMEROS + MAYUSCULAS + MINUSCULAS + ESPECIALES;
        String pswd = "";
        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }
        return pswd;
    }

    public static void main(String[] args) {
        Crypto c = new Crypto();
//         String nclave = c.getPassword();
//        System.out.println("nueva clave" + nclave);
//        System.out.println("to db:" + c.encrypt(nclave, Crypto.ALG_SHA256));

//        Map<String, String> gen = new HashMap<>();
//        String genPass = c.getPassword();
//        gen.put("ps", genPass);
//        gen.put("db", c.encrypt(genPass, Crypto.ALG_SHA256));
//        System.out.println(gen);
    }

}
