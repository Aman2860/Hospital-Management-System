package HospitalManagementSystem;

import java.sql.*;

public class Doctor {
    private Connection conn;

    public Doctor(Connection conn){
        this.conn = conn;
    }

    public void viewDoctors(){
        String qr = "select * from doctors";
        try{
            PreparedStatement ps = conn.prepareStatement(qr);
            ResultSet resultSet = ps.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+--------------------+------------------+");
            System.out.println("| Doctor Id  | Name               | Specialization   |");
            System.out.println("+------------+--------------------+------------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-10s | %-18s | %-16s |\n", id, name, specialization);
                System.out.println("+------------+--------------------+------------------+");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getDoctorById(int id){
        String qr = "select * from doctors where id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(qr);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
