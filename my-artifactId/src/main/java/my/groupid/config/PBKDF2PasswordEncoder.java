package my.groupid.config;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PBKDF2PasswordEncoder implements PasswordEncoder {
  @Override
  public String encode(CharSequence cs) {
    try {
      return PasswordHash.createHash(cs.toString());
    } catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException(ex);
    } catch (InvalidKeySpecException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public boolean matches(CharSequence cs, String string) {
    try {
      return PasswordHash.validatePassword(cs.toString(), string);
    } catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException(ex);
    } catch (InvalidKeySpecException ex) {
      throw new RuntimeException(ex);
    }
  }
}