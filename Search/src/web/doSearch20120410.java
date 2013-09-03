package web;

import ontology.Ontology;
import search.ExtendQueryParse;
import myPackage.FindStuff;

public class doSearch20120410 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		System.out.println(search("Gear"));
		//System.out.println(getContent(1, "Gear"));
	}
	private static SearchResults srs;
	public static String search(String keyword) {
		String path = "/home/kiwi/Documents/Ontology/Mechanical design rdf 20130320.owl";
		Ontology ontology = new Ontology(path);//导入本体
		ontology.getRelatedObject(keyword);
		
		ExtendQueryParse eqp=new ExtendQueryParse(ontology.triple);
//		System.out.println(eqp.parsedQuery);
		SearchRequest sr = new SearchRequest();//建立搜索要求
		sr.setQuery(eqp.parsedQuery);//建立查询
		SearchService ss = new SearchServiceImpl();//新建搜索服务类
		srs= ss.getSearchResults(sr);//以sr作为搜索要求进行搜索，返回id列表
		String temp="";
		for (int i=0;i<srs.getResults().size();i++)
		temp += FindStuff.findStuffById(srs.getResults().get(i)).getFilename() +"\r\n";
		
		return temp;
	}
	public static String getContent(int i, String keyword) throws Exception{
		HighLight highLight = new HighLight(FindStuff.findStuffById(srs.getResults().get(i-1)).getContents(), keyword);
		return highLight.highLight();
	}
	/**
	 * 返回第一搜索结果
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public static String getContent(String keyword) throws Exception{
		HighLight highLight = new HighLight(FindStuff.findStuffById(srs.getResults().get(0)).getContents(), keyword);
		return highLight.highLight();
	}

}
