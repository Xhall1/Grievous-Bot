package com.xhall.services.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class SwapiService {

    private final OkHttpClient client;
    private final Gson gson;

    private static final String URL = "https://swapi.dev/api/";

    public SwapiService() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public JsonObject getPlanet(String name) {
        String url = URL + "planets/?search=" + name;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute())
        {
            if (!response.isSuccessful()) return null;

            String jsonData = response.body().string();
            JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);
            JsonArray results = jsonObject.getAsJsonArray("results");

            if (results.size() > 0) return results.get(0).getAsJsonObject();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JsonArray getPlanetsList(int page)
    {
        String url = URL + "planets/?page=" + page;

        Request request = new Request.Builder().url(url).build();

        try(Response response = client.newCall(request).execute())
        {
            if(!response.isSuccessful()) return null;

            String jsonData = response.body().string();
            JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);

            return jsonObject.getAsJsonArray("results");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public JsonObject getPeople(String name)
    {
        String url = URL + "people/?search" + name;

        Request request = new Request.Builder().url(url).build();

        try(Response response = client.newCall(request).execute())
        {
            if(!response.isSuccessful()) return null;

            String jsonData = response.body().string();

            JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);
            JsonArray results = jsonObject.getAsJsonArray("results");

            if(results.size() > 0) return results.get(0).getAsJsonObject();


        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    public JsonArray getPeopleList(int page)
    {
       String url = URL + "people/?page" + page;
       Request request = new Request.Builder().url(url).build();

       try(Response response = client.newCall(request).execute())
       {
           if(!response.isSuccessful()) return null;

           String jsonData = response.body().string();
            JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);

            return jsonObject.getAsJsonArray("results");

       }
       catch(IOException e)
       {
           e.printStackTrace();
           return null;
       }

    }

    public JsonObject getSpecies(String name)
    {
        String url = URL + "species/?search" + name;

        Request request = new Request.Builder().url(url).build();

        try(Response response = client.newCall(request).execute())
        {
            if(!response.isSuccessful()) return null;

            String jsonData = response.body().string();

            JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);
            JsonArray results = jsonObject.getAsJsonArray("results");

            if(results.size() > 0) return results.get(0).getAsJsonObject();


        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    public JsonArray getSpeciesList(int page)
    {
        String url = URL + "species/?search=" + page;
        Request request = new Request.Builder().url(url).build();

        try(Response response = client.newCall(request).execute())
        {
            if(!response.isSuccessful()) return null;

            String jsonData = response.body().string();
            JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);

            return jsonObject.getAsJsonArray("results");

        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
