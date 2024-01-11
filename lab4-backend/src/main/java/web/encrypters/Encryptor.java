package web.encrypters;

public interface Encryptor {
    EncryptedValue encrypt(String value);
    Boolean checkEquals(String encrypted, EncryptedValue value);
}

