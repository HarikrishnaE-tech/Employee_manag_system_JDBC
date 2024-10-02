package main;

import java.sql.SQLException;
import java.util.Scanner;

import emplmanagSystem.Ems_imp;
import exception.InvalidException;
public class Main {
public static void main(String[] args) throws SQLException {
	Scanner sc=new Scanner(System.in);
	Ems_imp emsimp=new Ems_imp();
	while(true) {
		
		System.out.println("--------Employee DataBase---------");
		System.out.println("----------------------------------");
		System.out.println("1. to Add employee.\n2.Display the employee");
		System.out.println("3.Display All the Employee.\n4.remove the Employee");
		System.out.println("5.remove all the Employee.\n6.Update Employee");
		System.out.println("7.count the employee.\n8.Sort the employee");
		System.out.println("9.Highest salary of the employee.\n10.Lowest salary of the Employee");
		int choice=sc.nextInt();
		switch(choice){
		case 1:
			emsimp.addemployee();
			break;
		case 2:
			emsimp.displayemployee();
			break;
		case 3:
			emsimp.displayallemployee();
			break;
		case 4:
			emsimp.removeemployee();
			break;
		case 5:
			emsimp.removeallemployee();
			break;
		case 6:
			emsimp.updateemployee();
			break;
		case 7:
			emsimp.countemployee();
			break;
		case 8:
			emsimp.sortemployee();;
			break;
		case 9:
			emsimp.highestsalary();;
			break;
		case 10:
			emsimp.lowestsalary();
			break;
			
		default:
			String message="invalid number";
			try {
				throw new InvalidException(message);
			}
			catch(InvalidException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
	
}
}
