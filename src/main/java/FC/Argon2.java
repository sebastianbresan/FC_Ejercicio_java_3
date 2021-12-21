package FC;

import de.mkammerer.argon2.Argon2Factory;

public class Argon2 {

    de.mkammerer.argon2.Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    public String hashPassword(String pass) {

        char[] passwordArgon = pass.toCharArray();

        return argon2.hash(4, 1024 * 1024, 8, passwordArgon);
    }

    public boolean verifyPassword(String pass, String getPass) {

        char[] passwordArgon = pass.toCharArray();

        return argon2.verify(getPass, passwordArgon);

    }

}
