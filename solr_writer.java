//Create an instance of the Solr server
String SOLR_URL = "http://localhost:8983/solr"
SolrServer server = new HttpSolrServer(SOLR_URL);
//Create collection of documents to add to Solr server
SolrInputDocument doc1 = new SolrInputDocument();
document.addField("id",1);
document.addField("desc", "description text for doc 1");
SolrInputDocument doc2 = new SolrInputDocument();
document.addField("id",2);
document.addField("desc", "description text for doc 2");
Collection<SolrInputDocument> docs = new
ArrayList<SolrInputDocument>();
docs.add(doc1);
docs.add(doc2);
//Add the collection of documents to the Solr server and commit.
server.add(docs);
server.commit();
// Add all files from the <solr_directory>/dist folder to the classpath for compiling and running the HttpSolrServer program
// Reference: http://lucene.apache.org/solr/4_6_0/solr-solrj/org/apache/solr/client/solrj/impl/HttpSolrServer.html
