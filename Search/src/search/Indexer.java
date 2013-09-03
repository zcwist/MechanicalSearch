package search;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class Indexer {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void makeIndex() throws Exception {
		// TODO Auto-generated method stub
		String indexDir = "/home/kiwi/Documents/lucene index/wikidoc";
		String dataDir = "/home/kiwi/Documents/lucene data/wikidoc";
		
		long start = System.currentTimeMillis();
		Indexer indexer = new Indexer(indexDir);//建立索引目录
		int numIndexed;
		try {
			numIndexed = indexer.index(dataDir, new TextFilesFilter());
		} finally {
			indexer.close();
		}
		long end = System.currentTimeMillis();
		
		System.out.println("Indexing " + numIndexed + " files took "
				+ (end-start) + " milliseconds");
	}
	private IndexWriter writer;
	
	@SuppressWarnings("deprecation")
	public Indexer(String indexDir) throws IOException{
		Directory dir = FSDirectory.open(new File(indexDir));//存索引
		writer = new IndexWriter(dir, 
								new StandardAnalyzer(Version.LUCENE_30), 
								true,//创建或重写
								IndexWriter.MaxFieldLength.UNLIMITED);//最大文件数
	}
	
	public void close() throws IOException{
		writer.close();
	}
	
	public int index(String dataDir, FileFilter filter) throws Exception{
		File[] files = new File(dataDir).listFiles();//建立文件数组
		for (File f: files){//遍历文件
			if (!f.isDirectory() &&
				!f.isHidden() &&
				f.exists() &&
				f.canRead() &&
				(filter == null || filter.accept(f))){
				indexFile(f);
			}
		}	
		return writer.numDocs();	//索引文档数
	}
	
	private static class TextFilesFilter implements FileFilter{//筛选出txt文件
		public boolean accept(File path){
			return path.getName().toLowerCase().endsWith(".txt");
		}
	}
	
	protected Document getDocument(File f) throws Exception{
		Document doc = new Document();
		
		Field contents = new Field("contents", new FileReader(f));
		contents.setBoost(1);
		doc.add(contents);
		
		Field filename = new Field("filename",f.getName(),
					  Field.Store.YES, Field.Index.NOT_ANALYZED);
		filename.setBoost(2);
		doc.add(filename);
		doc.add(new Field("fullpath", f.getCanonicalPath(),
				     Field.Store.YES, Field.Index.NOT_ANALYZED));
		return doc;
	}
	
	private void indexFile(File f) throws Exception {
		System.out.println("Indexing " + f.getCanonicalPath());
		Document doc = getDocument(f);
		writer.addDocument(doc);
	}
}
