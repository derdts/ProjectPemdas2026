import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double[] tka = new double[5];
        double[] rapor = new double[5];
        double[] ratio = new double[5];
        double jumlahTKA = 0;
        
    
      System.out.println("===Cek Rapor dan TKA Siswa SMA di Kabupaten Jember===");
      System.out.println("Kepo, sekolah ini nilainya mencurigakan atau tidak??");
      System.out.print("Masukkan berapa list sekolah yang ingin di cek: ");
      int n = in.nextInt();
      in.nextLine();
      String[] sekolah = new String[n];

      for (int i = 0; i < n; i++) {
          System.out.print("Masukkan nama sekolah ke-" + (i + 1) + ": ");
          sekolah[i] = in.nextLine();
      }



      int i = 0;
      while (i < n) { 
          System.out.println("\nNilai " + sekolah[i]);
          System.out.print("Masukkan nilai rata - rata TKA Siswa: ");
          tka[i] = in.nextDouble();
          jumlahTKA += tka[i];

          if(tka[i]>100 || tka[i]<0){
            System.out.println("nilai TKA tidak boleh lebih dari 100 atau kurang dari 0");
            continue;
          }

          System.out.print("Masukkan nilai rata - rata Rapor Siswa: ");
          rapor[i] = in.nextDouble();
          if(rapor[i]>100 || rapor[i]<0){
            System.out.println("nilai Rapor tidak boleh lebih dari 100 atau kurang dari 0");
            continue;
          } else if (rapor[i]==0){
            rapor[i]=1;
          }
          i++;
      }

      //Out
      double rataTKA = jumlahTKA/n;
      System.out.printf("\nRata - rata TKA seKabupaten : %.2f", rataTKA);
      System.out.println(" ");
      
      String kategori = " ";
      for(i=0; i<n; i++){
        if(tka[i]>rataTKA){
            kategori="Diatas Rata-rata";
          }
          else if (tka[i]<rataTKA){
            kategori="Dibawah Rata-rata";
          }
          else{
            kategori="Rata-rata";
          }
        ratio[i] = tka[i] / rapor[i];
        if(ratio[i]>=1){
          System.out.println(sekolah[i] + " TKA: " + tka[i] + ", Kategori: " + kategori + ", Rapor: " + rapor[i] + ", Status: Tidak mencurigakan");
        }
        else if (ratio[i] < 1 && ratio[i]>= 0.9){
          System.out.println(sekolah[i] + " TKA: " + tka[i] + ", Kategori: " + kategori + ", Rapor: " + rapor[i] + ", Status: Cukup mencurigakan");
        } else {
            System.out.println(sekolah[i] + " TKA: " + tka[i] + ", Kategori: " + kategori + ", Rapor: " + rapor[i] + ", Status: Mencurigakan");
        }
      }   
    }
}
