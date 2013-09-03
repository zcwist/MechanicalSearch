package web;

import java.io.File;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import dbandindex.PropertyConfiguration;

public class SearchServiceImpl implements SearchService {
	
	private static final String STUFF_ID = "stuffid";
	
	private static final String INDEX_STORE_PATH = PropertyConfiguration.getIndexPath();
	
	private SearchResultDao searchResultDao;

	@Override
	public SearchResults getSearchResults(SearchRequest request) {
		
		SearchResults results = new SearchResults();
		
		QueryParser multiFieldParser = new MultiFieldQueryParser(Version.LUCENE_30, //多域查询解析器
				new String[] {"filename", "contents"},
				new StandardAnalyzer(Version.LUCENE_30));
		
		
		
		ArrayList<String> list = new ArrayList<String>();//搜索出文本序号
		
		try {
			Directory dir = FSDirectory.open(new File(INDEX_STORE_PATH));
			@SuppressWarnings("deprecation")
			IndexSearcher searcher = new IndexSearcher(dir);
			Query query = multiFieldParser.parse(request.getQuery());
			
			TopDocs hits = searcher.search(query, 100);
			
			for (ScoreDoc scoreDoc : hits.scoreDocs){
				Document doc = searcher.doc(scoreDoc.doc);
				String id = doc.get(STUFF_ID);
				list.add(id);
			}
			
			results.setResults(list);
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return results;		
	}
	
	public SearchResult getSearchResultById(int id){
		return searchResultDao.getSearchResultById(id);
	}
	
	public SearchResultDao getSearchResultDao(){
		return searchResultDao;
	}
	
	public void setSearchResultDao(SearchResultDao searchResultDao){
		this.searchResultDao = searchResultDao;
	}



}
