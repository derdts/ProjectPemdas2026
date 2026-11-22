public class Enemy {
    // Atribut (private = enkapsulasi)
    private String name;
    private int hp;
    private int maxHp;
    private int attackPower;
    private int goldReward;

    // Constructor
    public Enemy(String name, int maxHp, int attackPower, int goldReward) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attackPower = attackPower;
        this.goldReward = goldReward;
    }

    // ===== Getter =====
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getGoldReward() {
        return goldReward;
    }

    // ===== Aksi =====

    // Menyerang player
    public void attack(Player player) {
        System.out.println(name + " nyerang mental " + player.getName() + "! Mental -" + attackPower + ".");
        player.takeDamage(attackPower);
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

    public void showStatus() {
        System.out.println("[" + name + "] Progress belum kelar: " + hp + "/" + maxHp);
    }
}
