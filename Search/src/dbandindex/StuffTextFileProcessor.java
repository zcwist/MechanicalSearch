package dbandindex;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class StuffTextFileProcessor {
	
	private String[] directories;
	//DBUrl
	private static final String dbUrl = PropertyConfiguration.getDBUrl();
	//DB用户名
	private static final String dbUsr = PropertyConfiguration.getDBUsr();
	//DB密码
	private static final String dbPwd = PropertyConfiguration.getDBPwd();
	
	//StuffJDBC实例，处理数据库操作
	private StuffJDBC stuffJDBC = null;
	//处理索引操作
	private StuffIndexer indexer = null;
	
	private static final String indexPath = PropertyConfiguration.getIndexPath();
	
	public StuffTextFileProcessor(){
		initialize();
	}
	
	public void initialize(){
		try {
			stuffJDBC = new StuffJDBC(dbUrl, dbUsr, dbPwd);
			indexer = new StuffIndexer(indexPath); //建立索引
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//设置待处理文件夹
	public void setDirectories(String[] directories){
		this.directories = directories;
	}
	
	//处理逻辑
	protected void process() throws Exception{
		if (stuffJDBC == null){
			throw new Exception("Database connection failed, pls retry!");
		}
		if (indexer == null){
			throw new Exception("Lucene index failed, pls retry!");
		}
		if (directories == null || directories.length == 0){
			return;
		}
		try {
			//遍历目录
			
			for (int i = 0; i < directories.length; i++){
				File f = new File(directories[i]);
				traverse(f);
			}
			closeDB();
			closeIndex();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	//处理目录
	private void traverse(File file) throws Exception {
		// TODO Auto-generated method stub
		String [] files = file.list();
		//System.out.println(files.length);
		for (int i = 0; i < files.length; i++){
			File stuffFile = new File(file, files[i]);
			
			String name = stuffFile.getName();
			String path = stuffFile.getCanonicalPath();
			//System.out.println(stuffFile);
			BufferedReader reader = new BufferedReader(new FileReader(stuffFile));
			
			StringBuffer content = new StringBuffer();//读取每行内容，加入StringBuffer中
			String line = reader.readLine();
			while (line != null){
				content.append(line).append("\r\n");
				line = reader.readLine();
			}
			Stuff s = new Stuff();
			s.setFilename(name);
			s.setFullpath(path);
			
			String contentstr = content.toString();
			s.setContent(contentstr);
			
			int nextid = insert2DB(s);
			
			buildIndex(s,nextid);
		}
		optimizeIndex();
	}
	
	//插入数据库
	protected int insert2DB(Stuff s) throws Exception{
		return stuffJDBC.addStuff(s);
	}
	
	protected void buildIndex(Stuff s, int nextid) throws Exception{
		indexer.addStuff(s, nextid);
	}
	
	private void optimizeIndex() throws Exception{
		indexer.optimizeIndex();
	}
	
	private void closeIndex() throws Exception{
		indexer.close();
	}
	
	private void closeDB(){
		stuffJDBC.close();
	}
	public String getDbPwd(){
		return dbPwd;
	}
	public String getDbUrl(){
		return dbUrl;
	}
	public String getDbUsr(){
		return dbUsr;
	}
	public String getIndexPath(){
		return indexPath;
	}
}
