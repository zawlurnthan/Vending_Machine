package service;


import dao.VMPersistenceException;
import dto.Item;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class VMServiceLayerImplTest {

    private final VMServiceLayer testService;

    public VMServiceLayerImplTest() {
        // update to spring dependency Injection XML configuration from constructor injection
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testService = ctx.getBean("service", VMServiceLayerImpl.class);
    }

    @Test
    void testCreateItem() {
        // crate an item
        Item sprite = new Item("2");
        sprite.setName("Sprite");
        sprite.setCost(new BigDecimal("0.80"));
        sprite.setCount(7);

        try {
            testService.createItem(sprite.getId(), sprite);
        } catch (VMPersistenceException e) {
            // assert
            fail("Item was valid. No exception should have been thrown.");
        }
    }

    @Test
    void testGetItem() throws VMPersistenceException{
        // arrange and create item to reference in assert equal method
        Item coke = new Item("1");
        coke.setName("Coke");
        coke.setCost(new BigDecimal("0.75"));
        coke.setCount(5);

        // act & assert
        Item shouldBeCoke = testService.getItem("1");
        assertNotNull(shouldBeCoke, "Result should not be null");
        assertEquals(coke, shouldBeCoke, "It should be Coke.");

        Item shouldBeNull = testService.getItem("13");
        assertNull(shouldBeNull, "Id 13 should be null");
    }

    @Test
    void testGetAllItems() throws VMPersistenceException {
        // arrange
        Item coke = new Item("1");
        coke.setName("Coke");
        coke.setCost(new BigDecimal("0.75"));
        coke.setCount(5);

        // act & assert
        assertEquals(1, testService.getAllItems().size(), "Should have only one item.");
        assertTrue(testService.getAllItems().contains(coke), "The item should be Coke");
    }

    @Test
    void testDeleteItem() throws VMPersistenceException {
        // arrange
        Item coke = new Item("1");
        coke.setName("Coke");
        coke.setCost(new BigDecimal("0.75"));
        coke.setCount(5);

        // act &  assert
        Item shouldBeCoke = testService.deleteItem("1");
        assertNotNull(shouldBeCoke, "Item with ID 1 should not null.");
        assertEquals(coke, shouldBeCoke, "Removed item should be Coke");

        Item shouldBeNull = testService.deleteItem("33");
        assertNull(shouldBeNull, "Removing item should be null");
    }
}