public class Player {
    // Atribut (private = enkapsulasi)
    private String name;
    private int hp;
    private int maxHp;
    private int attackPower;
    private int potions;
    private int gold;

    // Constructor
    public Player(String name, int maxHp, int attackPower, int potions) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attackPower = attackPower;
        this.potions = potions;
        this.gold = 0;
    }

    // ===== Getter =====
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getPotions() {
        return potions;
    }

    public int getGold() {
        return gold;
    }

    // ===== Aksi kombat =====

    // Menyerang musuh
    public void attack(Enemy enemy) {
        System.out.println(name + " ngoding brutal! " + enemy.getName() + " kena " + attackPower + " damage!");
        enemy.takeDamage(attackPower);
    }

    // Minum ramuan: pulihkan 10 HP, tidak boleh melebihi max HP
    // Mengembalikan false jika ramuan sudah habis
    public boolean drinkPotion() {
        if (potions <= 0) {
            return false;
        }
        potions = potions - 1;
        hp = hp + 10;
        if (hp > maxHp) {
            hp = maxHp;
        }
        System.out.println(name + " nyedot Es Teh Jumbo. Seger parah! Mental sekarang " + hp + ".");
        return true;
    }

    // Menerima damage, HP tidak boleh di bawah 0
    public void takeDamage(int damage) {
        hp = hp - damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    // ===== Gold dan item =====

    public void addGold(int amount) {
        if (amount > 0) {
            gold = gold + amount;
        }
    }

    // Membeli item: gold dikurangi, lalu bonus item langsung diterapkan
    // Mengembalikan false jika gold tidak cukup
    public boolean buyItem(Item item) {
        if (gold < item.getPrice()) {
            return false;
        }
        gold = gold - item.getPrice();
        attackPower = attackPower + item.getAttackBonus();
        maxHp = maxHp + item.getHpBonus();
        hp = hp + item.getHpBonus();
        potions = potions + item.getPotionBonus();
        return true;
    }

    public void showStatus() {
        System.out.println("[" + name + "] Mental: " + hp + "/" + maxHp
                + " | Skill Ngoding: " + attackPower
                + " | Es Teh: " + potions
                + " | Saldo: " + gold);
    }
}
