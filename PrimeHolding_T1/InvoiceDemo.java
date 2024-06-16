import java.text.SimpleDateFormat;
import java.util.*;

public class InvoiceDemo 
{
	public static void main(String[] args)
	{
		//Creating date objects for the invoice tests
		Date d1 = new GregorianCalendar(2024, Calendar.JUNE, 03 ).getTime();
		Date d2 = new GregorianCalendar(2024, Calendar.JUNE, 13 ).getTime();
		Date d3 = new GregorianCalendar(2024, Calendar.JUNE, 18 ).getTime();
		//Car invoice
		Car T1 = new Car ("Mitsubishi","Mirage","John Doe",15000.0d,d1,d2,d2,3);
		//Motorcycle invoice
		Motorcycle T2 = new Motorcycle ("Triumph","Tiger Sport 660","Mary Johnson",10000.0d,d1,d2,d2,20);
		//Cargo van invoice 
		Van T3 = new Van ("Citroen","Jumper","John Markson",20000.0d,d1,d3,d2,8);
		
		//Printing using invoice class toString function
		System.out.print(T1);
		System.out.print(T2);
		System.out.print(T3);
		
	}
		
}