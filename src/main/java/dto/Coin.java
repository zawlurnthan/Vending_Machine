package dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Coin {
    private Note name;
    private BigDecimal value;
    private int count;

    public Coin() {
    }

    public Coin(Note name, String value) {
        this.name = name;
        this.value = new BigDecimal(value);
    }

    public Note getBill() {
        return name;
    }

    public void setBill(Note name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

        // this method will return exact coins of change
    public static List<Coin> getCoins(BigDecimal change) {
        // create a container
        List<Coin> list = new ArrayList<>();

        // create count and a hundred dollar bill
        int count = 0;
        Coin hundred = new Coin(Note.HUNDRED, "100");
        // loop if change is greater than a hundred
        while (change.compareTo(hundred.getValue()) >= 0) {
            // record iterating how many times it loops
            count++;
            // decrease a hundred each time loop
            change = change.subtract(hundred.getValue());
        }
        hundred.setCount(count);        // set count
        list.add(hundred);         // add note to the list
        // *****************************************************

        count = 0;          // reset count to zero
        // create fifty dollar bill
        Coin fifty = new Coin(Note.FIFTY, "50");

        while (change.compareTo(fifty.getValue()) >= 0) {
            count++;
            change = change.subtract(fifty.getValue());
        }
        fifty.setCount(count);
        list.add(fifty);
        // ******************************************************

        // reset count to zero and create twenty dollar bill
        count = 0;
        Coin twenty = new Coin(Note.TWENTY, "20");

        while (change.compareTo(twenty.getValue()) >= 0) {
            count++;
            change = change.subtract(twenty.getValue());
        }
        twenty.setCount(count);
        list.add(twenty);
        // ********************************************************

        // reset count to zero
        count = 0;
        // create ten dollar bill
        Coin ten = new Coin(Note.TEN, "10");
        // loop if change is grater than ten dollar bill
        while (change.compareTo(ten.getValue()) >= 0) {
            // record the time of iterating
            count++;
            change = change.subtract(ten.getValue());
        }
        ten.setCount(count);
        list.add(ten);
        // ****************************************************

        // reset count to zero and create five dollar bill
        count = 0;
        Coin five = new Coin(Note.FIVE, "5");
        while (change.compareTo(five.getValue()) >= 0) {
            count++;
            change = change.subtract(five.getValue());
        }
        five.setCount(count);
        list.add(five);
        // ******************************************************

        // reset count to zero and create two dollar bill
        count = 0;
        Coin two = new Coin(Note.TWO, "2");

        while (change.compareTo(two.getValue()) >= 0) {
            count++;
            change = change.subtract(two.getValue());
        }
        two.setCount(count);
        list.add(two);
        //******************************************************

        // reset count to zero and create a dollar bill
        count = 0;
        Coin one = new Coin(Note.ONE, "1");

        while (change.compareTo(one.getValue()) >= 0) {
            count++;
            change = change.subtract(one.getValue());
        }
        one.setCount(count);
        list.add(one);
        // ******************************************************

        // reset count to zero and create fifty cent coin
        count = 0;
        Coin half = new Coin(Note.HALF, "0.50");

        while (change.compareTo(half.getValue()) >= 0) {
            count++;
            change = change.subtract(half.getValue());
        }
        half.setCount(count);
        list.add(half);
        // ****************************************************

        // reset count to zero and create quarter cent coin
        count = 0;
        Coin quarter = new Coin(Note.QUARTER, "0.25");

        while (change.compareTo(quarter.getValue()) >= 0) {
            count++;
            change = change.subtract(quarter.getValue());
        }
        quarter.setCount(count);
        list.add(quarter);
        // *****************************************************

        // reset count to zero and create ten cent coin
        count = 0;
        Coin dime = new Coin(Note.DIME, "0.10");

        while (change.compareTo(dime.getValue()) >= 0) {
            count++;
            change = change.subtract(dime.getValue());
        }
        dime.setCount(count);
        list.add(dime);
        // *****************************************************

        // reset count to zero and create five cent coin
        count = 0;
        Coin nickel = new Coin(Note.NICKEL, "0.05");

        while (change.compareTo(nickel.getValue()) >= 0) {
            count++;
            change = change.subtract(nickel.getValue());
        }
        nickel.setCount(count);
        list.add(nickel);
        // *****************************************************

        // reset count to zero
        count = 0;
        // create one cent coin
        Coin penny = new Coin(Note.PENNY, "0.01");

        // loop if change is grater than one cent
        while (change.compareTo(penny.getValue()) >= 0) {
            // record the time of iterating
            count++;
            // decrease penny each time iterate
            change = change.subtract(penny.getValue());
        }
        penny.setCount(count);      // set count
        list.add(penny);            // add penny note to the note list

        // return the list of notes
        return list;
    }
}
