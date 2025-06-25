package com.railway.utilities;

import java.util.Date;

public class Ticket {
    private String Date;
    private String DepartStation;
    private String ArriveStation;
    private String SeatType;
    private String TicketAmount;

    public Ticket(String date, String departStation, String arriveStation, String seatType, String ticketAmount) {
        this.Date = date;
        this.DepartStation = departStation;
        this.ArriveStation = arriveStation;
        this.SeatType = seatType;
        this.TicketAmount = ticketAmount;
    }

    public String getDate() {
        return Date;
    }

    public String getDepartStation() {
        return DepartStation;
    }

    public String getArriveStation() {
        return ArriveStation;
    }

    public String getSeatType() {
        return SeatType;
    }

    public String getTicketAmount() {
        return TicketAmount;
    }
}
