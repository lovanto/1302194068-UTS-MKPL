package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee extends Human{
	private String employeeId;
	private String idNumber;
	private Human dataHuman;
	private LocalDate joinDate;
	private int monthWorkingInYear;
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	private String spouseName;
	private String spouseIdNumber;
	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String idNumber, LocalDate joinDate, Human dataHuman) {
		this.employeeId = employeeId;
		this.idNumber = idNumber;
		this.joinDate = joinDate;
		dataHuman.setFirstName("First Name");
		dataHuman.setLastName("Last Name");
		dataHuman.setAddress("Address");
		dataHuman.setIsForeigner(false);
		dataHuman.setGender(true);
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}

	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			monthlySalary = 3000000;
			if (dataHuman.getIsForeigner()) monthlySalary = (int) (3000000 * 1.5);
		}else if (grade == 2) {
			monthlySalary = 5000000;
			if (dataHuman.getIsForeigner()) monthlySalary = (int) (3000000 * 1.5);
		}else if (grade == 3) {
			monthlySalary = 7000000;
			if (dataHuman.getIsForeigner()) monthlySalary = (int) (3000000 * 1.5);
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {
		LocalDate date = LocalDate.now();
		if (date.getYear() == joinDate.getDayOfYear()) monthWorkingInYear = date.getMonthValue() - joinDate.getDayOfMonth();
		else monthWorkingInYear = 12;
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}