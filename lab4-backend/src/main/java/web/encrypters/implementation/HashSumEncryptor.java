package web.encrypters.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import web.encrypters.EncryptedValue;
import web.encrypters.Encryptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Random;

public class HashSumEncryptor implements Encryptor {
    private final String PEPPER;
    private final MessageDigest hashingAlgorithm;

    public HashSumEncryptor(String pepper, MessageDigest hashingAlgorithm) throws NoSuchAlgorithmException {
        this.PEPPER = pepper;
        this.hashingAlgorithm = hashingAlgorithm;
    }
    private String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    private String getSHA224Hash(String input) {
        byte[] inputBytes = input.getBytes();
        hashingAlgorithm.update(inputBytes);
        byte[] hashBytes = hashingAlgorithm.digest();
        StringBuilder sb = new StringBuilder();

        for (byte hashByte : hashBytes) {
            sb.append(String.format("%02x", hashByte));
        }
        return sb.toString();
    }


    @Override
    public EncryptedValue encrypt(String value) {
        String salt = generateRandomString();

        getSHA224Hash(PEPPER + value + salt);

        return new EncryptedValue(getSHA224Hash(PEPPER + value + salt), salt);
    }

    @Override
    public Boolean checkEquals(String encrypted, EncryptedValue value) {
        return encrypted.equals(getSHA224Hash(PEPPER + value.getHashedValue() + value.getSalt()));
    }

}
