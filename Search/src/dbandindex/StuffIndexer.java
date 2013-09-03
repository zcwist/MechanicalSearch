package dbandindex;
import java.io.File;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class StuffIndexer { //建立语料库索引文件
	private String indexPath = null;
	private IndexWriter writer = null;
//	private Analyzer analyzer = null;
	
	public StuffIndexer(String indexPath) throws Exception{
		this.indexPath = indexPath;
		initialize();
	}
	
	@SuppressWarnings("deprecation")
	private void initialize() throws Exception{
		
		Directory dir = FSDirectory.open(new File(indexPath));
		writer = new IndexWriter(dir, 
				new StandardAnalyzer(Version.LUCENE_30), 
				true,//创建或重写
				IndexWriter.MaxFieldLength.UNLIMITED);//最大文件数
	}
	
	public void close(){
		try{
			writer.close();
		} catch (Exception e){
			e.printStackTrace();
			writer = null;
		}
	}
	
	public void addStuff(Stuff stuff, int id) throws Exception{
		writer.addDocument(StuffDocument.buildStuffDocument(stuff, id));
	}
	
	@SuppressWarnings("deprecation")
	public void optimizeIndex() throws Exception {
		writer.optimize();
	}
	
	
}
