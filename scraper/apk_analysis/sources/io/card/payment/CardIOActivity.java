package io.card.payment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import io.card.payment.i18n.LocalizedStrings;
import io.card.payment.i18n.StringKey;
import io.card.payment.ui.ActivityHelper;
import io.card.payment.ui.ViewUtil;
import java.io.ByteArrayInputStream;
import java.util.Date;

/* loaded from: classes2.dex */
public final class CardIOActivity extends Activity {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String EXTRA_CAPTURED_CARD_IMAGE = "io.card.payment.capturedCardImage";
    public static final String EXTRA_GUIDE_COLOR = "io.card.payment.guideColor";
    public static final String EXTRA_HIDE_CARDIO_LOGO = "io.card.payment.hideLogo";
    public static final String EXTRA_KEEP_APPLICATION_THEME = "io.card.payment.keepApplicationTheme";
    public static final String EXTRA_LANGUAGE_OR_LOCALE = "io.card.payment.languageOrLocale";
    public static final String EXTRA_NO_CAMERA = "io.card.payment.noCamera";
    public static final String EXTRA_REQUIRE_CARDHOLDER_NAME = "io.card.payment.requireCardholderName";
    public static final String EXTRA_REQUIRE_CVV = "io.card.payment.requireCVV";
    public static final String EXTRA_REQUIRE_EXPIRY = "io.card.payment.requireExpiry";
    public static final String EXTRA_REQUIRE_POSTAL_CODE = "io.card.payment.requirePostalCode";
    public static final String EXTRA_RESTRICT_POSTAL_CODE_TO_NUMERIC_ONLY = "io.card.payment.restrictPostalCodeToNumericOnly";
    public static final String EXTRA_RETURN_CARD_IMAGE = "io.card.payment.returnCardImage";
    public static final String EXTRA_SCAN_EXPIRY = "io.card.payment.scanExpiry";
    public static final String EXTRA_SCAN_INSTRUCTIONS = "io.card.payment.scanInstructions";
    public static final String EXTRA_SCAN_OVERLAY_LAYOUT_ID = "io.card.payment.scanOverlayLayoutId";
    public static final String EXTRA_SCAN_RESULT = "io.card.payment.scanResult";
    public static final String EXTRA_SUPPRESS_CONFIRMATION = "io.card.payment.suppressConfirmation";
    public static final String EXTRA_SUPPRESS_MANUAL_ENTRY = "io.card.payment.suppressManual";
    public static final String EXTRA_SUPPRESS_SCAN = "io.card.payment.suppressScan";
    public static final String EXTRA_UNBLUR_DIGITS = "io.card.payment.unblurDigits";
    public static final String EXTRA_USE_CARDIO_LOGO = "io.card.payment.useCardIOLogo";
    public static final String EXTRA_USE_PAYPAL_ACTIONBAR_ICON = "io.card.payment.intentSenderIsPayPal";
    public static final int RESULT_CARD_INFO = 13274384;
    public static final int RESULT_CONFIRMATION_SUPPRESSED;
    public static final int RESULT_ENTRY_CANCELED;
    public static final int RESULT_SCAN_NOT_AVAILABLE;
    public static final int RESULT_SCAN_SUPPRESSED;
    private static final String TAG = "CardIOActivity";
    private static final long[] VIBRATE_PATTERN;
    private static int lastResult;
    static Bitmap markedCardImage;
    private static int numActivityAllocations;
    private LinearLayout customOverlayLayout;
    private CardScanner mCardScanner;
    private boolean mDetectOnly;
    private CreditCard mDetectedCard;
    private int mFrameOrientation;
    private Rect mGuideFrame;
    private int mLastDegrees;
    private FrameLayout mMainLayout;
    private OverlayView mOverlay;
    Preview mPreview;
    private RelativeLayout mUIBar;
    private boolean manualEntryFallbackOrForced = false;
    private OrientationEventListener orientationListener;
    private boolean suppressManualEntry;
    private boolean useApplicationTheme;
    private boolean waitingForPermission;

    static {
        int i = RESULT_CARD_INFO + 1;
        RESULT_ENTRY_CANCELED = i;
        RESULT_SCAN_NOT_AVAILABLE = i + 1;
        RESULT_SCAN_SUPPRESSED = i + 2;
        lastResult = i + 4;
        RESULT_CONFIRMATION_SUPPRESSED = i + 3;
        VIBRATE_PATTERN = new long[]{0, 70, 10, 40};
        markedCardImage = null;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = numActivityAllocations + 1;
        numActivityAllocations = i;
        if (i != 1) {
            Log.i(TAG, String.format("INTERNAL WARNING: There are %d (not 1) CardIOActivity allocations!", Integer.valueOf(i)));
        }
        Intent intent = getIntent();
        boolean booleanExtra = getIntent().getBooleanExtra(EXTRA_KEEP_APPLICATION_THEME, false);
        this.useApplicationTheme = booleanExtra;
        ActivityHelper.setActivityTheme(this, booleanExtra);
        LocalizedStrings.setLanguage(intent);
        this.mDetectOnly = intent.getBooleanExtra(EXTRA_SUPPRESS_SCAN, false);
        String manifestHasConfigChange = Util.manifestHasConfigChange(getPackageManager().resolveActivity(intent, 65536), CardIOActivity.class);
        if (manifestHasConfigChange != null) {
            throw new RuntimeException(manifestHasConfigChange);
        }
        this.suppressManualEntry = intent.getBooleanExtra(EXTRA_SUPPRESS_MANUAL_ENTRY, false);
        if (bundle != null) {
            this.waitingForPermission = bundle.getBoolean("io.card.payment.waitingForPermission");
        }
        if (intent.getBooleanExtra(EXTRA_NO_CAMERA, false)) {
            Log.i("card.io", "EXTRA_NO_CAMERA set to true. Skipping camera.");
            this.manualEntryFallbackOrForced = true;
            return;
        }
        if (!CardScanner.processorSupported()) {
            Log.i("card.io", "Processor not Supported. Skipping camera.");
            this.manualEntryFallbackOrForced = true;
            return;
        }
        try {
            if (!this.waitingForPermission) {
                if (checkSelfPermission("android.permission.CAMERA") == -1) {
                    Log.d(TAG, "permission denied to camera - requesting it");
                    this.waitingForPermission = true;
                    requestPermissions(new String[]{"android.permission.CAMERA"}, 11);
                } else {
                    checkCamera();
                    android23AndAboveHandleCamera();
                }
            }
        } catch (Exception e) {
            handleGeneralExceptionError(e);
        }
    }

    private void android23AndAboveHandleCamera() {
        if (this.manualEntryFallbackOrForced) {
            finishIfSuppressManualEntry();
        } else {
            showCameraScannerOverlay();
        }
    }

    private void android22AndBelowHandleCamera() {
        if (this.manualEntryFallbackOrForced) {
            finishIfSuppressManualEntry();
        } else {
            requestWindowFeature(1);
            showCameraScannerOverlay();
        }
    }

    private void finishIfSuppressManualEntry() {
        if (this.suppressManualEntry) {
            Log.i("card.io", "Camera not available and manual entry suppressed.");
            setResultAndFinish(RESULT_SCAN_NOT_AVAILABLE, null);
        }
    }

    private void checkCamera() {
        try {
            if (Util.hardwareSupported()) {
                return;
            }
            StringKey stringKey = StringKey.ERROR_NO_DEVICE_SUPPORT;
            Log.w("card.io", stringKey + ": " + LocalizedStrings.getString(stringKey));
            this.manualEntryFallbackOrForced = true;
        } catch (CameraUnavailableException unused) {
            StringKey stringKey2 = StringKey.ERROR_CAMERA_CONNECT_FAIL;
            String string = LocalizedStrings.getString(stringKey2);
            Log.e("card.io", stringKey2 + ": " + string);
            Toast makeText = Toast.makeText(this, string, 1);
            makeText.setGravity(17, 0, -75);
            makeText.show();
            this.manualEntryFallbackOrForced = true;
        }
    }

    private void showCameraScannerOverlay() {
        getWindow().getDecorView().setSystemUiVisibility(4);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        try {
            this.mGuideFrame = new Rect();
            this.mFrameOrientation = 1;
            if (getIntent().getBooleanExtra("io.card.payment.cameraBypassTestMode", false)) {
                if (!getPackageName().contentEquals("io.card.development")) {
                    throw new IllegalStateException("Illegal access of private extra");
                }
                this.mCardScanner = (CardScanner) Class.forName("io.card.payment.CardScannerTester").getConstructor(getClass(), Integer.TYPE).newInstance(this, Integer.valueOf(this.mFrameOrientation));
            } else {
                this.mCardScanner = new CardScanner(this, this.mFrameOrientation);
            }
            this.mCardScanner.prepareScanner();
            setPreviewLayout();
            this.orientationListener = new OrientationEventListener(this, 2) { // from class: io.card.payment.CardIOActivity.1
                @Override // android.view.OrientationEventListener
                public void onOrientationChanged(int i) {
                    CardIOActivity.this.doOrientationChange(i);
                }
            };
        } catch (Exception e) {
            handleGeneralExceptionError(e);
        }
    }

    private void handleGeneralExceptionError(Exception exc) {
        String string = LocalizedStrings.getString(StringKey.ERROR_CAMERA_UNEXPECTED_FAIL);
        Log.e("card.io", "Unknown exception, please post the stack trace as a GitHub issue", exc);
        Toast makeText = Toast.makeText(this, string, 1);
        makeText.setGravity(17, 0, -75);
        makeText.show();
        this.manualEntryFallbackOrForced = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doOrientationChange(int i) {
        CardScanner cardScanner;
        int i2;
        if (i < 0 || (cardScanner = this.mCardScanner) == null) {
            return;
        }
        int rotationalOffset = i + cardScanner.getRotationalOffset();
        if (rotationalOffset > 360) {
            rotationalOffset += SpaySdk.ERROR_SPAY_APP_INTEGRITY_CHECK_FAIL;
        }
        if (rotationalOffset < 15 || rotationalOffset > 345) {
            this.mFrameOrientation = 1;
            i2 = 0;
        } else if (rotationalOffset > 75 && rotationalOffset < 105) {
            this.mFrameOrientation = 4;
            i2 = 90;
        } else if (rotationalOffset > 165 && rotationalOffset < 195) {
            this.mFrameOrientation = 2;
            i2 = 180;
        } else if (rotationalOffset <= 255 || rotationalOffset >= 285) {
            i2 = -1;
        } else {
            this.mFrameOrientation = 3;
            i2 = 270;
        }
        if (i2 < 0 || i2 == this.mLastDegrees) {
            return;
        }
        this.mCardScanner.setDeviceOrientation(this.mFrameOrientation);
        setDeviceDegrees(i2);
        if (i2 == 90) {
            rotateCustomOverlay(270.0f);
        } else if (i2 == 270) {
            rotateCustomOverlay(90.0f);
        } else {
            rotateCustomOverlay(i2);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.waitingForPermission) {
            return;
        }
        if (this.manualEntryFallbackOrForced) {
            nextActivity();
            return;
        }
        Util.logNativeMemoryStats();
        getWindow().addFlags(1024);
        getWindow().addFlags(128);
        ActivityHelper.setFlagSecure(this);
        setRequestedOrientation(1);
        this.orientationListener.enable();
        if (!restartPreview()) {
            Log.e(TAG, "Could not connect to camera.");
            showErrorMessage(LocalizedStrings.getString(StringKey.ERROR_CAMERA_UNEXPECTED_FAIL));
            nextActivity();
        } else {
            setFlashOn(false);
        }
        doOrientationChange(this.mLastDegrees);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("io.card.payment.waitingForPermission", this.waitingForPermission);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        OrientationEventListener orientationEventListener = this.orientationListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
        setFlashOn(false);
        CardScanner cardScanner = this.mCardScanner;
        if (cardScanner != null) {
            cardScanner.pauseScanning();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.mOverlay = null;
        numActivityAllocations--;
        OrientationEventListener orientationEventListener = this.orientationListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
        setFlashOn(false);
        CardScanner cardScanner = this.mCardScanner;
        if (cardScanner != null) {
            cardScanner.endScanning();
            this.mCardScanner = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 11) {
            this.waitingForPermission = false;
            if (iArr.length > 0 && iArr[0] == 0) {
                showCameraScannerOverlay();
            } else {
                this.manualEntryFallbackOrForced = true;
            }
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 10) {
            return;
        }
        if (i2 == 0) {
            Log.d(TAG, "ignoring onActivityResult(RESULT_CANCELED) caused only when Camera Permissions are Denied in Android 23");
            return;
        }
        if (i2 == RESULT_CARD_INFO || i2 == RESULT_ENTRY_CANCELED || this.manualEntryFallbackOrForced) {
            if (intent != null && intent.hasExtra(EXTRA_SCAN_RESULT)) {
                Log.v(TAG, "EXTRA_SCAN_RESULT: " + intent.getParcelableExtra(EXTRA_SCAN_RESULT));
            } else {
                Log.d(TAG, "no data in EXTRA_SCAN_RESULT");
            }
            setResultAndFinish(i2, intent);
            return;
        }
        RelativeLayout relativeLayout = this.mUIBar;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (!this.manualEntryFallbackOrForced && this.mOverlay.isAnimating()) {
            try {
                restartPreview();
                return;
            } catch (RuntimeException e) {
                Log.w(TAG, "*** could not return to preview: " + e);
                return;
            }
        }
        if (this.mCardScanner != null) {
            super.onBackPressed();
        }
    }

    public static boolean canReadCardWithCamera() {
        try {
            return Util.hardwareSupported();
        } catch (CameraUnavailableException unused) {
            return false;
        } catch (RuntimeException unused2) {
            Log.w(TAG, "RuntimeException accessing Util.hardwareSupported()");
            return false;
        }
    }

    public static String sdkVersion() {
        return BuildConfig.PRODUCT_VERSION;
    }

    public static Date sdkBuildDate() {
        return new Date(BuildConfig.BUILD_TIME);
    }

    public static Bitmap getCapturedCardImage(Intent intent) {
        if (intent == null || !intent.hasExtra(EXTRA_CAPTURED_CARD_IMAGE)) {
            return null;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(intent.getByteArrayExtra(EXTRA_CAPTURED_CARD_IMAGE)), null, new BitmapFactory.Options());
    }

    void onFirstFrame(int i) {
        SurfaceView surfaceView = this.mPreview.getSurfaceView();
        OverlayView overlayView = this.mOverlay;
        if (overlayView != null) {
            overlayView.setCameraPreviewRect(new Rect(surfaceView.getLeft(), surfaceView.getTop(), surfaceView.getRight(), surfaceView.getBottom()));
        }
        this.mFrameOrientation = 1;
        setDeviceDegrees(0);
        if (i != this.mFrameOrientation) {
            Log.wtf("card.io", "the orientation of the scanner doesn't match the orientation of the activity");
        }
        onEdgeUpdate(new DetectionInfo());
    }

    void onEdgeUpdate(DetectionInfo detectionInfo) {
        this.mOverlay.setDetectionInfo(detectionInfo);
    }

    void onCardDetected(Bitmap bitmap, DetectionInfo detectionInfo) {
        float f;
        float f2;
        try {
            ((Vibrator) getSystemService("vibrator")).vibrate(VIBRATE_PATTERN, -1);
        } catch (SecurityException unused) {
            Log.e("card.io", "Could not activate vibration feedback. Please add <uses-permission android:name=\"android.permission.VIBRATE\" /> to your application's manifest.");
        } catch (Exception e) {
            Log.w("card.io", "Exception while attempting to vibrate: ", e);
        }
        this.mCardScanner.pauseScanning();
        this.mUIBar.setVisibility(4);
        if (detectionInfo.predicted()) {
            CreditCard creditCard = detectionInfo.creditCard();
            this.mDetectedCard = creditCard;
            this.mOverlay.setDetectedCard(creditCard);
        }
        int i = this.mFrameOrientation;
        if (i == 1 || i == 2) {
            f = this.mGuideFrame.right / 428.0f;
            f2 = 0.95f;
        } else {
            f = this.mGuideFrame.right / 428.0f;
            f2 = 1.15f;
        }
        float f3 = f * f2;
        Matrix matrix = new Matrix();
        matrix.postScale(f3, f3);
        this.mOverlay.setBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false));
        if (this.mDetectOnly) {
            Intent intent = new Intent();
            Util.writeCapturedCardImageIfNecessary(getIntent(), intent, this.mOverlay);
            setResultAndFinish(RESULT_SCAN_SUPPRESSED, intent);
            return;
        }
        nextActivity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nextActivity() {
        final Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra(EXTRA_SUPPRESS_CONFIRMATION, false)) {
            Intent intent2 = new Intent(this, (Class<?>) DataEntryActivity.class);
            CreditCard creditCard = this.mDetectedCard;
            if (creditCard != null) {
                intent2.putExtra(EXTRA_SCAN_RESULT, creditCard);
                this.mDetectedCard = null;
            }
            Util.writeCapturedCardImageIfNecessary(intent, intent2, this.mOverlay);
            setResultAndFinish(RESULT_CONFIRMATION_SUPPRESSED, intent2);
            return;
        }
        new Handler().post(new Runnable() { // from class: io.card.payment.CardIOActivity.2
            @Override // java.lang.Runnable
            public void run() {
                CardIOActivity.this.getWindow().clearFlags(1024);
                CardIOActivity.this.getWindow().addFlags(512);
                Intent intent3 = new Intent(CardIOActivity.this, (Class<?>) DataEntryActivity.class);
                Util.writeCapturedCardImageIfNecessary(intent, intent3, CardIOActivity.this.mOverlay);
                if (CardIOActivity.this.mOverlay != null) {
                    CardIOActivity.this.mOverlay.markupCard();
                    if (CardIOActivity.markedCardImage != null && !CardIOActivity.markedCardImage.isRecycled()) {
                        CardIOActivity.markedCardImage.recycle();
                    }
                    CardIOActivity.markedCardImage = CardIOActivity.this.mOverlay.getCardImage();
                }
                if (CardIOActivity.this.mDetectedCard != null) {
                    intent3.putExtra(CardIOActivity.EXTRA_SCAN_RESULT, CardIOActivity.this.mDetectedCard);
                    CardIOActivity.this.mDetectedCard = null;
                } else {
                    intent3.putExtra("io.card.payment.manualEntryScanResult", true);
                }
                intent3.putExtras(CardIOActivity.this.getIntent());
                intent3.addFlags(1082195968);
                CardIOActivity.this.startActivityForResult(intent3, 10);
            }
        });
    }

    private void showErrorMessage(String str) {
        Log.e("card.io", "error display: " + str);
        Toast.makeText(this, str, 1).show();
    }

    private boolean restartPreview() {
        this.mDetectedCard = null;
        boolean resumeScanning = this.mCardScanner.resumeScanning(this.mPreview.getSurfaceHolder());
        if (resumeScanning) {
            this.mUIBar.setVisibility(0);
        }
        return resumeScanning;
    }

    private void setDeviceDegrees(int i) {
        SurfaceView surfaceView = this.mPreview.getSurfaceView();
        if (surfaceView == null) {
            Log.wtf("card.io", "surface view is null.. recovering... rotation might be weird.");
            return;
        }
        Rect guideFrame = this.mCardScanner.getGuideFrame(surfaceView.getWidth(), surfaceView.getHeight());
        this.mGuideFrame = guideFrame;
        guideFrame.top += surfaceView.getTop();
        this.mGuideFrame.bottom += surfaceView.getTop();
        this.mOverlay.setGuideAndRotation(this.mGuideFrame, i);
        this.mLastDegrees = i;
    }

    void toggleFlash() {
        setFlashOn(!this.mCardScanner.isFlashOn());
    }

    void setFlashOn(boolean z) {
        if (this.mPreview == null || this.mOverlay == null || !this.mCardScanner.setFlashOn(z)) {
            return;
        }
        this.mOverlay.setTorchOn(z);
    }

    void triggerAutoFocus() {
        this.mCardScanner.triggerAutoFocus(true);
    }

    private void setPreviewLayout() {
        FrameLayout frameLayout = new FrameLayout(this);
        this.mMainLayout = frameLayout;
        frameLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.mMainLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        FrameLayout frameLayout2 = new FrameLayout(this);
        frameLayout2.setId(1);
        this.mCardScanner.getClass();
        this.mCardScanner.getClass();
        Preview preview = new Preview(this, null, 640, 480);
        this.mPreview = preview;
        preview.setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 48));
        frameLayout2.addView(this.mPreview);
        OverlayView overlayView = new OverlayView(this, null, Util.deviceSupportsTorch(this));
        this.mOverlay = overlayView;
        overlayView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        if (getIntent() != null) {
            this.mOverlay.setUseCardIOLogo(getIntent().getBooleanExtra(EXTRA_USE_CARDIO_LOGO, false));
            int intExtra = getIntent().getIntExtra(EXTRA_GUIDE_COLOR, 0);
            if (intExtra != 0) {
                int i = (-16777216) | intExtra;
                if (intExtra != i) {
                    Log.w("card.io", "Removing transparency from provided guide color.");
                }
                this.mOverlay.setGuideColor(i);
            } else {
                this.mOverlay.setGuideColor(-16711936);
            }
            this.mOverlay.setHideCardIOLogo(getIntent().getBooleanExtra(EXTRA_HIDE_CARDIO_LOGO, false));
            String stringExtra = getIntent().getStringExtra(EXTRA_SCAN_INSTRUCTIONS);
            if (stringExtra != null) {
                this.mOverlay.setScanInstructions(stringExtra);
            }
        }
        frameLayout2.addView(this.mOverlay);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(10);
        layoutParams.addRule(2, 2);
        this.mMainLayout.addView(frameLayout2, layoutParams);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.mUIBar = relativeLayout;
        relativeLayout.setGravity(80);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        this.mUIBar.setLayoutParams(layoutParams2);
        this.mUIBar.setId(2);
        this.mUIBar.setGravity(85);
        if (!this.suppressManualEntry) {
            Button button = new Button(this);
            button.setId(3);
            button.setText(LocalizedStrings.getString(StringKey.KEYBOARD));
            button.setOnClickListener(new View.OnClickListener() { // from class: io.card.payment.CardIOActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CardIOActivity.this.nextActivity();
                }
            });
            this.mUIBar.addView(button);
            ViewUtil.styleAsButton(button, false, this, this.useApplicationTheme);
            if (!this.useApplicationTheme) {
                button.setTextSize(14.0f);
            }
            button.setMinimumHeight(ViewUtil.typedDimensionValueToPixelsInt("42dip", this));
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) button.getLayoutParams();
            layoutParams3.width = -2;
            layoutParams3.height = -2;
            layoutParams3.addRule(12);
            ViewUtil.setPadding(button, "16dip", null, "16dip", null);
            ViewUtil.setMargins(button, "4dip", "4dip", "4dip", "4dip");
        }
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(12);
        int i2 = (int) ((getResources().getDisplayMetrics().density * 15.0f) + 0.5f);
        layoutParams4.setMargins(0, i2, 0, i2);
        this.mMainLayout.addView(this.mUIBar, layoutParams4);
        if (getIntent() != null) {
            LinearLayout linearLayout = this.customOverlayLayout;
            if (linearLayout != null) {
                this.mMainLayout.removeView(linearLayout);
                this.customOverlayLayout = null;
            }
            int intExtra2 = getIntent().getIntExtra(EXTRA_SCAN_OVERLAY_LAYOUT_ID, -1);
            if (intExtra2 != -1) {
                LinearLayout linearLayout2 = new LinearLayout(this);
                this.customOverlayLayout = linearLayout2;
                linearLayout2.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                getLayoutInflater().inflate(intExtra2, this.customOverlayLayout);
                this.mMainLayout.addView(this.customOverlayLayout);
            }
        }
        setContentView(this.mMainLayout);
    }

    private void rotateCustomOverlay(float f) {
        if (this.customOverlayLayout != null) {
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, f, r0.getWidth() / 2, this.customOverlayLayout.getHeight() / 2);
            rotateAnimation.setDuration(0L);
            rotateAnimation.setRepeatCount(0);
            rotateAnimation.setFillAfter(true);
            this.customOverlayLayout.setAnimation(rotateAnimation);
        }
    }

    private void setResultAndFinish(int i, Intent intent) {
        setResult(i, intent);
        markedCardImage = null;
        finish();
    }

    public Rect getTorchRect() {
        OverlayView overlayView = this.mOverlay;
        if (overlayView == null) {
            return null;
        }
        return overlayView.getTorchRect();
    }
}
