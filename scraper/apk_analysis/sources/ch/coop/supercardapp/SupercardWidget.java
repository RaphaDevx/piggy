package ch.coop.supercardapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Base64;
import android.widget.RemoteViews;
import androidx.core.content.res.ResourcesCompat;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercard;
import ch.coop.capacitor.extensionconnector.widget.WidgetType;
import ch.coop.supercardapp.core.helpers.FormatHelper;
import ch.coop.supercardapp.widget.BaseWidget;
import ch.coop.supercardapp.widget.WidgetHelper;
import com.salesforce.marketingcloud.push.g;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

/* loaded from: classes3.dex */
public class SupercardWidget extends BaseWidget {
    static final String TAG = "SupercardWidget";

    public void log(String str) {
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        int i;
        AppContainer appContainer = AppContainer.getInstance(context);
        boolean isLoggedIn = appContainer.getAuth().isLoggedIn();
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), getLayoutUsed(Boolean.valueOf(isLoggedIn)));
        new WidgetHelper(context).updateLink(remoteViews, context.getString(R.string.widget_supercard_deeplink_path), WidgetType.SUPERCARD);
        setWidgetLayoutLocalizations(context, remoteViews);
        Optional<LoyaltySupercard> supercard = appContainer.getSupercardState().getSupercard();
        if (isLoggedIn && supercard.isPresent()) {
            LoyaltySupercard loyaltySupercard = supercard.get();
            Optional<String> datamatrixImage = appContainer.getSupercardState().getDatamatrixImage();
            if (datamatrixImage.isPresent()) {
                new ByteArrayOutputStream().toByteArray();
                byte[] decode = Base64.decode(datamatrixImage.get(), 0);
                remoteViews.setImageViewBitmap(R.id.dataMatrixImage, BitmapFactory.decodeByteArray(decode, 0, decode.length));
            }
            if (appContainer.getDataUpdateState().getNeedsReauthorization().booleanValue()) {
                remoteViews.setViewVisibility(R.id.superPointsHeader, 8);
                remoteViews.setViewVisibility(R.id.superPointsImage, 8);
                remoteViews.setViewVisibility(R.id.superPointsError, 0);
            } else {
                remoteViews.setViewVisibility(R.id.superPointsHeader, 0);
                remoteViews.setViewVisibility(R.id.superPointsImage, 0);
                remoteViews.setViewVisibility(R.id.superPointsError, 8);
                Bitmap createBitmap = Bitmap.createBitmap(200, 46, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                Paint paint = new Paint();
                Typeface font = ResourcesCompat.getFont(context, R.font.coop_bold);
                paint.setAntiAlias(false);
                paint.setSubpixelText(true);
                paint.setTypeface(font);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(-1);
                paint.setTextSize(46.0f);
                if (remoteViews.getLayoutId() == R.layout.widget_datamatrix_loggedin_wide) {
                    paint.setTextAlign(Paint.Align.LEFT);
                    i = 0;
                } else {
                    paint.setTextAlign(Paint.Align.CENTER);
                    i = 100;
                }
                canvas.drawText(FormatHelper.formatPoints(context, loyaltySupercard.getPoints().intValue()), i, 42.0f, paint);
                remoteViews.setImageViewBitmap(R.id.superPointsImage, createBitmap);
            }
        }
        for (int i2 : iArr) {
            log("onUpdate() updates appWidgetId: " + i2);
            appWidgetManager.updateAppWidget(i2, remoteViews);
        }
    }

    protected String getAppLanguage(Context context) {
        return AppContainer.getInstance(context).getAppState().getAppLanguage();
    }

    protected void setWidgetLayoutLocalizations(Context context, RemoteViews remoteViews) {
        int i;
        int i2;
        int i3;
        log("setWidgetLayoutLocalizations() for lang: " + getAppLanguage(context));
        String appLanguage = getAppLanguage(context);
        appLanguage.hashCode();
        if (appLanguage.equals("fr")) {
            i = R.drawable.widget_loginmessage_fr;
            i2 = R.drawable.widget_superpunkte_fr;
            i3 = R.drawable.widget_tokenexpired_fr;
        } else if (appLanguage.equals(g.h)) {
            i = R.drawable.widget_loginmessage_it;
            i2 = R.drawable.widget_superpunkte_it;
            i3 = R.drawable.widget_tokenexpired_it;
        } else {
            i = R.drawable.widget_loginmessage_de;
            i2 = R.drawable.widget_superpunkte_de;
            i3 = R.drawable.widget_tokenexpired_de;
        }
        remoteViews.setImageViewResource(R.id.loggedOutTextImage, i);
        remoteViews.setImageViewResource(R.id.superPointsHeader, i2);
        remoteViews.setImageViewResource(R.id.superPointsError, i3);
    }

    public int getLayoutUsed(Boolean bool) {
        log("getLayoutUsed() returns datamatrix(_loggedout)_small");
        return bool.booleanValue() ? R.layout.widget_datamatrix_loggedin_small : R.layout.widget_datamatrix_loggedout_small;
    }
}
