package application;

import entities.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Room number: ");
        int roomNumber = sc.nextInt();
        System.out.print("Chek-in date (dd/MM/yyyy): ");
        Date checkInDate = sdf.parse(sc.next());
        System.out.print("Chek-out date (dd/MM/yyyy): ");
        Date checkOutDate = sdf.parse(sc.next());

        if (!checkOutDate.after(checkInDate) ) {
            System.out.println("Error in reservation: Check-ou date must be after check-in date");
        } else {
            Reservation reservation = new Reservation(roomNumber, checkInDate, checkOutDate);
            System.out.println("Reservation: " + reservation);

            System.out.println();
            System.out.println("Enter data to update the reservation:");
            System.out.print("Chek-in date (dd/MM/yyyy): ");
            checkInDate = sdf.parse(sc.next());
            System.out.print("Chek-out date (dd/MM/yyyy): ");
            checkOutDate = sdf.parse(sc.next());

            Date now =  new Date();
            if (checkInDate.before(now) || checkOutDate.before(now)) {
                System.out.println("Error in reservation: Reservation dates for update must be future dates");
            } else if (!checkOutDate.after(checkInDate)) {
                System.out.println("Error in reservation: Check-out date must be before check-in date");
            } else {
                reservation.updateDates(checkInDate, checkOutDate);
                System.out.println("Reservation: " + reservation);
            }
        }

        sc.close();
    }
}
