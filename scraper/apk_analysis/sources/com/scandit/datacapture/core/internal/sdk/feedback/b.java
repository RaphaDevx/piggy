package com.scandit.datacapture.core.internal.sdk.feedback;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import ch.datatrans.payment.N9$$ExternalSyntheticApiModelOutline0;
import com.scandit.datacapture.core.common.feedback.Sound;
import com.scandit.datacapture.core.common.feedback.Vibration;
import com.scandit.datacapture.core.common.feedback.WaveFormVibration;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.extensions.SoundPoolExtensionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements FeedbackEmitter {
    public static final HandlerThread j;
    public static final Handler k;
    public final Vibration a;
    public final Sound b;
    public boolean c;
    public boolean d;
    public SoundPool e;
    public int f;
    public final AudioManager g;
    public final Vibrator h;
    public final int i;

    static {
        final DefaultConstructorMarker defaultConstructorMarker = null;
        new Object(defaultConstructorMarker) { // from class: com.scandit.datacapture.core.internal.sdk.feedback.FeedbackEmitterImpl$Companion
        };
        HandlerThread handlerThread = new HandlerThread("thread-feedback");
        handlerThread.start();
        j = handlerThread;
        k = new Handler(handlerThread.getLooper());
    }

    public b(Vibration vibration, Sound sound, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = vibration;
        this.b = sound;
        Object systemService = context.getSystemService("audio");
        Vibrator vibrator = null;
        this.g = systemService instanceof AudioManager ? (AudioManager) systemService : null;
        if (Build.VERSION.SDK_INT >= 31) {
            Object systemService2 = context.getSystemService("vibrator_manager");
            VibratorManager m3500m = N9$$ExternalSyntheticApiModelOutline0.m3509m(systemService2) ? N9$$ExternalSyntheticApiModelOutline0.m3500m(systemService2) : null;
            if (m3500m != null) {
                vibrator = m3500m.getDefaultVibrator();
            }
        } else {
            Object systemService3 = context.getSystemService("vibrator");
            if (systemService3 instanceof Vibrator) {
                vibrator = (Vibrator) systemService3;
            }
        }
        this.h = vibrator;
        this.i = context.getPackageManager().checkPermission("android.permission.VIBRATE", context.getPackageName());
    }

    public static final void a(b this$0, SoundPool soundPool, SoundPool soundPool2, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(this$0.e, soundPool)) {
            this$0.c = true;
            this$0.c();
        }
    }

    public static final void b(b this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getClass();
        a();
        if (this$0.c) {
            return;
        }
        if (this$0.b == null) {
            this$0.c = true;
            return;
        }
        SoundPool b = this$0.b();
        this$0.e = b;
        this$0.f = SoundPoolExtensionsKt.load(b, AppAndroidEnvironment.INSTANCE.getApplicationContext(), this$0.b);
    }

    public final void c() {
        AudioManager audioManager;
        SoundPool soundPool;
        AudioManager audioManager2;
        if (this.d && this.c) {
            if (this.a != null && this.h != null && this.i == 0 && ((audioManager2 = this.g) == null || audioManager2.getRingerMode() != 0)) {
                long asMillis = this.a.getDuration().asMillis();
                int amplitude = this.a.getAmplitude();
                Vibration vibration = this.a;
                WaveFormVibration waveFormVibration = vibration instanceof WaveFormVibration ? (WaveFormVibration) vibration : null;
                this.h.vibrate(waveFormVibration != null ? waveFormVibration.getAmplitudes() != null ? VibrationEffect.createWaveform(waveFormVibration.getTimings(), waveFormVibration.getAmplitudes(), -1) : VibrationEffect.createWaveform(waveFormVibration.getTimings(), -1) : VibrationEffect.createOneShot(asMillis, amplitude));
            }
            if (this.f != 0 && this.e != null && (audioManager = this.g) != null && audioManager.getRingerMode() == 2 && (soundPool = this.e) != null) {
                soundPool.play(this.f, 1.0f, 1.0f, 1, 0, 1.0f);
            }
            this.d = false;
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.feedback.FeedbackEmitter
    public final void emitAsync() {
        k.post(new Runnable() { // from class: com.scandit.datacapture.core.internal.sdk.feedback.b$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                b.a(b.this);
            }
        });
    }

    @Override // com.scandit.datacapture.core.internal.sdk.feedback.FeedbackEmitter
    public final void loadAsync() {
        k.post(new Runnable() { // from class: com.scandit.datacapture.core.internal.sdk.feedback.b$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                b.b(b.this);
            }
        });
    }

    @Override // com.scandit.datacapture.core.internal.sdk.feedback.FeedbackEmitter
    public final void releaseAsync() {
        k.post(new Runnable() { // from class: com.scandit.datacapture.core.internal.sdk.feedback.b$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                b.c(b.this);
            }
        });
    }

    public static final void a(b this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getClass();
        a();
        this$0.d = true;
        this$0.c();
    }

    public static void a() {
        Intrinsics.areEqual(Thread.currentThread(), j);
    }

    public final SoundPool b() {
        final SoundPool build = new SoundPool.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(14).setContentType(4).build()).setMaxStreams(2).build();
        build.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.scandit.datacapture.core.internal.sdk.feedback.b$$ExternalSyntheticLambda6
            @Override // android.media.SoundPool.OnLoadCompleteListener
            public final void onLoadComplete(SoundPool soundPool, int i, int i2) {
                b.a(b.this, build, soundPool, i, i2);
            }
        });
        Intrinsics.checkNotNullExpressionValue(build, "apply(...)");
        return build;
    }

    public static final void c(b this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getClass();
        a();
        SoundPool soundPool = this$0.e;
        if (soundPool != null) {
            soundPool.setOnLoadCompleteListener(null);
        }
        SoundPool soundPool2 = this$0.e;
        if (soundPool2 != null) {
            soundPool2.release();
        }
        this$0.e = null;
        this$0.f = 0;
        this$0.c = false;
        this$0.d = false;
    }
}
