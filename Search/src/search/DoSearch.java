package search;


import ontology.Ontology;

public class DoSearch {
	public static String search(String keyword) throws Exception{
		System.setProperty("log4j.configuration", "file:/home/kiwi/Workspaces/MyEclipse 10/Learn_jena/bin/jena-log4j.properties"); 
		String path = "/home/kiwi/Documents/Ontology/Mechanical design rdf 20130320.owl";
		Ontology ontology = new Ontology(path);//导入本体
		ontology.getRelatedObject(keyword);
		
		ExtendQueryParse eqp=new ExtendQueryParse(ontology.triple);
		//System.out.println(eqp.parsedQuery);
		Searcher.search(eqp.parsedQuery);
		String result = Searcher.QueryResultWithScore();
		Searcher.closeSearch();
		return result;
	}
}
