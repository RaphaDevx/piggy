package com.capacitorjs.plugins.screenorientation;

import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes3.dex */
public class ScreenOrientation {
    private AppCompatActivity activity;
    private int configOrientation;

    public ScreenOrientation(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
    }

    public String getCurrentOrientationType() {
        if (Build.VERSION.SDK_INT >= 30) {
            return fromRotationToOrientationType(this.activity.getDisplay().getRotation());
        }
        return fromRotationToOrientationType(getLegacyDisplayRotation());
    }

    public void lock(String str) {
        this.activity.setRequestedOrientation(fromOrientationTypeToEnum(str));
    }

    public void unlock() {
        this.activity.setRequestedOrientation(-1);
    }

    public boolean hasOrientationChanged(int i) {
        if (i == this.configOrientation) {
            return false;
        }
        this.configOrientation = i;
        return true;
    }

    private String fromRotationToOrientationType(int i) {
        if (i == 1) {
            return "landscape-primary";
        }
        if (i == 2) {
            return "portrait-secondary";
        }
        if (i == 3) {
            return "landscape-secondary";
        }
        return "portrait-primary";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int fromOrientationTypeToEnum(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -147105566:
                if (str.equals("landscape-secondary")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 96748:
                if (str.equals("any")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1430647483:
                if (str.equals("landscape")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1862465776:
                if (str.equals("landscape-primary")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2012187074:
                if (str.equals("portrait-secondary")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return 8;
            case 1:
                return -1;
            case 2:
            case 3:
                return 0;
            case 4:
                return 9;
            default:
                return 1;
        }
    }

    private int getLegacyDisplayRotation() {
        return this.activity.getWindowManager().getDefaultDisplay().getRotation();
    }
}
