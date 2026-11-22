public class Item {
    private String name;
    private int price;
    private int attackBonus;   
    private int hpBonus;       
    private int potionBonus;   
    private String description;

    public Item(String name, int price, int attackBonus, int hpBonus, int potionBonus, String description) {
        this.name = name;
        this.price = price;
        this.attackBonus = attackBonus;
        this.hpBonus = hpBonus;
        this.potionBonus = potionBonus;
        this.description = description;
    }

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
