package ontology;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.Restriction;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;


public class Ontology {
private OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF);
private final String Mechanical_design="http://www.semanticweb.org/kiwi/ontologies/Mechanical_design#";
public Triple triple;

public Ontology(String path){
	OntDocumentManager dm = model.getDocumentManager();
	dm.addAltEntry("http://www.semanticweb.org/kiwi/ontologies/Mechanical_design",
					"file:" + path);
	model.read("http://www.semanticweb.org/kiwi/ontologies/Mechanical_design","RDF/XML");
}

public void doSearch(String Keyword){ //只列出了自建的objectProperty属性值
	String wordURI = Mechanical_design + Keyword;
	OntClass term = model.getOntClass(wordURI);
	ExtendedIterator<OntClass> relatedTerm = term.listSuperClasses();
	while(relatedTerm.hasNext()){
		OntClass temp = relatedTerm.next();
		if (temp.isRestriction()){
			Restriction r = temp.asRestriction();
			OntProperty pro = r.getOnProperty();
			System.out.print(pro.getLocalName() + ":");
			if (r.isSomeValuesFromRestriction()){
				System.out.print(r.asSomeValuesFromRestriction().getSomeValuesFrom().getLocalName());
			}
			System.out.println();			
		}
	}
}
public boolean getRelatedObject(String center){
	triple = new Triple(center);
	String wordURI = Mechanical_design + center;
	OntClass term = model.getOntClass(wordURI);
	if (term == null) return false;
	//找出超类
	ExtendedIterator<OntClass> superClasses = term.listSuperClasses(true);
	while(superClasses.hasNext()){
		OntClass superClass = superClasses.next();
		if (!superClass.isRestriction()){
			triple.addRelation("SuperClass", superClass.getLocalName());
		}
		if (superClass.isRestriction()){
			Restriction r = superClass.asRestriction();
			OntProperty pro = r.getOnProperty();
			triple.addRelation(pro.getLocalName(), r.asSomeValuesFromRestriction().getSomeValuesFrom().getLocalName());
		}
	}
	//找出等价类
	ExtendedIterator<OntClass> equivaClasses = term.listEquivalentClasses();
	while(equivaClasses.hasNext()){
		OntClass equivaClass = superClasses.next();
		if (equivaClass.isClass()){
			triple.addRelation("EquivalentClass", equivaClass.getLocalName());
		}
		if (equivaClass.isRestriction()){
			Restriction r = equivaClass.asRestriction();
			OntProperty pro = r.getOnProperty();
			triple.addRelation(pro.getLocalName(), r.asSomeValuesFromRestriction().getSomeValuesFrom().getLocalName());
		}		
	}
	//找出子类
	ExtendedIterator<OntClass> subClasses = term.listSubClasses(true);
	while(subClasses.hasNext()){
		OntClass subClass = subClasses.next();
		if (subClass.isClass()){
			triple.addRelation("SubClass", subClass.getLocalName());
		}
		if (subClass.isRestriction()){
			Restriction r = subClass.asRestriction();
			OntProperty pro = r.getOnProperty();
			triple.addRelation(pro.getLocalName(), r.asSomeValuesFromRestriction().getSomeValuesFrom().getLocalName());
		}		
	}
	//triple.printTriple();
	return true;
}
}
