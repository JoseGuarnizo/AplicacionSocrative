package handler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConvertJSON {
	public JsonObject convert(String message) {
		JsonObject messageObject = new JsonParser().parse(message).getAsJsonObject();
		String nombre = messageObject.get("pregunta").getAsString();
		System.out.println(nombre);
		return messageObject;
	}
}
