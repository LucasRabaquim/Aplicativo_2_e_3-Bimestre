package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String API_URL = "https://openlibrary.org/";
    private static final String QUERY_PARAM = "q";
    private static final String MODE = "mode";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";

    static String getBookByName(String queryString, int offset) throws IOException {
          /*  URI builtURI = URI.parse(API_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString).appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(QUERY_PARAM, queryString).appendQueryParameter(MODE, "works")
                    .appendQueryParameter(QUERY_PARAM, queryString).appendQueryParameter(LIMIT, "10")
                    .appendQueryParameter(QUERY_PARAM, queryString).appendQueryParameter(OFFSET, offset)
                    .build();*/

        //final String API_RESPONSE = apiResponse(builtURI,0);

        return "API_RESPONSE";
    }

    static String apiResponse(java.net.URI builtURI){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String responseAPI = null;
        try {
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            responseAPI = builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseAPI;
    }
}