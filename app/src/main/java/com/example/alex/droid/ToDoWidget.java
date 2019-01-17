package com.example.alex.droid;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link ToDoWidgetConfigureActivity ToDoWidgetConfigureActivity}
 */
public class ToDoWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        DBTache dbt;
        dbt = new DBTache(context);
        dbt.open();
        String widgetText = ToDoWidgetConfigureActivity.loadTitlePref(context, appWidgetId) ;
        try {
            ArrayList<String> values = dbt.getAllNomsTacheDuJour();
            if(!values.isEmpty())
                widgetText +=values.toString();
            else
                widgetText+=" pas de taches pour aujourd'hui ";
        }catch (Exception e){
            //widgetText +=e.toString();
            Toast.makeText(context,"CATCH",Toast.LENGTH_SHORT).show();
        }
        //+ "\n + "+values.toString();
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.to_do_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            ToDoWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

