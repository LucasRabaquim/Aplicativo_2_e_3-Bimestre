package com.example.appleitour.Api;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

@Deprecated
public class OpenLibraryUtils {
    private static final String API_URL = "https://openlibrary.org/search.json";
    private static final String QUERY_PARAM = "q";
    private static final String MODE = "mode";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";

    private static URL buildUrl(String queryString) {
        Uri buildURI = Uri.parse(API_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryString)
                .appendQueryParameter(LIMIT, "10")
                .build();
        URL url = null;
        try {
            url = new URL(buildURI.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    private static String getBookFromHttpUrl (URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }
        finally {
            urlConnection.disconnect();
        }
    }

}