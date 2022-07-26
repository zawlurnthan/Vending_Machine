package ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
    final private Scanner console = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }


    @Override
    public String readString(String prompt) {
        boolean invalid = true;
        String input = "";

        // catch empty string input
        while (invalid) {
            System.out.println(prompt);
            input = console.nextLine();
            // stop iterating
            if (!input.isEmpty()) {
                invalid = false;
            }
        }
        return input;
    }


    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                // print message Prompt
                String stringValue = this.readString(prompt);
                // get input line, and parse
                num = Integer.parseInt(stringValue);
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("Input error, Please try again.");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int result;
        do {
            result = this.readInt(prompt);
        } while (result < min || result > max);

        return result;
    }


    @Override
    public float readFloat(String prompt) {
        boolean invalidInput = true;
        float num = 0;
        while (invalidInput) {
            try {
                // print message Prompt
                String stringValue = this.readString(prompt);
                // get input line, and parse
                num = Float.parseFloat(stringValue);
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("Input error, Please try again.");
            }
        }
        return num;
    }


    @Override
    public float readFloat(String prompt, float min, float max) {
        float result;
        do {
            result = this.readFloat(prompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    public double readDouble(String prompt) {
        boolean invalidInput = true;
        double num = 0;
        while (invalidInput) {
            try {
                // print message Prompt
                String stringValue = this.readString(prompt);
                // get input line, and parse
                num = Double.parseDouble(stringValue);
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("Input error, Please try again.");
            }
        }
        return num;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double result;
        do {
            result = this.readDouble(prompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    public long readLong(String prompt) {
        boolean invalidInput = true;
        long num = 0;
        while (invalidInput) {
            try {
                // print message Prompt
                String stringValue = this.readString(prompt);
                // get input line, and parse
                num = Long.parseLong(stringValue);
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("Input error, Please try again.");
            }
        }
        return num;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long result;
        do {
            result = this.readLong(prompt);
        } while (result < min || result > max);

        return result;
    }
}
