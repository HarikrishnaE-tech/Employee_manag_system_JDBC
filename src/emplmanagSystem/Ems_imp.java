package emplmanagSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

import employee.Employee;
import exception.EmployeenotfoundException;
import exception.InvalidException;

public class Ems_imp implements Ems {
Scanner sc=new Scanner(System.in);

@Override
public void addemployee() throws SQLException  {
	System.out.println("enter the id of the employee");
	int id=sc.nextInt();
	System.out.println("Enter thr name of the employee");
	String name=sc.next();
	System.out.println("enter the age of the employee");
	int age=sc.nextInt();
	System.out.println("enter the salary of the employee");
	int salary=sc.nextInt();
	System.out.println("enter the designation");
	String designation=sc.next();
	
	Employee emp=new Employee(new Employee().getEmpid(), name, age, salary, designation);
	
	Driver driver=new com.mysql.cj.jdbc.Driver();
	DriverManager.registerDriver(driver);
	
	Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
	String insert="Insert into employees(empid,empname,empage,empsalary,empdesignation) values(?,?,?,?,?)";
	
	PreparedStatement pstmt=conn.prepareStatement(insert);
	pstmt.setInt(1,emp.getEmpid());
	pstmt.setString(2,name);
	pstmt.setInt(3,age);
	pstmt.setInt(4, salary);
	pstmt.setString(5, designation);
	
	int execute =pstmt.executeUpdate();
	if(execute>0) {
		System.out.println("the employee added");
	}
	else {
	String message="employee not found";
	try {
		 throw  new EmployeenotfoundException(message);
	}
	catch(EmployeenotfoundException e) {
		System.out.println(e.getMessage());
	}
	}
	
}

@Override
public void displayemployee() throws SQLException {
	System.out.println("enter the empid to be displayed");
	int id=sc.nextInt();
	
	Driver driver=new com.mysql.cj.jdbc.Driver();
	DriverManager.registerDriver(driver);
	
	Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
	CallableStatement call = conn.prepareCall("{call Display(?)}");
	call.setInt(1, id);
	ResultSet rs=call.executeQuery();
	while(rs.next()) {
		
		System.out.println(rs.getInt(1));
		System.out.println(rs.getString(2));
		System.out.println(rs.getInt(3));
		System.out.println(rs.getInt(4));
		System.out.println(rs.getString(5));
		
	}
	
	
	
}

@Override
public void displayallemployee() throws SQLException {
	Driver driver=new com.mysql.cj.jdbc.Driver();
	DriverManager.registerDriver(driver);
	
	Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
	String display="Select * from Employees";
	
	PreparedStatement pst=conn.prepareStatement(display);
	ResultSet rs=pst.executeQuery();
	while(rs.next()) {
		System.out.println(rs.getInt(1));
		System.out.println(rs.getString(2));
		System.out.println(rs.getInt(3));
		System.out.println(rs.getInt(4));
		System.out.println(rs.getString(5));
	}
		
	
}


@Override
public void removeemployee() throws SQLException {
	System.out.println("enter the empid of the employee to remove");
	int id=sc.nextInt();
	Driver driver=new com.mysql.cj.jdbc.Driver();
	DriverManager.registerDriver(driver);
	
	Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
	String remove="Delete from employees where empid=?";
	PreparedStatement prst=conn.prepareStatement(remove);
	prst.setInt(1, id);
	int execute=prst.executeUpdate();
	if(execute>0) {
		System.out.println("removed");
	}
	else {
		System.out.println("not removed");
	}
	
	
}

@Override
public void removeallemployee() throws SQLException {
	Driver driver=new com.mysql.cj.jdbc.Driver();
	DriverManager.registerDriver(driver);
	
	Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
	String remove="Delete from employees ";
	PreparedStatement prst=conn.prepareStatement(remove);
	
	int execute=prst.executeUpdate();
	if(execute>0) {
		System.out.println("removed");
	}
	else {
		System.out.println("not removed");
	}
	
}

@Override
public void updateemployee()  {
	System.out.println("enter the id of the employee to update");
	int id=sc.nextInt();
    System.out.println("select which properties need to update ");
    System.out.println("1.name of the employee\n2.Age of the employee");
    System.out.println("3.Salary of the employee\n4.Designation of the employee");
    int choice=sc.nextInt();
    
    switch(choice) {
    case 1:
    	System.out.println("enter the new name");
    	String name=sc.next();
    	
    	Driver driver1;
		try {
			driver1 = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver1);
	    	Connection conn1=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
	    	String nameupdate="update employees set empname=? where empid=?";
	    	
	    	PreparedStatement psmt1=conn1.prepareStatement(nameupdate);
	    	psmt1.setString(1, name);
	    	psmt1.setInt(2, id);
	    	psmt1.execute();
	    	String namedisplay="Select * from employees where empid=?";
	    	PreparedStatement psmt2=conn1.prepareStatement(namedisplay);
	    	psmt2.setInt(1, id);
	    	ResultSet rs1=psmt2.executeQuery();
	    	while(rs1.next()) {
	    		System.out.println(rs1.getInt(1));
	    		System.out.println(rs1.getString(2));
	    		System.out.println(rs1.getInt(3));
	    		System.out.println(rs1.getInt(4));
	    		System.out.println(rs1.getString(5));
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    case 2:
    	System.out.println("enter the age of the employee to update");
    	int age=sc.nextInt();
    	Driver driver2;
    	try {
			driver2=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver2);
			Connection conn2=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
			String ageupdate="update employees set empage=? where empid=?";
			
			PreparedStatement psmt3=conn2.prepareStatement(ageupdate);
			psmt3.setInt(1, age);
			psmt3.setInt(2, id);
			psmt3.execute();
			
			String agediaplay="select * from employees where empid=?";
			PreparedStatement psmt4=conn2.prepareStatement(agediaplay);
			psmt4.setInt(1, id);
			ResultSet rs2=psmt4.executeQuery();
			while(rs2.next()) {
				System.out.println(rs2.getInt(1));
	    		System.out.println(rs2.getString(2));
	    		System.out.println(rs2.getInt(3));
	    		System.out.println(rs2.getInt(4));
	    		System.out.println(rs2.getString(5));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    case 3:
    	System.out.println("enter the salary to update");
    	int salary=sc.nextInt();
		try {
			Driver driver3=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver3);
			Connection conn3=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
			String salaryupdate="Update employees set empsalary=? where empid=?";
			PreparedStatement ps=conn3.prepareStatement(salaryupdate);
			ps.setInt(1, salary);
			ps.setInt(2,id);
			ps.execute();
			
			String display="select * from employees where empid=?";
			PreparedStatement ps2=conn3.prepareStatement(display);
			ps2.setInt(1, id);
			ResultSet rs3=ps2.executeQuery();
			while(rs3.next()) {
				System.out.println(rs3.getInt(1));
	    		System.out.println(rs3.getString(2));
	    		System.out.println(rs3.getInt(3));
	    		System.out.println(rs3.getInt(4));
	    		System.out.println(rs3.getString(5));
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    case 4:
    	System.out.println("enter the deisgnation to update");
    	String designation =sc.next();
    	
    	Driver driver4;
		try {
			driver4 = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver4);
			Connection conn4=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
			String designationupdate="update  employees Set empdesignation=? where empid=?";
			PreparedStatement pst=conn4.prepareStatement(designationupdate);
			pst.setString(1, designation);
			pst.setInt(2, id);
			pst.execute();
			
			String display="Select * from employees where empid=?";
			PreparedStatement pst2=conn4.prepareStatement(display);
			pst2.setInt(1, id);
			ResultSet rs4=pst2.executeQuery();
			while(rs4.next()) {
				System.out.println(rs4.getInt(1));
	    		System.out.println(rs4.getString(2));
	    		System.out.println(rs4.getInt(3));
	    		System.out.println(rs4.getInt(4));
	    		System.out.println(rs4.getString(5));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
		}
		break;
		default:
			String message="invalid";
			try {
				throw new InvalidException(message);
				
			}
			catch(InvalidException e) {
				System.out.println(e.getmessage());
			}
		}
    }
    
    
    
	


@Override
public void countemployee() throws SQLException {
	Driver driver=new com.mysql.cj.jdbc.Driver();
	DriverManager.registerDriver(driver);
	Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
	String countemploy="Select count(*) from employees";
	PreparedStatement prs=conn.prepareStatement(countemploy);
	ResultSet rs=prs.executeQuery();
	while(rs.next()) {
		System.out.println(rs.getInt(1));
	}
}

@Override
public void sortemployee() throws SQLException {
	List<Employee> a=new ArrayList<Employee>();
	System.out.println("1.sort based on id\n2.sort based on name");
	System.out.println("3.sort based on age\n4.sort based on salary");
	System.out.println("5.sort based on designation");
	int choice =sc.nextInt();
	switch(choice) {
	case 1:
		Driver driver=new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
		
		String sortid="Select * from employees ";
		PreparedStatement pst=conn.prepareStatement(sortid);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			int id=rs.getInt(1);
			
    		String name=rs.getString(2);
    		int age=rs.getInt(3);
    		int salary=rs.getInt(4);
    		String designation=rs.getString(5);
    		Employee emp=new Employee(id,name,age,salary,designation);
    		a.add(emp);
		}
        a.stream().sorted((a1,a2)->a1.getEmpid()-a2.getEmpid()).forEach(System.out::println);
//		System.out.println(a);
	
		break;
	case 2:
		
		Driver driver1=new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver1);
		Connection conn1=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
		
		String sortname ="Select *  from employees ";
		PreparedStatement pst1=conn1.prepareStatement(sortname);
		ResultSet rs1=pst1.executeQuery();
		while(rs1.next()) {
			Integer id=rs1.getInt(1);
    		String name=rs1.getString(2);
    		Integer age=rs1.getInt(3);
    		Integer salary=rs1.getInt(4);
    		String designation=rs1.getString(5);
    		Employee emp=new Employee(id,name,age,salary,designation);
    		a.add(emp);		
		}
		a.stream()
		 .sorted((a1, a2) -> a1.getEmpname().compareTo(a2.getEmpname()))
		 .forEach(System.out::println);

		break;
	case 3:
		Driver driver2=new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver2);
		Connection conn2=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
		
		String sortage="Select * from employees";
		PreparedStatement pst2=conn2.prepareStatement(sortage);
		
		ResultSet rs2=pst2.executeQuery();
		while(rs2.next()) {
			int id=rs2.getInt(1);
    		String name=rs2.getString(2);
    		int age=rs2.getInt(3);
    		int salary=rs2.getInt(4);
    		String designation=rs2.getString(5);
    		Employee emp=new Employee(id,name,age,salary,designation);
    		a.add(emp);
		}
		a.stream().sorted((a1,a2) -> a1.getEmpage()-a2.getEmpage()).forEach(System.out::println);
		break;
	case 4:
		Driver driver3=new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver3);
		Connection conn3=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
		
		String sortsalary="Select * from employees ";
		PreparedStatement pst3=conn3.prepareStatement(sortsalary);
		ResultSet rs3=pst3.executeQuery();
		while(rs3.next()) {
			int id=rs3.getInt(1);
    		String name=rs3.getString(2);
    		int age=rs3.getInt(3);
    		int salary=rs3.getInt(4);
    		String designation=rs3.getString(5);
    		Employee emp=new Employee(id,name,age,salary,designation);
    		a.add(emp);
		}
		a.stream().sorted((a1,a2) -> a1.getEmpsalary()-a2.getEmpsalary()).forEach(System.out::println);
		break;
		
	case 5:
		Driver driver4=new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver4);
		Connection conn4=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
		
		String sortdesignation="Select * from employees ";
		PreparedStatement pst4=conn4.prepareStatement(sortdesignation);
		ResultSet rs4=pst4.executeQuery();
		
		while(rs4.next()) {
			int id=rs4.getInt(1);
    		String name=rs4.getString(2);
    		int age=rs4.getInt(3);
    		int salary=rs4.getInt(4);
    		String designation=rs4.getString(5);
    		Employee emp=new Employee(id,name,age,salary,designation);
    		a.add(emp);
		}
		a.stream()
		 .sorted((a1, a2) -> a1.getEmpdesignation().compareTo(a2.getEmpdesignation()))
		 .forEach(System.out::println);

		break;
		default:
		
			String message="invalid";
			try {
				throw new InvalidException(message);
				
			}
			catch(InvalidException e) {
				System.out.println(e.getmessage());
			}
		}
		
		
		
	}
		
	
	
	


@Override
public void highestsalary() throws SQLException {
	List<Employee> a=new ArrayList<Employee>();
	
	Driver driver=new com.mysql.cj.jdbc.Driver();
	DriverManager.registerDriver(driver);
	Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
	String highestsalary="SELECT * FROM employees ";
	PreparedStatement pst=conn.prepareStatement(highestsalary);
	ResultSet rs=pst.executeQuery();
	while(rs.next()) {
		int id=rs.getInt(1);
		String name= rs.getString(2);
		int age=rs.getInt(3);
		int salary=rs.getInt(4);
		String designation=rs.getString(5);
		Employee emp1=new Employee(id,name,age,salary,designation);		
		a.add(emp1);
	}
	a.stream().max((a1, a2) -> a1.getEmpsalary() - a2.getEmpsalary()).ifPresent(System.out::println);

	pst.close();
	conn.close();
	
	
}

@Override
public void lowestsalary() throws SQLException {
	List<Employee> a=new ArrayList<Employee>();
	Driver driver=new com.mysql.cj.jdbc.Driver();
	DriverManager.registerDriver(driver);
	Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee","root","HAri7510@");
	String lowestsalary="Select * from employees ";
	PreparedStatement pst=conn.prepareStatement(lowestsalary);
	ResultSet rs=pst.executeQuery();
	while(rs.next()) {
		int id=rs.getInt(1);
		String name=rs.getString(2);
		int age=rs.getInt(3);
		int salary= rs.getInt(4);
		String designation=rs.getString(5);
		Employee emp=new Employee(id,name,age,salary,designation);
		a.add(emp);
	}
	a.stream().min((a1,a2) -> a1.getEmpsalary() - a2.getEmpsalary()).ifPresent(System.out::println);
	
	
}
}
