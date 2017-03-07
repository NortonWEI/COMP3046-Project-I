import controller.DatabaseConnector;
import model.Seat;

/**
 * Created by NortonWEI on 27/2/2017.
 */
public class MovieTicketingSystemSimulator {

    public static void main(String[] args) {
        DatabaseConnector databaseConnector = new DatabaseConnector();

        /** create seats
         Seat[] seats = new Seat[40];
         for (int i=0;i<40;i++) {
         seats[i] = new Seat((i+40)/20 + 1,(i+40)%20 + 1,0);
         }
         int initSeatId = Seat.getInitSeatIdInDb();
         databaseConnector.createSeat(initSeatId, seats);
         */

    }
}
