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

    // sistem kombat
    public void attack(Enemy enemy) {
        System.out.println(name + "nyerang" + enemy.getName() + " dan nge damage " + attackPower);
        enemy.takeDamage(attackPower);
    }

    public boolean drinkPotion() {

        if (potions <= 0) {
            return false;
        }

        potions -= 1;

        hp = hp + 10;

        if (hp > maxHp) {
            hp = maxHp;
        }

        System.out.println(name + " minum Es Teh Jumbo. 'jos jis', Mental sekarang " + hp);
        return true;
    }

    public void takeDamage(int damage) {
        hp = hp - damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public boolean buyItem(Item item) {

        if (gold < item.getPrice()) {
            return false;
        }

        gold -= item.getPrice();
        attackPower = attackPower + item.getAttackBonus();
        maxHp += item.getHpBonus();
        hp += item.getHpBonus();
        potions += item.getPotionBonus();
        return true;
    }

    public void showStatus() {
        System.out.println("[" + name + "] Mental: " + hp + "/" + maxHp
                + " | Skill: " + attackPower
                + " | Es Teh Jumbo: " + potions
                + " | Saldo Qris: " + gold);
    }
}
