package my.groupid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.groupid.config.PasswordHash;

class Convert {
  public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    BufferedReader sc = null;
    try {
      sc = new BufferedReader(new InputStreamReader(System.in));
      String line = null;
      do {
        line = sc.readLine();
        if (line != null) {
          Matcher m = Pattern.compile(Pattern.quote("UPDATE users SET password = '") + "(.*)" + Pattern.quote("' WHERE users.id = ") + "(.*);").matcher(line);
          if (m.matches()) {
            System.out.println("UPDATE users SET password = '" + PasswordHash.createHash(m.group(1)) + "' WHERE users.id = " + m.group(2));
          }
        }
      } while (line != null);
    } finally {
      if (sc != null) {
        sc.close();
      }
    }
  }
}