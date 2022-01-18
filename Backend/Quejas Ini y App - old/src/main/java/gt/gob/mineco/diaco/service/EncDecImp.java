package gt.gob.mineco.diaco.service;

import gt.gob.mineco.diaco.exception.ErrorException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import org.apache.commons.codec.binary.Base64;

@Stateless
public class EncDecImp {

    private static final String SECRETO = "[7KF\"9J),Kh?X-8K";
    private static final String SALTO = "X6m)rehLTpJJ6y&n";

    /**
     * Almacena la cadena: UTF8-8
     */
    private static final String UTF8 = "UTF-8";
    /**
     * Almacena la cadena: AES
     */
    private static final String AES = "AES";

    /**
     * Metodo que sirve para desencriptar una cadena.
     *
     * @param pCadena Cadena para desencriptar.
     * @return La cadena original.
     * @throws ErrorException Si ocurre un error.
     */
    public String encrypt(String pCadena) throws ErrorException {
        System.out.println("Metodo: encrypt");
        try {
            IvParameterSpec pSalto = new IvParameterSpec(SALTO.getBytes(UTF8));
            SecretKeySpec pSecreto = new SecretKeySpec(SECRETO.getBytes(UTF8), AES);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, pSecreto, pSalto);

            byte[] strEncriptado = cipher.doFinal(pCadena.getBytes(UTF8));
            return Base64.encodeBase64URLSafeString(strEncriptado);
        } catch (Exception pError) {
            System.out.println("Error: " + pError.getMessage());
            throw new ErrorException("Error al encriptar la cadena.");
        }
    }

    /**
     * Metodo que sirve para desencriptar una cadena.
     *
     * @param pCadena Cadena para desencriptar.
     * @return La cadena original.
     * @throws ErrorException Si ocurre un error.
     */
    public String decrypt(String pCadena) throws ErrorException {
        try {
            IvParameterSpec pSalto = new IvParameterSpec(SALTO.getBytes(UTF8));
            SecretKeySpec pSecreto = new SecretKeySpec(SECRETO.getBytes(UTF8), AES);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, pSecreto, pSalto);

            byte[] strOriginal = cipher.doFinal(Base64.decodeBase64(pCadena));
            return new String(strOriginal);
        } catch (Exception pError) {
            System.out.println("Error: " + pError.getMessage());
            throw new ErrorException("Error al des-encriptar la cadena.");
        }
    }
}
