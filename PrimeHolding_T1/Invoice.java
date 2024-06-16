import java.text.SimpleDateFormat;
import java.util.*;


public abstract class Invoice
{
	//Data members
	private String model;	// Vehicle model
	
	private String brand;	// Vehicle brand
	
	private String name;	// Rentee's full name
	
	private double val;		// Vehicle value
	
	private Date startD;	// Starting date of rent period
		
	private Date endD;		// Ending date of rent period
	
	private Date returnD;	// Date vehicle is returned on
	
	private double rent;	// Rent total
	
	private double insurance;// Insurance total
	
	private double insuranceR = 0; // Early return discount
	
	private double rentD = 0;	  // Early return discount
	
	
	//CONSTRUCTOR 
	public Invoice (String model, String brand, String name, double val, Date startD, Date endD, Date returnD ){
		
		this.setModel(model);
		this.setBrand(brand);
		this.name = name;
		//ensure non zero
		if ( val > 0 ){
			this.setVal(val);
		} else {
			System.out.println("Insufficient value setting to default 1.");
			this.setVal(1);
		}
		
		this.setStartD(startD);
		this.setEndD(endD);
		this.setReturnD(returnD);
		
	}
	
	//Vehicle specific calculation functions
	public abstract String Rent();
	public abstract String Insurance();
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Date getStartD() {
		return startD;
	}

	public void setStartD(Date startD) {
		this.startD = startD;
	}

	public Date getEndD() {
		return endD;
	}

	public void setEndD(Date endD) {
		this.endD = endD;
	}

	public Date getReturnD() {
		return returnD;
	}

	public void setReturnD(Date returnD) {
		this.returnD = returnD;
	}

	public double getVal() {
		return val;
	}

	public void setVal(double val) {
		if ( val > 0 ){
			this.val = val;
		} else {
			System.out.println("Insufficient value setting to default 1.");
			this.val = 1;
		}
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		if (rent > 0 ) {
			this.rent = rent;
		} else {
			System.out.println("Insufficient value setting to default 1.");
			this.rent = 1;
		}
	}

	public double getInsurance() {
		return insurance;
	}

	public void setInsurance(double insurance) {
		if (insurance > 0 ) {
			this.insurance = insurance;
		} else {
			System.out.println("Insufficient value setting to default 1.");
			this.insurance = 1;
		}
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}


	public double getInsuranceR() {
		return insuranceR;
	}

	public void setInsuranceR(double insuranceR) {
		if (insuranceR > 0 ) {
			this.insuranceR = insuranceR;
		} else {
			System.out.println("Insufficient value setting to default 1.");
			this.insuranceR = 1;
		}
	}

	public double getRentD() {
		return rentD;
	}

	public void setRentD(double rentD) {
		if (rentD > 0 ) {
			this.rentD = rentD;
		} else {
			System.out.println("Insufficient value setting to default 1.");
			this.rentD = 1;
		}
	}
	//Override of toString method for invoice printing
	 @Override
	 public String toString() {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 String str = "XXXXXXXXXX\nDate: " + format.format(getReturnD()) + "\nCustomer Name: " + getName() + 
				 "\nRented Vehicle: " + getBrand() + " " + getModel() + "\n\nReservation start date: " + format.format(getStartD())
				 + "\nResevation end date: " + format.format(getEndD()); 
		 str = str + Rent();
		 str = str + Insurance();
		 if (rentD > 0) {
			 str = str + "\n\nEarly return discount for rent: $" + String.format("%.2f",getRentD());
		 }
		 if (insuranceR > 0) {
			 str = str + "\nEarly return discount for insurance: $" + String.format("%.2f",getInsuranceR());
		 }
		 str = str + "\n\nTotal rent: $" +String.format("%.2f", getRent()) + "\nTotal insurance: $" + String.format("%.2f",getInsurance()) + 
				 "\nTotal: $" + String.format("%.2f", (getRent()+getInsurance()))+ "\nXXXXXXXXXX\n\n";
	 return str;
	 }

	
}