package search;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;


public class Ontology_RDF {
private Model model = ModelFactory.createDefaultModel();
private final String Mechanical_design="http://www.semanticweb.org/kiwi/ontologies/Mechanical_design#";

Ontology_RDF(String path){
	InputStream in = FileManager.get().open(path);
	if(in == null){
		throw new IllegalArgumentException("File not found");
	}
	model.read(new InputStreamReader(in),"");	
}
public void doSearch(String Keyword){
	String WordURI = Mechanical_design + Keyword;
	Resource asSubject  = model.getResource(WordURI);
	StmtIterator properties = asSubject.listProperties();
	while (properties.hasNext()){
		Statement temp = properties.nextStatement();
		System.out.print(Keyword + ":");
		System.out.print(temp.getPredicate().getLocalName() + ":");
		System.out.println(temp.getObject().toString().substring(temp.getObject().toString().indexOf("#")+1));
		
	}
	
}
}
