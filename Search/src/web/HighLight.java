package web;
import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.util.Version;


public class HighLight {
	private String text = "";
	private String query = "";
	
	HighLight(String text, String query){
		this.text = text;
		this.query = query;
	}
	public String highLight() throws ParseException, IOException, InvalidTokenOffsetsException{
		String searcherText = query;
		QueryParser parser = new QueryParser(Version.LUCENE_30, "f", new StandardAnalyzer(Version.LUCENE_30));
		Query query = parser.parse(searcherText);
		SimpleHTMLFormatter formatter =
				new SimpleHTMLFormatter("<span class=\"highlight\">","</span>");
		TokenStream tokens = new StandardAnalyzer(Version.LUCENE_30).tokenStream("f", new StringReader(text));
		QueryScorer scorer = new QueryScorer(query, "f");
		Highlighter highlighter
			= new Highlighter(formatter, scorer);
		highlighter.setTextFragmenter(new SimpleSpanFragmenter(scorer));
		String result =
				highlighter.getBestFragments(tokens, text, 3, "...");
		return result;
	}

}
