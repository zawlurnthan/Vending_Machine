package ui;

import dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VMView {
    UserIO io;

    public VMView(UserIO io) {
        this.io = io;
    }

    public boolean continueOrExit() {
        String input = io.readString("\nEnter 'y' for Continue, any key to Exit.");
        return input.equals("y");
    }

    public BigDecimal prompt() {
        double input = io.readDouble("Enter bills or coins " +
                "to buy items");
        return BigDecimal.valueOf(input);
    }

    public void displayAllItems(List<Item> itemList) {
        io.print("-----------------------------------------------------------------");
        io.print("+++++++++++++++++++++ Today Available Drinks ++++++++++++++++++++");
        io.print("-----------------------------------------------------------------");
        if (!itemList.isEmpty()){
            itemList.forEach(x ->
                    System.out.printf("(%s) %s $%s%n",
                            x.getId(), x.getName(), x.getCost()));
        } else {
            io.print("List is empty!");
        }
        io.print("-----------------------------------------------------------------");
    }

    public String getItemId() {
        int min = 1, max = 10, num = 0;
        String input = "";

        // repeat input if less or greater than list number
        // try and catch if input is not a number
            do {
                try {
                    input = io.readString("Select a ID number");
                    num = Integer.parseInt(input);
                }
                catch (NumberFormatException e) {
                    io.print("Invalid input!");
                }
            } while (num < min || num > max);
        return input;
    }

    public void displayItem(Item item) {
        System.out.printf("%s. %s $%s%n",
                item.getId(), item.getName(), item.getCost());
    }

    public Item getNewItemInfo() {
        String id = io.readString("Enter Item Id");
        String name = io.readString("Enter Item Name");
        BigDecimal cost = BigDecimal.valueOf(io.readDouble("Enter Item Cost"));
        int count = io.readInt("Enter Item Count");
        return new Item(id, name, cost, count);
    }
}
