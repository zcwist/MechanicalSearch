package search;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class CountWeightValue {
	public static HashMap<String,Integer> weightValue = new HashMap<String,Integer>();
	public static void addFiles(ArrayList<String> fileNameList,int weight){
		Iterator<String> files = fileNameList.iterator();
		while (files.hasNext()){
			String file = files.next();
			if (weightValue.keySet().contains(file)){//权值表中含有该文件名
				int temp = weightValue.get(file);
				weightValue.put(file, temp+weight);
			}
			else{
				weightValue.put(file,weight);
			}
		}
	}
	public static void print(){
		for(String file:weightValue.keySet()){
			System.out.print(file + ":");
			System.out.println(weightValue.get(file));
		}
	}

}
