package neo4jtest;
import java.sql.*;
import java.util.*;

public class JDBCTEST {
	public static Connection con = null;
	
	public static void main(String[] args) throws SQLException,ClassNotFoundException  {
			Class.forName("org.neo4j.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:neo4j://localhost:7474","neo4j","1");
			
			System.out.println("Select operations to perform \n 1. add a customer \n 2. display all the customers \n 3. create Relationship");
			
			Scanner s = new Scanner(System.in);
			int ch = s.nextInt();
			switch(ch){
			
			case 1:
				System.out.print("Enter the name:");
				String name=s.next();
				System.out.print("Enter the balance:");
				int b=s.nextInt();
				System.out.print("Enter the age:");
				int a=s.nextInt();
				
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("name", name);
				params.put("balance", b);
				params.put("age", a);
				
				addNode(params);
				break;
			case 2:	
				displayNode();
				break;
				
			case 3:
				System.out.print("Enter the name of fromNode:");
				String fromNode = s.next();
				System.out.print("Enter the name of toNode:");
				String toNode = s.next();
				System.out.println("Enter the amount paid:");
				int amountPaid = s.nextInt();
				
				
				if(fromNode!=null && toNode!=null ){
					createRelationship(fromNode,toNode,amountPaid);
					break;
				}else
				{
					System.out.println("EXCEPTION");
					break;
				}
			
			default:
				System.out.println("Invalid choice");
				break;
				
				
			}
			
		con.close();
			
			
			
			
			
			
	}

	public static void addNode(Map<String,Object> params) throws SQLException{
		
		String query = "CREATE (a:Customer{1})";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setObject(1,params);
		ps.executeUpdate();
		System.out.println(params.get("name") + "Added Successfully "  );
		
	}
	
	public static void displayNode() throws SQLException{
		
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("MATCH (a:Customer) RETURN a.name,a.balance,a.age");
		
		
		 System.out.println("name \tbalance\tage");
		while(rs.next()){
				System.out.println(rs.getString("a.name") + "\t" + rs.getInt("a.balance") + "\t" + rs.getInt("a.age"));
		}
		
	}
	
	public static void createRelationship(String fromNode , String toNode,int amountPaid) throws SQLException{
		
		String query = "MATCH (a:Customer{name:{1}}),(b:Customer{name:{2}}) CREATE (a)-[:PAYS{amount:{3}}]->(b) RETURN a.balance,b.balance";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, fromNode);
		ps.setString(2, toNode);
		ps.setInt(3, amountPaid);
		
		ResultSet rs = ps.executeQuery();
		
		int balOfA = rs.getInt("a.balance");
		int balofB = rs.getInt("b.balance");
		
		//balOfA -= amountPaid;
		//balofB += amountPaid;
		
		System.out.println("balance of a:" + balOfA + " balance of b:" + balofB);
	}
}
