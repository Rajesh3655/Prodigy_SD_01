package demo;
import java.util.*;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.*;

public class Task1 
{
	static Connection con;
	
	public static Connection conection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3308/rajesh","root","");
//			System.out.println("connection done!!!!");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e);
		}
		return con;
	}
	public static void main(String[] args) throws SQLException 
	{
		
		int ch;
		System.out.println("CONTACT MANAGEMENT SYSTEM");
		while(0==0) 
		{
			System.out.println("1.Add a new contact");
			System.out.println("2.View the contact");
			System.out.println("3.Edit the contact");
			System.out.println("4.Delete the contact");
			System.out.println("5.exit");
			System.out.println("Enter your choice:");
			
			Scanner sc=new Scanner(System.in);
			ch=sc.nextInt();
			
			switch(ch)
			{
				case 1:
					create();
					break;
				case 2:
					view();
					break;
				case 3:
					edit();
					break;
				case 4:
					delete();
					break;
				case 5:
					System.exit(0);
			}
		}
	}


	private static void delete() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("press 1 for delete a single row:");
		System.out.println("press 2 for delete a entire row:");
		int n;
		Scanner scc=new Scanner(System.in);
		n=scc.nextInt();
		Connection cn=conection();
		
		if(n==1)
		{
			
			String querry="Select name from stu";
			Statement statement=(Statement) cn.createStatement();
			ResultSet output=statement.executeQuery(querry);
			
			System.out.println("Names:");
			while(output.next()) {
				String name=output.getString("name");
				System.out.println(name);
			}
			
			System.out.println("Enter a name for delete their details:");
			String name;
			name=scc.next();
			String querry1="delete from stu where name=?";
			PreparedStatement ps=(PreparedStatement) cn.prepareStatement(querry1);
			ps.setString(1, name);
			ps.execute();
			System.out.println("Successfully row deleted...........!");
			
		}
		else if(n==2) {
			String querry="delete from stu ";
			PreparedStatement ps=(PreparedStatement) cn.prepareStatement(querry);
			ps.execute();
			System.out.println("Entire row Deleted.............!");
		}
		else
		{
			System.out.println("select only 1 or 2\n\n");
		}
	}



	private static void view() throws SQLException {
		// TODO Auto-generated method stub
		String querry="Select *from stu";
		Connection cn=conection();
		System.out.println("\n");
		Statement statement=(Statement) cn.createStatement();
		ResultSet output=statement.executeQuery(querry);
		int count=1;
		while(output.next()) {
			String name=output.getString("name");
			String email=output.getString("email");
			String phnumber=output.getString("phnumber");
			System.out.println(count+".Name: "+name+"\n  Email: "+email+"\n  Ph_Number: "+phnumber+"\n");
			count++;
			
		}
		
	}

	private static void create() {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		String querry="insert into stu values(?,?,?)";
		System.out.println("Enter your name:");
		String name,email;
		name=sc.nextLine();
		System.out.println("Enter your Email:");
		email=sc.nextLine();
		System.out.println("Enter the phone number:");
		double number=sc.nextDouble();
		
		System.out.println("\n");
		Connection cn=conection();
		try {
			PreparedStatement ps=(PreparedStatement) cn.prepareStatement(querry);
			ps.setString(1, name);
			ps.setDouble(2, number);
			ps.setString(3, email);
			ps.execute();
			System.out.println("Contact inserted successfully...........!\n\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		private static void edit() throws SQLException {
			// TODO Auto-generated method stub
			String querry="Select name from stu";
			Connection cn=conection();
			Statement statement=(Statement) cn.createStatement();
			ResultSet output=statement.executeQuery(querry);
			System.out.println("Names:");
			while(output.next()) {
				String name=output.getString("name");
				System.out.println(name);
			}
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter a name for edit their details:");
			String prename=sc.next();
			String querry1="update stu set name=?,phnumber=?,email=? where name=?";
			System.out.println("Enter your new name:");
			String name,email;
			name=sc.next();
			System.out.println("Enter your new Email:");
			email=sc.next();
			System.out.println("Enter the new phone number:");
			double number=sc.nextDouble();
			try {
				PreparedStatement ps=(PreparedStatement) cn.prepareStatement(querry1);
				ps.setString(1, name);
				ps.setDouble(2, number);
				ps.setString(3, email);
				ps.setString(4, prename);
				ps.execute();
				System.out.println("Contact inserted successfully...........!\n\n");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Successfully updated...........!\n");
			
			
		}
	
}

	

