import java.text.SimpleDateFormat;
import java.util.Date;

public class Car extends Invoice {

	//Unique data member car safety rating
	private int safetyR;
	
	// Car invoice constructor 
	public Car(String model, String brand, String name, double val, Date startD, Date endD, Date returnD, int safetyR) {
		super(model, brand, name, val, startD, endD, returnD); // calling super constructor 
		
		//Ensure rating is within correct range
		if(safetyR >= 1 && safetyR <= 5) {
			this.safetyR = safetyR;
		} else {
			System.out.println("Insufficient safety rating setting to default of 1.");
			this.safetyR = 1;
		}
	}

	// Getter and Setter
	public int getSafetyR() {
		return safetyR;
	}

	public void setSafetyR(int safetyR) {
		if(safetyR >= 1 && safetyR <= 5) {
			this.safetyR = safetyR;
		} else {
			System.out.println("Insufficient safety rating setting to default of 1.");
			this.safetyR = 1;
		}
	}
	
	//Rent calculation definition for Car
	@Override
	public String Rent() {
		//Actual elapsed time in days
		long rtime = (getReturnD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24);
		//Expected elapsed time in days
		long rEtime = (getEndD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		long f;
		String output = "\nReserved rental days: " + format.format(getEndD()) + "\n\nActual return date: " + format.format(getReturnD()) + 
				 "\nActual rental days: " + rtime + "\n\nRental cost per day: ";
		//System.out.println(rtime);
		if (rtime <= 7 && rtime == rEtime) {
			//If full price and returned as expected
			 f = rtime * 20;
			 output = output  + "$20.00\n" ;
		} else if (rtime <= 7) {
			//If full price and returned early 
			 f = rtime * 20 + ((getEndD().getTime() - getReturnD().getTime()) / (1000 * 60 * 60 * 24))*10;
			 output = output  + "$20.00\n" ;
			 
			 // Set rent discount
			 setRentD(f - (rtime * 20));
			
		} else if (rtime > 7 && rtime == rEtime) {
			//If extended rent price and returned as expected
			 f = rtime * 15;
			 output = output  + "$15.00\n" ;
			 
		} else {
			//If extended rent price and returned early
			 f = rtime * 15 + ((getEndD().getTime() - getReturnD().getTime()) / (1000 * 60 * 60 * 24))*(15/2);
			 output = output  + "$15.00\n" ;
			 
			 // Set rent discount
			 setRentD(f -(rtime * 15));
		}
		setRent(f);
		return output;
	
	}

	@Override
	public String Insurance() {
		String output;
		// 0.01% of car value  reduced by 10% for high safety rating only charged for elapsed days
		
		double dayV = getVal()*(0.01d/100); // 0.01% of value
		output = "Insurance per day: $" +  String.format("%.2f",dayV);
		
		
		//double insurance = ((getReturnD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24))*dayV; //total for all days
		
		if (getSafetyR()>= 4) {
		//10% discount
			output = output + "\nInsurance discount per day: $0.10";
			dayV = (dayV - (dayV*0.10d));
			output = output + "\nInsurance per day: $" + String.format("%.2f",dayV);
		//	insurance = (insurance/100)*10d;
	
	}
		double insurance = ((getReturnD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24))*dayV; //total for all days

		if (getReturnD()!= getEndD()) {
			//Insurance early return discount
			setInsuranceR((getEndD().getTime() - getReturnD().getTime()) / (1000 * 60 * 60 * 24)*dayV);
		}
		setInsurance(insurance);
		return output;
		//System.out.printf( " \nTotal insurance:$%.2f", insurance );
	}

}
