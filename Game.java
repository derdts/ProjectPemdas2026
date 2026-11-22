import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean shopping;

        // bikin player
        System.out.println("Tahun ini kamu udah jadi mahasiswa baru");
        System.out.print("Siapa nama mu??? ");
        String nama = scanner.nextLine();
        Player pemain = new Player(nama, 30, 5, 2);

        // bikin musuh
        Enemy tugasOspek = new Enemy("Tugas Ospek", 10, 3, 20);
        Enemy laporanPraktikum = new Enemy("Live Koding Looping", 15, 4, 30);
        Enemy liveCoding = new Enemy("U A P😈", 30, 6, 0);

        // bikin item
        Item[] shopItems = {
            new Item("Americano", 25, 3, 0, 0, "+3 Skill Ngoding"),
            new Item("Nasi Padang", 20, 0, 10, 0, "+10 max Mental"),
            new Item("Es Teh Jumbo", 10, 0, 0, 1, "+1 Es Teh Jumbo")
        };

        // text kalo kalah
        String textGameOver = "\nMental kamu habis. Kamu ketiduran di depan laptop dan kebangun jam 00.01.\n"
                + "Deadline lewat. Tombol submit udah abu-abu.\n"
                + "===== STATUS: SKILL ISSUE Wlee =====\n"
                + "Cuma kurang Es Teh. Coba lagi nub";

        System.out.println("\n===== CHAPTER 1: MABA ERA, LANGSUNG DIHAJAR =====");

        
        System.out.println("\nSelamat datang di FILKOM wahai, " + pemain.getName());
        System.out.println("Kamu sekarang udah resmi jadi maba Teknik Informatika");
        System.out.println("sekarang lagi masa ospek, kamu lagi doomscrolling tiba tiba...");
        System.out.println("kamu dapat notif dari mentor mu" + "\"ayo yang belum submit penugasan segera ya, terakhir hari ini\"");
        System.out.println("tugas nya: bikin twibbon pakai template yang udah di kasih");
        System.out.println("'okeh ez nih'");

        System.out.println("\n>>> VS TUGAS TWIBBON <<<");

        int giliran1 = 1;
        while (pemain.isAlive() && tugasOspek.isAlive()) {

            pemain.showStatus();

            tugasOspek.showStatus();

            if (giliran1 == 1) {
                System.out.println("1. cari foto yang estetik");
                System.out.println("1. buka canva dan ");
                System.out.println("2. Minum Es Teh Jumbo");
                System.out.print("Pilih aksi: ");
                input = scanner.nextLine().trim();

                if (input.equals("1")) {

                    pemain.attack(tugasOspek);

                } else if (input.equals("2")) {

                    if (!pemain.drinkPotion()) {
                        System.out.println("Es Teh abis, bestie! Pilih aksi lain.");
                        continue;
                    }

                }

                if (tugasOspek.isAlive()) {
                    tugasOspek.attack(pemain);
                }
            } else if (giliran1 == 2) {

            }


        }

        if (!pemain.isAlive()) {
            System.out.println(textGameOver);
            scanner.close();
            return;
        }

        System.out.println("\nTugas Ospek KELAR!");
        System.out.println("Name tag kardus jadi, esai kelar, tanda tangan kating lengkap 30.");
        System.out.println("Kating yang galak pas ospek ternyata baik, kamu ditraktir.");
        pemain.addGold(tugasOspek.getGoldReward());
        System.out.println("Saldo +" + tugasOspek.getGoldReward());

        // ===== KANTIN 1 =====
        System.out.println("\nKamu mampir ke kantin. Ibu kantin udah hafal muka maba abis begadang.");
        System.out.println("\"Mau yang biasa, Mas?\" Padahal baru pertama kali ke sini. Beda level.");
        shopping = true;
        while (shopping) {
            System.out.println("\n========== KANTIN ==========");
            pemain.showStatus();
            for (int j = 0; j < shopItems.length; j++) {
                System.out.println((j + 1) + ". " + shopItems[j].getName() + " - "
                        + shopItems[j].getPrice() + " saldo (" + shopItems[j].getDescription() + ")");
            }
            System.out.println("0. Cabut dari kantin");
            System.out.print("Pilih: ");
            input = scanner.nextLine().trim();

            if (input.equals("0")) {
                shopping = false;
                continue;
            }

            Item chosen = null;
            for (int j = 0; j < shopItems.length; j++) {
                if (input.equals(String.valueOf(j + 1))) {
                    chosen = shopItems[j];
                }
            }

            if (chosen == null) {
                System.out.println("Menu itu ga ada, bestie.");
            } else if (pemain.buyItem(chosen)) {
                System.out.println("Kamu beli " + chosen.getName() + ". Healing dikit lah.");
            } else {
                System.out.println("Saldo kamu ga cukup. Akhir bulan vibes.");
            }
        }

        // =====================================================
        // BAGIAN 2: LAPORAN PRAKTIKUM
        // =====================================================
        System.out.println("\nOspek kelar, kuliah dimulai. Grup kelas langsung bunyi:");
        System.out.println("\"GUYS LAPORAN PRAKTIKUM DIKUMPUL MALEM INI JAM 23.59, NO TOLERANSI\"");
        System.out.println("Laptop dibuka. Word masih kosong. Kursornya kedip-kedip kayak lagi ngeledek.");
        System.out.println("Template-nya 12 halaman, screenshot output wajib ada nama + NIM.");
        System.out.println("LAPORAN PRAKTIKUM BAB 1 muncul! Auranya nyebelin parah.");
        System.out.println("\n>>> VS LAPORAN PRAKTIKUM BAB 1 <<<");

        while (pemain.isAlive() && laporanPraktikum.isAlive()) {
            System.out.println();
            pemain.showStatus();
            laporanPraktikum.showStatus();
            System.out.println("1. Ngoding brutal (serang)");
            System.out.println("2. Sedot Es Teh Jumbo (+10 Mental)");
            System.out.print("Pilih aksi: ");
            input = scanner.nextLine().trim();

            if (input.equals("1")) {
                pemain.attack(laporanPraktikum);
            } else if (input.equals("2")) {
                if (!pemain.drinkPotion()) {
                    System.out.println("Es Teh abis, bestie! Pilih aksi lain.");
                    continue;
                }
            } else {
                System.out.println("Typo ya? Ketik 1 atau 2.");
                continue;
            }

            if (laporanPraktikum.isAlive()) {
                laporanPraktikum.attack(pemain);
            }
        }

        if (!pemain.isAlive()) {
            System.out.println(textGameOver);
            scanner.close();
            return;
        }

        System.out.println("\nLaporan Praktikum Bab 1 KELAR!");
        System.out.println("Submit jam 23.57. Literally 2 menit sebelum deadline. Jantung? Udah ga aman.");
        System.out.println("Tapi menang tetep menang, no debat. Ortu transfer uang jajan.");
        pemain.addGold(laporanPraktikum.getGoldReward());
        System.out.println("Saldo +" + laporanPraktikum.getGoldReward());

        // ===== KANTIN 2 =====
        System.out.println("\nSebelum praktikum besok, kamu mampir ke kantin lagi.");
        System.out.println("Ibu kantin: \"Muka kamu kayak abis begadang ya, Mas. Makan dulu.\"");
        shopping = true;
        while (shopping) {
            System.out.println("\n========== KANTIN ==========");
            pemain.showStatus();
            for (int j = 0; j < shopItems.length; j++) {
                System.out.println((j + 1) + ". " + shopItems[j].getName() + " - "
                        + shopItems[j].getPrice() + " saldo (" + shopItems[j].getDescription() + ")");
            }
            System.out.println("0. Cabut dari kantin");
            System.out.print("Pilih: ");
            input = scanner.nextLine().trim();

            if (input.equals("0")) {
                shopping = false;
                continue;
            }

            Item chosen = null;
            for (int j = 0; j < shopItems.length; j++) {
                if (input.equals(String.valueOf(j + 1))) {
                    chosen = shopItems[j];
                }
            }

            if (chosen == null) {
                System.out.println("Menu itu ga ada, bestie.");
            } else if (pemain.buyItem(chosen)) {
                System.out.println("Kamu beli " + chosen.getName() + ". Healing dikit lah.");
            } else {
                System.out.println("Saldo kamu ga cukup. Akhir bulan vibes.");
            }
        }

        // =====================================================
        // BAGIAN 3: BOSS - LIVE CODING PRAKTIKUM
        // =====================================================
        System.out.println("\nBesoknya praktikum. Asprak masuk, senyum tipis, terus bilang:");
        System.out.println("\"Oke adik-adik, hari ini live coding ya. Ga boleh buka Google, ga boleh buka AI.\"");
        System.out.println("Satu lab langsung hening. Ada yang pura-pura ke toilet.");
        System.out.println("Kode kamu merah semua. NullPointerException ngeliatin kamu kayak mantan.");
        System.out.println("BOSS MUNCUL: LIVE CODING PRAKTIKUM!");
        System.out.println("\n>>> VS LIVE CODING PRAKTIKUM <<<");

        while (pemain.isAlive() && liveCoding.isAlive()) {
            System.out.println();
            pemain.showStatus();
            liveCoding.showStatus();
            System.out.println("1. Ngoding brutal (serang)");
            System.out.println("2. Sedot Es Teh Jumbo (+10 Mental)");
            System.out.print("Pilih aksi: ");
            input = scanner.nextLine().trim();

            if (input.equals("1")) {
                pemain.attack(liveCoding);
            } else if (input.equals("2")) {
                if (!pemain.drinkPotion()) {
                    System.out.println("Es Teh abis, bestie! Pilih aksi lain.");
                    continue;
                }
            } else {
                System.out.println("Typo ya? Ketik 1 atau 2.");
                continue;
            }

            if (liveCoding.isAlive()) {
                liveCoding.attack(pemain);
            }
        }

        if (!pemain.isAlive()) {
            System.out.println(textGameOver);
            scanner.close();
            return;
        }

        // =====================================================
        // ENDING CHAPTER 1
        // =====================================================
        System.out.println("\nLive Coding Praktikum KELAR!");
        System.out.println("Program kamu akhirnya jalan. Output-nya persis kayak soal.");
        System.out.println("Asprak ngangguk: \"Oke, aman.\"");
        System.out.println("Dua kata yang rasanya lebih berharga dari IPK 4. Menyala abangku!");
        System.out.println("\n===== CHAPTER 1 CLEAR =====");
        System.out.println("...tapi grup kelas bunyi lagi: \"Pembagian tugas kelompok udah keluar ya guys.\"");
        System.out.println("Plot twist incoming. (Bersambung)");

        scanner.close();
    }
}
