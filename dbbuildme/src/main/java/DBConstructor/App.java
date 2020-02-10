package DBConstructor;

public class App {
    public static void main(String[] args) {

        ConnectionFactory.createConnection();

        ConstructTables chineseWorkers = new ConstructTables();
        chineseWorkers.createTables();

        PopulateIndividual confusingName = new PopulateIndividual();
        confusingName.populate();

        try {
            ConnectionFactory.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Fuck you.");
    }
}
