package search;
import ontology.Triple;


public class ExtendQueryParse {
	public String parsedQuery;
	public ExtendQueryParse(Triple triple){ //将Triple类的关系结构转换成符合Lucene加权搜索语法的查询
		Weight weight= new Weight();
		parsedQuery = triple.getSubject() + "^" + weight.getWeight("subject");
		for (String extendWord:triple.getObjectPredict().keySet()){//遍历triple中所有的宾语，即扩展词
			String predict = triple.getObjectPredict().get(extendWord);
			//System.out.println(predict);
			int weightValue = weight.getWeight(predict);
			extendWord = extendWord.replaceAll("_", " AND ");//连接符替换空格
			parsedQuery += " (" + extendWord + ")" + "^" + weightValue;
			}
	}

}
