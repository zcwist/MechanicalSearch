package dbandindex;
import java.sql.*;

public class StuffJDBC {
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	private boolean autoCommit = true;
	
	public StuffJDBC(String url, String usr, String pwd) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection(url, usr, pwd);
		con.setAutoCommit(autoCommit);
		pstmt = con.prepareStatement("alter table stuff AUTO_INCREMENT 1;");
		pstmt.execute();
		
	}
	
	public int addStuff(Stuff s) throws Exception{ //向数据库中加入数据
		int nextid = getNextId();
		if (nextid < 0) {
			throw new Exception("Can't get next id.");
		}
		String contents = s.getContents();
		String filename = s.getFilename();
		String fullpath = s.getFullpath();
		
		//用PreparedStatement向数据库插入数据
		String expr = "insert into stuff (filename, fullpath, contents) values (?,?,?)";
		pstmt = con.prepareStatement(expr);
		pstmt.setString(1, filename);
		pstmt.setString(2, fullpath);
		pstmt.setString(3, contents);
		try {
			pstmt.execute();
		} catch(Exception e) {
			System.out.println(filename);
		} finally{}
		return nextid;		
	}

	private int getNextId() throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		String sql = "select max(id)+1 from stuff";
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()){
			result = rs.getInt(1);//取第1列整数值
		}
		return result;
	}
	
	public void close(){
		if (con != null){
			try {
				con.close();
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				con = null;
			}
		}
	}
	

}
