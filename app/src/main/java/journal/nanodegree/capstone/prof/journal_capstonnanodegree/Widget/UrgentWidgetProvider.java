package journal.nanodegree.capstone.prof.journal_capstonnanodegree.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import journal.nanodegree.capstone.prof.journal_capstonnanodegree.Activities.HomeActivity;
import journal.nanodegree.capstone.prof.journal_capstonnanodegree.R;
import journal.nanodegree.capstone.prof.journal_capstonnanodegree.helpers.Services.UrgentWidgetService;

/**
 * Created by Prof-Mohamed Atef on 1/27/2019.
 */

public class UrgentWidgetProvider extends AppWidgetProvider {
    public static void updateAppWidget(Context context, String widgetText , AppWidgetManager appWidgetManager,
                                       int []appWidgetId) {
        Intent intent=new Intent(context, HomeActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(context,0,intent,0);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.urgent_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        views.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        UrgentWidgetService.startActionFillWidget(context);
    }
}
