package cinema;

import java.util.Scanner;

public class Cinema {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rowsNumber = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsNumber = scanner.nextInt();
        String[][] room = new String[rowsNumber + 1][seatsNumber + 1];
        initRoomArray(room);
        printMenu(room);
    }

    public static void printMenu(String[][] array) {
        boolean menu = true;
        while (menu){
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    printRoom(array);
                    break;
                case 2:
                    buyTicket(array);
                    break;
                case 3:
                    printStat(array);
                    break;
                case 0:
                    menu = false;
                    break;
                default:
                    break;
            }
        }
    }

    public static void printStat(String[][] array) {
        int numBoughtTickets = 0;
        int inc = 0;
        int sum = 0;
        int price;
        for (int i = 1; i <= array.length - 1; i++) {
            for (int j = 1; j <= array[i].length - 1; j++) {
                price = getTicketPrice(array.length - 1, array[i].length - 1, i);
                inc += price;
                if (array[i][j].equals("B")) {
                    numBoughtTickets += 1;
                    sum += price;
                }
            }
        }

        float percent = (float) numBoughtTickets * 100 / ((float)(array.length - 1) * ((float)array[0].length - 1));
        System.out.printf("Number of purchased tickets: %d", numBoughtTickets);
        System.out.println("");
        System.out.printf("Percentage: %.2f", percent);
        System.out.println("%");
        System.out.printf("Current income: $%d", sum);
        System.out.println("");
        System.out.printf("Total income: $%d", inc);
        System.out.println("");
    }

    public static void initRoomArray(String[][] array) {
        array[0][0] = " ";
        for (int i = 1; i <= array.length - 1; i++) {
            array[i][0] = String.valueOf(i);
        }
        for (int j = 1; j <= array[0].length - 1; j++) {
            array[0][j] = String.valueOf(j);
        }
        for (int i = 1; i <= array.length - 1; i++) {
            for (int j = 1; j <= array[i].length - 1; j++) {
                array[i][j] = "S";
            }
        }
    }

    public static void printRoom(String[][] array) {
        System.out.println("Cinema:");
        for (int i = 0; i <= array.length - 1; i++) {
            for (int j = 0; j <= array[i].length - 1; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyTicket(String[][] array) {
        boolean soldTicket = true;
        while (soldTicket) {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();
            if (row >= array.length || row < 0 || seat >= array[0].length || seat < 0) {
                System.out.println("Wrong input!");
            } else {
                if (array[row][seat].equals("B")) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    array[row][seat] = "B";
                    int price = getTicketPrice(array.length - 1, array[0].length - 1, row);
                    System.out.println("Ticket price: $" + price);
                    System.out.println();
                    soldTicket = false;
                }
            }
        }
    }

    public static int getTicketPrice(int rowNum, int seatNum, int currentRow) {
        if ((rowNum) * (seatNum) <= 60) {
            return 10;
        } else {
            if (currentRow <= (rowNum - 1) / 2) {
                return 10;
            } else {
                return 8;
            }
        }
    }
}