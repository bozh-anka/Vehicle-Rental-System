import java.text.SimpleDateFormat;
import java.util.Date;

public class Motorcycle extends Invoice {
	private int ageD;

	public Motorcycle(String model, String brand, String name, double val, Date startD, Date endD, Date returnD, int ageD) {
		super(model, brand, name, val, startD, endD, returnD);
		if(ageD > 0) {
			this.ageD = ageD;
		} else {
			System.out.println("Insufficient driver age, setting to default 1");
			this.ageD = 1;
		}
	}
	public int getAgeD() {
		return ageD;
	}
	
	public void setAgeD(int ageD) {
		if(ageD > 0) {
			this.ageD = ageD;
		} else {
			System.out.println("Insufficient driver age, setting to default 1");
			this.ageD = 1;
		}
	}
	@Override
	public String Rent() {
		//time elapsed in days
		long rtime = (getReturnD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24);
		//expected time in days
		long rEtime = (getEndD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24);
		long f;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String output = "\nReserved rental days: " + rEtime + "\n\nActual return date: " + format.format(getReturnD()) + 
				 "\nActual rental days: " + rtime + "\n\nRental cost per day: ";
		
		//If statement logic identical to Car with number adjustments for rent price
		if (rtime <= 7 && rtime == rEtime) {
			 f = rtime * 15;
			 output = output  + "$15.00\n";
		} else if (rtime <= 7) {
			 f = rtime * 15 + ((getEndD().getTime() - getReturnD().getTime()) / (1000 * 60 * 60 * 24))*(15/2);
			 setRentD(f -(rtime * 15));
			 output = output  + "$15.00\n";
		} else if (rtime > 7 && rtime == rEtime) {
			 f = rtime * 10;
			 output = output  + "$10.00\n";
			 
		} else {
			 f = rtime * 10 + ((getEndD().getTime() - getReturnD().getTime()) / (1000 * 60 * 60 * 24))*(5);
			 setRentD(f - (rtime * 10));
			 output = output  + "$10.00\n";
		}
		setRent(f);
		return output;
		//System.out.printf( " \nTotal rent:$%.2f", f );

	}

	@Override
	public String Insurance() {
		//Insurance day value
		double dayV = getVal()*(0.02d/100);
		String output = "Insurance per day: $" + String.format("%.2f", dayV) + "\n\nInitial insurance per day: $" +  String.format("%.2f",dayV);
		//Total insurance
		double insurance = ((getReturnD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24))*dayV;
		if (getAgeD() < 25) {
			//20% increase 
			output = output + "\nInsurance addition per day: $" +String.format("%.2f",dayV*0.2d);
			dayV = (dayV + (dayV*0.2d));
			output = output + "\nInsurance per day: $" +  String.format("%.2f",dayV);
			insurance = insurance *1.2d;
		}
		if (getReturnD()!= getEndD()) {
			//setInsuranceR((getEndD().getTime() - getReturnD().getTime()) / (1000 * 60 * 60 * 24));
			setInsuranceR(((getEndD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24)*(dayV) - insurance));
		}
		setInsurance(insurance);
		return output;
		//System.out.printf( " \nTotal insurance:$%.2f", insurance );
	}

}
