package database;

import java.sql.Connection;
import java.sql.SQLException;

public interface Home_interface {

	void add_employee(Connection con) throws SQLException;
	void search_by_id(Connection con) throws SQLException;
	void show_all_data(Connection con) throws SQLException;
	void update_database(Connection con) throws SQLException;
	void delete_particular(Connection con) throws SQLException;
	void clear_table(Connection con) throws SQLException;
	void search_by_salary(Connection con) throws SQLException;
	void sort_by_salary(Connection con) throws SQLException;
	void search_by_date(Connection con) throws SQLException;
}
