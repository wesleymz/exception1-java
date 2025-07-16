package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date chekIn;
    private Date chekOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation() {
    }

    public Reservation(Integer roomNumber, Date chekIn, Date chekOut) {

        if (!chekOut.after(chekIn)) {
            throw new DomainException("Check-out date must be before check-in date");
        }

        this.roomNumber = roomNumber;
        this.chekIn = chekIn;
        this.chekOut = chekOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getChekIn() {
        return chekIn;
    }

    public Date getChekOut() {
        return chekOut;
    }

    public long duration() {
        long diff = chekOut.getTime() - chekIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date chekIn, Date chekOut) {

        Date now = new Date();
        if (chekIn.before(now) || chekOut.before(now)) {
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if (!chekOut.after(chekIn)) {
            throw new DomainException("Check-out date must be before check-in date");
        }

        this.chekIn = chekIn;
        this.chekOut = chekOut;

    }

    @Override
    public String toString() {
        return "Room " + roomNumber
                + ", chekIn: "
                + sdf.format(chekIn)
                + ", chekOut: "
                + sdf.format(chekOut) +
                ", "
                + this.duration()
                + " nights";
    }
}
