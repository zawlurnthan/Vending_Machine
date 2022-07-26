package dao;

import dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VMDaoFileImpl implements VMDao {
    // have a data structure to hold all information
    private final Map<String, Item> inventory = new LinkedHashMap<>();

    public String INVENTORY_FILE = "inventory.txt";
    public static String DELIMITER = "::";

    public VMDaoFileImpl() {
    }

    public VMDaoFileImpl(String testFile) {
        INVENTORY_FILE = testFile;
    }

    @Override
    public Item createItem(String id, Item item) throws VMPersistenceException {
        loadInventory();
        Item newItem = inventory.put(id, item);
        writeInventory();
        return newItem;
    }

    @Override
    public Item getItem(String id) throws VMPersistenceException {
        loadInventory();
        return inventory.get(id);
    }

    @Override
    public List<Item> getAllItems() throws VMPersistenceException {
        loadInventory();
        return inventory.values().stream().toList();
    }

    @Override
    public Item deleteItem(String id) throws VMPersistenceException {
        loadInventory();
        Item item = inventory.remove(id);
        writeInventory();
        return item;
    }

    // convert object to string
    private String marshallItem(Item item) {
        return  item.getId() + DELIMITER +
                item.getName() + DELIMITER +
                item.getCost() + DELIMITER +
                item.getCount();
    }

    // write file
    private void writeInventory() throws VMPersistenceException {
        PrintWriter out;

        // try and catch if it's not able to write in the file
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VMPersistenceException("Could not save item data.", e);
        }

        // get all items from the Map
        List<Item> itemList = this.getAllItems();
        for (Item i : itemList) {
            // convert object to string
            String text = marshallItem(i);
            // write strings into the file
            out.println(text);
            out.flush();
        }
        out.close();
    }

    // convert string to object
    private Item unmarshallItem(String text) {
        // split text into the array
        String[] tokens = text.split(DELIMITER);
        // get all info and convert original data type
        String id = tokens[0];
        String name = tokens[1];
        BigDecimal cost = new BigDecimal(tokens[2]);
        int count = Integer.parseInt(tokens[3]);
        // return as item object
        return new Item(id, name, cost, count);
    }

    // read file
    private void loadInventory() throws VMPersistenceException {
        Scanner scanner;

        // try and catch if the file is not found
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VMPersistenceException("-_- Could not load inventory data into memory.", e);
        }

        while (scanner.hasNextLine()) {
            // read line by line
            String line = scanner.nextLine();
            // convert string to object
            Item item = unmarshallItem(line);
            // keep objects in Map called inventory
            inventory.put(item.getId(), item);
        }
        scanner.close();
    }
}
