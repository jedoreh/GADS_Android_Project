package com.example.jothamgadsleaderboardproject;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil(){}

    public static final String BASE_API_URL =
            "https://gadsapi.herokuapp.com/api/";
    public static final String QUERY_PARAMETER_KEY = "";

    public static URL buildUrl(String title) {
        //String fullUrl = BASE_API_URL + title;
        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendPath(title)
                .build();
        try {
            url = new URL(uri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();


        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");

            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();
            } else {
                return null;
            }
        }
        catch (Exception e) {
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }

    }

    public static ArrayList<Learner> getLearners(String json) {

        final String NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY = "country";
        final String BADGE_URL = "badgeUrl";
        final String ITEMS = "items";


        ArrayList<Learner> learners = new ArrayList<Learner>();
        try {
            JSONArray jsonLearners = new JSONArray(json);
            //JSONArray arrayLearners = jsonLearners.getJSONArray(ITEMS);
            //int numberofLearners = arrayLearners.length();
            jsonLearners = sortJsonArray(jsonLearners, HOURS);
            for (int i = 0; i < 20; i++) {
                JSONObject learnerJSON = jsonLearners.getJSONObject(i);


                Learner learner = new Learner(

                        learnerJSON.getString(NAME),
                        learnerJSON.getDouble(HOURS),
                        learnerJSON.getString(COUNTRY),
                        learnerJSON.getString(BADGE_URL)
                );
                learners.add(learner);
            }
        }
        catch (JSONException e) {

        }

        return learners;
    }

    public static ArrayList<Skill> getSkill(String json) {

        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY = "country";
        final String BADGE_URL = "badgeUrl";



        ArrayList<Skill> skills = new ArrayList<Skill>();
        try {
            JSONArray jsonSkills = new JSONArray(json);
            //JSONArray arraySkills = jsonSkills.getJSONArray(ITEMS);
            //int numberofLearners = arrayLearners.length();
            jsonSkills = sortJsonArray(jsonSkills, SCORE);
            for (int i = 0; i < 20; i++) {
                JSONObject SkillJSON = jsonSkills.getJSONObject(i);


                Skill skill = new Skill(

                        SkillJSON.getString(NAME),
                        SkillJSON.getDouble(SCORE),
                        SkillJSON.getString(COUNTRY),
                        SkillJSON.getString(BADGE_URL)
                );
                skills.add(skill);
                Log.d("Debug", SkillJSON.getString(NAME));
            }
        }
        catch (JSONException e) {
            Log.d("Error", e.getMessage());
        }

        return skills;
    }

    public static JSONArray sortJsonArray(JSONArray array, final String sortBy) {
        try {
            List<JSONObject> jsons = new ArrayList<JSONObject>();
            for (int i = 0; i < array.length(); i++) {
                jsons.add(array.getJSONObject(i));
            }
            Collections.sort(jsons, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject lhs, JSONObject rhs) {
                    try {
                        String lid = lhs.getString(sortBy);
                        String rid = rhs.getString(sortBy);
                        // Here you could parse string id to integer and then compare.
                        String temp = "";
                        Double intLID = Double.parseDouble(lid);
                        Double intRID = Double.parseDouble(rid);

                        int test = intLID.compareTo(intRID);
                        if (test < 0) {
                            temp = rid;
                            rid = lid;
                            lid = temp;
                        }


                    }
                    catch (Exception e) {
                        return -1;
                    }
                    return 1;
                }
            });
            return new JSONArray(jsons);
        }
        catch (JSONException e) {
            return null;
        }

    }
}
