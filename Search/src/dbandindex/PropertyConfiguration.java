package dbandindex;

public class PropertyConfiguration {
	private static String DBUrl = "jdbc:mysql://localhost:3306/mechanicaldesigndb";
	private static String DBUsr = "root";
	private static String DBPwd = "112358";
	private static String indexPath = "/home/kiwi/Documents/lucene index/wikidoc sql";
	private static String datapath = "/home/kiwi/Documents/lucene data/wikidoc/";
	
	public static String getDBUrl(){
		return DBUrl;
	}
	public static String getDBUsr(){
		return DBUsr;
	}
	public static String getDBPwd(){
		return DBPwd;
	}
	public static String getIndexPath(){
		return indexPath;
	}
	public static String getDataPath(){
		return datapath;
	}
}
