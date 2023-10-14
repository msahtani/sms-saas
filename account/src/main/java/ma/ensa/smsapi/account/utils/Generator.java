package ma.ensa.smsapi.account.utils;


import java.security.SecureRandom;

public class Generator {

    public static String generateRandom(int len)
    {
        SecureRandom random = new SecureRandom();
        return random.ints(48, 123)
                .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i))
                .limit(len)
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append
                ).toString();
    }


}