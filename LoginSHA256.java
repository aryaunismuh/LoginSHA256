import java.util.HashMap;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginSHA256 {

    // Database sederhana
    static HashMap<String, String> database = new HashMap<>();

    // Fungsi untuk mengubah password menjadi hash SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Fungsi registrasi
    public static void registrasi(Scanner input) {
        System.out.print("Masukkan Username : ");
        String username = input.nextLine();

        System.out.print("Masukkan Password : ");
        String password = input.nextLine();

        // Hash password
        String hashedPassword = hashPassword(password);

        // Simpan ke database
        database.put(username, hashedPassword);

        System.out.println("\n=== Registrasi Berhasil ===");
        System.out.println("Username       : " + username);
        System.out.println("Hash Password  : " + hashedPassword);
    }

    // Fungsi login
    public static void login(Scanner input) {
        System.out.print("Masukkan Username : ");
        String username = input.nextLine();

        System.out.print("Masukkan Password : ");
        String password = input.nextLine();

        // Cek username
        if (database.containsKey(username)) {

            // Hash password input
            String hashedInput = hashPassword(password);

            // Verifikasi password
            if (database.get(username).equals(hashedInput)) {
                System.out.println("Status Login : BERHASIL");
            } else {
                System.out.println("Status Login : GAGAL - Password Salah");
            }

        } else {
            System.out.println("Status Login : GAGAL - Username Tidak Ditemukan");
        }
    }

    // Main Program
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Registrasi");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.print("Pilih Menu : ");

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

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
                    System.out.println("Pilihan Tidak Valid");
            }

        } while (pilihan != 3);

        input.close();
    }
}
