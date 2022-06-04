package encryptdecrypt;

interface EncryptingStrategy {
    String encrypt(String message, int key);

    String decrypt(String message, int key);
}

public class Encrypter {
    private final EncryptingStrategy strategy;

    public Encrypter(EncryptingStrategy strategy) {
        this.strategy = strategy;
    }

    public String encrypt(String message, int key) {
        return strategy.encrypt(message, key);
    }

    public String decrypt(String message, int key) {
        return strategy.decrypt(message, key);
    }
}

class UnicodeStrategy implements EncryptingStrategy {
    @Override
    public String encrypt(String message, int key) {
        char[] arr = message.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] += key;
        }
        return String.valueOf(arr);
    }

    @Override
    public String decrypt(String message, int key) {
        char[] arr = message.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= key;
        }
        return String.valueOf(arr);
    }
}

class ShiftStrategy implements EncryptingStrategy {
    public String encrypt(String message, int key) {
        char[] arr = message.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                continue;
            } else if (arr[i] + key > 'z') {
                arr[i] -= 26 - key;
            } else {
                arr[i] += key;
            }
        }
        return String.valueOf(arr);
    }

    public String decrypt(String message, int key) {
        char[] arr = message.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                continue;
            } else if (arr[i] - key < 'a') {
                arr[i] -= key - 26;
            } else {
                arr[i] -= key;
            }
        }
        return String.valueOf(arr);
    }
}