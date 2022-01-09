package car;

import java.util.Scanner;

public interface Interactive {

    default float promptFloat(final String prompt) throws NumberFormatException {
        return Float.parseFloat(this.promptString(prompt));
    }

    default boolean promptBoolean(final String prompt) {
        return this.promptString(prompt + " (Y for yes)").toUpperCase().contains("Y");
    }

    default String promptString(final String prompt) {
        final String response;
        final Scanner scanner = new Scanner(System.in);

        System.out.print(prompt + " ");
        response = scanner.nextLine();
        return response;
    }

}
