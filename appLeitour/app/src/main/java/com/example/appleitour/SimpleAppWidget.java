package com.example.appleitour;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.appleitour.Controller.BookActivity;
import com.example.appleitour.Controller.SavedActivity;
import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Book;
import com.example.appleitour.R;
import com.squareup.picasso.Picasso;

public class SimpleAppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.simple_app_widget);

        String lastInsertedCoverUrl = getLastInsertedCoverUrl(context);

        views.setTextViewText(R.id.tvWidget, lastInsertedCoverUrl);


        //manda pra saved
        Intent intent = new Intent(context, SavedActivity.class);
        context.startActivity(intent);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        views.setOnClickPendingIntent(R.id.tvWidget, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private String getLastInsertedCoverUrl(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        int lastInsertedId = databaseHelper.selectLastInsert();
        Book book = databaseHelper.selectBookById(lastInsertedId);
        if (book != null) {
            return book.getCover();
        }
        return "";
    }
}
