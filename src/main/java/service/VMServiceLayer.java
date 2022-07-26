package service;

import dao.VMPersistenceException;
import dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VMServiceLayer {
    Item createItem(String id, Item item) throws VMPersistenceException;
    Item getItem(String id) throws VMPersistenceException;
    List<Item> getAllItems() throws VMPersistenceException;
    Item deleteItem(String id) throws VMPersistenceException;

    void showMessage(Item item, BigDecimal amount) throws VMPersistenceException, InsufficientFunException;
}
