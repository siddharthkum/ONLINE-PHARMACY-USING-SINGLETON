import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class PharmaDemo
{
	 static int count=1; 
	 static int choice; 
	 public static void main(String[] args) throws IOException
	 { 
		 MedicineSingleton jdbc = MedicineSingleton.getInstance();
		 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		 do
			 {
			 System.out.println("\n\nDATABASE OPERATIONS FOR ONLINE PHARMACY");
			 System.out.println("---------------------------------------");
			 System.out.println(" 1. INSERT MEDICINE DATA"); 
			 System.out.println(" 2. VIEW AVAILABLE MEDICINES"); 
			 System.out.println(" 3. DELETE MEDICINE DATA"); 
			 System.out.println(" 4. UPDATE MEDICINE AVAILIBILITY"); 
			 System.out.println(" 5. Exit ");
			 System.out.print("\n"); 
			 System.out.print("Please enter the choice what you want to perform in 
			the database: ");
			 choice=Integer.parseInt(br.readLine()); 
			 switch(choice)
			 {
				 
				 case 1:
				 { 
					 System.out.print("Enter the Medicine ID you want to insert 
					data into the database: "); 
					 int ID=Integer.parseInt(br.readLine());
					 System.out.print("Enter the Medicine Name you want to insert 
					data into the database: "); 
					 String NAME=br.readLine();
					 System.out.print("Enter the Medicine Price you want to insert 
					data into the database: "); 
					 int PRICE=Integer.parseInt(br.readLine());
					 System.out.print("Enter the Medicine Quantity you want to 
					insert data into the database: "); 
					 int QUANTITY=Integer.parseInt(br.readLine());
					 System.out.print("Enter the Medicine Availibility you want to 
					insert data into the database: "); 
					 String AVAILIBILITY=br.readLine();
					 try
					{
						int i= jdbc.insert(ID,NAME,PRICE,QUANTITY,AVAILIBILITY);
						 if (i>0)
						 { 
							System.out.println((count++) + " Data has been inserted successfully"); 
						 }
						 else
						{
							System.out.println("Data has not been inserted "); 
						}
					 }
					catch (Exception e) {System.out.println(e); } 
				 }
				 //End of case 1 
				 break; 
				 
				 case 2:
				 { 
					 System.out.print("Enter the Medicine ID : "); 
					 int ID=Integer.parseInt(br.readLine()); 
					 try
					{
						jdbc.view(ID); 
					 }
					catch (Exception e) { System.out.println(e); } 
				 }
				 //End of case 2 
				 break; 
				 
				 case 3:
				 { 
					 System.out.print("Enter the Medicine ID you want to delete: 
					"); 
					 int ID=Integer.parseInt(br.readLine()); 
					 try
					{ 
						 int i= jdbc.delete(ID); 
						 if (i>0)
						 { 
							System.out.println((count++) + " Data has been 
							deleted successfully"); 
						 }
						else
						 {
							System.out.println("Data has not been deleted"); 
						 } 
					 
					 }
					catch (Exception e) { System.out.println(e); } 
				 
				 }
				 //End of case 3 
				 break; 
				 
				 case 4:
				 { 
					 System.out.print("Enter the Medicine ID you want to update 
					the availibility for: "); 
					 int ID=Integer.parseInt(br.readLine()); 
					 System.out.print("Enter the new Availibility "); 
					 String AVAILIBILITY=br.readLine(); 
					 try
					{ 
						int i= jdbc.update(ID,AVAILIBILITY); 
						 if (i>0)
						 { 
							System.out.println((count++) + " Data has been updated successfully"); 
						 } 
					 }
					catch (Exception e) { System.out.println(e); } 
				 }
				 // End of case 4 
				 break; 
				 
				 default: 
				 return;
			 } 
		 
		 } while (choice!=4);
	 } 
} 