import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double[] tka = new double[5];
        double[] rapor = new double[5];
        double[] ratio = new double[5];
        double jumlahTKA = 0;
    
      System.out.println("Kepo, sekolah ini nilainya mencurigakan atau tidak??");
      System.out.println("List sekolah di Jember");
      System.out.println("1. SMAN 1 Jember");
      System.out.println("2. SMAN 2 Jember");
      System.out.println("3. SMAN 3 Jember");
      System.out.println("4. SMAN 4 Jember");
      System.out.println("5. SMAN 5 Jember\n");
      
      int i = 0;
      while (i < 5) { 
          System.out.println("\nNilai SMAN " + (i+1) + " Jember");
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
          }
          i++;
      }

      //Out
      double rataTKA = jumlahTKA/5;
      System.out.println("Rata - rata TKA seKabupaten : " + rataTKA);
      
      String kategori = " ";
      for(i=0; i<5; i++){
        ratio[i] = tka[i] / rapor[i];
        if(ratio[i]>=1){
          if(tka[i]>rataTKA){
            kategori="Diatas Rata-rata";
          }
          else if (tka[i]<rataTKA){
            kategori="Dibawah Rata-rata";
          }
          else{
            kategori="Rata-rata";
          }
          System.out.println("SMAN " + (i+1) + " Jember TKA: " + tka[i] + ", Kategori: " + kategori + ", Rapor: " + rapor[i] + ", Status: Tidak mencurigakan");
        }
        else if (ratio[i] < 1 && ratio[i]>= 0.9){
          System.out.println("SMAN " + (i+1) + " Jember TKA: " + tka[i] + ", Kategori: " + kategori + ", Rapor: " + rapor[i] + ", Status: Cukup mencurigakan");
        } else {
            System.out.println("SMAN " + (i+1) + " Jember TKA: " + tka[i] + ", Kategori: " + kategori + ", Rapor: " + rapor[i] + ", Status: Mencurigakan");
        }
      }   
      

    }
}
