package DBConstructor;
import java.sql.*;
import java.util.Random;

public class PopulateIndividual {

    public PopulateIndividual(){
    }

    public int getRandomInRange(int min,int max){
        if (min >= max ) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public boolean create(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement createRow = null;
        String createRowSQL = "INSERT INTO individual_game_data" +
        "(winner_id,rounds_played,draws)" +
        "VALUES (?,?,?);";
           
        try {

            createRow = con.prepareStatement(createRowSQL);
            createRow.setInt(1,getRandomInRange(0, 4));
            createRow.setInt(2,getRandomInRange(10, 40));
            createRow.setInt(3,getRandomInRange(5, 30));

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

        PopulatePerformance performance = new PopulatePerformance();
        for(int i = 0;i<10;i++){
            create();
            performance.create();
        }

        System.out.println("Relations populated.");
    }
}