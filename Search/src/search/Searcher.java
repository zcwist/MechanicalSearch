package search;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class Searcher {
	public static ArrayList<String> fileNameList = new ArrayList<String>(); //含有关键词的文件
	public static IndexSearcher is;
	public static TopDocs hits;//查询结果
	private static Query query;
	public static void search(String q) throws CorruptIndexException, IOException, ParseException{
		String indexDir = "/home/kiwi/Documents/lucene index/wikidoc sql";
		Directory dir = FSDirectory.open(new File(indexDir));
		IndexReader reader = IndexReader.open(dir);
		is =  new IndexSearcher(reader);
		
//		QueryParser parser = new QueryParser(Version.LUCENE_30, //查询解析器
//												"contents", 
//												new StandardAnalyzer(Version.LUCENE_30));
		QueryParser multiFieldParser = new MultiFieldQueryParser(Version.LUCENE_30, //多域查询解析器
																new String[] {"filename", "contents"},
																new StandardAnalyzer(Version.LUCENE_30));
		query = multiFieldParser.parse(q);//只对contents域进行搜索 

		hits = is.search(query, 200);//Top对象的的形式返回搜索结果集 只包括引用 不包括加载
		for (ScoreDoc scoreDoc : hits.scoreDocs){
			Document doc = is.doc(scoreDoc.doc);
			fileNameList.add(doc.get("filename"));
		}		
	}
	public static void closeSearch() throws IOException{
		is.close();
	}
	public static void printQueryResult(){//打印文件列表 
		Iterator<String> file = fileNameList.iterator();
		while (file.hasNext()){
			System.out.println(file.next());
		}
	}
	public static void printQueryResultWithScore() throws Exception{//打印带评分的文件列表 
		System.out.println(QueryResultWithScore());
	}
	public static String QueryResultWithScore() throws Exception{
		String temp = "";
		for (ScoreDoc scoreDoc : hits.scoreDocs){
			Explanation explanation = is.explain(query, scoreDoc.doc);
			Document doc = is.doc(scoreDoc.doc);
			temp = temp + doc.get("filename") + ":" + explanation.getValue() + "\r\n";
			temp = temp + doc.get("contents") +"\r\n";
		}
		return temp;
	}

}
