package employee;

public class Employee {
private int  empid;
private String empname;
private int empage;
private int empsalary;
private String empdesignation;
private static int count=1001;


public Employee(int empid,String empname,int empage,int empsalary,String empdesignation ) {
	this.empid=empid;
	this.empname=empname;
	this.empage=empage;
	this.empsalary=empsalary;
	this.empdesignation=empdesignation;
}


public Employee() {
	// TODO Auto-generated constructor stub
}


public int getEmpid() {
	return empid;
}


public void setEmpid(int empid) {
	this.empid = empid;
}


public String getEmpname() {
	return empname;
}


public void setEmpname(String empname) {
	this.empname = empname;
}


public int getEmpage() {
	return empage;
}


public void setEmpage(int empage) {
	this.empage = empage;
}


public int getEmpsalary() {
	return empsalary;
}


public void setEmpsalary(int empsalary) {
	this.empsalary = empsalary;
}


public String getEmpdesignation() {
	return empdesignation;
}


public void setEmpdesignation(String empdesignation) {
	this.empdesignation = empdesignation;
}


public static int getCount() {
	return count;
}


public static void setCount(int count) {
	Employee.count = count;
}


@Override
public String toString() {
	return "Employee [empid=" + empid + ", empname=" + empname + ", empage=" + empage + ", empsalary=" + empsalary
			+ ", empdesignation=" + empdesignation + "]";
}











}
