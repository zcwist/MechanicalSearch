package web;

import java.io.IOException;

import ontology.Ontology;
import ontology.Triple;
import search.ExtendQueryParse;
import myPackage.FindStuff;

public class DoSearch {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		DoSearch doSearch = new DoSearch();
		//doSearch.search("Gear");
		//doSearch.result();
		System.out.println(doSearch.getResultJson("Gear"));
	}
	
	
	/**
	 * 搜索结果集
	 */
	private SearchResults srs;
	
	/**
	 * 搜索结果条目数
	 */
	public int amount;
	
	/**
	 * 搜索关键字
	 */
	public String term;
	
	/**
	 * 扩展词汇XML文件 
	 */
	public String ExtendWordsXML;
	
	/**
	 * 
	 */
	public String resultJson;
	
	/**
	 * 执行搜索过程
	 * @param keyword
	 * @throws Exception 
	 */
	public void search(String keyword) throws Exception{
		term = keyword;
		String path = "/home/kiwi/Documents/Ontology/Mechanical design rdf 20130320.owl";
		Ontology ontology = new Ontology(path);//导入本体
		SearchRequest sr = new SearchRequest();//建立搜索要求
		if (ontology.getRelatedObject(keyword)) {
		ExtendQueryParse eqp=new ExtendQueryParse(ontology.triple);
		sr.setQuery(eqp.parsedQuery);//建立查询
		} else {
			sr.setQuery(keyword);
		}
		
		
//		System.out.println(eqp.parsedQuery);
		
		
		SearchService ss = new SearchServiceImpl();//新建搜索服务类
		srs= ss.getSearchResults(sr);//以sr作为搜索要求进行搜索，返回id列表
		amount = srs.getResults().size();
		ExtendWordsXML = makeExtendWordsXML(ontology.triple);
		resultJson = makeResultJson(ontology.triple);
		
		SearchRequest sr2 = new SearchRequest();
		sr2.setQuery(keyword);
		SearchResults resultsnoextend = ss.getSearchResults(sr2);
		int amount2 = resultsnoextend.getResults().size();
		for (int i=0;i<amount2;i++){
			System.out.println(
					FindStuff.findStuffById(resultsnoextend.getResults().get(i)).getFilename());
			
		}
		
		
	}
	/**
	 * 生成扩展词汇XML文件
	 * @param t
	 * @return
	 * @throws IOException 
	 */
	private String makeExtendWordsXML(Triple t) throws IOException{
		
		String temp="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" +
				"<dataset>\r\n";
		temp += "<record>" +
				"<word>" +
				t.getSubject() +
				"</word>" +
				"<descn>" +
				"center" +
				"</descn>" +
				"</record>\r\n";
		for (String extendWord:t.getObjectPredict().keySet()){
			temp += "<record>" +
					"<word>" +
					extendWord +
					"</word>" +
					"<descn>" +
					t.getObjectPredict().get(extendWord) +
					"</descn>" +
					"</record>\r\n";	
		}
		temp += "</dataset>";
		
//		//生成XML文件//貌似不靠谱啊
//		FileWriter fw = new FileWriter("extendWord.xml");
//		BufferedWriter bw = new BufferedWriter(fw);
//		bw.write(temp);	
//		bw.flush();
//		bw.close();
		
		return temp;
	}
	private String makeResultJson(Triple t) throws Exception{
		String temp="{" +
				"success:true,\r\n" +
				"msg:'success',\r\n" +
				"extendWord:[\r\n";
		temp += "{word:'" +
				t.getSubject() +
				"'," +
				"descn:" +
				"'center'" +
				"},\r\n" ;
		for (String extendWord:t.getObjectPredict().keySet()){
			temp += "{word:'" +
					extendWord +
					"'," +
					"descn:'" +
					t.getObjectPredict().get(extendWord) +
					"'},\r\n";	
		}
		temp=temp.substring(0,temp.length()-3);//去掉最后一个逗号
		temp += "],\r\n" +
				"content:\'" ;
		//temp += result().replaceAll("\"", "\\\"").replaceAll("<", "\\u003C").replaceAll(">","\\u003E");
		temp +=result().replaceAll("\'","\\\\\'");
		
		temp += "\',\r\n";
		temp += getFileListJson();
		temp += "}";
		return temp;
	}
	public String getResultJson(String query) throws Exception{
		String temp="";
		search(query);
		temp = resultJson;
		return temp;
	}
	
	public String getAExample(){
		return "{success:true, msg:'success'}";
	}
	
	public String getExtendWordsXML(String query) throws Exception{
		String temp="";
		search(query);
		temp = ExtendWordsXML;
		return temp;
		
	}
	
	/**
	 * 根据条目编号，返回文件名
	 * @param i
	 * @return
	 */
	
	public String getFilename(int i){
//		System.out.println("No." + i);
//		System.out.println(FindStuff.findStuffById(srs.getResults().get(i)).getFilename());
		return FindStuff.findStuffById(srs.getResults().get(i)).getFilename();
	}
	
	public String getFileID(int i){
		return srs.getResults().get(i);
	}
	
	/**
	 * 根据条目编号，返回内容
	 * @param i
	 * @return
	 * @throws Exception
	 */
	public String getContent(int i) throws Exception{
		HighLight highLight = new HighLight(FindStuff.findStuffById(srs.getResults().get(i)).getContents(), term);
		return highLight.highLight();
	}
	/**
	 * 返回第一搜索结果
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public String getContent(String keyword) throws Exception{
		HighLight highLight = new HighLight(FindStuff.findStuffById(srs.getResults().get(0)).getContents(), keyword);
		return highLight.highLight();
	}
	
	public String result(String query) throws Exception{
		String temp="";
		search(query);
		for (int i=0; i<amount; i++){
			temp += "<h3>" + getFilename(i) + "</h3>" +
					getContent(i).replaceAll("\r\n", "\\\\r\\\\n") +
					"<br>";			
		}
		return temp;
	}
	public String getAllResult(String query) throws Exception{
		String temp = getExtendWordsXML(query);
		
		temp += "<aboutHTML>\r\n";

		
		for (int i=0; i<amount; i++){
			temp += "<h3>" + getFilename(i) + "</h3>\r\n" +
					getContent(i) +
					"<br>";			
		}
		temp += "</aboutHTML>\r\n";
		temp += "</dataset>";
		return temp;
		
	}
	
	public String result() throws Exception{
		String temp="";
		for (int i=0; i<amount; i++){
			temp += "<h3>" + getFilename(i) + "</h3>" +
					getContent(i).replaceAll("\r\n", "\\\\r\\\\n") +
					"<br>";			
		}
		return temp;
	}
	public String getFileListJson(){
		String temp="fileList:[\r\n";
		for (int i=0; i<amount; i++){
			temp += "{id:'" + getFileID(i) +"',fileName:'" + getFilename(i) + "'},\r\n";
		}
		temp=temp.substring(0,temp.length()-3);//去掉最后一个逗号
		temp += "]";
		return temp;
		
	}

}
