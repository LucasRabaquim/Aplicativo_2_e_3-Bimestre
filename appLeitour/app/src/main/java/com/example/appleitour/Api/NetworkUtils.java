package com.example.appleitour.Api;

import android.net.Uri;
import android.util.Log;

import com.example.appleitour.Model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String API_URL = "https://openlibrary.org/search.json";
    private static final String QUERY_PARAM = "title";
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

    private static List<Book> jsonFormatter(String jsonResponse) {
        List<Book> bookList = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(jsonResponse);

            Log.d("JSON", jsonResponse);

            JSONArray docs = json.getJSONArray("docs");

            int dataLen = 10;

            if(docs.length() < dataLen) {
                dataLen = docs.length();
            }

            for (int i = 0; i < dataLen; i++) {
                JSONObject currentBook = docs.getJSONObject(i);
                String bookName = currentBook.getString("title");

                String bookKey = currentBook.getString("key").replace("/works/","");

                JSONArray isbnArray = currentBook.getJSONArray("isbn");
                int bookIsbn = 0;
                if (isbnArray.length() > 0) {
                    bookIsbn = isbnArray.getInt(0);
                }

                JSONArray authorArray = currentBook.getJSONArray("author_name");
                String bookAuthor = "";
                if (authorArray.length() > 0) {
                    bookAuthor = authorArray.getString(0);
                }


                int bookPages = 0;
                try {
                JSONArray pagesArray = currentBook.getJSONArray("number_of_pages_median");
                if (pagesArray.length() > 0)
                        bookPages = Integer.parseInt(pagesArray.getString(0));
                }catch (Exception e){
                    bookPages = 0;
                }

                int bookEdition = 0;
                try {
                    JSONArray editionArray = currentBook.getJSONArray("edition_count");
                    if (editionArray.length() > 0)
                        bookEdition = Integer.parseInt(editionArray.getString(0));
                    }catch (Exception e){
                    bookEdition = 0;
                }

                JSONArray publisherArray = currentBook.getJSONArray("publisher");
                String bookEditora = "";
                if (publisherArray.length() > 0) {
                    bookEditora = publisherArray.getString(0);
                }

                JSONArray sinopseArray = currentBook.optJSONArray("language");
                String bookSinopse = "";
                if (sinopseArray != null && sinopseArray.length() > 0) {
                    bookSinopse = sinopseArray.getString(0);
                } else {
                    bookSinopse = "-";
                }
                JSONArray languageArray = currentBook.optJSONArray("language");
                List<String> bookLangList = new ArrayList<>();
                if (languageArray != null && languageArray.length() > 0) {
                    for (int j = 0; j < languageArray.length(); j++) {
                        String language = languageArray.getString(j);
                        bookLangList.add(language);
                    }
                } else {
                    bookLangList.add("-");
                }

                String bookLang;
                if (!bookLangList.isEmpty()) {
                    bookLang = String.join(", ", bookLangList);
                } else {
                    bookLang = "-";
                }



                JSONArray publishDateArray = currentBook.getJSONArray("publish_year");
                String bookDate = "";
                if (publishDateArray.length() > 0) {
                    bookDate = publishDateArray.getString(0);
                }

                Log.v("Data", "Number" + (i + 1));

                Byte bookCover = (byte) 123;
                Book Book = new Book(bookKey,bookIsbn, bookName, bookAuthor, bookEditora, bookPages, bookEdition, bookCover, bookSinopse, bookLang, bookDate);
                bookList.add(Book);
            }
        } catch (JSONException ex) {
            Log.v("Network", "Cannot read JSON", ex);
        }
        return bookList;
    }

    public static List<Book> getDataFromApi(String query) throws IOException {
        URL apiURL = buildUrl(query);
        String jsonResponse = getBookFromHttpUrl(apiURL);
        return jsonFormatter(jsonResponse);
    }
}