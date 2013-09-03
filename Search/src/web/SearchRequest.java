package web;
//用户在页面单击一次搜索按钮或选择分页时，通过DWR构造一个SearchRequest对象
public class SearchRequest {
	
	private String query;
	
	public String getQuery(){
		return query;
	}
	
	public void setQuery(String query){
		this.query = query;
	}
}
