package ch.coop.supercardapp;

/* loaded from: classes3.dex */
public class SupercardWidgetBig extends SupercardWidget {
    @Override // ch.coop.supercardapp.SupercardWidget
    public void log(String str) {
    }

    @Override // ch.coop.supercardapp.SupercardWidget
    public int getLayoutUsed(Boolean bool) {
        log("getLayoutUsed() returns datamatrix(_loggedout)_wide");
        return bool.booleanValue() ? R.layout.widget_datamatrix_loggedin_wide : R.layout.widget_datamatrix_loggedout_wide;
    }
}
