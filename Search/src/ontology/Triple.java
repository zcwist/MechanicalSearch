package ontology;
import java.util.ArrayList;
import java.util.HashMap;


public class Triple {
	private String subject;//中心搜索词汇
	private HashMap<String, ArrayList<String>> predictObject = new HashMap<String, ArrayList<String>>();
	private HashMap<String,String> objectPredict = new HashMap<String,String>();
	private ArrayList<String> object = new ArrayList<String>();//宾语列表
	Triple(String subject){
		this.subject=subject;
	}
	public String getSubject(){//返回主语
		return subject;
	}
	public void addRelation(String predict, String object){//增加谓宾关系
		if (predictObject.containsKey(predict)){
			ArrayList<String> tempObject = predictObject.get(predict);
			tempObject.add(object);
			predictObject.put(predict, tempObject);			
		}
		else{
			ArrayList<String> tempObject = new ArrayList<String>();
			tempObject.add(object);
			predictObject.put(predict, tempObject);	
		}
		objectPredict.put(object, predict);
		this.object.add(object);
	}
	public HashMap<String, ArrayList<String>> getPredictObject(){
		return predictObject;
	}
	public HashMap<String,String> getObjectPredict(){
		return objectPredict;
	}
	public ArrayList<String> getObjectList(){//输出所有相应结点
		return object;
	}
	public ArrayList<String> getObjectList(String predict){//输出确定边对应结点
		return predictObject.get(predict);
	}
	public void printTriple(){
		System.out.println(subject + ":");
		for (String predict:predictObject.keySet()){
			System.out.println("     " + predict + ":");
			System.out.println("           " + predictObject.get(predict).toString());
		}
	}


}
