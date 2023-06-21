package com.example.appleitour;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;

import com.example.appleitour.Controller.BookActivity;
import com.example.appleitour.Controller.SavedActivity;
import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Book;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class SimpleAppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.simple_app_widget);

        Book randomBook = getRandomBook(context);
        if(randomBook != null){
            views.setViewVisibility(R.id.widgetNoBook, View.GONE);
            views.setViewVisibility(R.id.bookName, View.VISIBLE);
            views.setViewVisibility(R.id.bookAuthor, View.VISIBLE);
            views.setViewVisibility(R.id.book_publisher, View.VISIBLE);
            views.setViewVisibility(R.id.bookDate, View.VISIBLE);
            views.setTextViewText(R.id.bookName, randomBook.getName());
            views.setTextViewText(R.id.bookAuthor,"Autor: " + randomBook.getAuthor());
            views.setTextViewText(R.id.book_publisher, "Editora: " + randomBook.getPublisher());
            views.setTextViewText(R.id.bookDate, "Ano: " + randomBook.getYear());
        }

        // manda o usuário pra savedActivity
        Intent intent = new Intent(context, SavedActivity.class);
        //context.startActivity(intent);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.layoutWidget, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private Book getRandomBook(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SharedPreferences settings = context.getSharedPreferences("com.example.appleitour", 0);
        int userId = settings.getInt("LastUser", 0);
        Book book = databaseHelper.selectRandomBook(userId);
        return book;
    }
}
