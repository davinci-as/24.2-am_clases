package ar.edu.davinci.a242_clase_03;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetRicks extends AsyncTask<String, Integer, String> {


    private final TextView textField;

    GetRicks(TextView secondText) {
        this.textField = secondText;
    }
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String response = run(strings[0]);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject info = jsonObject.getJSONObject("info");
            Integer count = info.getInt("count");
            Log.i("api request", String.valueOf(count));

            JSONArray results = jsonObject.getJSONArray("results");
            Integer length = results.length();
            JSONObject alienRick = results.getJSONObject(2);
            String alienRickName = alienRick.getString("name");
            String alienRickSpecie = alienRick.getString("species");

            String textString = String.format("%s (%s)", alienRickName, alienRickSpecie);
            Log.i("api request", String.valueOf(textString));

            this.textField.setText(textString);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
