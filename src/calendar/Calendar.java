package calendar;

import java.util.Scanner;

/*
Author: Ronald Bauer
Project: Calendar
*/
public class Calendar {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) { // Begin continuous loop
            System.out.print("Enter year (1980 onwords or -1 to exit): ");
            int year = scan.nextInt();
            if (year == -1) {
                System.out.println("Exiting the program.");
                break; 
            }

            System.out.print("Enter month (1-12): ");
            int month = scan.nextInt();

            if (month < 1 || month > 12 || year < 1980) {
                System.out.println("Invalid month or year. Please try again.");
            } else {
                month(year, month);
            }

            System.out.println("Press any key to continue or type 'exit' to quit:");
            scan.nextLine(); // Consume the newline
            String userInput = scan.nextLine();
            if ("exit".equalsIgnoreCase(userInput)) {
                break; 
            }
        }

        scan.close();
        System.exit(0);
    } //End of main method

    static void month(int year, int month) {
        output(year, month);
        outprint(year, month);
    }

    static void output(int year, int month) {
        System.out.println("         " + nMonth(month) + " " + year);
        System.out.println("--=========================--");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat ");
    }

    static String nMonth(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June",
                               "July", "August", "September", "October", "November", "December"};
        if (month >= 1 && month <= 12) {
            return monthNames[month - 1];
        }
        return "";
    }

    static void outprint(int year, int month) {
        int genesis = startDate(year, month);
        int numberOfDaysInMonth = daysInMonth(year, month);
        
        for (int i = 0; i < genesis; i++)
            System.out.print("    ");
        
        for (int i = 1; i <= numberOfDaysInMonth; i++) {
            System.out.printf("%4d", i);
            if ((i + genesis) % 7 == 0)
                System.out.println();
        }
        System.out.println();
    }

    static int startDate(int year, int month) {
        int genesis1800 = 3;
        int totalNumberOfDays = totalDayNumbers(year, month);

        return (totalNumberOfDays + genesis1800) % 7;
    }

    static int totalDayNumbers(int year, int month) {
        int total = 0;
        for (int i = 1980; i < year; i++) {
            if (isLeapYear(i)) {
                total += 366;
            } else {
                total += 365;
            }
        }
        for (int i = 1; i < month; i++) {
            total += daysInMonth(year, i);
        }

        return total;
    }

    static int daysInMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 ||
            month == 8 || month == 10 || month == 12)
            return 31;
        if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;
        if (month == 2)
            return isLeapYear(year) ? 29 : 28;

        return 0;
    }

    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
