package web;

import java.util.ArrayList;
/**
 * 保存所有查询结果ID
 * @author kiwi
 *
 */
//
public class SearchResults {
	
	//保存结果ID
	private ArrayList<String> results = new ArrayList<String>();
	
//	//当前页起始的索引号
//	private int startindex;
//	
//	//当前页面中所要显示的最小页
//	private int minpage;
//	
//	//当前页面中所要显示的最大页
//	private int maxpage;
//	
//	//还有比maxpage更大的页数吗
//	private int hasnext;
//	
//	public int getHasnext(){
//		return hasnext;
//	}
//	
//	public void setHasnext(int hasnext){
//		this.hasnext = hasnext;
//	}
//	
//	public int getMaxpage(){
//		return maxpage;
//	}
//	
//	public void setMaxpage(int maxpage){
//		this.maxpage = maxpage;
//	}
//	
//	public int getMinpage(){
//		return minpage;
//	}
	/**
	 * 返回结果,ArrayList
	 * @return
	 */
	public ArrayList<String> getResults(){
		return results;
	}
	
	public void setResults(ArrayList<String> results){
		this.results = results;
	}
	
//	public int getStartindex(){
//		return startindex;
//	}
//	
//	public void setStartindex(int startindex){
//		this.startindex = startindex;
//	}
}
