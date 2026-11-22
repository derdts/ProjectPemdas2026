import java.util.Scanner;
 
public class Game {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;
        boolean shop;
 
        // bikin player
        System.out.println("Tahun ini kamu udah jadi mahasiswa baru");
        System.out.print("Siapa nama mu??? ");
        String nama = input.nextLine();
        Player pemain = new Player(nama, 30, 5, 2);
 
        // bikin musuh
        Enemy laprak = new Enemy("Laprak", 15, 4, 50);
        Enemy uap = new Enemy("U A P😈", 30, 7, 0);
 
        // bikin item
        Item[] menuKantin = {
            new Item("Americano", 25, 3, 0, 0, "+3 Skill Ngoding"),
            new Item("Nasi Padang", 20, 0, 10, 0, "+10 max Mental"),
            new Item("Es Teh Jumbo", 10, 0, 0, 1, "+1 Es Teh Jumbo")
        };
 
        // text kalo kalah
        String teksKalah = "\nMental mu udah down. Kamu malah ketiduran di depan laptop dan kebangun jam 00.01.\n"
                + "Deadline lewat. Tombol submit udah abu-abu.\n"
                + "===== STATUS: SKILL ISSUE Wlee =====\n"
                + "Cuma kurang Es Teh. Coba lagi nub";
 
        // text kalo giliran habis tapi musuh belum kalah
        String teksWaktuHabis = "===== STATUS: WAKTU HABIS =====\n"
                + "kamu kebanyakan merenung, coba kali ini lebih fokus.";
 
        System.out.println("\nCHAPTER 1: ERA MABA");
 
        System.out.println("\nSelamat datang di FILKOM wahai, " + pemain.getName());
        System.out.println("Kamu sekarang udah resmi jadi maba Teknik Informatika");
        System.out.println("Minggu pertama kuliah kamu sudah praktikum dan disuruh bikin laprak");
        System.out.println("\"GUYS LAPRAK DIKUMPULKAN MALEM INI JAM 23.59\"");
        System.out.println("kamu buka laptop. word masih kosong");
        System.out.println("Template-nya 12 halaman, screenshot output wajib ada nama + NIM.");

        System.out.println("\n>>> VS " + laprak.getName() + " <<<");
 

        String[][] menuLaprak = {
            { "Screenshot output satu per satu",
              "membaca modul praktikum",
              "bikin cover laprak" },   
            { "nyusun jawaban tiap soal dari modul",
              "jawaban nya jelasin kode line by line",
              "tanya teman di grup kelas" },  
            { "sesuaikan laporan sama template",
              "Benerin caption gambar",
              "Tambahin daftar pustaka" },   
            { "Nulis kesimpulan",
              "Convert laprak ke PDF",
              "Klik submit di eling" }   
        };

        String[][] teksLaprak = {
            { "kamu run program dan screenshot output program satu per satu, lengkap sama nama + NIM.",
              "kamu rangkum modul yang isi nya 10 halaman, bagian pentingnya ternyata cuma 3 halaman.",
              "cover laprak udah jadi, lengkap logo kampus, nama, dan NIM" },   
            { "jawaban dibuat pakai bahasa sendiri tanpa claudd dan kawan kawan",
              "kamu jelasin tiap baris kode, ternyata kamu sendiri baru ngerti pas nulis ini",
              "Kamu nanya di grup kelas. Ada yang bales: 'sama, aku juga belum'" },   
            { "Margin, font, spasi, semua kamu samain sama template. Asprak pasti seneng.",
              "Caption 'Gambar 1.1' sampai 'Gambar 1.12' kamu benerin satu-satu. Teliti parah.",
              "Daftar pustaka kamu isi pakai format yang bener. Rapi banget." },  
            { "Kamu nulis kesimpulan: \"Praktikum ini sangat bermanfaat.\" Klasik, tapi aman.",
              "Laprak di-convert ke PDF",
              "Kamu klik submit. Loading-nya lama banget, jantung ikut deg-degan." }   
        };

        int jumlahGiliranLaprak = menuLaprak.length;
 
        System.out.println("Kamu cuma punya " + jumlahGiliranLaprak + " giliran sebelum waktu habis!");
 
        for (int giliran = 0; (giliran < jumlahGiliranLaprak) && pemain.isAlive() && laprak.isAlive(); giliran++) {
            System.out.println("\n Giliran ke " + (giliran + 1) + " dari " + jumlahGiliranLaprak);

            pemain.showStatus();

            laprak.showStatus();

            for (int i = 0; i < menuLaprak[giliran].length; i++) {
                System.out.println((i + 1) + ". " + menuLaprak[giliran][i]);
            }

            if (pemain.getPotions() <= 0) {
                System.out.println("4. Es teh Habis");
            } else if (pemain.getHp() >= pemain.getMaxHp()) {
                System.out.println("4. (Es Teh: Mental masih full)");
            } else {
                System.out.println("4. Minum Es Teh Jumbo (+10 Mental)");
            }

            System.out.print("Pilih aksi: ");
            pilihan = input.nextInt();

            System.out.println();
 
            if (pilihan >= 1 && pilihan <= 3) {
                int nomorPilihan = pilihan - 1;
                System.out.println(teksLaprak[giliran][nomorPilihan]);
                pemain.attack(laprak);
            } else if (pilihan == 4) {
                if (pemain.getPotions() <= 0) {
                    System.out.println("Es Teh-nya ternyata udah abis! Kamu cuma bengong, giliran ini kebuang.");
                } else if (pemain.getHp() >= pemain.getMaxHp()) {
                    System.out.println("Mental kamu masih full, Es Teh-nya ga jadi diminum");
                } else {
                    System.out.println("Word masih loading. Kamu minum Es Teh");
                    pemain.drinkPotion();
                }
            } else {
                System.out.println("Kamu kelamaan mikir dan malah doomscrolling tiktok");
            }
 
            if (laprak.isAlive()) {
                System.out.println();
                laprak.attack(pemain);
            }
        }
 
        if (pemain.isAlive() != true) {
            System.out.println(teksKalah);
            input.close();
            return;
        }
        if (laprak.isAlive()) {
            System.out.println("\nGiliran habis, " + laprak.getName() + " belum kelar juga.");
            System.out.println(teksWaktuHabis);
            input.close();
            return;
        }
 
        System.out.println("\n" + laprak.getName() + " KELAR!");
        System.out.println("kamu submit jam 23.57, literally 2 menit pas sebelum deadline.");
        System.out.println("kamu ngerasa lega, sekarang kamu nyisihan uang buat self rewad");
        pemain.addGold(laprak.getGoldReward());
        System.out.println("Saldo Qris +" + laprak.getGoldReward());

        System.out.println("\n1 minggu sudah berlalu");
        System.out.println("\nsebelum UAP jam 7, kamu mampir ke kantin FILKOM");
        shop = true;
        while (shop) {
            System.out.println("\nKANTIN ");

            pemain.showStatus();

            for (int i = 0; i < menuKantin.length; i++) {
                System.out.println((i + 1) + ". " + menuKantin[i].getName() + " - Rp. "
                        + menuKantin[i].getPrice() + " (" + menuKantin[i].getDescription() + ")");
            }

            System.out.println("0. cabut dari kantin");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();
 
            if (pilihan == 0) {
                shop = false;
                continue;
            }
 
            if (pilihan < 1 || pilihan > menuKantin.length) {
                System.out.println("Menu tidak tersedia di kantin");
            } else if (pemain.buyItem(menuKantin[pilihan - 1])) {
                System.out.println("Kamu beli " + menuKantin[pilihan - 1].getName() + ". jajan dikit boleh lah.");
            } else {
                System.out.println("Saldo mu ga cukup. padahal belum akhir bulan.");
            }
        }
 
        System.out.println("\nSekarang udah masuk akhir semester. Hari ini ada UAP: Ujian Akhir Praktikum.");
        System.out.println("Pengawas masuk ruangan: \"Waktunya 2 jam. Sistem nya pakai safe exam browser jadi kamu gak minta bantuan claud\"");
        System.out.println("Satu ruangan langsung hening. fokus sama soal yang ada");
        System.out.println("Soal udah tersedia. Kamu baca sekilas... kok ga ada yang mirip latihan yaa?");
        System.out.println("BOSS MUNCUL: UAP!");
        System.out.println("\n>>> VS " + uap.getName() + " <<<");
 
        String[][] menuUap = {
            { "Baca semua soal dulu",
              "Kerjain soal paling gampang duluan",
              "Tarik napas, tenangkan pikirann" },   
            { "Bikin class yang diminta soal",
              "Bikin constructor",
              "Bikin getter dan setter" },  
            { "Tulis method perhitungan bilangan prima",
              "Pakai if-else buat kondisi sesuai soal",
              "Pakai looping buat ngolah variabel di soal" },   
            { "Run pertama kali",
              "Debug error satu per satu",
              "Cek nama variabel yang typo" },   
            { "Coba input contoh dari soal",
              "Print nilai variabel buat ngecek",
              "Rapihin indentasi kode" },   
            { "Run semua soal sekali lagi",
              "Cek ulang nama file",
              "Submit sebelum waktu habis" }  
        };
        String[][] teksUap = {
            { "Ada 3 soal kamu baca semuanya dulu biar tau mana yang paling gampang",
              "Soal nomor 2 paling gampang. Langsung gas biar dapet nilai aman dulu",
              "Tarik napas... buang... kamu sekarang jadi lebih fokus sama soal" },
            { "Class udah dibikin lengkap sama atribut yang private dan method yang public, udah nerapin konsep enkapsulasi.",
              "Constructor di di declare. Semua atribut keisi udah masuk",
              "Getter dan setter jadi, dosen mu yang ngajar OOP pasti bangga😎" }, 
            { "Method buat ngitung banyak bilangan prima total kamu tulis. Rumusnya udah kamu cek dua kali.",
              "If-else dibuat. Semua kondisi di soal udah ke-cover.",
              "Looping buat ngolah variabel jadi. Kali ini ga lupa sama i++." },
            { "Kamu klik evaluate. Muncul error merah, tapi sekarang jadi tahu salahnya di mana.",
              "Pesan error dibaca baris per baris. Ternyata kurang titik koma.",
              "Ternyata kamu nulis 'jumlah' jadi 'jumlha'. Pantes error dari tadi." },  
            { "Input contoh dari soal dimasukin. Output-nya udah mendekati.",
              "Kamu tambahin System.out.println di tengah kode. Bug-nya ketahuan.",
              "Kode dirapihin. Kurung kurawal yang ilang akhirnya ketemu." },  
            { "Semua soal di-run ulang. Output keluar tanpa error.",
              "Nama file kamu sesuaiin format NIM_Nama. Hampir aja salah.",
              "Kamu klik submit 1 menit sebelum waktu habis. Tangan masih gemeteran." }  
        };
        int jumlahGiliranUap = menuUap.length;  
 
        System.out.println("Kamu cuma punya " + jumlahGiliranUap + " giliran sebelum waktu habis!");
 
        for (int giliran = 0; giliran < jumlahGiliranUap && pemain.isAlive() && uap.isAlive(); giliran++) {
            System.out.println("\n Giliran ke " + (giliran + 1) + " dari " + jumlahGiliranUap);

            pemain.showStatus();

            uap.showStatus();

            for (int i = 0; i < menuUap[giliran].length; i++) {
                System.out.println((i + 1) + ". " + menuUap[giliran][i]);
            }

            if (pemain.getPotions() <= 0) {
                System.out.println("4. Es teh Habis");
            } else if (pemain.getHp() >= pemain.getMaxHp()) {
                System.out.println("4. (Es Teh: Mental masih full)");
            } else {
                System.out.println("4. Sedot Es Teh Jumbo diam-diam (+10 Mental)");
            }

            System.out.print("Pilih aksi: ");
            pilihan = input.nextInt();

            System.out.println();
 
            if (pilihan >= 1 && pilihan <= 3) {
                int nomorPilihan = pilihan - 1;
                System.out.println(teksUap[giliran][nomorPilihan]);
                pemain.attack(uap);
            } else if (pilihan == 4) {
                if (pemain.getPotions() <= 0) {
                    System.out.println("Es Teh-nya ternyata udah abis! Kamu cuma bengong, giliran ini kebuang.");
                } else if (pemain.getHp() >= pemain.getMaxHp()) {
                    System.out.println("Mental kamu masih full, Es Teh-nya ga jadi diminum");
                } else {
                    System.out.println("Pas pengawas nengok ke arah lain, kamu nyedot Es Teh di bawah meja.");
                    pemain.drinkPotion();
                }
            } else {
                System.out.println("Kamu kelamaan mikir");
            }
 
            if (uap.isAlive()) {
                System.out.println();
                uap.attack(pemain);
            }
        }
 
        if (!pemain.isAlive()) {
            System.out.println(teksKalah);
            input.close();
            return;
        }
        if (uap.isAlive()) {
            System.out.println("\nGiliran habis, " + uap.getName() + " belum kelar juga.");
            System.out.println(teksWaktuHabis);
            input.close();
            return;
        }
 
        System.out.println("\n" + uap.getName() + " KELAR!");
        System.out.println("Semua soal ke-submit. Output-nya persis kayak test case");
        System.out.println("nilai mu 100, mood mu jadi happy");
        System.out.println("\n===== CHAPTER 1 CLEAR =====");
        System.out.println("...tapi grup kelas bunyi lagi: \"Pembagian tugas kelompok udah keluar ya guys.\"");
        System.out.println("Plot twist incoming. (Bersambung)");
    }
}