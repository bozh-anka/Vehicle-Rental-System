import java.text.SimpleDateFormat;
import java.util.Date;

public class Van extends Invoice {
	
	private int exp;

	public Van(String model, String brand,String name, double val, Date startD, Date endD, Date returnD, int exp) {
		super(model, brand, name, val, startD, endD, returnD);
		if(exp >=0) {
			this.exp = exp;
		} else {
			System.out.println("Insufficient driver experience setting to default of 0.");
			this.exp = 0;
		}
	}
	public int getExp() {
		return exp;
	}
	
	public void setExp(int exp) {
		if(exp >=0) {
			this.exp = exp;
		} else {
			System.out.println("Insufficient driver experience setting to default of 0.");
			this.exp = 0;
		}
	}
	@Override
	public String Rent() {
		
		long rtime = (getReturnD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24);
		long rEtime = (getEndD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24);
		long f;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String output = "\nReserved rental days: " + rEtime + "\n\nActual return date: " + format.format(getReturnD()) + 
				 "\nActual rental days: " + rtime + "\n\nRental cost per day: ";
		//System.out.println(rtime);
		if (rtime <= 7 && rtime == rEtime) { 
			 f = rtime * 50;
			 output = output  + "$50.00\n";
		} else if (rtime <= 7) {
			 f = rtime * 50 + ((getEndD().getTime() - getReturnD().getTime()) / (1000 * 60 * 60 * 24))*25;
			 output = output  + "$50.00\n";
			 setRentD(f - (rtime * 50));
		} else if (rtime > 7 && rtime == rEtime) {
			 f = rtime * 40;
			 output = output  + "$40.00\n";
		} else {
			 f = rtime * 40 + ((getEndD().getTime() - getReturnD().getTime()) / (1000 * 60 * 60 * 24))*(20);
			 output = output  + "$40.00\n";
			 setRentD(f -(rtime * 40));
			 //setRentD(((getEndD().getTime() - getReturnD().getTime()) / (1000 * 60 * 60 * 24))*(20));
		}
		setRent(f);
		
		//System.out.printf( " \nTotal rent:$%.2f", f );
		return output;
	}

	@Override
	public String Insurance() {
		// 0.01% of car value  reduced by 10% for high safety rating only charged for elapsed days
				double dayV = getVal()*(0.03d/100);
				//double insurance = ((getReturnD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24))*dayV;
				String output = "Insurance per day: $" +  String.format("%.2f",dayV);
				if (getExp() > 5) {
					//15% discount
					output = output + "\nInsurance discount per day: $0"+ String.format("%.2f",dayV*0.15d);
					dayV = (dayV - (dayV*0.15d));
					output = output +"\nInsurance per day: $" + String.format("%.2f",dayV);
					
				}
				
				double insurance = ((getReturnD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24))*dayV;

				if (getReturnD()!= getEndD()) {
					setInsuranceR(((getEndD().getTime() - getStartD().getTime()) / (1000 * 60 * 60 * 24)*(dayV) - insurance));
				}
				setInsurance(insurance);
				//System.out.printf( " \nTotal insurance:$%.2f", insurance );
				return output;
	}

}
