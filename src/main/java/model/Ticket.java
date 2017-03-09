package model;

import controller.DatabaseConnector;

import java.util.ArrayList;

/**
 * Created by NortonWEI on 3/3/2017.
 */
public class Ticket {
    private int ticketId = 0;
    private int userId = 0;
    private int scheduleId = 0;
    private int category = 0;
    private int seatId = 0;

    public Ticket(String username, String movieName, boolean if3D, String startTime, int seatRowNo, int seatColumnNo, int category) {
        setId();
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.userId = databaseConnector.findUser(username).getUserId();
        Schedule schedule = databaseConnector.findSchedule(movieName, if3D, startTime);
        this.scheduleId = schedule.getScheduleId();
        this.category = category;
        this.seatId = databaseConnector.findSeat(seatRowNo, seatColumnNo, schedule.getHouseId()).getScheduleId();
    }

    public Ticket(int ticketId, int userId, int scheduleId, int category, int seatId) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.category = category;
        this.seatId = seatId;
    }

    private void setId() {
        boolean ifBreak = false;
        DatabaseConnector databaseConnector = new DatabaseConnector();
        ArrayList<Integer> ticketIdList = databaseConnector.listId("TICKET");
        for (int i=0; i<ticketIdList.size(); i++) {
            if (ticketIdList.get(i) != i) {
                ticketId = i;
                ifBreak = true;
                break;
            }
        }
        if (!ifBreak) {
            if (!ticketIdList.isEmpty()) {
                ticketId = ticketIdList.get(ticketIdList.size()-1) + 1;
            }
        }
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getCategory() {
        return category;
    }

    public int getSeatId() {
        return seatId;
    }
}
