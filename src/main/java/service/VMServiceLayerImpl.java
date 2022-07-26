package service;

import dao.*;
import dto.Item;
import dto.Coin;


import java.math.BigDecimal;
import java.util.List;

public class VMServiceLayerImpl implements VMServiceLayer{
    VMDao dao;
    VMAuditDao auditDao;

    public VMServiceLayerImpl(VMDao dao, VMAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Item createItem(String id, Item item) throws VMPersistenceException {
        Item newItem = dao.createItem(id, item);
        auditDao.writeAuditEntry("Item " + id + " was CREATED.");
        return newItem;
    }

    @Override
    public Item getItem(String id) throws VMPersistenceException{
        // make sure if item is not out of order
        List<Item> list = this.getAllItems();
        auditDao.writeAuditEntry("Item " + id + " was SELECTED.");
        Item item = dao.getItem(id);
        return list.contains(item) ? item : null;
    }

    @Override
    public List<Item> getAllItems() throws VMPersistenceException {
        auditDao.writeAuditEntry("List was VIEWED");
        // get all items which items are not count zero
        return dao.getAllItems().stream().filter(x -> x.getCount() > 0).toList();
    }

    @Override
    public Item deleteItem(String id) throws VMPersistenceException {
        Item item = dao.deleteItem(id);
        if (item == null) {
            System.out.println("No such a item.");
            return null;
        }
        auditDao.writeAuditEntry("Item " + id + " was DELETED.");
        return item;
    }

    public void showMessage(Item item, BigDecimal amount) throws VMPersistenceException, InsufficientFunException {
        if (item != null) {
            // get the change amount
            BigDecimal change = amount.subtract(item.getCost());

            // display if fun insufficient
            if (change.doubleValue() < 0.0) {
                // throw insufficient fun exception
                throw new InsufficientFunException("Insufficient fund, your input is $" + amount + ", add $" + change.abs() +
                        " more to get selected item!");
            }
            // display if there is no change and update count of the item
            else if (change.doubleValue() == 0) {
                System.out.println("No change this time, Thank You.");
                updateItemCount(item.getId());
            }
            // display if there is change return and update count of the item
            else {
                System.out.printf("\nHere is the change $%s.%n", change.abs());
                getCoins(change);
                updateItemCount(item.getId());
            }
        }
    }

    private void getCoins(BigDecimal change){
        // get coins of change
        List<Coin> list = Coin.getCoins(change);
        // print each coins
        list.stream()
                .filter(x -> x.getCount() > 0)
                .forEach(x -> System.out.println(x.getCount() + " * " + x.getBill()));
    }

    private void updateItemCount(String id) throws VMPersistenceException {
        Item item = dao.getItem(id);
        int count = item.getCount();
        // decrease count value
        item.setCount(count - 1);
        // update item in inventory
        dao.createItem(id, item);
    }
}
