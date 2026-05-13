import java.util.HashMap;
import java.util.Scanner;
import java.security.MessageDigest;

public class LoginSHA256 {

    static HashMap<String, String> database = new HashMap<>();

    // Fungsi hash SHA-256 
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(password.getBytes());

            StringBuilder hasil = new StringBuilder();

            for (byte b : hash) {
                hasil.append(String.format("%02x", b));
            }

            return hasil.toString();

        } catch (Exception e) {
            return null;
        }
    }

    // Registrasi
    public static void registrasi(Scanner input) {

        System.out.print("Masukkan Username : ");
        String username = input.nextLine();

        System.out.print("Masukkan Password : ");
        String password = input.nextLine();

        String hash = hashPassword(password);

        database.put(username, hash);

        System.out.println("\nRegistrasi Berhasil");
        System.out.println("Hash Password : " + hash);
    }

    // Login
    public static void login(Scanner input) {

        System.out.print("Masukkan Username : ");
        String username = input.nextLine();

        System.out.print("Masukkan Password : ");
        String password = input.nextLine();

        String hashLogin = hashPassword(password);

        if (database.containsKey(username)) {

            if (database.get(username).equals(hashLogin)) {
                System.out.println("Login Berhasil");
            } else {
                System.out.println("Password Salah");
            }

        } else {
            System.out.println("Username Tidak Ditemukan");
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int pilih;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Registrasi");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.print("Pilih : ");

            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {

                case 1:
                    registrasi(input);
                    break;

                case 2:
                    login(input);
                    break;

                case 3:
                    System.out.println("Program Selesai");
                    break;

                default:
                    System.out.println("Pilihan Salah");
            }

        } while (pilih != 3);

        input.close();
    }
}