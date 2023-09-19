package com.example.appleitour.Api.NetWorkUtils;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class NetworkUtils {
    private static final String API_URL = "http://192.168.15.31:80/api/";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "UPDATE";
    public static final String DELETE = "DELETE";
    public static final String AUTO_LOGIN = "AUTO_LOGIN";
    public static String buildUrl(String path) {
        String buildURI = API_URL + path;
        return buildURI;
    }

    public String ObjectToString(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static String ReadResponse(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }

    public static String HttpPost (String stringUrl, String token,String jsonObject) throws URISyntaxException, UnsupportedEncodingException {
        URI url = new URI(buildUrl(stringUrl));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            StringEntity se = new StringEntity(jsonObject);
            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Token", token);
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpclient.execute(httpPost);
            InputStream inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null)
                return ReadResponse(inputStream);
            else
                return "";
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String HttpAutoLogin (String stringUrl, String token) throws URISyntaxException, UnsupportedEncodingException {
        URI url = new URI(buildUrl(stringUrl));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Token", token);
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpclient.execute(httpPost);
            InputStream inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null){
                Log.d("Valor", "HttpGet: "+ ReadResponse(inputStream));
                return ReadResponse(inputStream);
            }
            else
                return "";
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String HttpGet (String stringUrl, String token) throws URISyntaxException {
        URI url = new URI(buildUrl(stringUrl));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Accept", "application/json");
            if(token != null)
                httpGet.setHeader("Token", token);
            httpGet.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpclient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null)
                return ReadResponse(inputStream);
            else
                return "";
        } catch (ClientProtocolException e) {
           // Toast.makeText(activity, "Verifique o sinal de internet", Toast.LENGTH_SHORT).show();
            return e.toString();
        } catch (IOException e) {
           // Toast.makeText(activity, "Tente novamente", Toast.LENGTH_SHORT).show();
            return e.toString();
        }
    }

    public static String HttpPut (String stringUrl, String token,String jsonObject) throws URISyntaxException, UnsupportedEncodingException {
        URI url = new URI(buildUrl(stringUrl));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPut httpPut = new HttpPut(url);
            StringEntity se = new StringEntity(jsonObject);
            httpPut.setEntity(se);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Token", token);
            httpPut.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpclient.execute(httpPut);
            InputStream inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null)
                return ReadResponse(inputStream);
            else
                return "";
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String HttpDelete (String stringUrl, String token) throws URISyntaxException {
        URI url = new URI(buildUrl(stringUrl));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpDelete httpDelete = new HttpDelete(url);
            httpDelete.setHeader("Accept", "application/json");
            if(token != null)
                httpDelete.setHeader("Token", token);
            httpDelete.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpclient.execute(httpDelete);
            InputStream inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null)
                return ReadResponse(inputStream);
            else
                return "";
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}