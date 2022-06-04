package encryptdecrypt;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Encrypter encrypter = null;
        String mode = "enc";
        int key = 0;
        String data = "";
        String in = "";
        String out = "";

        //arguments
        for (int i = 1; i < args.length; i++) {
            switch (args[i - 1]) {
                case "-alg":
                    if (args[i].equals("unicode")) {
                        encrypter = new Encrypter(new UnicodeStrategy());
                    } else {
                        encrypter = new Encrypter(new ShiftStrategy());
                    }
                    break;
                case "-mode":
                    mode = args[i];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i]);
                    break;
                case "-data":
                    data = args[i];
                    break;
                case "-in":
                    in = args[i];
                    break;
                case "-out":
                    out = args[i];
                    break;
            }
        }
        //data
        if (!in.equals("") && data.equals("")) {
            try {
                File input = new File(in);
                Scanner scanner = new Scanner(input);
                data = scanner.nextLine();
            } catch (IOException e) {
                System.out.println("Error");
                System.exit(0);
            }
        }

        //output
        if (!out.equals("")) {
            try {
                FileWriter writer = new FileWriter(out);
                if (mode.equals("enc")) {
                    writer.write(encrypter.encrypt(data, key));
                } else {
                    writer.write(encrypter.decrypt(data, key));
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Error");
                System.exit(0);
            }
        } else {
            if (mode.equals("enc")) {
                System.out.println(encrypter.encrypt(data, key));
            } else {
                System.out.println(encrypter.decrypt(data, key));
            }
        }
    }
}
