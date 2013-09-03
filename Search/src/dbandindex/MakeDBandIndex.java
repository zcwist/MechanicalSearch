package dbandindex;

public class MakeDBandIndex{ //建立数据库并建立索引
	public static void make() throws Exception {
		StuffTextFileProcessor stu = new StuffTextFileProcessor();		
		stu.setDirectories(new String[] {PropertyConfiguration.getDataPath()});
		stu.process();
	}

}
