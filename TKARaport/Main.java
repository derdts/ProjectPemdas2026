import java.util.Scanner;

public class Main {

    public static void tampilkanAwal() {
        System.out.println("Cek Rapor dan TKA Siswa SMA di Kabupaten Jember");
        System.out.println("Kepo, sekolah ini nilainya mencurigakan atau tidak??");
    }

    public static void inputNamaSekolah(Scanner in, String[] sekolah) {
        for (int i = 0; i < sekolah.length; i++) {
            System.out.print("Masukkan nama sekolah ke-" + (i + 1) + ": ");
            sekolah[i] = in.nextLine();
        }
    }

    public static void inputNilai(Scanner in, String[] sekolah, double[] tka, double[] rapor) {
        int i = 0;
        while (i < sekolah.length) {
            System.out.println("\nNilai " + sekolah[i]);
            System.out.print("Masukkan nilai rata - rata TKA Siswa: ");
            tka[i] = in.nextDouble();

            if (tka[i] > 100 || tka[i] < 0) {
                System.out.println("nilai TKA tidak boleh lebih dari 100 atau kurang dari 0");
                continue;
            }

            System.out.print("Masukkan nilai rata - rata Rapor Siswa: ");
            rapor[i] = in.nextDouble();

            if (rapor[i] > 100 || rapor[i] < 0) {
                System.out.println("nilai Rapor tidak boleh lebih dari 100 atau kurang dari 0");
                continue;
            } 
            i++;
        }
    }

    public static double hitungRataRata(double[] data) {
        double total = 0;
        for (double nilai : data) {
            total += nilai;
        }
        return total / data.length;
    }

    public static String tentukanKategori(double nilai, double rataRata) {
        if (nilai > rataRata) {
            return "Diatas Rata-rata";
        } else if (nilai < rataRata) {
            return "Dibawah Rata-rata";
        } else {
            return "Rata-rata";
        }
    }

    public static String tentukanStatus(double ratio) {
        if (ratio >= 1) {
            return "Tidak mencurigakan";
        } else if (ratio >= 0.9) {
            return "Cukup mencurigakan";
        } else if (ratio == 0){
            return "nilai rapor = 0 atau tka = 0";
        } else {
            return "Mencurigakan";
        }
    }

    public static void tampilkanHasil(String[] sekolah, double[] tka, double[] rapor, double rataTKA) {
        System.out.printf("\nRata - rata TKA seKabupaten : %.2f\n", rataTKA);

        for (int i = 0; i < sekolah.length; i++) {
            double ratio = tka[i] / rapor[i];
            if(rapor[i] == 0){
                ratio = 0;
            }

            String kategori = tentukanKategori(tka[i], rataTKA);
            String status = tentukanStatus(ratio);

            System.out.println(
                sekolah[i] + " TKA: " + tka[i] +
                ", Kategori: " + kategori +
                ", Rapor: " + rapor[i] +
                ", Status: " + status
            );
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
      tampilkanAwal();
      System.out.print("Masukkan berapa list sekolah yang ingin di cek: ");
      int n = in.nextInt();
      in.nextLine();

      if(n<1){
        System.out.println("Jumlah sekolah tidak boleh kurang dari 1");
        return;
      }

      String[] sekolah = new String[n];
      double[] tka = new double[n];
      double[] rapor = new double[n];

      inputNamaSekolah(in, sekolah);
      inputNilai(in, sekolah, tka, rapor);

      double rataTKA = hitungRataRata(tka);
      
      tampilkanHasil(sekolah, tka, rapor, rataTKA); 
    }
}
