package DBConstructor;
import java.sql.*;
import java.util.Random;
public class PopulateRoundWinners {

    public PopulateRoundWinners() {
        randomStats = new int[5];
    }

    private int[] randomStats;

    public void fillRandomStats(int[] a){

        for(int i = 0;i<a.length;i++){
            a[i] = getRandomInRange(0, 20);
        }
    }

    public int getRandomInRange(int min,int max){
        if (min >= max ) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public boolean populateRoundWinners(int[] a){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement createRow = null;
        String createRowSQL = 

            "INSERT INTO round_winners" +
            "(human_wins,ai_1_wins," +
            "ai_2_wins,ai_3_wins,ai_4_wins)" +
            "VALUES (?,?,?,?,?);";

        try {

            createRow = con.prepareStatement(createRowSQL);
            int j = 0;
            for(int i = 1;i<=5;i++){
                createRow.setInt(i,a[j]);
                j++;
            }
            
            createRow.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    } 

    public void populate(){

        for(int i = 0;i<10;i++){
            fillRandomStats(randomStats);
            populateRoundWinners(randomStats);
        }

        System.out.println("Round winners relation populated.");
    }
}