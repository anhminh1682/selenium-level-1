package com.railway.utilities;

import com.railway.constant.Constants;

import java.time.LocalDateTime;

public class Ticket {
    private String departDate;
    private String departStation;
    private String arriveStation;
    private String seatType;
    private String ticketAmount;

    public Ticket() {
    }

    public Ticket(String date, String departStation, String arriveStation, String seatType, String ticketAmount) {
        this.departDate = date;
        this.departStation = departStation;
        this.arriveStation = arriveStation;
        this.seatType = seatType;
        this.ticketAmount = ticketAmount;
    }

    public Ticket(int days, String departStation, String arriveStation, String seatType, String ticketAmount) {
        this.departDate = LocalDateTime.now().plusDays(days + 3).format(Constants.MyDateTimeFormat.M_d_yyyy);
        this.departStation = departStation;
        this.arriveStation = arriveStation;
        this.seatType = seatType;
        this.ticketAmount = ticketAmount;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public void setDepartStation(String departStation) {
        this.departStation = departStation;
    }

    public void setArriveStation(String arriveStation) {
        this.arriveStation = arriveStation;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public void setTicketAmount(String ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public String getDepartDate() {
        return departDate;
    }

    public String getDepartStation() {
        return departStation;
    }

    public String getArriveStation() {
        return arriveStation;
    }

    public String getSeatType() {
        return seatType;
    }

    public String getTicketAmount() {
        return ticketAmount;
    }
}
