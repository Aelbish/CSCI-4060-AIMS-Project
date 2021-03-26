package csci4060.project.newaimsapp;

import org.json.JSONException;
import org.json.JSONObject;

public class TripJSONParser {
    private String input;
    private final JSONObject reader;

    public TripJSONParser(String input) throws JSONException {
        this.input = input;
        this.reader = new JSONObject(input);
    }

    public void setInput(String input) {
        this.input = input;
    }
}
