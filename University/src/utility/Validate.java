package utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Lim Jia Qing
 */
public class Validate {

    //String input and validation function, digit input is not allowed 
    public static String stringInput(String promptMsg, String errorMsg) {
        String input;
        boolean invalidInput;
        Scanner sc = new Scanner(System.in);

        do {
            invalidInput = false;
            System.out.print(promptMsg);
            input = sc.nextLine();

            if (input.length() == 0) {
                System.out.println(errorMsg);
                invalidInput = true;
                continue;
            }

            for (int i = 0; i < input.length(); i++) {
                if (!Character.isLetter(input.charAt(i)) && input.charAt(i) != ' ') {
                    System.out.println(errorMsg);
                    invalidInput = true;
                    break;
                }
            }
        } while (invalidInput);

        return input;
    }

    //String input and validation function, validate for null input
    public static String stringNullCheckingInput(String promptMsg, String errorMsg) {
        String input;
        boolean invalidInput;
        Scanner sc = new Scanner(System.in);

        do {
            invalidInput = false;
            System.out.print(promptMsg);
            input = sc.nextLine();

            if (input.length() == 0) {
                invalidInput = true;
            }
        } while (invalidInput);

        return input;
    }

    //Integer input and validation function
    public static int intInput(String promptMsg, String errorMsg) {
        int input = 0;
        boolean invalidInput;
        Scanner sc = new Scanner(System.in);

        do {
            invalidInput = false;
            System.out.print(promptMsg);

            try {
                input = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine(); // Get rid of the newline if a string is inputted.
                System.out.println(errorMsg);
                invalidInput = true;
            }
        } while (invalidInput);

        return input;
    }

    //Double input and validation function
    public static double doubleInput(String promptMsg, String errorMsg) {
        double input = 0.00;
        boolean invalidInput;
        Scanner sc = new Scanner(System.in);

        do {
            invalidInput = false;
            System.out.print(promptMsg);

            try {
                input = sc.nextDouble();
            } catch (Exception e) {
                sc.nextLine();      // Get rid of the newline if a string is inputted.
                System.out.println(errorMsg);
                invalidInput = true;
            }

        } while (invalidInput);

        return input;
    }

    //Character input and validation function
    public static char charInput(String promptMsg, String errorMsg) {
        String input;
        boolean invalidInput;
        Scanner sc = new Scanner(System.in);

        do {
            invalidInput = false;
            System.out.print(promptMsg);
            input = sc.next();

            if (!(Character.isLetter(input.charAt(0)) || input.length() == 1)) {
                System.out.println(errorMsg);
                invalidInput = true;
            }
        } while (invalidInput);

        return input.charAt(0);
    }

    //Yes or No input and validation function, character input 'Y' or 'N' is accepted
    public static char yesNoInput(String promptMsg, String errorMsg) {
        char input;
        boolean invalidInput;

        do {
            input = Character.toUpperCase(charInput(promptMsg, errorMsg));
            invalidInput = false;
            if (input != 'Y' && input != 'N') {
                System.out.println("  Invalid input, please enter Y or N.");
                invalidInput = true;
            }
        } while (invalidInput);

        return input;
    }

    //Phone number input and validation function
    public static String phoneInput(String promptMsg) {
        Scanner sc = new Scanner(System.in);
        String phoneNum;

        do {
            System.out.print(promptMsg);
            phoneNum = sc.next();
        } while (!phoneNumValidation(phoneNum));

        return phoneNum;
    }

    //Phone number format validation function
    public static boolean phoneNumValidation(String phoneNum) {
        if (phoneNum.length() > 11 || phoneNum.length() < 10) {
            System.out.println("  Invalid length of phone number");
            return false;
        } else {
            for (int i = 0; i < phoneNum.length(); i++) {
                if (Character.isDigit(phoneNum.charAt(i)) == false) {
                    System.out.println("  Invalid phone number format");
                    return false;
                }
            }
        }

        return true;
    }

    //IC input and validation function
    public static String icInput(String promptMsg) {
        Scanner sc = new Scanner(System.in);
        String ic;

        do {
            System.out.print(promptMsg);
            ic = sc.next();
        } while (!icValidation(ic));

        return ic;
    }

    //IC format validation function
    public static boolean icValidation(String ic) {
        if (ic.length() != 12) {
            System.out.println("  Invalid length of ic");
            return false;
        }

        int birthYearPrefix = Integer.parseInt(ic.substring(0, 2));
        String birthDateStr = ic.substring(4, 6) + "/" + ic.substring(2, 4);

        if (birthYearPrefix <= 20) {
            birthDateStr += "/20" + birthYearPrefix;
        } else {
            birthDateStr += "/19" + birthYearPrefix;
        }

        if (!dateChecking(birthDateStr)) {
            System.out.println("  Invalid ic birthdate format");
            return false;
        }

        for (int i = 0; i < ic.length(); i++) {
            if (!Character.isDigit(ic.charAt(i))) {
                System.out.println("  Invalid ic number format");
                return false;
            }
        }

        return true;
    }

/*
    public static boolean icValidation(String ic) {
        if (ic.length() != 12) {
            System.out.println("  Invalid length of ic");
            return false;
        } else {
            if (Integer.parseInt(ic.substring(0, 2)) <= 20) {
                if (dateChecking(ic.substring(4, 6) + "/" + ic.substring(2, 4) + "/20" + ic.substring(0, 2)) == false) {
                    System.out.println("  Invalid ic birthdate format");
                    return false;
                }
            } else {
                if (dateChecking(ic.substring(4, 6) + "/" + ic.substring(2, 4) + "/19" + ic.substring(0, 2)) == false) {
                    System.out.println("  Invalid ic birthdate format");
                    return false;
                }
            }

            for (int i = 0; i < ic.length(); i++) {
                if (Character.isDigit(ic.charAt(i)) == false) {
                    System.out.println("  Invalid ic number format");
                    return false;
                }
            }
        }

        return true;
    }
*/
    
    //Date input and validation function
    public static String dateInput(String promptMsg, String errorMsg) {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean invalidInput;

        do {
            invalidInput = false;
            System.out.print(promptMsg);
            input = sc.next();

            if (!dateChecking(input)) {
                System.out.println(errorMsg);
                invalidInput = true;
            }

        } while (invalidInput);

        return input;
    }

    //Birthdate input and validation function, birthdate must same as stated in the ic
    public static String birthDateInput(String promptMsg, String errorMsg, String ic) {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean invalidInput;

        do {
            invalidInput = false;
            input = dateInput(promptMsg, errorMsg);

            if (ic.charAt(0) != input.charAt(8) || ic.charAt(1) != input.charAt(9)
                    || ic.charAt(2) != input.charAt(3) || ic.charAt(3) != input.charAt(4)
                    || ic.charAt(4) != input.charAt(0) || ic.charAt(5) != input.charAt(1)) {
                System.out.println("  Birthdate didn't match with IC");
                invalidInput = true;
            }
        } while (invalidInput);

        return input;
    }

    //Date format validation function
    public static boolean dateChecking(String dateStr) {
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        date.setLenient(false);
        try {
            date.parse(dateStr);
        } catch (ParseException e) {
            return false;

        }

        return true;
    }

    //Obtain current date time function 
    public static String getCurrentDateTime(String mode) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        switch (mode) {
            case "dateTime":
                return dtf.format(now);
            case "time":
                return dtf.format(now).substring(11);
            case "date":
                return dtf.format(now).substring(0, 10);
            case "yy":
                return dtf.format(now).substring(8, 10);
            case "yyyy":
                return dtf.format(now).substring(6, 10);
            case "mm":
                return dtf.format(now).substring(3, 5);
            case "dd":
                return dtf.format(now).substring(0, 2);
            case "yymm":
                return dtf.format(now).substring(8, 10) + dtf.format(now).substring(3, 5);
            case "yymmdd":
                return dtf.format(now).substring(8, 10) + dtf.format(now).substring(3, 5) + dtf.format(now).substring(0, 2);
            default:
                System.out.println("  Invalid mode selection");
                break;
        }

        return "Invalid mode selection";
    }

    //Age calculation function
    public static int ageCalc(String birthDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        long difference_In_Years = 0;
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(birthDate);
            Date d2 = sdf.parse(getCurrentDateTime("date"));

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            // Calucalte time difference in seconds,
            // minutes, hours, years, and days
            difference_In_Years = TimeUnit.MILLISECONDS
                    .toDays(difference_In_Time)
                    / 365l;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) difference_In_Years;
    }
}
