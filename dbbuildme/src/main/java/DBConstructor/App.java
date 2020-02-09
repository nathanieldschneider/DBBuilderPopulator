package DBConstructor;

public class App {
    public static void main(String[] args) {

        ConnectionFactory.createConnection();

        ConstructTables chineseWorkers = new ConstructTables();
        chineseWorkers.createTables();

        PopulateRoundWinners rounders = new PopulateRoundWinners();
        rounders.populate();

        PopulateIndividualGameData confusingName = new PopulateIndividualGameData();
        confusingName.populate();

        PopulatePersistentStats.populate();

        try {
            ConnectionFactory.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Fuck you.");
    }
}
