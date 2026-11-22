public class Enemy {
    private String name;
    private int hp;
    private int maxHp;
    private int attackPower;
    private int goldReward;

    public Enemy(String name, int maxHp, int attackPower, int goldReward) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attackPower = attackPower;
        this.goldReward = goldReward;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public void attack(Player player) {
        System.out.println(name + " nyerang mental " + player.getName() + "! Mental -" + attackPower);
        player.takeDamage(attackPower);
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

    public void showStatus() {
        System.out.println("[" + name + "] belum kelar: " + hp + "/" + maxHp);
    }
}
