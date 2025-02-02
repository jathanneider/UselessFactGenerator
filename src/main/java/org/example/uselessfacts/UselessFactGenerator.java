package org.example.uselessfacts;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class UselessFactGenerator {
    private static final String API_URL = "https://uselessfacts.jsph.pl/random.json?language=en";

    public static void main(String[] args) {
        String fact = fetchRandomFact();
        if (fact != null) {
            System.out.println("\nRandom Useless Fact:");
            System.out.println(fact);
        } else {
            System.out.println("Failed to retrieve a fact.");
        }
    }

    private static String fetchRandomFact() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(API_URL).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                JsonObject jsonObject = JsonParser.parseString(response.body().string()).getAsJsonObject();
                return jsonObject.get("text").getAsString();
            }
        } catch (IOException e) {
            System.err.println("Error fetching fact: " + e.getMessage());
        }
        return null;
    }
}
