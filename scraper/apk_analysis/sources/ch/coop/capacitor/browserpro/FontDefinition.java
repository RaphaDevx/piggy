package ch.coop.capacitor.browserpro;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import com.getcapacitor.JSObject;
import io.sentry.rrweb.RRWebVideoEvent;
import org.json.JSONException;

/* loaded from: classes3.dex */
public class FontDefinition {
    String path = null;
    float size = -1.0f;

    public static FontDefinition from(JSObject jSObject) {
        float f;
        if (jSObject == null) {
            return null;
        }
        String string = jSObject.getString("name");
        try {
            f = (float) jSObject.getDouble(RRWebVideoEvent.JsonKeys.SIZE);
        } catch (JSONException unused) {
            f = -1.0f;
        }
        if (string == null || f < 0.0f) {
            return null;
        }
        FontDefinition fontDefinition = new FontDefinition();
        fontDefinition.path = "fonts/" + string + ".ttf";
        fontDefinition.size = f;
        return fontDefinition;
    }

    public void apply(Context context, TextView textView) {
        if (this.path != null) {
            textView.setTypeface(Typeface.createFromAsset(context.getAssets(), this.path));
        }
        float f = this.size;
        if (f >= 0.0f) {
            textView.setTextSize(f);
        }
    }
}
