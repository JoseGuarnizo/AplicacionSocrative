package mongoDB;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.bson.BsonDocument;
import org.bson.Document;

import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;

public class InsertData {
	public void insert(String jo) {
		/*Mongo mongo = new Mongo();
		mongo.connectDatabase();
		BsonDocument document = BsonDocument.parse(jo);
		mongo.getMongodb().getCollection("test").insertOne(message);*/
		
		MongoClient mongo = new MongoClient();
		DB db = mongo.getDB("cuestonario");
		
		
		DBCollection collection = db.getCollection("test");
		collection.drop();
		DBObject doc;
		//jo = jo.replace("[", "");
		//jo = jo.replace("]", "");
		
		ArrayList<String> preguntas = sacarObjeto(jo);
		for (int i = 0; i < preguntas.size(); i++) {
			doc = (DBObject) JSON.parse(preguntas.get(i));
			collection.insert(doc);
		}
		
		/*DBObject doc = (DBObject) JSON.parse(jo);
		
		collection.insert(doc);
		System.out.println("guardado");*/
		
		/*StringTokenizer tok = new StringTokenizer(jo, "},{");
		while (tok.hasMoreElements()) {
			System.out.println(tok.nextToken());
			
		}*/
		
		
	}
	
	public ArrayList<String> sacarObjeto(String jo) {
		ArrayList<String> preguntas = new ArrayList<String>();
		String temPre = "";
		for (int i = 1; i < jo.length(); i++) {	
			if (jo.charAt(i) == ']') {
				preguntas.add(temPre);
			}else {
				if (jo.charAt(i) == '}' && jo.charAt(i+1) == ',' && jo.charAt(i+2) == '{') {
					preguntas.add(temPre + jo.charAt(i));
					temPre = "";
					i = (i + 1);
				}else {
					temPre+= jo.charAt(i);
				}
			}			
		}
		for (int i = 0; i < preguntas.size(); i++) {
			System.out.println(preguntas.get(i));
		}
		return preguntas;
	}

	/*public static void main(String args[]) {
		//InsertData is = new InsertData();
		//is.insert();
		System.out.println(getTest());
	}*/
	
	/*public static String getTest() {
		return "{\n" + "  \"name\" : \"My first test\",\n" + "  \"questions\" : [\n" + "    {\n" + "      \"id\" : 1,\n"
				+ "      \"text\": \"¿Cuál es la capital de Ecuador?\",\n" + "      \"options\" :[\n" + "        {\n"
				+ "          \"id\":1,\n" + "          \"text\" : \"Loja\"\n" + "        },\n" + "        {\n"
				+ "          \"id\":2,\n" + "          \"text\" : \"Quito\"\n" + "        },\n" + "        {\n"
				+ "          \"id\":3,\n" + "          \"text\" : \"Nueva Loja\"\n" + "         }\n" + "      ]\n"
				+ "    },\n" + "    {\n" + "      \"id\" : 2,\n" + "      \"text\": \"¿Cuál es la capital de Perú?\",\n"
				+ "      \"options\" :[\n" + "        {\n" + "          \"id\":1,\n" + "          \"text\" : \"Lima\"\n"
				+ "        },\n" + "        {\n" + "          \"id\":2,\n" + "          \"text\" : \"Chiclayo\"\n"
				+ "        },\n" + "        {\n" + "          \"id\":3,\n" + "          \"text\" : \"El Cusco\"\n"
				+ "         }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}";
	}*/
}
