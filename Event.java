import java.util.*;

// Abstract class representing the Event Management System
abstract class EventMangementSystem 
{
    // Instance variables
    String place; // Stadium
    float price; // User's selected price
    String genre; // Type of event (singers, dancers, comedians)
    String date;
    String ticket;
    String artist;
    
    // Constants
    final static String[] ticketType = { "Fan Pit", "Vip", "General Area" };
    final static float[] Discount = { 10f, 5f, 2f };
    final static int capacity = 7500;

    static int count = 0; // Total events booked by the user

    // Default constructor
    EventMangementSystem() { 
        place = "Not mentioned";
        price = 1200f;
    }

    // Parameterized constructor
    EventMangementSystem(String artist, String place, float price, String genre, String date, String ticket)
 {
        this.artist = artist;
        this.place = place; // Stadium
        this.price = price;
        this.genre = genre;
        this.date = date;
        this.ticket = ticket;
    }

    static void incrementCount() {
        count++;
    }

    // Abstract method to display event details
    abstract void display();
}

// Subclass for Singer's event
class JLN extends EventMangementSystem
 {
    String singer;
    final static String[] total1 = { "Post Malone", "Arijit Singh", "Dimitri Vegas And Like Mike" };
    final static String[] date1 = { "4 Nov", "5 Nov", "6 Nov" };
    final static float[] price1 = { 3000f, 2300f, 4000f };

    JLN(String artist, String place, float price, String genre, String date, String ticket, String singer) {
        super(artist, place, price, genre, date, ticket);
        this.singer = singer;
    }

    // Override display method to show Singer's event details
    void display() {
        System.out.printf("Artist: %s\nPrice: %f  Place: %s\nSinger: %s\nGenre: %s\n  Date: %s", artist, price, place, singer, genre, date);
    }
}

// Subclass for Dancer's event
class IG extends EventMangementSystem
 {
    String dancers;
    final static String[] total2 = { "Prabhu Deva", "Remo D'Souza", "Raghav Juyal" };
    final static String[] date2 = { "7 Nov", "8 Nov", "9 Nov" };
    final static float[] price2 = { 3000f, 2300f, 4000f };

    IG(String artist, String place, float price, String genre, String date, String ticket, String dancers) {
        super(artist, place, price, genre, date, ticket);
        this.dancers = dancers;
    }

    // Override display method to show Dancer's event details
    void display() {
        System.out.printf("Artist: %s\nPrice: %f  Place: %s\nDancer: %s\nGenre: %s\n  Date: %s", artist, price, place, dancers, genre, date);
    }
}

// Subclass for Comedian's event
class SIRIFORT extends EventMangementSystem 
{
    String comedians;
    final static String[] total3 = { "Samay Raina", "Anubhav Singh Bassi", "Vir Das" };
    final static String[] date3 = { "10 Nov", "11 Nov", "12 Nov" };
    final static float[] price3 = { 3000f, 2300f, 4000f };

    SIRIFORT(String artist, String place, float price, String genre, String date, String ticket, String comedians) {
        super(artist, place, price, genre, date, ticket);
        this.comedians = comedians;
    }

    // Override display method to show Comedian's event details
    void display() {
        System.out.printf("Artist: %s\nPrice: %f\nPlace: %s\nDate: %s\nComedians: %s", artist, price, place, date, comedians);
    }
}

// Interface for User
interface User {
    void display();
}

// Class representing User Details
class UserDetails implements User 
{
    private String name;
    private int age;
    private String mailID;

    UserDetails() {
        name = "";
        age = 18;
        mailID = "";
    }

    UserDetails(String name, int age, String mailID) {
        this.name = name;
        this.age = age;
        this.mailID = mailID;
    }

    // Implement the display method from the User interface to show user details
    public void display() {
        System.out.printf("Name: %s\nAge: %d\nMail id: %s\n", name, age, mailID);
    }
}

class Event {
    public static String selectTicket() {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your preferred ticket: ");
        for (int i = 0; i < EventMangementSystem.ticketType.length; i++) {
            System.out.printf("%d. %s\t", i + 1, EventMangementSystem.ticketType[i]);
        }
        try {
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            // Handle the exception or prompt the user to input again
            // For example, sc.nextLine(); // Consume the invalid input
            choice = 1; // Assume the default choice
            sc.nextLine(); // Consume the invalid input
        }


        if (choice >= 1 && choice <= 3) {
            return EventMangementSystem.ticketType[choice - 1];
        } else {
            return EventMangementSystem.ticketType[0];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Chetanya's Event Management System.");
        System.out.println("We have events starting from 4 Nov to 12 Nov.");
        System.out.println("These events will take place in 3 stadiums.");
        System.out.println("You are allowed to buy only one ticket per stadium to avoid repetitive fans.");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your age: ");
        int age = 18; // Default age
        try {
            age = sc.nextInt();
            sc.nextLine(); // Consume the newline character
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for age. Using default age (18).");
            sc.nextLine(); // Consume the invalid input
        }


        System.out.print("Enter your Mail ID: ");
        String mailID = sc.nextLine();

        UserDetails userDetails = new UserDetails(name, age, mailID);
        System.out.println("\nUser Details:");
        userDetails.display();

        int choice;

        EventMangementSystem[] events;
        events = new EventMangementSystem[3];

        System.out.print("\nDo you wish to attend a Singer's Concert at JLN (1: Yes, 0: No): ");
        choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        if (choice == 1) {
            String place = "JLN";
            String genre = "Singing";
            String artist = "Singer";
            System.out.println("Enter the singer's event you want to attend:");
            for (int i = 0; i < JLN.total1.length; i++) {
                System.out.printf("%d. Singer: %s\tDate of Event: %s\tPrice: %f\n", i + 1, JLN.total1[i], JLN.date1[i], JLN.price1[i]);
            }
            System.out.print("Enter your choice here: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (choice >= 1 && choice <= JLN.total1.length) {
                float price = JLN.price1[choice - 1];
                String date = JLN.date1[choice - 1];
                String singer = JLN.total1[choice - 1];

                System.out.println("Price: " + price);
                System.out.println("Date: " + date);

                String ticket = selectTicket();

                if (ticket.equals(EventMangementSystem.ticketType[0])) {
                    System.out.println("Congratulations! You are given a discount of 10%");
                    price = price - (EventMangementSystem.Discount[0] / 100) * price;
                } else if (ticket.equals(EventMangementSystem.ticketType[1])) {
                    price = price - (EventMangementSystem.Discount[1] / 100) * price;
                } else if (ticket.equals(EventMangementSystem.ticketType[2])) {
                    price = price - (EventMangementSystem.Discount[2] / 100) * price;
                }

                JLN jlnEvent = new JLN(artist, place, price, genre, date, ticket, singer);
                jlnEvent.display();
                events[EventMangementSystem.count] = jlnEvent;
                EventMangementSystem.incrementCount();
            }
        } else {
            System.out.println("\nMaybe next time you might want to attend.");
        }

        System.out.print("\nDo you wish to attend Dancer's Concert at IG (1: Yes, 0: No): ");
        choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        if (choice == 1) {
            String place = "IG";
            String genre = "Dancing";
            String artist = "Dancer";
            System.out.println("Enter the Dancer's event you want to attend:");
            for (int i = 0; i < IG.total2.length; i++) {
                System.out.printf("%d. Dancer: %s\tDate of Event: %s\tPrice: %f\n", i + 1, IG.total2[i], IG.date2[i], IG.price2[i]);
            }
            System.out.print("Enter your choice here: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (choice >= 1 && choice <= IG.total2.length) {
                float price = IG.price2[choice - 1];
                String date = IG.date2[choice - 1];
                String dancers = IG.total2[choice - 1];

                System.out.println("Price: " + price);
                System.out.println("Date: " + date);

                String ticket = selectTicket();

                if (ticket.equals(EventMangementSystem.ticketType[0])) {
                    System.out.println("Congratulations! You are given a discount of 10%");
                    price = price - (EventMangementSystem.Discount[0] / 100) * price;
                } else if (ticket.equals(EventMangementSystem.ticketType[1])) {
                    price = price - (EventMangementSystem.Discount[1] / 100) * price;
                } else if (ticket.equals(EventMangementSystem.ticketType[2])) {
                    price = price - (EventMangementSystem.Discount[2] / 100) * price;
                }

                IG igEvent = new IG(artist, place, price, genre, date, ticket, dancers);
                igEvent.display();
                events[EventMangementSystem.count] = igEvent;
                EventMangementSystem.incrementCount();
            }
        } else {
            System.out.println("\nMaybe next time you might want to attend.");
        }

        System.out.print("\nDo you wish to attend Comedian's Event at SIRIFORT (1: Yes, 0: No): ");
        choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        if (choice == 1) {
            String place = "SIRIFORT";
            String genre = "Comedy";
            String artist = "Comedians";
            System.out.println("Enter the comedian's event you want to attend:");
            for (int i = 0; i < SIRIFORT.total3.length; i++) {
                System.out.printf("%d. Comedian: %s\nDate of Event: %s\nPrice: %f\n\n\n", i + 1, SIRIFORT.total3[i], SIRIFORT.date3[i], SIRIFORT.price3[i]);
            }
            System.out.print("Enter your choice here: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (choice >= 1 && choice <= SIRIFORT.total3.length) {
                float price = SIRIFORT.price3[choice - 1];
                String date = SIRIFORT.date3[choice - 1];
                String comedians = SIRIFORT.total3[choice - 1];

                System.out.println("Price: " + price);
                System.out.println("Date: " + date);

                String ticket = selectTicket();

                if (ticket.equals(EventMangementSystem.ticketType[0])) {
                    System.out.println("Congratulations! You are given a discount of 10%");
                    price = price - (EventMangementSystem.Discount[0] / 100) * price;
                } else if (ticket.equals(EventMangementSystem.ticketType[1])) {
                    price = price - (EventMangementSystem.Discount[1] / 100) * price;
                } else if (ticket.equals(EventMangementSystem.ticketType[2])) {
                    price = price - (EventMangementSystem.Discount[2] / 100) * price;
                }

                SIRIFORT sirifortEvent = new SIRIFORT(artist, place, price, genre, date, ticket, comedians);
                sirifortEvent.display();
                events[EventMangementSystem.count] = sirifortEvent;
                EventMangementSystem.incrementCount();
            }
        } else {
            System.out.println("\nMaybe next time you might want to attend.");
        }

        sc.close();
    }
}



