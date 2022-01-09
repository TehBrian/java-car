package car;

import java.util.Scanner;

public interface Interactive {
    public default float getFloat(final String prompt) {
        return Float.parseFloat(this.getString(prompt));
    }

    public default boolean getBoolean(final String prompt) {
        return (getString(prompt + " (Y is yes)").toUpperCase().contains("Y"));
    }

    public default String getString(final String prompt) {
        String response = "";
        final Scanner reader = new Scanner(System.in);
        System.out.print(prompt + " Press enter when ready. ");
        response = reader.nextLine();
        //reader.close();
        return response;
    }

}
