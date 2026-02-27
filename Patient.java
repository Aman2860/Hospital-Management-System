package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Patient {
    private Connection conn;
    private Scanner sc;

    public Patient(Connection conn, Scanner sc){
        this.conn = conn;
        this.sc = sc;
    }
    public void addPatient(){
        System.out.println("Enter Patient Name : ");
        String name = sc.nextLine();
        System.out.println("Enter Patient Age : ");
        int age = sc.nextInt();
        System.out.println("Enter Patient Gender : ");
        String gender = sc.nextLine();

        try{
            String qr = "insert into patients(name, age, gender) values(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(qr);
            ps.setString(1,name);
            ps.setInt(2,age);
            ps.setString(3,gender);
            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0){
                System.out.println("Patient Added Successfully");
            }
            else{
                System.out.println("Failed To Add Patient");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void viewPatient(){
        String qr = "select * from patients";
        try{
            PreparedStatement ps = conn.prepareStatement(qr);
            ResultSet rs = ps.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+--------------------+----------+------------+");
            System.out.println("| Patient Id | Name               | Age      | Gender     |");
            System.out.println("+------------+--------------------+----------+------------+");
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", id, name, age, gender);
                System.out.println("+------------+--------------------+----------+------------+");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getPatientById(int id){
        String qr = "select * from patients where id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(qr);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}







