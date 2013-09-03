package dbandindex;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;


public class StuffDocument {
	private static final String STUFF_ID = "stuffid";
	private static final String CONTENTS = "contents";
	private static final String FULLPATH = "fullpath";
	private static final String FILENAME = "filename";
	
	public static Document buildStuffDocument(Stuff stuff, int id){ //通过Stuff 建立Document
		Document doc = new Document();
		
		Field identifier = new Field(STUFF_ID, id+"",Field.Store.YES,Field.Index.NOT_ANALYZED);
		Field contents = new Field(CONTENTS, stuff.getContents(), Field.Store.YES,Field.Index.NOT_ANALYZED);
		Field fullpath = new Field(FULLPATH, stuff.getFullpath(),Field.Store.YES, Field.Index.ANALYZED);
		Field filename = new Field(FILENAME, stuff.getFilename(),Field.Store.YES, Field.Index.ANALYZED);
		String text = stuff.getFullpath();
		text += " " + stuff.getFilename();
		text += " " + stuff.getContents();
		Field all = new Field("all", text, Field.Store.YES, Field.Index.ANALYZED);
		
		doc.add(identifier);
		doc.add(fullpath);
		doc.add(filename);
		doc.add(contents);
		doc.add(all);
		return doc;		
	}

}
