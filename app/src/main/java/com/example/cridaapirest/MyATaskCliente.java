package com.example.cridaapirest;

import static com.example.cridaapirest.MainActivity.path;
import static com.example.cridaapirest.MainActivity.result;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class MyATaskCliente extends AsyncTask<String, Void, String> {
    protected String doInBackground(String... values) {
        String pp = path.getText().toString();
        StringBuffer sb = new StringBuffer();
        Date date = Calendar.getInstance().getTime();

        try {
            URL url = new URL(pp);
            HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();

            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginArray();
                while(jsonReader.hasNext()) {
                    jsonReader.beginObject();
                    while(jsonReader.hasNext()) {
                        String key = jsonReader.nextName();
                        if (key.equals("id")) {
                            sb.append("{id:" + jsonReader.nextString() + ", ");
                        } else if (key.equals("name")) {
                            sb.append("name:" + jsonReader.nextString() + ", ");
                        } else if (key.equals("comentario")) {
                            sb.append("comentario: "+jsonReader.nextString() + " fecha: "+ date + "}\n");
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                }
            } else {
                result.setText("No ha funcionado");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String value) {
        result.setText(value);
    }
}
