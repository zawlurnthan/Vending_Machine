package dao;

import dto.Item;

import java.util.List;

public interface VMDao {
    Item createItem(String id, Item item) throws VMPersistenceException;
    Item getItem(String id) throws VMPersistenceException;
    List<Item> getAllItems() throws VMPersistenceException;
    Item deleteItem(String id) throws VMPersistenceException;
}
