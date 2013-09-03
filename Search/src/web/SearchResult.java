package web;
//代表一个查询结果的Bean类，对结果属性进行封装
public class SearchResult { //同/dbandindex.stuff
	private String contents = null;	//文本内容
	private String filename = null;	//文件名
	private String fullpath = null;	//文件路径
	
	
	public String getContents(){
		return contents;
	}
	
	public void setContent(String contents){
		this.contents = contents;
	}
	
	public String getFilename(){
		return filename;
	}
	
	public void setFilename(String filename){
		this.filename = filename;
	}
	
	public String getFullpath(){
		return fullpath;
	}
	
	public void setFullpath(String fullpath){
		this.fullpath = fullpath;
	}
}
