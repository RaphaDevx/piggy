package io.card.payment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
class CardScanner implements Camera.AutoFocusCallback, Camera.PreviewCallback, SurfaceHolder.Callback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "CardScanner";
    private static boolean manualFallbackForError;
    private static boolean processingInProgress;
    private long captureStart;
    private Bitmap detectedBitmap;
    private boolean isSurfaceValid;
    private long mAutoFocusCompletedAt;
    private long mAutoFocusStartedAt;
    private Camera mCamera;
    private int mFrameOrientation;
    private byte[] mPreviewBuffer;
    protected WeakReference<CardIOActivity> mScanActivityRef;
    private boolean mScanExpiry;
    private boolean mSuppressScan;
    private int mUnblurDigits;
    private int numAutoRefocus;
    private int numFramesSkipped;
    private int numManualRefocus;
    private int numManualTorchChange;
    final int mPreviewWidth = 640;
    final int mPreviewHeight = 480;
    private boolean mFirstPreviewFrame = true;
    protected boolean useCamera = true;

    private native void nCleanup();

    private native void nGetGuideFrame(int i, int i2, int i3, Rect rect);

    private native int nGetNumFramesScanned();

    private native void nResetAnalytics();

    private native void nScanFrame(byte[] bArr, int i, int i2, int i3, DetectionInfo detectionInfo, Bitmap bitmap, boolean z);

    private native void nSetup(boolean z, float f);

    private native void nSetup(boolean z, float f, int i);

    public static native boolean nUseNeon();

    public static native boolean nUseTegra();

    public static native boolean nUseX86();

    static {
        Log.i("card.io", "card.io 5.5.1 03/17/2017 14:23:12 -0400");
        try {
            loadLibrary("cardioDecider");
            Log.d("card.io", "Loaded card.io decider library.");
            Log.d("card.io", "    nUseNeon(): " + nUseNeon());
            Log.d("card.io", "    nUseTegra():" + nUseTegra());
            Log.d("card.io", "    nUseX86():  " + nUseX86());
            if (usesSupportedProcessorArch()) {
                loadLibrary("opencv_core");
                Log.d("card.io", "Loaded opencv core library");
                loadLibrary("opencv_imgproc");
                Log.d("card.io", "Loaded opencv imgproc library");
            }
            if (nUseNeon()) {
                loadLibrary("cardioRecognizer");
                Log.i("card.io", "Loaded card.io NEON library");
            } else if (nUseX86()) {
                loadLibrary("cardioRecognizer");
                Log.i("card.io", "Loaded card.io x86 library");
            } else if (nUseTegra()) {
                loadLibrary("cardioRecognizer_tegra2");
                Log.i("card.io", "Loaded card.io Tegra2 library");
            } else {
                Log.w("card.io", "unsupported processor - card.io scanning requires ARMv7 or x86 architecture");
                manualFallbackForError = true;
            }
        } catch (UnsatisfiedLinkError e) {
            Log.e("card.io", "Failed to load native library: " + e.getMessage());
            manualFallbackForError = true;
        }
        processingInProgress = false;
    }

    private static void loadLibrary(String str) throws UnsatisfiedLinkError {
        try {
            System.loadLibrary(str);
        } catch (UnsatisfiedLinkError e) {
            String alternativeLibsPath = CardIONativeLibsConfig.getAlternativeLibsPath();
            if (alternativeLibsPath == null || alternativeLibsPath.length() == 0) {
                throw e;
            }
            if (!File.separator.equals(Character.valueOf(alternativeLibsPath.charAt(alternativeLibsPath.length() - 1)))) {
                alternativeLibsPath = alternativeLibsPath + File.separator;
            }
            String str2 = alternativeLibsPath + Build.CPU_ABI + File.separator + System.mapLibraryName(str);
            Log.d("card.io", "loadLibrary failed for library " + str + ". Trying " + str2);
            System.load(str2);
        }
    }

    private static boolean usesSupportedProcessorArch() {
        return nUseNeon() || nUseTegra() || nUseX86();
    }

    static boolean processorSupported() {
        return !manualFallbackForError && usesSupportedProcessorArch();
    }

    CardScanner(CardIOActivity cardIOActivity, int i) {
        boolean z = false;
        this.mSuppressScan = false;
        this.mUnblurDigits = -1;
        this.mFrameOrientation = 1;
        Intent intent = cardIOActivity.getIntent();
        if (intent != null) {
            this.mSuppressScan = intent.getBooleanExtra(CardIOActivity.EXTRA_SUPPRESS_SCAN, false);
            if (intent.getBooleanExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false) && intent.getBooleanExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true)) {
                z = true;
            }
            this.mScanExpiry = z;
            this.mUnblurDigits = intent.getIntExtra(CardIOActivity.EXTRA_UNBLUR_DIGITS, -1);
        }
        this.mScanActivityRef = new WeakReference<>(cardIOActivity);
        this.mFrameOrientation = i;
        nSetup(this.mSuppressScan, 6.0f, this.mUnblurDigits);
    }

    private Camera connectToCamera(int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.useCamera) {
            do {
                try {
                    try {
                        return Camera.open();
                    } catch (InterruptedException e) {
                        Log.e("card.io", "Interrupted while waiting for camera", e);
                    }
                } catch (RuntimeException unused) {
                    Log.w("card.io", "Wasn't able to connect to camera service. Waiting and trying again...");
                    Thread.sleep(i);
                } catch (Exception e2) {
                    Log.e("card.io", "Unexpected exception. Please report it as a GitHub issue", e2);
                    i2 = 0;
                }
            } while (System.currentTimeMillis() - currentTimeMillis < i2);
        }
        Log.w(TAG, "camera connect timeout");
        return null;
    }

    void prepareScanner() {
        Camera.Size size;
        this.mFirstPreviewFrame = true;
        this.mAutoFocusStartedAt = 0L;
        this.mAutoFocusCompletedAt = 0L;
        this.numManualRefocus = 0;
        this.numAutoRefocus = 0;
        this.numManualTorchChange = 0;
        this.numFramesSkipped = 0;
        boolean z = this.useCamera;
        if (z && this.mCamera == null) {
            Camera connectToCamera = connectToCamera(50, 5000);
            this.mCamera = connectToCamera;
            if (connectToCamera == null) {
                Log.e("card.io", "prepare scanner couldn't connect to camera!");
                return;
            }
            setCameraDisplayOrientation(connectToCamera);
            Camera.Parameters parameters = this.mCamera.getParameters();
            List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            if (supportedPreviewSizes != null) {
                Iterator<Camera.Size> it = supportedPreviewSizes.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        size = null;
                        break;
                    }
                    size = it.next();
                    if (size.width == 640 && size.height == 480) {
                        break;
                    }
                }
                if (size == null) {
                    Log.w("card.io", "Didn't find a supported 640x480 resolution, so forcing");
                    Camera.Size size2 = supportedPreviewSizes.get(0);
                    size2.width = 640;
                    size2.height = 480;
                }
            }
            parameters.setPreviewSize(640, 480);
            this.mCamera.setParameters(parameters);
        } else if (!z) {
            Log.w(TAG, "useCamera is false!");
        } else if (this.mCamera != null) {
            Log.v(TAG, "we already have a camera instance: " + this.mCamera);
        }
        if (this.detectedBitmap == null) {
            this.detectedBitmap = Bitmap.createBitmap(428, 270, Bitmap.Config.ARGB_8888);
        }
    }

    boolean resumeScanning(SurfaceHolder surfaceHolder) {
        if (this.mCamera == null) {
            prepareScanner();
        }
        boolean z = this.useCamera;
        if (z && this.mCamera == null) {
            Log.i(TAG, "null camera. failure");
            return false;
        }
        if (z && this.mPreviewBuffer == null) {
            byte[] bArr = new byte[(ImageFormat.getBitsPerPixel(this.mCamera.getParameters().getPreviewFormat()) / 8) * 921600];
            this.mPreviewBuffer = bArr;
            this.mCamera.addCallbackBuffer(bArr);
        }
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(3);
        if (this.useCamera) {
            this.mCamera.setPreviewCallbackWithBuffer(this);
        }
        if (this.isSurfaceValid) {
            makePreviewGo(surfaceHolder);
        }
        setFlashOn(false);
        this.captureStart = System.currentTimeMillis();
        nResetAnalytics();
        return true;
    }

    public void pauseScanning() {
        setFlashOn(false);
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.stopPreview();
                this.mCamera.setPreviewDisplay(null);
            } catch (IOException e) {
                Log.w("card.io", "can't stop preview display", e);
            }
            this.mCamera.setPreviewCallback(null);
            this.mCamera.release();
            this.mPreviewBuffer = null;
            this.mCamera = null;
        }
    }

    public void endScanning() {
        if (this.mCamera != null) {
            pauseScanning();
        }
        nCleanup();
        this.mPreviewBuffer = null;
    }

    private boolean makePreviewGo(SurfaceHolder surfaceHolder) {
        this.mFirstPreviewFrame = true;
        if (this.useCamera) {
            try {
                this.mCamera.setPreviewDisplay(surfaceHolder);
                try {
                    this.mCamera.startPreview();
                    this.mCamera.autoFocus(this);
                } catch (RuntimeException e) {
                    Log.e("card.io", "startPreview failed on camera. Error: ", e);
                    return false;
                }
            } catch (IOException e2) {
                Log.e("card.io", "can't set preview display", e2);
                return false;
            }
        }
        return true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.mCamera != null || !this.useCamera) {
            this.isSurfaceValid = true;
            makePreviewGo(surfaceHolder);
        } else {
            Log.wtf("card.io", "CardScanner.surfaceCreated() - camera is null!");
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Log.d(TAG, String.format("Preview.surfaceChanged(holder?:%b, f:%d, w:%d, h:%d )", Boolean.valueOf(surfaceHolder != null), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.stopPreview();
            } catch (Exception e) {
                Log.e("card.io", "error stopping camera", e);
            }
        }
        this.isSurfaceValid = false;
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (bArr == null) {
            Log.w(TAG, "frame is null! skipping");
            return;
        }
        if (processingInProgress) {
            Log.e(TAG, "processing in progress.... dropping frame");
            this.numFramesSkipped++;
            if (camera != null) {
                camera.addCallbackBuffer(bArr);
                return;
            }
            return;
        }
        processingInProgress = true;
        if (this.mFirstPreviewFrame) {
            this.mFirstPreviewFrame = false;
            this.mFrameOrientation = 1;
            this.mScanActivityRef.get().onFirstFrame(1);
        }
        DetectionInfo detectionInfo = new DetectionInfo();
        nScanFrame(bArr, 640, 480, this.mFrameOrientation, detectionInfo, this.detectedBitmap, this.mScanExpiry);
        if (detectionInfo.focusScore < 6.0f) {
            triggerAutoFocus(false);
        } else if (detectionInfo.predicted() || (this.mSuppressScan && detectionInfo.detected())) {
            this.mScanActivityRef.get().onCardDetected(this.detectedBitmap, detectionInfo);
        }
        if (camera != null) {
            camera.addCallbackBuffer(bArr);
        }
        processingInProgress = false;
    }

    void onEdgeUpdate(DetectionInfo detectionInfo) {
        this.mScanActivityRef.get().onEdgeUpdate(detectionInfo);
    }

    Rect getGuideFrame(int i, int i2, int i3) {
        if (!processorSupported()) {
            return null;
        }
        Rect rect = new Rect();
        nGetGuideFrame(i, i2, i3, rect);
        return rect;
    }

    Rect getGuideFrame(int i, int i2) {
        return getGuideFrame(this.mFrameOrientation, i, i2);
    }

    void setDeviceOrientation(int i) {
        this.mFrameOrientation = i;
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public void onAutoFocus(boolean z, Camera camera) {
        this.mAutoFocusCompletedAt = System.currentTimeMillis();
    }

    boolean isAutoFocusing() {
        return this.mAutoFocusCompletedAt < this.mAutoFocusStartedAt;
    }

    void triggerAutoFocus(boolean z) {
        if (!this.useCamera || isAutoFocusing()) {
            return;
        }
        try {
            this.mAutoFocusStartedAt = System.currentTimeMillis();
            this.mCamera.autoFocus(this);
            if (z) {
                this.numManualRefocus++;
            } else {
                this.numAutoRefocus++;
            }
        } catch (RuntimeException e) {
            Log.w(TAG, "could not trigger auto focus: " + e);
        }
    }

    public boolean isFlashOn() {
        if (this.useCamera) {
            return this.mCamera.getParameters().getFlashMode().equals("torch");
        }
        return false;
    }

    public boolean setFlashOn(boolean z) {
        Camera camera = this.mCamera;
        if (camera == null) {
            return false;
        }
        try {
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(z ? "torch" : "off");
            this.mCamera.setParameters(parameters);
            this.numManualTorchChange++;
            return true;
        } catch (RuntimeException e) {
            Log.w(TAG, "Could not set flash mode: " + e);
            return false;
        }
    }

    private void setCameraDisplayOrientation(Camera camera) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(0, cameraInfo);
        camera.setDisplayOrientation(((cameraInfo.orientation - getRotationalOffset()) + 360) % 360);
    }

    int getRotationalOffset() {
        int rotation = ((WindowManager) this.mScanActivityRef.get().getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation == 0) {
            return 0;
        }
        if (rotation == 1) {
            return 90;
        }
        if (rotation == 2) {
            return 180;
        }
        return rotation == 3 ? 270 : 0;
    }
}
