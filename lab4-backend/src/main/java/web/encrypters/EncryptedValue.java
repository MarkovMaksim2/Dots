package web.encrypters;

public class EncryptedValue {
    private String hashedValue;
    private String salt;

    public EncryptedValue(String hashedValue, String salt) {
        this.hashedValue = hashedValue;
        this.salt = salt;
    }

    public String getHashedValue() {
        return hashedValue;
    }

    public String getSalt() {
        return salt;
    }
}
