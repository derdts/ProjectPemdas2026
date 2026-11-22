public class Item {
    // Atribut (private = enkapsulasi)
    private String name;
    private int price;
    private int attackBonus;   // tambahan attack
    private int hpBonus;       // tambahan max HP
    private int potionBonus;   // tambahan jumlah ramuan
    private String description;

    // Constructor
    public Item(String name, int price, int attackBonus, int hpBonus, int potionBonus, String description) {
        this.name = name;
        this.price = price;
        this.attackBonus = attackBonus;
        this.hpBonus = hpBonus;
        this.potionBonus = potionBonus;
        this.description = description;
    }

    // ===== Getter =====
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getHpBonus() {
        return hpBonus;
    }

    public int getPotionBonus() {
        return potionBonus;
    }

    public String getDescription() {
        return description;
    }
}
