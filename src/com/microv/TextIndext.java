package com.microv;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class TextIndext {
	public static void main(String[] args){
		try {
			analyzeToken("hello world, how are you doing baby zyx@examplie.com 123 ?");
			System.out.println("\n==Finished==");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void indexString(String doc) throws IOException{
		String indexPath = "index";
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_2, analyzer);
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		Directory dir = FSDirectory.open(new File(indexPath));
		IndexWriter writer = new IndexWriter(dir, iwc);
		indexDocs(writer);
		writer.close();
		System.out.println("indexed");	
	}
	
	private static void indexDocs(IndexWriter writer) throws IOException{
		Document doc = new Document();
		Field pathField = new StringField("path", "/home/vinh/test.txt", Field.Store.YES);
		doc.add(pathField);
		writer.addDocument(doc);
	}
	
	private static void analyzeToken(String doc) throws IOException{
		Analyzer analyzer = new StandardAnalyzer();
		TokenStream tokenStream = analyzer.tokenStream("Content", new StringReader(doc));
		CharTermAttribute term = tokenStream.addAttribute(CharTermAttribute.class);
		TypeAttribute type = tokenStream.addAttribute(TypeAttribute.class);
		tokenStream.reset();
		while(tokenStream.incrementToken()){
			System.out.print("[" + term.toString() + "]" + "(" + type.type() +")");
		}
		analyzer.close();
	}
}