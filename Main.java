package org.example;
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        System.out.println("Digite um termo de pesquisa geral (OBS, para mais de uma palavra separe por +. Ex: Mulheres+empilhadas)");
        Scanner scanner = new Scanner(System.in);
        String searchTerm = scanner.nextLine();
        System.out.println(searchTerm);
        String response = apiRequest(searchTerm, 0,0);
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um ISBN");
        String search = scanner.nextLine();

        final String ISBN_10_13_PATTERN = "((978|979){0,1}\\d{9})(\\d|x)";
        final Pattern ISBN = Pattern.compile(ISBN_10_13_PATTERN,Pattern.CASE_INSENSITIVE);
        Matcher matcher = ISBN.matcher(search);

        if(matcher.find())
            if(verifyISBN(search,search.length()))
                System.out.println(search + " É valido");
            else
                System.out.println(search + " Não é valido");
        else
            System.out.println("Erro de entrada!");*/
         System.out.println(response);
    }
    public static boolean verifyISBN(String isbn, int length){
        int sum = 0;
        final int CHECK_NUM = (isbn.charAt(length-1) == 'x') ? 10 : isbn.charAt(length-1) - '0';
        if(length == 10)
            for (int i = 0; i < 9; i++)
                sum += (isbn.charAt(i) - '0') * (10 - i);
        else
            for (int i = 0; i < 12; i++)
                sum += (i%2 == 1) ? (isbn.charAt(i) - '0') : (isbn.charAt(i) - '0')*3;
        return (length == 10 ? 11 - sum % 11 : sum % 10) == CHECK_NUM;
    }

      public static String apiRequest(String query, int target, int offset) throws URISyntaxException, IOException {
          URI uri = null;
          switch (target) {
              case 0 ->
                      uri = new URI("https://openlibrary.org/search.json?q=" + query + "&mode=everything&limit=20&offset=" + offset);
              case 1 -> uri = new URI("https://openlibrary.org/authors/" + query + "json?limit=20&offset=" + offset);
              case 2 ->
                      uri = new URI("https://openlibrary.org/search.json?q=" + query + "&mode=works&limit=20&offset=" + offset);
              case 3 ->
                      uri = new URI("https://openlibrary.org/isbn/" + query + ".json?mode=everything&limit=20&offset=" + offset);
              default -> {
                  return "Erro"; // Msg de erro ?
              }
          }
          return NetworkUtils.apiResponse(uri);
    }

}
