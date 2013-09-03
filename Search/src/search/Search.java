package search;

//import dbandindex.MakeDBandIndex;

public class Search {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		Indexer.makeIndex();//创建索引
//		MakeDBandIndex.make();//建立数据库及索引
//		String keyword = "Cam";
//		
//		System.setProperty("log4j.configuration", "file:/home/kiwi/Workspaces/MyEclipse 10/Learn_jena/bin/jena-log4j.properties"); 
//		String path = "/home/kiwi/Documents/Ontology/Mechanical design rdf 20130320.owl";
//		Ontology ontology = new Ontology(path);//导入本体
//		ontology.getRelatedObject(keyword);//扩展语义
//		
////		//自已的朴素的加权方法
////		Weight weight = new Weight();//初始化权值
////		Searcher.search(keyword);
////		CountWeightValue.addFiles(Searcher.fileNameList, 2);
////		for (String extendWord:ontology.triple.getObjectPredict().keySet()){//遍历triple中所有的宾语，即扩展词
////			extendWord.replace('_', ' ');//空格替换下划线
////			Searcher.search(extendWord);
////			String predict = ontology.triple.getObjectPredict().get(extendWord);
////			//System.out.println(predict);
////			int weightValue = weight.getWeight(predict);
////			CountWeightValue.addFiles(Searcher.fileNameList, weightValue);
////		}
////		Searcher.closeSearch();
////		CountWeightValue.print();
//		
//		//采用Lucene的加权方法
//		ExtendQueryParse eqp=new ExtendQueryParse(ontology.triple);
//		//System.out.println(eqp.parsedQuery);
//		Searcher.search(eqp.parsedQuery);
//		Searcher.printQueryResultWithScore();
//		Searcher.closeSearch();
		
		
		System.out.println(DoSearch.search("Cam"));
		
		
	}
}
