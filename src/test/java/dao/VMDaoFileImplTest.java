package dao;

import dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VMDaoFileImplTest {

    VMDao testDao;

    @BeforeEach
    void setUp() throws IOException {
        // create temporary file for inventory
        String testFile = "testInventory.txt";
        new FileWriter(testFile);
        // assign it in Dao implement file constructor
        testDao = new VMDaoFileImpl(testFile);
    }

    @Test
    void testAddAndGetItem() throws VMPersistenceException {
        // create item test
        String id = "1";
        String name = "Coke";
        BigDecimal cost = new BigDecimal("0.75");
        int count = 5;
        Item createItem = new Item(id, name, cost, count);

        // add item to Dao
        testDao.createItem(id, createItem);
        // get item from Dao
        Item retrievedItem = testDao.getItem(id);

        // check (one by one of object properties) if objects are equal
        assertEquals(createItem.getId(), retrievedItem.getId(), "Checking item id.");
        assertEquals(createItem.getName(), retrievedItem.getName(), "Checking item name.");
        assertEquals(createItem.getCost(), retrievedItem.getCost(), "Checking item cost.");
        assertEquals(createItem.getCount(), retrievedItem.getCount(), "Checking item count.");
    }

    @Test
    void testGetAllItems() throws VMPersistenceException {
        // create first item test
        Item coke = new Item("1");
        coke.setName("Coke");
        coke.setCost(new BigDecimal("0.75"));
        coke.setCount(5);

        // create second item
        Item sprite = new Item("2");
        sprite.setName("Sprite");
        sprite.setCost(new BigDecimal("0.80"));
        sprite.setCount(7);

        // add both item objects to Dao
        testDao.createItem(coke.getId(), coke);
        testDao.createItem(sprite.getId(), sprite);

        // retrieved item objects from Dao
        List<Item> allItems = testDao.getAllItems();

        assertNotNull(allItems, "The list should not be null.");
        assertEquals(2, allItems.size(), "The list of item should have 2 items.");

        assertTrue(testDao.getAllItems().contains(coke), "Coke should be in the list.");
        assertTrue(testDao.getAllItems().contains(sprite), "Sprite should be in the list.");
    }


    @Test
    void deleteItem() throws VMPersistenceException {
        // create first item test
        Item coke = new Item("1");
        coke.setName("Coke");
        coke.setCost(new BigDecimal("0.75"));
        coke.setCount(5);

        // create second item
        Item sprite = new Item("2");
        sprite.setName("Sprite");
        sprite.setCost(new BigDecimal("0.80"));
        coke.setCount(7);

        // add both item objects to Dao
        testDao.createItem(coke.getId(), coke);
        testDao.createItem(sprite.getId(), sprite);

        // remove first item - coke
        Item removedCoke = testDao.deleteItem(coke.getId());
        // check the correct object was removed
        assertEquals(removedCoke, coke, "The removed item should be Coke.");

        // get all items
        List<Item> allItems = testDao.getAllItems();

        // first check general contents of the list
        assertNotNull(allItems, "All items should not be null");
        assertEquals(1, allItems.size(), "All items should only have 1 item.");

        assertFalse(allItems.contains(coke), "All items should not include Coke");
        assertTrue(allItems.contains(sprite), "All items should include Sprite.");

        // remove second item - sprite
        Item removedSprite = testDao.deleteItem(sprite.getId());
        // check the correct object was removed
        assertEquals(removedSprite, sprite, "The removed item should be Sprite.");

        // retrieve all the items
        allItems = testDao.getAllItems();

        // check the content of the list, it should be empty
        assertTrue(allItems.isEmpty(), "The list should be empty.");

        // try to get both items by using their old id, it should be null.
        Item retrievedCoke = testDao.getItem(coke.getId());
        Item retrievedSprite = testDao.getItem(sprite.getId());

        assertNull(retrievedCoke, "Coke was removed, should be null");
        assertNull(retrievedSprite, "Sprite was removed, should be null");
    }
}