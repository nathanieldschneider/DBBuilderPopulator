package DBConstructor;
import java.sql.*;
import java.util.Random;

public class PopulateIndividualGameData {

    public PopulateIndividualGameData(){

        randomStats = new int[2];
        randomPlayers = new String[]{"Human","Ai One","Ai Two","Ai Three","Ai Four"};
    }

    public void fillRandomStats(int[] a){

        for(int i = 0;i<a.length;i++){
            a[i] = getRandomInRange(20, 50);
        }
    }

    public int getRandomInRange(int min,int max){
        if (min >= max ) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    int[] randomStats;
    String[] randomPlayers;

    public boolean create(int[] a, String[] b){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement createRow = null;
        String createRowSQL = "INSERT INTO individual_game_data(winner,rounds_played,draws)" +
        "VALUES (?,?,?);";

        try {

            createRow = con.prepareStatement(createRowSQL);
            createRow.setString(1,b[getRandomInRange(0, 4)]);
            createRow.setInt(2,a[0]);
            createRow.setInt(3,a[1]);
            createRow.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

        finally{
            try{
                if(createRow != null){createRow.close();}
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return true;
    } 

    public void populate(){

        for(int i = 0;i<10;i++){
            fillRandomStats(randomStats);
            create(randomStats,randomPlayers);
        }

        System.out.println("Individual game stats relation populated.");
    }
}