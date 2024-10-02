package emplmanagSystem;

import java.sql.SQLException;

public interface Ems {
public void addemployee() throws SQLException;
public void displayemployee() throws SQLException;
public void displayallemployee() throws SQLException;
public void removeemployee() throws SQLException;
public void removeallemployee() throws SQLException;
public void updateemployee();
public void countemployee() throws SQLException;
public void sortemployee() throws SQLException;
public void highestsalary() throws SQLException;
public void lowestsalary() throws SQLException;

}
