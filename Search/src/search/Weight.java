package search;
import java.util.HashMap;


public class Weight { 
public HashMap<String,Integer> weight = new HashMap<String,Integer>();//关系权值表

Weight(){     //不同边的关系给与不同的权值
	weight.put("subject", 4);
	weight.put("SuperClass", 1);
	weight.put("EquivalentClass",4);
	weight.put("SubClass", 1);
	weight.put("has_Some_Feature:", 1);
	weight.put("has_Some_Function", 1);
	weight.put("has_Some_Links", 1);
}
public int getWeight(String relationship){//根据关系返回权值
	return weight.get(relationship);
}
public void setWeight(String relationship, int value){//设置关系权值
	weight.put(relationship, value);
}

}
