package cinema;
import java.util.Scanner;

public class Cinema {
    private static int currentIncome;
    private static int ticket;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        String[][] tab = createAnArray(rows+1, seats+1);

        chooseOption(rows,seats, tab);
    }

    public static void chooseOption(int rows, int seats, String[][] tab) {
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                showTheSeats(tab);
                chooseOption(rows,seats, tab);
                break;
            case 2:
                buyATicket(rows, seats, tab);
                chooseOption(rows,seats, tab);
                break;
            case 3:
                statistica(rows,seats, tab);
                chooseOption(rows,seats, tab);
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println("Wrong option! Please try again.");
                chooseOption(rows,seats, tab);
                break;

        }

    }

    public static void showTheSeats(String[][] tab) {
        System.out.println("Cinema: ");
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void buyATicket(int rows, int seats, String[][] tab) {
        int rowNumber;
        int seatNumber;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (rowNumber > rows || seatNumber > seats) {
                System.out.println("Wrong input");
                return;
            } else {
                if (tab[rowNumber][seatNumber] == "B") {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    break;
                }
            }
        }
        int ticketPrice = 10;
        if (((tab.length-1) * (tab[0].length-1) > 60) && (rowNumber > (tab.length-1)/2)) {
            ticketPrice = 8;
        }
        currentIncome += ticketPrice;
        ticket++;
        System.out.println("Ticket price: $" + ticketPrice);
        tab[rowNumber][seatNumber] = "B";

    }

    public static void exit() {
        return;
    }

    public static String[][] createAnArray(int a, int b) {
        String[][] tab = new String[a][b];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (i == 0 && j == 0) {
                    tab[i][j] = " ";
                } else if (i == 0){
                    tab[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    tab[i][j] = Integer.toString(i);
                } else {
                    tab[i][j] = "S";
                }
            }
        }
        return tab;
    }


    public static void statistica(int rows, int seats, String[][] tab) {
        float total = rows * seats;

        int frontrows = rows / 2;
        int frontRowsIncome = frontrows * seats * 10;
        int backRows = (rows - frontrows) * seats * 8;
        int doxod = frontRowsIncome + backRows;

        float procent = 100 * ticket / total ;
        String result = String.format("%.2f",procent);
        System.out.println("Number of purchased tickets: " + ticket);
        System.out.println("Percentage: " + result + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + doxod);

    }







}