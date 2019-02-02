package mongoDB;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Projections.excludeId;

public class ListData {

	public String getData(String nomTest) {
		String json = "{\"name\":\""+ nomTest+"\",\"question\":[";//para darle formato al json
		Mongo mongodb = new Mongo();
		mongodb.connectDatabase();//conectarse a la base

		MongoCollection<Document> collection = mongodb.getMongodb().getCollection(nomTest);
		//se guarda en la coleccion todo lo de la bd

		FindIterable<Document> it = collection.find().projection(excludeId());
		ArrayList<Document> docs = new ArrayList<Document>();

		it.into(docs);
		for (Document doc : docs) {
			json = json + doc.toJson() + ",";
			//json = doc.toJson() + ",";
		}
		json = json.substring(0, json.length() - 1);
		json = json + "]}";
		return json;
	}
}
