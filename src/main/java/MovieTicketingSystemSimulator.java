import controller.DatabaseConnector;

/**
 * Created by NortonWEI on 27/2/2017.
 */
public class MovieTicketingSystemSimulator {

    public static void main(String[] args) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
//        databaseConnector.findUser("ha");
        databaseConnector.updateUser("sisisi","ha","sisi", "01-02-1990",1,34231143);
    }
}
