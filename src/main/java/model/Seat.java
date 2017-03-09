package model;

import controller.DatabaseConnector;

import java.util.ArrayList;

/**
 * Created by NortonWEI on 3/3/2017.
 */
public class Seat {
    private int seatId = 0;
    private int rowNo = -1;
    private int columnNo = -1;
    private int houseId = -1;
    private int scheduleId = -1;
    private boolean ifOccupied = false;

    public Seat(int rowNo, int columnNo, int houseId) {
        this.rowNo = rowNo;
        this.columnNo = columnNo;
        this.houseId = houseId;
    }

    public Seat(int seatId, int rowNo, int columnNo, int houseId) {
        this.seatId = seatId;
        this.rowNo = rowNo;
        this.columnNo = columnNo;
        this.houseId = houseId;
    }

    public static int getInitSeatIdInDb() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        ArrayList<Integer> seatIdList = databaseConnector.listId("SEAT");
        if (!seatIdList.isEmpty()) {
            return seatIdList.get(seatIdList.size()-1) + 1;
        } else {
            return 0;
        }
    }

    public int getSeatId() {
        return seatId;
    }

    public int getRowNo() {
        return rowNo;
    }

    public int getColumnNo() {
        return columnNo;
    }

    public int getHouseId() {
        return houseId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public boolean isIfOccupied() {
        return ifOccupied;
    }

    public void setIfOccupied(boolean ifOccupied) {
        this.ifOccupied = ifOccupied;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
}
