package ar.edu.davinci.a242_clase_03;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RickAPI {
    private RickApiInfo info;

    public ArrayList<Character> getResults() {
        return results;
    }

    public void setResults(ArrayList<Character> results) {
        this.results = results;
    }

    private ArrayList<Character> results;

    public RickAPI(String string) throws JSONException {
        JSONObject jsonObject = new JSONObject(string);
        JSONObject info = jsonObject.getJSONObject("info");
        Integer count = info.getInt("count");

        JSONArray results = jsonObject.getJSONArray("results");
        Integer length = results.length();
        this.results = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            JSONObject characterObject = results.getJSONObject(i);
            Character character = new Character();
            character.setName(characterObject.getString("name"));
            character.setSpecies(characterObject.getString("species"));
            character.setStatus(characterObject.getString("status"));
            this.results.add(character);
        }
        this.info = new RickApiInfo();
        this.info.setCount(count);

    }

    public RickApiInfo getInfo() {
        return info;
    }

    public void setInfo(RickApiInfo info) {
        this.info = info;
    }
}
