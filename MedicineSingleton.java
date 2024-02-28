import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 

class MedicineSingleton {
	
	private static MedicineSingleton jdbc;
	private MedicineSingleton() { }
	public static MedicineSingleton getInstance() 
	 {
		 if (jdbc==null)
		 {
			jdbc = new MedicineSingleton();
		 }
		 return jdbc;
	 } 
	 
	private static Connection getConnection()throws ClassNotFoundException, 
	SQLException
	 {
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 Connection con = 
		DriverManager.getConnection("jdbc:oracle:thin:@siddhvrth:1521:xe","system","siddha
		rth");
		 return con;
	 }
	 
	 public int insert(int id,String name, int price, int quantity, String avail) 
	throws SQLException
	 {
		 Connection c=null;
		 PreparedStatement ps=null;
		 int recordCounter=0;
		 try
		 {
			 c = this.getConnection();
			 ps = c.prepareStatement("insert into 
			medicineData(MEDICINE_ID,MEDICINE_NAME,MEDICINE_PRICE,MEDICINE_QUANTITY,MEDICINE_A
			VAILIBILITY)values(?,?,?,?,?)");
			 ps.setInt(1, id);
			 ps.setString(2, name);
			 ps.setInt(3, price);
			 ps.setInt(4, quantity);
			 ps.setString(5, avail);
			 recordCounter=ps.executeUpdate();
		 }
		 catch (Exception e) { e.printStackTrace(); } 
		 finally
		 {
			 if (ps!=null)
			 {
				ps.close(); 
			 }
			 if(c!=null)
			 {
				c.close(); 
			 }
		 }
		 return recordCounter;
	 }
	 
	 public void view(int id) throws SQLException
	 {
		 Connection con = null; 
		 PreparedStatement ps = null; 
		 ResultSet rs = null;
		 try
		 {
			 con=this.getConnection();
			 ps=con.prepareStatement("select * from medicineData where 
			MEDICINE_ID=?");
			 ps.setInt(1, id);
			 rs=ps.executeQuery();
			 while (rs.next())
			 {
				 System.out.println("Medicine ID= "+rs.getInt(1)+"\t"+"Medicine 
				Name= "+rs.getString(2)+"\t"+"Medicine Price= "+rs.getInt(3)+"\t"+"Medicine 
				Quantity= "+rs.getInt(4)+"\t"+"Medicine Availibility= "+rs.getString(5));
			 }
		 } 
		 catch (Exception e) { System.out.println(e);} 
		 finally
		 {
			 if(rs!=null)
			 {
				rs.close(); 
			 }if (ps!=null)
			 {
				ps.close();
			 }
			 if(con!=null)
			 {
				con.close(); 
			 }
		 }
	 }
	 
	 public int update(int id, String avail) throws SQLException
	 {
		 Connection c=null;
		 PreparedStatement ps=null;
		 int recordCounter=0;
		 try
		 {
			 c=this.getConnection();
			 ps=c.prepareStatement(" update medicineData set 
			MEDICINE_AVAILIBILITY=? where MEDICINE_ID= '"+id+"' ");
			 ps.setString(1, avail);
			 recordCounter=ps.executeUpdate();
		 }
		 catch (Exception e) { e.printStackTrace(); }
		 finally
		 {
			 if (ps!=null)
			 {
				ps.close(); 
			 }
			 if(c!=null)
			 {
				c.close();
			 }
		 }
		 return recordCounter;
	 }
	 
	 public int delete(int id) throws SQLException
	 {
		 Connection c=null;
		 PreparedStatement ps=null;
		 int recordCounter=0;
		 try
		 {
			 c=this.getConnection();
			 ps=c.prepareStatement("delete from medicineData where 
			MEDICINE_ID='"+id+"' ");
			 recordCounter=ps.executeUpdate(); 
		 }
		 catch (Exception e) { e.printStackTrace(); }
		 finally
		 {
			 if (ps!=null)
			 {
				ps.close(); 
			 }
			 if(c!=null)
			 {
				c.close(); 
			 } 
		 } 
		 return recordCounter; 
	 } 
}
