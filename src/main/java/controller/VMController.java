package controller;

import dao.VMPersistenceException;
import dto.Item;
import service.InsufficientFunException;
import service.OutOfOrderException;
import service.VMServiceLayer;
import ui.VMView;

import java.math.BigDecimal;
import java.util.List;
import java.util.UnknownFormatConversionException;

public class VMController {
    VMView view;
    VMServiceLayer service;

    public VMController(VMView view, VMServiceLayer service) {
        this.view = view;
        this.service = service;
    }


    public void run() throws VMPersistenceException {
        boolean keepGoing = true;
        viewAllItems();

        while (keepGoing) {
            try {
                BigDecimal amount = getMoney();
                selectItem(amount);
                keepGoing = exit();
            } catch (VMPersistenceException | UnknownFormatConversionException | OutOfOrderException | InsufficientFunException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nExiting the Program...");
    }

    private boolean exit() {
        return view.continueOrExit();
    }

    private BigDecimal getMoney() {
        return view.prompt();
    }

    private void viewAllItems() throws VMPersistenceException {
        List<Item> list = service.getAllItems();
        view.displayAllItems(list);
    }

    private void selectItem(BigDecimal amount) throws VMPersistenceException, OutOfOrderException, InsufficientFunException {
        String id = view.getItemId();
        // try and catch if item is null
        try {
            Item item = service.getItem(id);
            view.displayItem(item);
            service.showMessage(item, amount);
        } catch (NullPointerException e) {
            throw new OutOfOrderException("Out of order, No such item available!");
        }
    }

    private void addItem() throws VMPersistenceException {
        Item item = view.getNewItemInfo();
        service.createItem(item.getId(), item);
    }

    private void removeItem() throws VMPersistenceException {
        String id = view.getItemId();
        service.deleteItem(id);
    }
}







// 1. load display all items and prices when program start
// 2. Promp to input some amount of money to select items
// 3. vend only one item at a time
// 4. display if insufficient fund for selected item
// 5. Return the change if the fund is over than the cost (Must display each coins returned)
// 6. store items in a file, Read Inventory when program start, Item -> Name, Cost, Number of items
// 7. update inventory when vending