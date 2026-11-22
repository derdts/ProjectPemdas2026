import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean shopping;

        // ===== Membuat player =====
        System.out.println("==========================================");
        System.out.println("   DEADLINE SURVIVOR: EDISI FILKOM");
        System.out.println("==========================================");
        System.out.print("Masukkan nama kamu, maba: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Maba";
        }

        // nama, Mental (HP), Skill Ngoding (attack), jumlah Es Teh Jumbo
        Player player = new Player(name, 30, 5, 2);

        // ===== Musuh chapter 1 =====
        // nama, Mental/HP, attack, hadiah saldo
        Enemy tugasOspek = new Enemy("Tugas Ospek", 10, 3, 20);
        Enemy laporanPraktikum = new Enemy("Laporan Praktikum Bab 1", 15, 4, 30);
        Enemy liveCoding = new Enemy("Live Coding Praktikum", 30, 6, 0);

        // ===== Item kantin =====
        // nama, harga, +skill, +max mental, +es teh, deskripsi
        Item[] shopItems = {
            new Item("Kopi Susu Gula Aren", 25, 3, 0, 0, "+3 Skill Ngoding"),
            new Item("Nasi Padang Porsi Kuli", 20, 0, 10, 0, "+10 max Mental"),
            new Item("Es Teh Jumbo", 10, 0, 0, 1, "+1 Es Teh Jumbo")
        };

        // Teks game over dipakai di beberapa tempat
        String gameOverText = "\nMental kamu habis. Kamu ketiduran di depan laptop dan kebangun jam 00.01.\n"
                + "Deadline lewat. Tombol submit udah abu-abu.\n"
                + "===== STATUS: MENTAL BREAKDOWN =====\n"
                + "Skill issue? Bukan. Cuma kurang Es Teh. Coba lagi, bestie.";

        System.out.println("\n===== CHAPTER 1: MABA ERA, LANGSUNG DIHAJAR =====");

        // =====================================================
        // BAGIAN 1: TUGAS OSPEK
        // =====================================================
        System.out.println("\nSelamat datang di Fakultas Ilmu Komputer, bestie.");
        System.out.println("Kamu resmi jadi maba Teknik Informatika. Ekspektasi: ngoding santuy, hidup estetik.");
        System.out.println("Realita: kuliah aja belum mulai, grup ospek udah 99+ notif.");
        System.out.println("\"Dek, tugas ospek dikumpul besok jam 06.00 ya. Telat = push up.\"");
        System.out.println("Isinya: name tag dari kardus, video perkenalan, esai 1000 kata");
        System.out.println("'Kenapa Aku Memilih FILKOM', plus tanda tangan 30 kating.");
        System.out.println("TUGAS OSPEK muncul! Kecil-kecil cabe rawit.");
        System.out.println("\n>>> VS TUGAS OSPEK <<<");

        while (player.isAlive() && tugasOspek.isAlive()) {
            System.out.println();
            player.showStatus();
            tugasOspek.showStatus();
            System.out.println("1. Ngoding brutal (serang)");
            System.out.println("2. Sedot Es Teh Jumbo (+10 Mental)");
            System.out.print("Pilih aksi: ");
            input = scanner.nextLine().trim();

            if (input.equals("1")) {
                player.attack(tugasOspek);
            } else if (input.equals("2")) {
                if (!player.drinkPotion()) {
                    System.out.println("Es Teh abis, bestie! Pilih aksi lain.");
                    continue;
                }
            } else {
                System.out.println("Typo ya? Ketik 1 atau 2.");
                continue;
            }

            if (tugasOspek.isAlive()) {
                tugasOspek.attack(player);
            }
        }

        if (!player.isAlive()) {
            System.out.println(gameOverText);
            scanner.close();
            return;
        }

        System.out.println("\nTugas Ospek KELAR!");
        System.out.println("Name tag kardus jadi, esai kelar, tanda tangan kating lengkap 30.");
        System.out.println("Kating yang galak pas ospek ternyata baik, kamu ditraktir.");
        player.addGold(tugasOspek.getGoldReward());
        System.out.println("Saldo +" + tugasOspek.getGoldReward());

        // ===== KANTIN 1 =====
        System.out.println("\nKamu mampir ke kantin. Ibu kantin udah hafal muka maba abis begadang.");
        System.out.println("\"Mau yang biasa, Mas?\" Padahal baru pertama kali ke sini. Beda level.");
        shopping = true;
        while (shopping) {
            System.out.println("\n========== KANTIN ==========");
            player.showStatus();
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
            } else if (player.buyItem(chosen)) {
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

        while (player.isAlive() && laporanPraktikum.isAlive()) {
            System.out.println();
            player.showStatus();
            laporanPraktikum.showStatus();
            System.out.println("1. Ngoding brutal (serang)");
            System.out.println("2. Sedot Es Teh Jumbo (+10 Mental)");
            System.out.print("Pilih aksi: ");
            input = scanner.nextLine().trim();

            if (input.equals("1")) {
                player.attack(laporanPraktikum);
            } else if (input.equals("2")) {
                if (!player.drinkPotion()) {
                    System.out.println("Es Teh abis, bestie! Pilih aksi lain.");
                    continue;
                }
            } else {
                System.out.println("Typo ya? Ketik 1 atau 2.");
                continue;
            }

            if (laporanPraktikum.isAlive()) {
                laporanPraktikum.attack(player);
            }
        }

        if (!player.isAlive()) {
            System.out.println(gameOverText);
            scanner.close();
            return;
        }

        System.out.println("\nLaporan Praktikum Bab 1 KELAR!");
        System.out.println("Submit jam 23.57. Literally 2 menit sebelum deadline. Jantung? Udah ga aman.");
        System.out.println("Tapi menang tetep menang, no debat. Ortu transfer uang jajan.");
        player.addGold(laporanPraktikum.getGoldReward());
        System.out.println("Saldo +" + laporanPraktikum.getGoldReward());

        // ===== KANTIN 2 =====
        System.out.println("\nSebelum praktikum besok, kamu mampir ke kantin lagi.");
        System.out.println("Ibu kantin: \"Muka kamu kayak abis begadang ya, Mas. Makan dulu.\"");
        shopping = true;
        while (shopping) {
            System.out.println("\n========== KANTIN ==========");
            player.showStatus();
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
            } else if (player.buyItem(chosen)) {
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

        while (player.isAlive() && liveCoding.isAlive()) {
            System.out.println();
            player.showStatus();
            liveCoding.showStatus();
            System.out.println("1. Ngoding brutal (serang)");
            System.out.println("2. Sedot Es Teh Jumbo (+10 Mental)");
            System.out.print("Pilih aksi: ");
            input = scanner.nextLine().trim();

            if (input.equals("1")) {
                player.attack(liveCoding);
            } else if (input.equals("2")) {
                if (!player.drinkPotion()) {
                    System.out.println("Es Teh abis, bestie! Pilih aksi lain.");
                    continue;
                }
            } else {
                System.out.println("Typo ya? Ketik 1 atau 2.");
                continue;
            }

            if (liveCoding.isAlive()) {
                liveCoding.attack(player);
            }
        }

        if (!player.isAlive()) {
            System.out.println(gameOverText);
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
