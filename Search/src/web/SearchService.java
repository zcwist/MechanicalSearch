package web;


public interface SearchService {
	//返回一个SearchResults
	public abstract SearchResults getSearchResults(SearchRequest request);
	
	//返回单个结果
	public abstract SearchResult getSearchResultById(int id);//数据库中取出一个条目信息

}
