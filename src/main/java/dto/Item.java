package dto;

import java.math.BigDecimal;

public class Item {
    private String id;
    private String name;
    private BigDecimal cost;
    private int count;

    public Item() {
    }

    public Item(String id) {
        this.id = id;
    }

    public Item(String id, String name, BigDecimal cost, int count) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!id.equals(item.id)) return false;
        if (!name.equals(item.name)) return false;
        return cost.equals(item.cost);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + cost.hashCode();
        return result;
    }
}
