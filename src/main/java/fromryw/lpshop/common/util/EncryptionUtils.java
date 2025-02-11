package fromryw.lpshop.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtils {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "a72ha@61@16213iu"; // 암호화 및 복호화에 사용되는 고정된 비밀 키. 실무에서는 노출하면 안됨

    public static String encrypt(String value) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedValue = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (Exception e) {
            throw new RuntimeException("데이터 암호화 중 오류가 발생했습니다.");
        }
    }

    public static String decrypt(String value) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedValue = Base64.getDecoder().decode(value);
            return new String(cipher.doFinal(decryptedValue));
        } catch (Exception e) {
            throw new RuntimeException("데이터 복호화 중 오류가 발생했습니다.");
        }
    }
}
