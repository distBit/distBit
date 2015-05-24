package 第九章;
import java.awt.Dimension;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class shiwuqudong {
	  private static List<String> a = new ArrayList<String>();
	  private static List<String> b = new ArrayList<String>();	 
	  private static List<String> c = new ArrayList<String>();
	  private static List<String> d = new ArrayList<String>();
      public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException{
			System.out.println(System.currentTimeMillis());
			String url="jdbc:mysql://121.194.104.118/tpcc_new";
		    String user="root";
		    String pwd="62288848";
		    //加载驱动，这一句也可写为：
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//建立到MySQL的连接
			Connection conn = DriverManager.getConnection(url,user, pwd);
			//执行SQL语句
			Statement stmt = conn.createStatement();//创建语句对象，用以执行sql语言
		    
			//取四个属性值成数组
			String sql="select distinct(discount) from test ";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
					d.add(rs.getString(1));
				}
			
			sql="select distinct(credit) from test ";
			 rs = stmt.executeQuery(sql);
			while(rs.next()){
					c.add(rs.getString(1));
				}
			
			sql="select distinct(state) from test ";
			 rs = stmt.executeQuery(sql);
			while(rs.next()){
					b.add(rs.getString(1));
				}
			
			sql="select distinct(t_last) from test ";
			 rs = stmt.executeQuery(sql);
			while(rs.next()){
					a.add(rs.getString(1));
				}
		
			
			
	       //Random random = new Random();
	       Random random = new Random(System.currentTimeMillis());
			
			
	       //事务操作
	   for(int i=0;i<30;i++){
		   
	   
	       String[] shuzu=new String[6];
	      String sql1 = "select Id from test where discount="+d.get(random.nextInt(d.size()));
		 //System.out.println(sql1);
		shuzu[0]=sql1;
		    // rs = stmt.executeQuery(sql);
		    // System.out.println("this is the first"+d.get(random.nextInt(d.size())));
		 
		 	
		 	String sql2 = "select * from test where discount = "+d.get(random.nextInt(d.size()));
		 	//System.out.println(sql2);
			shuzu[1]=sql2;
			//rs = stmt.executeQuery(sql);
			//System.out.println("this is the second"+d.get(random.nextInt(d.size())));
			
			
			String sql3 = "select * from test where discount < "+d.get(random.nextInt(d.size()));
			//System.out.println(sql3);
			shuzu[2]=sql3;
			//rs = stmt.executeQuery(sql);
			//System.out.println("this is the third"+d.get(random.nextInt(d.size())));
			
			
			/*stmt.executeUpdate("insert into test values('30077771','zsss','zs','BC',0.99)");
            stmt.executeUpdate("update test  set discount=discount*3");  
         */
			
		   String sql4 = "select avg(discount) from test  where state=\""+b.get(random.nextInt(b.size()))+"\"";
		    //System.out.println(sql4);
			shuzu[3]=sql4;
			//rs = stmt.executeQuery(sql);
			//System.out.println("this is the forth"+b.get(random.nextInt(b.size())));
		
			int r1=random.nextInt(d.size());
			int r2=random.nextInt(d.size());
			double num1=Double.parseDouble(d.get(r1));
			double num2=Double.parseDouble(d.get(r2));
			if(num1 < num2)
			{
			double e=0;
			 e=num2;
			 num2=num1;
			 num1=e;
				
			}
		String	sql5 = "select count(distinct state) from test where discount >"+num2+ "and discount <"+num1;
			//rs = stmt.executeQuery(sql);
			//System.out.println("this is the fifth "+num1 + num2);
			//System.out.println(sql5);
			shuzu[4]=sql5;
			String	 sql6 = "select Id  from test where credit=\""+c.get(random.nextInt(c.size()))+"\" order by discount desc limit  10 ";
			//rs = stmt.executeQuery(sql);
			
			//System.out.println("this is the sixth "+c.get(random.nextInt(c.size())));
			//System.out.println(sql6);
			shuzu[5]=sql6;
			//Random r = new Random(System.currentTimeMillis()); 
			System.out.println(shuzu[random.nextInt(6)]);
	   }
			rs.close();
			stmt.close();
			conn.close();

}}
