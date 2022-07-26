package service;

import dao.VMDao;
import dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DaoStubImpl implements VMDao {
    public Item onlyItem;

    public DaoStubImpl() {
        // create a item object
        onlyItem = new Item("1");
        onlyItem.setName("Coke");
        onlyItem.setCost(new BigDecimal("0.75"));
        onlyItem.setCount(5);
    }

    @Override
    public Item createItem(String id, Item item) {
        // return item if created item's id and exist item's  id
        return id.equals(onlyItem.getId()) ? onlyItem : null;
    }

    @Override
    public Item getItem(String id) {
        return id.equals(onlyItem.getId()) ? onlyItem : null;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(onlyItem);
        return allItems;
    }

    @Override
    public Item deleteItem(String id) {
        return id.equals(onlyItem.getId()) ? onlyItem : null;
    }
}
