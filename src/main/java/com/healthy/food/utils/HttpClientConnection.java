package com.healthy.food.utils;

import com.google.gson.Gson;
import com.healthy.food.entities.Ingredient;
import com.healthy.food.entities.Meal;
import com.healthy.food.entities.Measure;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HttpClientConnection {
    private static final HttpClient client = HttpClient.newBuilder().build();

    public static <T> T getModel(Class<T> tClass, String API, String endpoint) {
        final var randomUriApi = API + endpoint;
        final var request =
                HttpRequest.newBuilder(URI.create(randomUriApi))
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();

        T obj = null;
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonObj = response.body();
            if (tClass.getName().contains("Meal")) {
                jsonObj = jsonObj.substring(jsonObj.indexOf("[{") + 1, jsonObj.lastIndexOf("}]") + 1);
            }
            obj = new Gson().fromJson(jsonObj, tClass);
            if (obj instanceof Meal) {
                List<Ingredient> ingredients = new ArrayList<>();
                List<Measure> measures = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(jsonObj);
                for (int i = 0; i < 20; i++) {
                    Random random = new Random();
                    if (jsonObject.get("strIngredient" + (i + 1)) != JSONObject.NULL) {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setId(random.nextLong());
                        ingredient.setName((String) jsonObject.get("strIngredient" + (i + 1)));
                        ingredients.add(ingredient);
                    }
                    if (jsonObject.get("strMeasure" + (i + 1)) != JSONObject.NULL) {
                        Measure measure = new Measure();
                        measure.setId(random.nextLong());
                        measure.setValue((String) jsonObject.get("strMeasure" + (i + 1)));
                        measures.add(measure);
                    }
                }
                ((Meal) obj).setMeasures(measures);
                ((Meal) obj).setIngredients(ingredients);
            }
        } catch (IOException | InterruptedException e) {
            // TODO: handle exceptions
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return obj;
    }
}
