package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class Homecontrolling_mainclass implements Home_interface{
public static void main(String[] args) throws SQLException {
	// Connecting DB
	String url="jdbc:mysql://localhost:3306/first_db";
	String name="root";
	String password ="9089";
	Connection con = DriverManager.getConnection(url,name,password);
	System.out.println("DB is connected");

	try (Scanner sc = new Scanner(System.in)) {
		int counter =0;
		Home_interface home_interface = new ServicesClass();
		home_interface.show_all_data(con);
		while(counter==0) {
			System.out.println("\n1.add data            2.Search data by id    3.Show all data  4.Update DB\n5.Delete particular   6.remove all datas from the table       7.Search by salary\n8.Sort by salary   9.Search by date  10.exit");
			int option = sc.nextInt();
			switch(option) {
			case 1: home_interface.add_employee(con);
				break;
			case 2: home_interface.search_by_id(con);
				break;
			case 3:home_interface.show_all_data(con);
				break;
			case 4:home_interface.update_database(con);
				break;
			case 5:home_interface.delete_particular(con);
				break;
			case 6:home_interface.clear_table(con);
				break;
			case 7:home_interface.search_by_salary(con);
				break;
			case 8:home_interface.sort_by_salary(con);
				break;
			case 9:home_interface.search_by_date(con);
				break;
			case 10:counter++;
			break;
			
			}
		}
	}
}
}
