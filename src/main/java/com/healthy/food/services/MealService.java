package com.healthy.food.services;

import com.healthy.food.entities.Ingredient;
import com.healthy.food.entities.Meal;
import com.healthy.food.entities.Measure;
import com.healthy.food.repositories.IngredientRepository;
import com.healthy.food.repositories.MealRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MealService {

    private MealRepository mealRepository;
    private IngredientRepository ingredientRepository;

    private static HttpURLConnection connection;

    public List<Meal> getAllMealsFilteredByIngredientID(Long ingredientID) {
        Ingredient ingredientFound = ingredientRepository.findById(ingredientID).get();
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getIngredients().contains(ingredientFound))
                .collect(Collectors.toList());
    }

    public List<Meal> getAllMealsFilteredByFirstLetterPartialOrFullName(String firstLetter) {
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getName().contains(firstLetter))
                .collect(Collectors.toList());
    }

    public List<Meal> getAllMealsFilteredByCategory(String category) {
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<Meal> getAllMealsFilteredByArea(String area) {
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getArea().equals(area))
                .collect(Collectors.toList());
    }


    //facade endpoint based on a request to the free api www.themealdb.com
    public Meal getRandomMeal() {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("https://www.themealdb.com/api/json/v1/1/random.php");
            connection = (HttpURLConnection) url.openConnection();

            //request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            String responseString = responseContent.toString();
            return parseBody(responseString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    private Meal parseBody(String responseBody) {
        responseBody = (String) responseBody.subSequence(responseBody.indexOf("["), responseBody.lastIndexOf("]") + 1);
        JSONArray jsonArray = new JSONArray(responseBody);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        Meal meal = new Meal();
        if (jsonObject.get("idMeal") != JSONObject.NULL) {
            Long id = Long.parseLong((String) jsonObject.get("idMeal"));
            meal.setId(id);
        }
        if (jsonObject.get("strMeal") != JSONObject.NULL) {
            meal.setName((String) jsonObject.get("strMeal"));
        }
        if (jsonObject.get("strDrinkAlternate") != JSONObject.NULL) {
            meal.setDrinkAlternate((String) jsonObject.get("strDrinkAlternate"));
        }
        if (jsonObject.get("strCategory") != JSONObject.NULL) {
            meal.setCategory((String) jsonObject.get("strCategory"));
        }
        if (jsonObject.get("strArea") != JSONObject.NULL) {
            meal.setArea((String) jsonObject.get("strArea"));
        }
        if (jsonObject.get("strInstructions") != JSONObject.NULL) {
            meal.setInstructions((String) jsonObject.get("strInstructions"));
        }
        if (jsonObject.get("strMealThumb") != JSONObject.NULL) {
            meal.setMealThumb((String) jsonObject.get("strMealThumb"));
        }
        if (jsonObject.get("strTags") != JSONObject.NULL) {
            meal.setTags((String) jsonObject.get("strTags"));
        }
        if (jsonObject.get("strYoutube") != JSONObject.NULL) {
            meal.setUrlYoutube((String) jsonObject.get("strYoutube"));
        }

        List<Ingredient> ingredients = new ArrayList<>();
        List<Measure> measures = new ArrayList<>();
        Pattern pattern = Pattern.compile("strIngredient*");
        Matcher matcher = pattern.matcher(jsonObject.toString());
        int countIngredients = 0;
        while (matcher.find()) {
            countIngredients++;
        }

        for (int i = 0; i < countIngredients; i++) {
            if (jsonObject.get("strIngredient" + (i + 1)) != JSONObject.NULL) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName((String) jsonObject.get("strIngredient" + (i + 1)));
                ingredients.add(ingredient);
            }
            if (jsonObject.get("strMeasure" + (i + 1)) != JSONObject.NULL) {
                Measure measure = new Measure();
                measure.setValue((String) jsonObject.get("strMeasure" + (i + 1)));
                measures.add(measure);
            }
        }
        meal.setMeasures(measures);
        meal.setIngredients(ingredients);
        if (jsonObject.get("strSource") != JSONObject.NULL) {
            meal.setSource((String) jsonObject.get("strSource"));
        }
        if (jsonObject.get("strImageSource") != JSONObject.NULL) {
            meal.setImageSource((String) jsonObject.get("strImageSource"));
        }
        if (jsonObject.get("dateModified") != JSONObject.NULL) {
            meal.setDateModified((String) jsonObject.get("dateModified"));
        }

        return meal;
    }

}
