package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class ServicesClass implements Home_interface{
	Scanner sc = new Scanner(System.in);
	
public void add_employee(Connection con) throws SQLException
{
	
	System.out.println("Enter the id");
	int uid = sc.nextInt();
	System.out.println("Enter the name");
	String uname = sc.next();
	System.out.println("Enter the salary");
	int usalary = sc.nextInt();
	
	LocalDate localdate = LocalDate.now();
	int date = localdate.getDayOfMonth();
	int month = localdate.getMonthValue();
	
		
	int year = localdate.getYear();
	String con_month = null;
	String con_date = Integer.toString(date);
	if(month<10) {
	String add = Integer.toString(month);
	String add2 = "0";
	add2+=add;
	con_month =add2;
	}
	else {
		con_month = Integer.toString(month);
	}
	
	String con_year = Integer.toString(year);
	String joined_date = con_date+con_month+con_year;
	int joindate = Integer.parseInt(joined_date);
	

	
	String query = "INSERT INTO home(id,name,salary,date_of_join)VALUE(?,?,?,?);";
	PreparedStatement ps = con.prepareStatement(query);
	 ps.setInt(1,uid);
	 ps.setString(2,uname);
	 ps.setInt(3,usalary);
	 ps.setInt(4,joindate);
	 ps.executeUpdate();
	 System.out.println("Employee added successfully");
}

@Override
public void search_by_id(Connection con) throws SQLException {
	System.out.println("Enter the id to get details");
	 String uid2 = sc.next();
	 String query2="SELECT id, name, salary, date_of_join FROM home WHERE id = ";
	 query2 = query2+uid2;
	try {
	 Statement stmnt = con.createStatement();
	 ResultSet resset = stmnt.executeQuery(query2);
	 resset.next();
	 int retrived_id = resset.getInt("id");
	 String retrived_name = resset.getString("name");
	 int retrived_salary = resset.getInt("salary");
	 int retrived_date = resset.getInt("date_of_join");
	 System.out.println("id   name  salary  Date_of_join");
	 System.out.println(retrived_id+"     "+retrived_name+"    "+retrived_salary+" "+retrived_date);
	}catch(Exception e) {
		System.out.println("Datas not found");
	}
	
}

@Override
public void show_all_data(Connection con) throws SQLException {
	 String query2="SELECT * FROM home";
	 Statement stmnt = con.createStatement();
	 ResultSet resset = stmnt.executeQuery(query2);
	
	 
	 System.out.println("id     name     salary  date_ of_join");
	 while( resset.next()) {
		 int retrived_id = resset.getInt("id");
		 String retrived_name = resset.getString("name");
		 int retrived_salary = resset.getInt("salary");
		 int retrived_date = resset.getInt("date_of_join");
		 System.out.println(retrived_id+"     "+retrived_name+"    "+retrived_salary+"         "+retrived_date); 
	}
	
}

@Override
public void update_database(Connection con) throws SQLException {
	System.out.println("Enter the id to update");
	int uid = sc.nextInt();
	System.out.println("Enter the name to be updated");
	String uname = sc.next();
	System.out.println("Enter the salary to be updated");
	int usalary = sc.nextInt();
	System.out.println("Enter the date of join to be updated");
	int ujoin = sc.nextInt();
	String query3 = "UPDATE home SET name = ?, salary = ?, date_of_join = ? WHERE id = ?;";
	PreparedStatement ps = con.prepareStatement(query3);
	 ps.setString(1,uname);
	 ps.setInt(2,usalary);
	 ps.setInt(3,ujoin);
	 ps.setInt(4, uid);
	 ps.executeUpdate();
	 System.out.println("updated successfully!!!");
	
}

@Override
public void delete_particular(Connection con) throws SQLException {
	System.out.println("Enter the id to be deleted");
	int uid = sc.nextInt();
	String query4 = "DELETE FROM home WHERE id = ?;";
	PreparedStatement ps = con.prepareStatement(query4);
	 ps.setInt(1,uid);
	 ps.executeUpdate();
	 System.out.println(" id = "+uid+" deleted successfully!!!");
}

@Override
public void clear_table(Connection con) throws SQLException {
	System.out.println("Warning all datas will be deleted  1 =>yes  0 =>no");
	int delete = sc.nextInt();
	if(delete == 1) {
		String query6 = "DELETE FROM home;";
		PreparedStatement ps = con.prepareStatement(query6);
		ps.executeUpdate();
		System.out.println("Deleted successfully!!!");
	}
}
	
@Override
public void search_by_salary(Connection con) throws SQLException {
	System.out.println("Enter the salary to find employees");
	String find_by_salary = sc.next();
	String query7 = "SELECT * FROM home WHERE salary = ";
	 query7 = query7+find_by_salary;
		 Statement stmnt = con.createStatement();
		 ResultSet resset = stmnt.executeQuery(query7);
		 System.out.println("id     name     salary  date_of_join");
		 while(resset.next()) {
		 int retrived_id = resset.getInt("id");
		 String retrived_name = resset.getString("name");
		 int retrived_salary = resset.getInt("salary");
		 int retrived_date = resset.getInt("date_of_join");
		 System.out.println(retrived_id+"      "+retrived_name+"     "+retrived_salary+"    "+retrived_date); 
		 }
	
}

@Override
public void sort_by_salary(Connection con) throws SQLException {
	
	String query2="SELECT * FROM home ORDER BY salary";

	try {
		Statement stmnt = con.createStatement();
		 ResultSet resset = stmnt.executeQuery(query2);
		 
		 while( resset.next()) {
			 int retrived_id = resset.getInt("id");
			 String retrived_name = resset.getString("name");
			 int retrived_salary = resset.getInt("salary");
			 int retrived_date = resset.getInt("date_of_join");
			 System.out.println(retrived_id+"     "+retrived_name+"    "+retrived_salary+"         "+retrived_date); 
		}
	 }catch(Exception e) {
		 e.printStackTrace();
	 }

}

@Override
public void search_by_date(Connection con) throws SQLException {
	
	System.out.println("1.Search by full date \n2.Search by month\n3.Search by year\n4.Search by selected month and year");
	int date_option = sc.nextInt();
	if(date_option == 1) {
		System.out.println("Enter the Date to find employees");
		String find_by_date = sc.next();
		String query9 = "SELECT * FROM home WHERE date_of_join = ";
		 query9 = query9+find_by_date;
			 Statement stmnt = con.createStatement();
			 ResultSet resset = stmnt.executeQuery(query9);
			 System.out.println("id     name     salary  date_of_join");
			 while(resset.next()) {
			 int retrived_id = resset.getInt("id");
			 String retrived_name = resset.getString("name");
			 int retrived_salary = resset.getInt("salary");
			 int retrived_date = resset.getInt("date_of_join");
			 System.out.println(retrived_id+"      "+retrived_name+"     "+retrived_salary+"    "+retrived_date); 
			 }
	}
	else if(date_option == 2) {
		System.out.println("Enter the month to find employees");
		int find_by_month = sc.nextInt();
		String query9 = "SELECT * FROM home ";
			 Statement stmnt = con.createStatement();
			 ResultSet resset = stmnt.executeQuery(query9);
			 System.out.println("id     name     salary  date_of_join");
			 while(resset.next()) {
			 int retrived_id = resset.getInt("id");
			 String retrived_name = resset.getString("name");
			 int retrived_salary = resset.getInt("salary");
			 int retrived_date = resset.getInt("date_of_join");
			 int temp = retrived_date;
			 temp = temp/10000;
			 temp = temp%100;
			 if(temp == find_by_month) {
			 System.out.println(retrived_id+"      "+retrived_name+"     "+retrived_salary+"    "+retrived_date); 
			 }
			 }
		
	}
	else if(date_option == 3) {
		System.out.println("Enter the year to find employees");
		int find_by_year = sc.nextInt();
		String query9 = "SELECT * FROM home ";
			 Statement stmnt = con.createStatement();
			 ResultSet resset = stmnt.executeQuery(query9);
			 System.out.println("id     name     salary  date_of_join");
			 while(resset.next()) {
			 int retrived_id = resset.getInt("id");
			 String retrived_name = resset.getString("name");
			 int retrived_salary = resset.getInt("salary");
			 int retrived_date = resset.getInt("date_of_join");
			 int temp = retrived_date;
			 temp = temp%10000;
			 if(temp == find_by_year) {
			 System.out.println(retrived_id+"      "+retrived_name+"     "+retrived_salary+"    "+retrived_date); 
			 }
			 }
		
	}
	else if(date_option == 4) {
		System.out.println("Enter the year to find employees");
		int find_by_year = sc.nextInt();
		System.out.println("Enter month in "+find_by_year+" :");
		int find_by_month=sc.nextInt();
		String query9 = "SELECT * FROM home ";
			 Statement stmnt = con.createStatement();
			 ResultSet resset = stmnt.executeQuery(query9);
			 System.out.println("id     name     salary  date_of_join");
			 while(resset.next()) {
			 int retrived_id = resset.getInt("id");
			 String retrived_name = resset.getString("name");
			 int retrived_salary = resset.getInt("salary");
			 int retrived_date = resset.getInt("date_of_join");
			 int temp = retrived_date;
			 temp = temp%10000;
			 if(temp == find_by_year) {
			 temp = retrived_date;
			 temp = temp/10000;
			 temp = temp%100;
			 if(temp == find_by_month) {
				 System.out.println(retrived_id+"      "+retrived_name+"     "+retrived_salary+"    "+retrived_date); 
				 }
			 }
			 }
		
		
		
	}
	else {
		System.out.println("Invalied Entry   please try again!!!");
		ServicesClass add_data = new ServicesClass();
		add_data.search_by_date(con);
	}
	
}
}

