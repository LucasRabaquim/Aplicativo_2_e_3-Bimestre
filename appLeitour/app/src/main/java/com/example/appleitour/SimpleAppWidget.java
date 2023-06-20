package com.example.appleitour;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import com.example.appleitour.Controller.SavedActivity;
import com.example.appleitour.Database.DatabaseHelper;
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

        String lastInsertedCoverUrl = getLastInsertedCoverUrl(context);

        views.setTextViewText(R.id.tvWidget, lastInsertedCoverUrl);

        // manda o usuário pra savedActivity
        Intent intent = new Intent(context, SavedActivity.class);
        context.startActivity(intent);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        views.setOnClickPendingIntent(R.id.tvWidget, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private String getLastInsertedCoverUrl(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SharedPreferences settings = context.getSharedPreferences("com.example.appleitour", 0);
        int userId = settings.getInt("UserId", 0);
        String bookCover = databaseHelper.selectRandomCover(userId);
        return Objects.requireNonNullElse(bookCover, "");
    }
}
