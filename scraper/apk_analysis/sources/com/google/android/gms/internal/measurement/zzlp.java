package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.coop.auth.AuthBrowserView;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes3.dex */
final class zzlp<T> implements zzlx<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzmy.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzlm zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzla zzm;
    private final zzmo zzn;
    private final zzjs zzo;
    private final zzlr zzp;
    private final zzlh zzq;

    private zzlp(int[] iArr, Object[] objArr, int i, int i2, zzlm zzlmVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzlr zzlrVar, zzla zzlaVar, zzmo zzmoVar, zzjs zzjsVar, zzlh zzlhVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = z;
        boolean z3 = false;
        if (zzjsVar != null && zzjsVar.zzc(zzlmVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzj = iArr2;
        this.zzk = i3;
        this.zzl = i4;
        this.zzp = zzlrVar;
        this.zzm = zzlaVar;
        this.zzn = zzmoVar;
        this.zzo = zzjsVar;
        this.zzg = zzlmVar;
        this.zzq = zzlhVar;
    }

    private static int zzA(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzB(int i) {
        return this.zzc[i + 1];
    }

    private static long zzC(Object obj, long j) {
        return ((Long) zzmy.zzf(obj, j)).longValue();
    }

    private final zzkj zzD(int i) {
        int i2 = i / 3;
        return (zzkj) this.zzd[i2 + i2 + 1];
    }

    private final zzlx zzE(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzlx zzlxVar = (zzlx) this.zzd[i3];
        if (zzlxVar != null) {
            return zzlxVar;
        }
        zzlx zzb2 = zzlu.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzF(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzG(Object obj, int i) {
        zzlx zzE = zzE(i);
        long zzB = zzB(i) & 1048575;
        if (!zzT(obj, i)) {
            return zzE.zze();
        }
        Object object = zzb.getObject(obj, zzB);
        if (zzW(object)) {
            return object;
        }
        Object zze = zzE.zze();
        if (object != null) {
            zzE.zzg(zze, object);
        }
        return zze;
    }

    private final Object zzH(Object obj, int i, int i2) {
        zzlx zzE = zzE(i2);
        if (!zzX(obj, i, i2)) {
            return zzE.zze();
        }
        Object object = zzb.getObject(obj, zzB(i2) & 1048575);
        if (zzW(object)) {
            return object;
        }
        Object zze = zzE.zze();
        if (object != null) {
            zzE.zzg(zze, object);
        }
        return zze;
    }

    private static Field zzI(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzJ(Object obj) {
        if (!zzW(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzK(Object obj, Object obj2, int i) {
        if (zzT(obj2, i)) {
            long zzB = zzB(i) & 1048575;
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(obj2, zzB);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzlx zzE = zzE(i);
            if (!zzT(obj, i)) {
                if (zzW(object)) {
                    Object zze = zzE.zze();
                    zzE.zzg(zze, object);
                    unsafe.putObject(obj, zzB, zze);
                } else {
                    unsafe.putObject(obj, zzB, object);
                }
                zzM(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzB);
            if (!zzW(object2)) {
                Object zze2 = zzE.zze();
                zzE.zzg(zze2, object2);
                unsafe.putObject(obj, zzB, zze2);
                object2 = zze2;
            }
            zzE.zzg(object2, object);
        }
    }

    private final void zzL(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzX(obj2, i2, i)) {
            long zzB = zzB(i) & 1048575;
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(obj2, zzB);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzlx zzE = zzE(i);
            if (!zzX(obj, i2, i)) {
                if (zzW(object)) {
                    Object zze = zzE.zze();
                    zzE.zzg(zze, object);
                    unsafe.putObject(obj, zzB, zze);
                } else {
                    unsafe.putObject(obj, zzB, object);
                }
                zzN(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzB);
            if (!zzW(object2)) {
                Object zze2 = zzE.zze();
                zzE.zzg(zze2, object2);
                unsafe.putObject(obj, zzB, zze2);
                object2 = zze2;
            }
            zzE.zzg(object2, object);
        }
    }

    private final void zzM(Object obj, int i) {
        int zzy = zzy(i);
        long j = 1048575 & zzy;
        if (j == 1048575) {
            return;
        }
        zzmy.zzq(obj, j, (1 << (zzy >>> 20)) | zzmy.zzc(obj, j));
    }

    private final void zzN(Object obj, int i, int i2) {
        zzmy.zzq(obj, zzy(i2) & 1048575, i);
    }

    private final void zzO(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzB(i) & 1048575, obj2);
        zzM(obj, i);
    }

    private final void zzP(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzB(i2) & 1048575, obj2);
        zzN(obj, i, i2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void zzQ(Object obj, zzng zzngVar) throws IOException {
        int i;
        boolean z;
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            int zzB = zzB(i4);
            int[] iArr = this.zzc;
            int i6 = iArr[i4];
            int zzA = zzA(zzB);
            if (zzA <= 17) {
                int i7 = iArr[i4 + 2];
                int i8 = i7 & i2;
                if (i8 != i3) {
                    i5 = unsafe.getInt(obj, i8);
                    i3 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            long j = zzB & i2;
            switch (zzA) {
                case 0:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzf(i6, zzmy.zza(obj, j));
                        break;
                    }
                case 1:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzo(i6, zzmy.zzb(obj, j));
                        break;
                    }
                case 2:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzt(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 3:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzJ(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 4:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzr(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 5:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzm(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 6:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzk(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 7:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzb(i6, zzmy.zzw(obj, j));
                        break;
                    }
                case 8:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzZ(i6, unsafe.getObject(obj, j), zzngVar);
                        break;
                    }
                case 9:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzv(i6, unsafe.getObject(obj, j), zzE(i4));
                        break;
                    }
                case 10:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzd(i6, (zzje) unsafe.getObject(obj, j));
                        break;
                    }
                case 11:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzH(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 12:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzi(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 13:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzw(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 14:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzy(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 15:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzA(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 16:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzC(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 17:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzq(i6, unsafe.getObject(obj, j), zzE(i4));
                        break;
                    }
                case 18:
                    zzlz.zzJ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 19:
                    zzlz.zzN(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 20:
                    zzlz.zzQ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 21:
                    zzlz.zzY(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 22:
                    zzlz.zzP(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 23:
                    zzlz.zzM(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 24:
                    zzlz.zzL(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 25:
                    zzlz.zzH(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 26:
                    zzlz.zzW(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar);
                    break;
                case 27:
                    zzlz.zzR(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, zzE(i4));
                    break;
                case 28:
                    zzlz.zzI(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    z = false;
                    zzlz.zzX(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 30:
                    z = false;
                    zzlz.zzK(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 31:
                    z = false;
                    zzlz.zzS(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 32:
                    z = false;
                    zzlz.zzT(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 33:
                    z = false;
                    zzlz.zzU(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 34:
                    z = false;
                    zzlz.zzV(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 35:
                    zzlz.zzJ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 36:
                    zzlz.zzN(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 37:
                    zzlz.zzQ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 38:
                    zzlz.zzY(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 39:
                    zzlz.zzP(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 40:
                    zzlz.zzM(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 41:
                    zzlz.zzL(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 42:
                    zzlz.zzH(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 43:
                    zzlz.zzX(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 44:
                    zzlz.zzK(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 45:
                    zzlz.zzS(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 46:
                    zzlz.zzT(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 47:
                    zzlz.zzU(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    zzlz.zzV(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zzlz.zzO(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, zzE(i4));
                    break;
                case 50:
                    zzR(zzngVar, i6, unsafe.getObject(obj, j), i4);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzf(i6, zzn(obj, j));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzo(i6, zzo(obj, j));
                    }
                    break;
                case 53:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzt(i6, zzC(obj, j));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzJ(i6, zzC(obj, j));
                    }
                    break;
                case 55:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzr(i6, zzr(obj, j));
                    }
                    break;
                case AuthBrowserView.TOOLBAR_HEIGHT_DP /* 56 */:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzm(i6, zzC(obj, j));
                    }
                    break;
                case 57:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzk(i6, zzr(obj, j));
                    }
                    break;
                case 58:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzb(i6, zzY(obj, j));
                    }
                    break;
                case 59:
                    if (zzX(obj, i6, i4)) {
                        zzZ(i6, unsafe.getObject(obj, j), zzngVar);
                    }
                    break;
                case 60:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzv(i6, unsafe.getObject(obj, j), zzE(i4));
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzd(i6, (zzje) unsafe.getObject(obj, j));
                    }
                    break;
                case 62:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzH(i6, zzr(obj, j));
                    }
                    break;
                case 63:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzi(i6, zzr(obj, j));
                    }
                    break;
                case 64:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzw(i6, zzr(obj, j));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzy(i6, zzC(obj, j));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzA(i6, zzr(obj, j));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzC(i6, zzC(obj, j));
                    }
                    break;
                case 68:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzq(i6, unsafe.getObject(obj, j), zzE(i4));
                    }
                    break;
            }
            i4 += 3;
            i2 = 1048575;
        }
        zzmo zzmoVar = this.zzn;
        zzmoVar.zzi(zzmoVar.zzd(obj), zzngVar);
    }

    private final void zzR(zzng zzngVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private final boolean zzS(Object obj, Object obj2, int i) {
        return zzT(obj, i) == zzT(obj2, i);
    }

    private final boolean zzT(Object obj, int i) {
        int zzy = zzy(i);
        long j = zzy & 1048575;
        if (j != 1048575) {
            return (zzmy.zzc(obj, j) & (1 << (zzy >>> 20))) != 0;
        }
        int zzB = zzB(i);
        long j2 = zzB & 1048575;
        switch (zzA(zzB)) {
            case 0:
                return Double.doubleToRawLongBits(zzmy.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzmy.zzb(obj, j2)) != 0;
            case 2:
                return zzmy.zzd(obj, j2) != 0;
            case 3:
                return zzmy.zzd(obj, j2) != 0;
            case 4:
                return zzmy.zzc(obj, j2) != 0;
            case 5:
                return zzmy.zzd(obj, j2) != 0;
            case 6:
                return zzmy.zzc(obj, j2) != 0;
            case 7:
                return zzmy.zzw(obj, j2);
            case 8:
                Object zzf = zzmy.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                }
                if (zzf instanceof zzje) {
                    return !zzje.zzb.equals(zzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzmy.zzf(obj, j2) != null;
            case 10:
                return !zzje.zzb.equals(zzmy.zzf(obj, j2));
            case 11:
                return zzmy.zzc(obj, j2) != 0;
            case 12:
                return zzmy.zzc(obj, j2) != 0;
            case 13:
                return zzmy.zzc(obj, j2) != 0;
            case 14:
                return zzmy.zzd(obj, j2) != 0;
            case 15:
                return zzmy.zzc(obj, j2) != 0;
            case 16:
                return zzmy.zzd(obj, j2) != 0;
            case 17:
                return zzmy.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzU(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzT(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzV(Object obj, int i, zzlx zzlxVar) {
        return zzlxVar.zzk(zzmy.zzf(obj, i & 1048575));
    }

    private static boolean zzW(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzkf) {
            return ((zzkf) obj).zzbO();
        }
        return true;
    }

    private final boolean zzX(Object obj, int i, int i2) {
        return zzmy.zzc(obj, (long) (zzy(i2) & 1048575)) == i;
    }

    private static boolean zzY(Object obj, long j) {
        return ((Boolean) zzmy.zzf(obj, j)).booleanValue();
    }

    private static final void zzZ(int i, Object obj, zzng zzngVar) throws IOException {
        if (obj instanceof String) {
            zzngVar.zzF(i, (String) obj);
        } else {
            zzngVar.zzd(i, (zzje) obj);
        }
    }

    static zzmp zzd(Object obj) {
        zzkf zzkfVar = (zzkf) obj;
        zzmp zzmpVar = zzkfVar.zzc;
        if (zzmpVar != zzmp.zzc()) {
            return zzmpVar;
        }
        zzmp zzf = zzmp.zzf();
        zzkfVar.zzc = zzf;
        return zzf;
    }

    static zzlp zzl(Class cls, zzlj zzljVar, zzlr zzlrVar, zzla zzlaVar, zzmo zzmoVar, zzjs zzjsVar, zzlh zzlhVar) {
        if (zzljVar instanceof zzlw) {
            return zzm((zzlw) zzljVar, zzlrVar, zzlaVar, zzmoVar, zzjsVar, zzlhVar);
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0261  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.google.android.gms.internal.measurement.zzlp zzm(com.google.android.gms.internal.measurement.zzlw r34, com.google.android.gms.internal.measurement.zzlr r35, com.google.android.gms.internal.measurement.zzla r36, com.google.android.gms.internal.measurement.zzmo r37, com.google.android.gms.internal.measurement.zzjs r38, com.google.android.gms.internal.measurement.zzlh r39) {
        /*
            Method dump skipped, instructions count: 1020
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlp.zzm(com.google.android.gms.internal.measurement.zzlw, com.google.android.gms.internal.measurement.zzlr, com.google.android.gms.internal.measurement.zzla, com.google.android.gms.internal.measurement.zzmo, com.google.android.gms.internal.measurement.zzjs, com.google.android.gms.internal.measurement.zzlh):com.google.android.gms.internal.measurement.zzlp");
    }

    private static double zzn(Object obj, long j) {
        return ((Double) zzmy.zzf(obj, j)).doubleValue();
    }

    private static float zzo(Object obj, long j) {
        return ((Float) zzmy.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzp(Object obj) {
        int i;
        int zzA;
        int zzA2;
        int zzA3;
        int zzB;
        int zzA4;
        int zzv;
        int zzA5;
        int zzA6;
        int zzd;
        int zzA7;
        int i2;
        int zzu;
        boolean z;
        int zzd2;
        int zzi;
        int zzz;
        int zzA8;
        int i3;
        int zzA9;
        int zzA10;
        int zzA11;
        int zzB2;
        int zzA12;
        int zzd3;
        int zzA13;
        int i4;
        Unsafe unsafe = zzb;
        int i5 = 1048575;
        int i6 = 1048575;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < this.zzc.length) {
            int zzB3 = zzB(i7);
            int[] iArr = this.zzc;
            int i10 = iArr[i7];
            int zzA14 = zzA(zzB3);
            if (zzA14 <= 17) {
                int i11 = iArr[i7 + 2];
                int i12 = i11 & i5;
                i = 1 << (i11 >>> 20);
                if (i12 != i6) {
                    i9 = unsafe.getInt(obj, i12);
                    i6 = i12;
                }
            } else {
                i = 0;
            }
            long j = zzB3 & i5;
            switch (zzA14) {
                case 0:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA = zzjm.zzA(i10 << 3);
                        zzA5 = zzA + 8;
                        i8 += zzA5;
                        break;
                    }
                case 1:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA2 = zzjm.zzA(i10 << 3);
                        zzA5 = zzA2 + 4;
                        i8 += zzA5;
                        break;
                    }
                case 2:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(obj, j);
                        zzA3 = zzjm.zzA(i10 << 3);
                        zzB = zzjm.zzB(j2);
                        i8 += zzA3 + zzB;
                        break;
                    }
                case 3:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        long j3 = unsafe.getLong(obj, j);
                        zzA3 = zzjm.zzA(i10 << 3);
                        zzB = zzjm.zzB(j3);
                        i8 += zzA3 + zzB;
                        break;
                    }
                case 4:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i13 = unsafe.getInt(obj, j);
                        zzA4 = zzjm.zzA(i10 << 3);
                        zzv = zzjm.zzv(i13);
                        i2 = zzA4 + zzv;
                        i8 += i2;
                        break;
                    }
                case 5:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA = zzjm.zzA(i10 << 3);
                        zzA5 = zzA + 8;
                        i8 += zzA5;
                        break;
                    }
                case 6:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA2 = zzjm.zzA(i10 << 3);
                        zzA5 = zzA2 + 4;
                        i8 += zzA5;
                        break;
                    }
                case 7:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA5 = zzjm.zzA(i10 << 3) + 1;
                        i8 += zzA5;
                        break;
                    }
                case 8:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzje) {
                            zzA6 = zzjm.zzA(i10 << 3);
                            zzd = ((zzje) object).zzd();
                            zzA7 = zzjm.zzA(zzd);
                            i2 = zzA6 + zzA7 + zzd;
                            i8 += i2;
                            break;
                        } else {
                            zzA4 = zzjm.zzA(i10 << 3);
                            zzv = zzjm.zzy((String) object);
                            i2 = zzA4 + zzv;
                            i8 += i2;
                        }
                    }
                case 9:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA5 = zzlz.zzo(i10, unsafe.getObject(obj, j), zzE(i7));
                        i8 += zzA5;
                        break;
                    }
                case 10:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzje zzjeVar = (zzje) unsafe.getObject(obj, j);
                        zzA6 = zzjm.zzA(i10 << 3);
                        zzd = zzjeVar.zzd();
                        zzA7 = zzjm.zzA(zzd);
                        i2 = zzA6 + zzA7 + zzd;
                        i8 += i2;
                        break;
                    }
                case 11:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i14 = unsafe.getInt(obj, j);
                        zzA4 = zzjm.zzA(i10 << 3);
                        zzv = zzjm.zzA(i14);
                        i2 = zzA4 + zzv;
                        i8 += i2;
                        break;
                    }
                case 12:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i15 = unsafe.getInt(obj, j);
                        zzA4 = zzjm.zzA(i10 << 3);
                        zzv = zzjm.zzv(i15);
                        i2 = zzA4 + zzv;
                        i8 += i2;
                        break;
                    }
                case 13:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA2 = zzjm.zzA(i10 << 3);
                        zzA5 = zzA2 + 4;
                        i8 += zzA5;
                        break;
                    }
                case 14:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA = zzjm.zzA(i10 << 3);
                        zzA5 = zzA + 8;
                        i8 += zzA5;
                        break;
                    }
                case 15:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i16 = unsafe.getInt(obj, j);
                        zzA4 = zzjm.zzA(i10 << 3);
                        zzv = zzjm.zzA((i16 >> 31) ^ (i16 + i16));
                        i2 = zzA4 + zzv;
                        i8 += i2;
                        break;
                    }
                case 16:
                    if ((i & i9) == 0) {
                        break;
                    } else {
                        long j4 = unsafe.getLong(obj, j);
                        i8 += zzjm.zzA(i10 << 3) + zzjm.zzB((j4 >> 63) ^ (j4 + j4));
                        break;
                    }
                case 17:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA5 = zzjm.zzu(i10, (zzlm) unsafe.getObject(obj, j), zzE(i7));
                        i8 += zzA5;
                        break;
                    }
                case 18:
                    zzA5 = zzlz.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 19:
                    zzA5 = zzlz.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 20:
                    zzA5 = zzlz.zzm(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 21:
                    zzA5 = zzlz.zzx(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 22:
                    zzA5 = zzlz.zzk(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 23:
                    zzA5 = zzlz.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 24:
                    zzA5 = zzlz.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 25:
                    zzA5 = zzlz.zza(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 26:
                    zzu = zzlz.zzu(i10, (List) unsafe.getObject(obj, j));
                    i8 += zzu;
                    break;
                case 27:
                    zzu = zzlz.zzp(i10, (List) unsafe.getObject(obj, j), zzE(i7));
                    i8 += zzu;
                    break;
                case 28:
                    zzu = zzlz.zzc(i10, (List) unsafe.getObject(obj, j));
                    i8 += zzu;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    zzu = zzlz.zzv(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzu;
                    break;
                case 30:
                    z = false;
                    zzd2 = zzlz.zzd(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 31:
                    z = false;
                    zzd2 = zzlz.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 32:
                    z = false;
                    zzd2 = zzlz.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 33:
                    z = false;
                    zzd2 = zzlz.zzq(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 34:
                    z = false;
                    zzd2 = zzlz.zzs(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 35:
                    zzi = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 36:
                    zzi = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 37:
                    zzi = zzlz.zzn((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 38:
                    zzi = zzlz.zzy((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 39:
                    zzi = zzlz.zzl((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 40:
                    zzi = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 41:
                    zzi = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 42:
                    zzi = zzlz.zzb((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 43:
                    zzi = zzlz.zzw((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 44:
                    zzi = zzlz.zze((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 45:
                    zzi = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 46:
                    zzi = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 47:
                    zzi = zzlz.zzr((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    zzi = zzlz.zzt((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zzu = zzlz.zzj(i10, (List) unsafe.getObject(obj, j), zzE(i7));
                    i8 += zzu;
                    break;
                case 50:
                    zzlh.zza(i10, unsafe.getObject(obj, j), zzF(i7));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzX(obj, i10, i7)) {
                        zzA9 = zzjm.zzA(i10 << 3);
                        zzu = zzA9 + 8;
                        i8 += zzu;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzX(obj, i10, i7)) {
                        zzA10 = zzjm.zzA(i10 << 3);
                        zzu = zzA10 + 4;
                        i8 += zzu;
                    }
                    break;
                case 53:
                    if (zzX(obj, i10, i7)) {
                        long zzC = zzC(obj, j);
                        zzA11 = zzjm.zzA(i10 << 3);
                        zzB2 = zzjm.zzB(zzC);
                        i8 += zzA11 + zzB2;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzX(obj, i10, i7)) {
                        long zzC2 = zzC(obj, j);
                        zzA11 = zzjm.zzA(i10 << 3);
                        zzB2 = zzjm.zzB(zzC2);
                        i8 += zzA11 + zzB2;
                    }
                    break;
                case 55:
                    if (zzX(obj, i10, i7)) {
                        int zzr = zzr(obj, j);
                        i3 = zzjm.zzA(i10 << 3);
                        zzi = zzjm.zzv(zzr);
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case AuthBrowserView.TOOLBAR_HEIGHT_DP /* 56 */:
                    if (zzX(obj, i10, i7)) {
                        zzA9 = zzjm.zzA(i10 << 3);
                        zzu = zzA9 + 8;
                        i8 += zzu;
                    }
                    break;
                case 57:
                    if (zzX(obj, i10, i7)) {
                        zzA10 = zzjm.zzA(i10 << 3);
                        zzu = zzA10 + 4;
                        i8 += zzu;
                    }
                    break;
                case 58:
                    if (zzX(obj, i10, i7)) {
                        zzu = zzjm.zzA(i10 << 3) + 1;
                        i8 += zzu;
                    }
                    break;
                case 59:
                    if (zzX(obj, i10, i7)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzje) {
                            zzA12 = zzjm.zzA(i10 << 3);
                            zzd3 = ((zzje) object2).zzd();
                            zzA13 = zzjm.zzA(zzd3);
                            i4 = zzA12 + zzA13 + zzd3;
                            i8 += i4;
                        } else {
                            i3 = zzjm.zzA(i10 << 3);
                            zzi = zzjm.zzy((String) object2);
                            i4 = i3 + zzi;
                            i8 += i4;
                        }
                    }
                    break;
                case 60:
                    if (zzX(obj, i10, i7)) {
                        zzu = zzlz.zzo(i10, unsafe.getObject(obj, j), zzE(i7));
                        i8 += zzu;
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzX(obj, i10, i7)) {
                        zzje zzjeVar2 = (zzje) unsafe.getObject(obj, j);
                        zzA12 = zzjm.zzA(i10 << 3);
                        zzd3 = zzjeVar2.zzd();
                        zzA13 = zzjm.zzA(zzd3);
                        i4 = zzA12 + zzA13 + zzd3;
                        i8 += i4;
                    }
                    break;
                case 62:
                    if (zzX(obj, i10, i7)) {
                        int zzr2 = zzr(obj, j);
                        i3 = zzjm.zzA(i10 << 3);
                        zzi = zzjm.zzA(zzr2);
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 63:
                    if (zzX(obj, i10, i7)) {
                        int zzr3 = zzr(obj, j);
                        i3 = zzjm.zzA(i10 << 3);
                        zzi = zzjm.zzv(zzr3);
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 64:
                    if (zzX(obj, i10, i7)) {
                        zzA10 = zzjm.zzA(i10 << 3);
                        zzu = zzA10 + 4;
                        i8 += zzu;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (zzX(obj, i10, i7)) {
                        zzA9 = zzjm.zzA(i10 << 3);
                        zzu = zzA9 + 8;
                        i8 += zzu;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzX(obj, i10, i7)) {
                        int zzr4 = zzr(obj, j);
                        i3 = zzjm.zzA(i10 << 3);
                        zzi = zzjm.zzA((zzr4 >> 31) ^ (zzr4 + zzr4));
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzX(obj, i10, i7)) {
                        long zzC3 = zzC(obj, j);
                        i8 += zzjm.zzA(i10 << 3) + zzjm.zzB((zzC3 >> 63) ^ (zzC3 + zzC3));
                    }
                    break;
                case 68:
                    if (zzX(obj, i10, i7)) {
                        zzu = zzjm.zzu(i10, (zzlm) unsafe.getObject(obj, j), zzE(i7));
                        i8 += zzu;
                    }
                    break;
            }
            i7 += 3;
            i5 = 1048575;
        }
        zzmo zzmoVar = this.zzn;
        int zza2 = i8 + zzmoVar.zza(zzmoVar.zzd(obj));
        if (!this.zzh) {
            return zza2;
        }
        this.zzo.zza(obj);
        throw null;
    }

    private final int zzq(Object obj) {
        int zzA;
        int zzA2;
        int zzA3;
        int zzB;
        int zzA4;
        int zzv;
        int zzA5;
        int zzA6;
        int zzd;
        int zzA7;
        int zzo;
        int zzz;
        int zzA8;
        int i;
        Unsafe unsafe = zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int zzB2 = zzB(i3);
            int zzA9 = zzA(zzB2);
            int i4 = this.zzc[i3];
            long j = zzB2 & 1048575;
            if (zzA9 >= zzjx.DOUBLE_LIST_PACKED.zza() && zzA9 <= zzjx.SINT64_LIST_PACKED.zza()) {
                int i5 = this.zzc[i3 + 2];
            }
            switch (zzA9) {
                case 0:
                    if (zzT(obj, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzT(obj, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzT(obj, i3)) {
                        long zzd2 = zzmy.zzd(obj, j);
                        zzA3 = zzjm.zzA(i4 << 3);
                        zzB = zzjm.zzB(zzd2);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzT(obj, i3)) {
                        long zzd3 = zzmy.zzd(obj, j);
                        zzA3 = zzjm.zzA(i4 << 3);
                        zzB = zzjm.zzB(zzd3);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzT(obj, i3)) {
                        int zzc = zzmy.zzc(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzv(zzc);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzT(obj, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzT(obj, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzT(obj, i3)) {
                        zzA5 = zzjm.zzA(i4 << 3);
                        zzo = zzA5 + 1;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzT(obj, i3)) {
                        break;
                    } else {
                        Object zzf = zzmy.zzf(obj, j);
                        if (zzf instanceof zzje) {
                            zzA6 = zzjm.zzA(i4 << 3);
                            zzd = ((zzje) zzf).zzd();
                            zzA7 = zzjm.zzA(zzd);
                            i = zzA6 + zzA7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzA4 = zzjm.zzA(i4 << 3);
                            zzv = zzjm.zzy((String) zzf);
                            i = zzA4 + zzv;
                            i2 += i;
                        }
                    }
                case 9:
                    if (zzT(obj, i3)) {
                        zzo = zzlz.zzo(i4, zzmy.zzf(obj, j), zzE(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzT(obj, i3)) {
                        zzje zzjeVar = (zzje) zzmy.zzf(obj, j);
                        zzA6 = zzjm.zzA(i4 << 3);
                        zzd = zzjeVar.zzd();
                        zzA7 = zzjm.zzA(zzd);
                        i = zzA6 + zzA7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzT(obj, i3)) {
                        int zzc2 = zzmy.zzc(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzA(zzc2);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzT(obj, i3)) {
                        int zzc3 = zzmy.zzc(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzv(zzc3);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzT(obj, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzT(obj, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzT(obj, i3)) {
                        int zzc4 = zzmy.zzc(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzA((zzc4 >> 31) ^ (zzc4 + zzc4));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzT(obj, i3)) {
                        long zzd4 = zzmy.zzd(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzB((zzd4 >> 63) ^ (zzd4 + zzd4));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzT(obj, i3)) {
                        zzo = zzjm.zzu(i4, (zzlm) zzmy.zzf(obj, j), zzE(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzo = zzlz.zzh(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 19:
                    zzo = zzlz.zzf(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 20:
                    zzo = zzlz.zzm(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 21:
                    zzo = zzlz.zzx(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 22:
                    zzo = zzlz.zzk(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 23:
                    zzo = zzlz.zzh(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 24:
                    zzo = zzlz.zzf(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 25:
                    zzo = zzlz.zza(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 26:
                    zzo = zzlz.zzu(i4, (List) zzmy.zzf(obj, j));
                    i2 += zzo;
                    break;
                case 27:
                    zzo = zzlz.zzp(i4, (List) zzmy.zzf(obj, j), zzE(i3));
                    i2 += zzo;
                    break;
                case 28:
                    zzo = zzlz.zzc(i4, (List) zzmy.zzf(obj, j));
                    i2 += zzo;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    zzo = zzlz.zzv(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 30:
                    zzo = zzlz.zzd(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 31:
                    zzo = zzlz.zzf(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 32:
                    zzo = zzlz.zzh(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 33:
                    zzo = zzlz.zzq(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 34:
                    zzo = zzlz.zzs(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 35:
                    zzv = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    zzv = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    zzv = zzlz.zzn((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    zzv = zzlz.zzy((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    zzv = zzlz.zzl((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    zzv = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    zzv = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    zzv = zzlz.zzb((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    zzv = zzlz.zzw((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    zzv = zzlz.zze((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    zzv = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    zzv = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    zzv = zzlz.zzr((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    zzv = zzlz.zzt((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zzo = zzlz.zzj(i4, (List) zzmy.zzf(obj, j), zzE(i3));
                    i2 += zzo;
                    break;
                case 50:
                    zzlh.zza(i4, zzmy.zzf(obj, j), zzF(i3));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzX(obj, i4, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzX(obj, i4, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzX(obj, i4, i3)) {
                        long zzC = zzC(obj, j);
                        zzA3 = zzjm.zzA(i4 << 3);
                        zzB = zzjm.zzB(zzC);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzX(obj, i4, i3)) {
                        long zzC2 = zzC(obj, j);
                        zzA3 = zzjm.zzA(i4 << 3);
                        zzB = zzjm.zzB(zzC2);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzX(obj, i4, i3)) {
                        int zzr = zzr(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzv(zzr);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case AuthBrowserView.TOOLBAR_HEIGHT_DP /* 56 */:
                    if (zzX(obj, i4, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzX(obj, i4, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzX(obj, i4, i3)) {
                        zzA5 = zzjm.zzA(i4 << 3);
                        zzo = zzA5 + 1;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzX(obj, i4, i3)) {
                        break;
                    } else {
                        Object zzf2 = zzmy.zzf(obj, j);
                        if (zzf2 instanceof zzje) {
                            zzA6 = zzjm.zzA(i4 << 3);
                            zzd = ((zzje) zzf2).zzd();
                            zzA7 = zzjm.zzA(zzd);
                            i = zzA6 + zzA7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzA4 = zzjm.zzA(i4 << 3);
                            zzv = zzjm.zzy((String) zzf2);
                            i = zzA4 + zzv;
                            i2 += i;
                        }
                    }
                case 60:
                    if (zzX(obj, i4, i3)) {
                        zzo = zzlz.zzo(i4, zzmy.zzf(obj, j), zzE(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzX(obj, i4, i3)) {
                        zzje zzjeVar2 = (zzje) zzmy.zzf(obj, j);
                        zzA6 = zzjm.zzA(i4 << 3);
                        zzd = zzjeVar2.zzd();
                        zzA7 = zzjm.zzA(zzd);
                        i = zzA6 + zzA7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzX(obj, i4, i3)) {
                        int zzr2 = zzr(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzA(zzr2);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzX(obj, i4, i3)) {
                        int zzr3 = zzr(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzv(zzr3);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzX(obj, i4, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (zzX(obj, i4, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzX(obj, i4, i3)) {
                        int zzr4 = zzr(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzA((zzr4 >> 31) ^ (zzr4 + zzr4));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzX(obj, i4, i3)) {
                        long zzC3 = zzC(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzB((zzC3 >> 63) ^ (zzC3 + zzC3));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzX(obj, i4, i3)) {
                        zzo = zzjm.zzu(i4, (zzlm) zzmy.zzf(obj, j), zzE(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzmo zzmoVar = this.zzn;
        return i2 + zzmoVar.zza(zzmoVar.zzd(obj));
    }

    private static int zzr(Object obj, long j) {
        return ((Integer) zzmy.zzf(obj, j)).intValue();
    }

    private final int zzs(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzir zzirVar) throws IOException {
        Unsafe unsafe = zzb;
        Object zzF = zzF(i3);
        Object object = unsafe.getObject(obj, j);
        if (!((zzlg) object).zze()) {
            zzlg zzb2 = zzlg.zza().zzb();
            zzlh.zzb(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        throw null;
    }

    private final int zzt(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzir zzirVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzis.zzp(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzis.zzb(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 53:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                if (i5 == 0) {
                    int zzm = zzis.zzm(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzirVar.zzb));
                    unsafe.putInt(obj, j2, i4);
                    return zzm;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int zzj = zzis.zzj(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzirVar.zza));
                    unsafe.putInt(obj, j2, i4);
                    return zzj;
                }
                break;
            case AuthBrowserView.TOOLBAR_HEIGHT_DP /* 56 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzis.zzp(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzis.zzb(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int zzm2 = zzis.zzm(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzirVar.zzb != 0));
                    unsafe.putInt(obj, j2, i4);
                    return zzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int zzj2 = zzis.zzj(bArr, i, zzirVar);
                    int i9 = zzirVar.zza;
                    if (i9 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else {
                        if ((i6 & 536870912) != 0 && !zznd.zzf(bArr, zzj2, zzj2 + i9)) {
                            throw zzkp.zzc();
                        }
                        unsafe.putObject(obj, j, new String(bArr, zzj2, i9, zzkn.zzb));
                        zzj2 += i9;
                    }
                    unsafe.putInt(obj, j2, i4);
                    return zzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    Object zzH = zzH(obj, i4, i8);
                    int zzo = zzis.zzo(zzH, zzE(i8), bArr, i, i2, zzirVar);
                    zzP(obj, i4, i8, zzH);
                    return zzo;
                }
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                if (i5 == 2) {
                    int zza2 = zzis.zza(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, zzirVar.zzc);
                    unsafe.putInt(obj, j2, i4);
                    return zza2;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int zzj3 = zzis.zzj(bArr, i, zzirVar);
                    int i10 = zzirVar.zza;
                    zzkj zzD = zzD(i8);
                    if (zzD != null && !zzD.zza(i10)) {
                        zzd(obj).zzj(i3, Long.valueOf(i10));
                        return zzj3;
                    }
                    unsafe.putObject(obj, j, Integer.valueOf(i10));
                    unsafe.putInt(obj, j2, i4);
                    return zzj3;
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                if (i5 == 0) {
                    int zzj4 = zzis.zzj(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzji.zzb(zzirVar.zza)));
                    unsafe.putInt(obj, j2, i4);
                    return zzj4;
                }
                break;
            case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                if (i5 == 0) {
                    int zzm3 = zzis.zzm(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzji.zzc(zzirVar.zzb)));
                    unsafe.putInt(obj, j2, i4);
                    return zzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    Object zzH2 = zzH(obj, i4, i8);
                    int zzn = zzis.zzn(zzH2, zzE(i8), bArr, i, i2, (i3 & (-8)) | 4, zzirVar);
                    zzP(obj, i4, i8, zzH2);
                    return zzn;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x029b, code lost:
    
        if (r0 != r15) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x029d, code lost:
    
        r15 = r29;
        r14 = r30;
        r12 = r31;
        r13 = r33;
        r11 = r34;
        r8 = r18;
        r2 = r19;
        r1 = r23;
        r7 = r26;
        r6 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x02b3, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x02e0, code lost:
    
        if (r0 != r15) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0304, code lost:
    
        if (r0 != r15) goto L100;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0086. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzu(java.lang.Object r30, byte[] r31, int r32, int r33, com.google.android.gms.internal.measurement.zzir r34) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 880
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlp.zzu(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzir):int");
    }

    private final int zzv(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzir zzirVar) throws IOException {
        int i8;
        int zzl;
        Unsafe unsafe = zzb;
        zzkm zzkmVar = (zzkm) unsafe.getObject(obj, j2);
        if (!zzkmVar.zzc()) {
            int size = zzkmVar.size();
            zzkmVar = zzkmVar.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j2, zzkmVar);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzjo zzjoVar = (zzjo) zzkmVar;
                    int zzj = zzis.zzj(bArr, i, zzirVar);
                    int i9 = zzirVar.zza + zzj;
                    while (zzj < i9) {
                        zzjoVar.zze(Double.longBitsToDouble(zzis.zzp(bArr, zzj)));
                        zzj += 8;
                    }
                    if (zzj == i9) {
                        return zzj;
                    }
                    throw zzkp.zzf();
                }
                if (i5 == 1) {
                    zzjo zzjoVar2 = (zzjo) zzkmVar;
                    zzjoVar2.zze(Double.longBitsToDouble(zzis.zzp(bArr, i)));
                    int i10 = i + 8;
                    while (i10 < i2) {
                        int zzj2 = zzis.zzj(bArr, i10, zzirVar);
                        if (i3 != zzirVar.zza) {
                            return i10;
                        }
                        zzjoVar2.zze(Double.longBitsToDouble(zzis.zzp(bArr, zzj2)));
                        i10 = zzj2 + 8;
                    }
                    return i10;
                }
                break;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzjy zzjyVar = (zzjy) zzkmVar;
                    int zzj3 = zzis.zzj(bArr, i, zzirVar);
                    int i11 = zzirVar.zza + zzj3;
                    while (zzj3 < i11) {
                        zzjyVar.zze(Float.intBitsToFloat(zzis.zzb(bArr, zzj3)));
                        zzj3 += 4;
                    }
                    if (zzj3 == i11) {
                        return zzj3;
                    }
                    throw zzkp.zzf();
                }
                if (i5 == 5) {
                    zzjy zzjyVar2 = (zzjy) zzkmVar;
                    zzjyVar2.zze(Float.intBitsToFloat(zzis.zzb(bArr, i)));
                    int i12 = i + 4;
                    while (i12 < i2) {
                        int zzj4 = zzis.zzj(bArr, i12, zzirVar);
                        if (i3 != zzirVar.zza) {
                            return i12;
                        }
                        zzjyVar2.zze(Float.intBitsToFloat(zzis.zzb(bArr, zzj4)));
                        i12 = zzj4 + 4;
                    }
                    return i12;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzlb zzlbVar = (zzlb) zzkmVar;
                    int zzj5 = zzis.zzj(bArr, i, zzirVar);
                    int i13 = zzirVar.zza + zzj5;
                    while (zzj5 < i13) {
                        zzj5 = zzis.zzm(bArr, zzj5, zzirVar);
                        zzlbVar.zzg(zzirVar.zzb);
                    }
                    if (zzj5 == i13) {
                        return zzj5;
                    }
                    throw zzkp.zzf();
                }
                if (i5 == 0) {
                    zzlb zzlbVar2 = (zzlb) zzkmVar;
                    int zzm = zzis.zzm(bArr, i, zzirVar);
                    zzlbVar2.zzg(zzirVar.zzb);
                    while (zzm < i2) {
                        int zzj6 = zzis.zzj(bArr, zzm, zzirVar);
                        if (i3 != zzirVar.zza) {
                            return zzm;
                        }
                        zzm = zzis.zzm(bArr, zzj6, zzirVar);
                        zzlbVar2.zzg(zzirVar.zzb);
                    }
                    return zzm;
                }
                break;
            case 22:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzis.zzf(bArr, i, zzkmVar, zzirVar);
                }
                if (i5 == 0) {
                    return zzis.zzl(i3, bArr, i, i2, zzkmVar, zzirVar);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzlb zzlbVar3 = (zzlb) zzkmVar;
                    int zzj7 = zzis.zzj(bArr, i, zzirVar);
                    int i14 = zzirVar.zza + zzj7;
                    while (zzj7 < i14) {
                        zzlbVar3.zzg(zzis.zzp(bArr, zzj7));
                        zzj7 += 8;
                    }
                    if (zzj7 == i14) {
                        return zzj7;
                    }
                    throw zzkp.zzf();
                }
                if (i5 == 1) {
                    zzlb zzlbVar4 = (zzlb) zzkmVar;
                    zzlbVar4.zzg(zzis.zzp(bArr, i));
                    int i15 = i + 8;
                    while (i15 < i2) {
                        int zzj8 = zzis.zzj(bArr, i15, zzirVar);
                        if (i3 != zzirVar.zza) {
                            return i15;
                        }
                        zzlbVar4.zzg(zzis.zzp(bArr, zzj8));
                        i15 = zzj8 + 8;
                    }
                    return i15;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzkg zzkgVar = (zzkg) zzkmVar;
                    int zzj9 = zzis.zzj(bArr, i, zzirVar);
                    int i16 = zzirVar.zza + zzj9;
                    while (zzj9 < i16) {
                        zzkgVar.zzh(zzis.zzb(bArr, zzj9));
                        zzj9 += 4;
                    }
                    if (zzj9 == i16) {
                        return zzj9;
                    }
                    throw zzkp.zzf();
                }
                if (i5 == 5) {
                    zzkg zzkgVar2 = (zzkg) zzkmVar;
                    zzkgVar2.zzh(zzis.zzb(bArr, i));
                    int i17 = i + 4;
                    while (i17 < i2) {
                        int zzj10 = zzis.zzj(bArr, i17, zzirVar);
                        if (i3 != zzirVar.zza) {
                            return i17;
                        }
                        zzkgVar2.zzh(zzis.zzb(bArr, zzj10));
                        i17 = zzj10 + 4;
                    }
                    return i17;
                }
                break;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzit zzitVar = (zzit) zzkmVar;
                    int zzj11 = zzis.zzj(bArr, i, zzirVar);
                    int i18 = zzirVar.zza + zzj11;
                    while (zzj11 < i18) {
                        zzj11 = zzis.zzm(bArr, zzj11, zzirVar);
                        zzitVar.zze(zzirVar.zzb != 0);
                    }
                    if (zzj11 == i18) {
                        return zzj11;
                    }
                    throw zzkp.zzf();
                }
                if (i5 == 0) {
                    zzit zzitVar2 = (zzit) zzkmVar;
                    int zzm2 = zzis.zzm(bArr, i, zzirVar);
                    zzitVar2.zze(zzirVar.zzb != 0);
                    while (zzm2 < i2) {
                        int zzj12 = zzis.zzj(bArr, zzm2, zzirVar);
                        if (i3 != zzirVar.zza) {
                            return zzm2;
                        }
                        zzm2 = zzis.zzm(bArr, zzj12, zzirVar);
                        zzitVar2.zze(zzirVar.zzb != 0);
                    }
                    return zzm2;
                }
                break;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        int zzj13 = zzis.zzj(bArr, i, zzirVar);
                        int i19 = zzirVar.zza;
                        if (i19 < 0) {
                            throw zzkp.zzd();
                        }
                        if (i19 == 0) {
                            zzkmVar.add("");
                        } else {
                            zzkmVar.add(new String(bArr, zzj13, i19, zzkn.zzb));
                            zzj13 += i19;
                        }
                        while (zzj13 < i2) {
                            int zzj14 = zzis.zzj(bArr, zzj13, zzirVar);
                            if (i3 != zzirVar.zza) {
                                return zzj13;
                            }
                            zzj13 = zzis.zzj(bArr, zzj14, zzirVar);
                            int i20 = zzirVar.zza;
                            if (i20 < 0) {
                                throw zzkp.zzd();
                            }
                            if (i20 == 0) {
                                zzkmVar.add("");
                            } else {
                                zzkmVar.add(new String(bArr, zzj13, i20, zzkn.zzb));
                                zzj13 += i20;
                            }
                        }
                        return zzj13;
                    }
                    int zzj15 = zzis.zzj(bArr, i, zzirVar);
                    int i21 = zzirVar.zza;
                    if (i21 < 0) {
                        throw zzkp.zzd();
                    }
                    if (i21 == 0) {
                        zzkmVar.add("");
                        i8 = zzj15;
                    } else {
                        i8 = zzj15 + i21;
                        if (!zznd.zzf(bArr, zzj15, i8)) {
                            throw zzkp.zzc();
                        }
                        zzkmVar.add(new String(bArr, zzj15, i21, zzkn.zzb));
                    }
                    while (i8 < i2) {
                        int zzj16 = zzis.zzj(bArr, i8, zzirVar);
                        if (i3 != zzirVar.zza) {
                            return i8;
                        }
                        i8 = zzis.zzj(bArr, zzj16, zzirVar);
                        int i22 = zzirVar.zza;
                        if (i22 < 0) {
                            throw zzkp.zzd();
                        }
                        if (i22 == 0) {
                            zzkmVar.add("");
                        } else {
                            int i23 = i8 + i22;
                            if (!zznd.zzf(bArr, i8, i23)) {
                                throw zzkp.zzc();
                            }
                            zzkmVar.add(new String(bArr, i8, i22, zzkn.zzb));
                            i8 = i23;
                        }
                    }
                    return i8;
                }
                break;
            case 27:
                if (i5 == 2) {
                    return zzis.zze(zzE(i6), i3, bArr, i, i2, zzkmVar, zzirVar);
                }
                break;
            case 28:
                if (i5 == 2) {
                    int zzj17 = zzis.zzj(bArr, i, zzirVar);
                    int i24 = zzirVar.zza;
                    if (i24 < 0) {
                        throw zzkp.zzd();
                    }
                    if (i24 > bArr.length - zzj17) {
                        throw zzkp.zzf();
                    }
                    if (i24 == 0) {
                        zzkmVar.add(zzje.zzb);
                    } else {
                        zzkmVar.add(zzje.zzl(bArr, zzj17, i24));
                        zzj17 += i24;
                    }
                    while (zzj17 < i2) {
                        int zzj18 = zzis.zzj(bArr, zzj17, zzirVar);
                        if (i3 != zzirVar.zza) {
                            return zzj17;
                        }
                        zzj17 = zzis.zzj(bArr, zzj18, zzirVar);
                        int i25 = zzirVar.zza;
                        if (i25 < 0) {
                            throw zzkp.zzd();
                        }
                        if (i25 > bArr.length - zzj17) {
                            throw zzkp.zzf();
                        }
                        if (i25 == 0) {
                            zzkmVar.add(zzje.zzb);
                        } else {
                            zzkmVar.add(zzje.zzl(bArr, zzj17, i25));
                            zzj17 += i25;
                        }
                    }
                    return zzj17;
                }
                break;
            case 30:
            case 44:
                if (i5 == 2) {
                    zzl = zzis.zzf(bArr, i, zzkmVar, zzirVar);
                } else if (i5 == 0) {
                    zzl = zzis.zzl(i3, bArr, i, i2, zzkmVar, zzirVar);
                }
                zzlz.zzC(obj, i4, zzkmVar, zzD(i6), null, this.zzn);
                return zzl;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzkg zzkgVar3 = (zzkg) zzkmVar;
                    int zzj19 = zzis.zzj(bArr, i, zzirVar);
                    int i26 = zzirVar.zza + zzj19;
                    while (zzj19 < i26) {
                        zzj19 = zzis.zzj(bArr, zzj19, zzirVar);
                        zzkgVar3.zzh(zzji.zzb(zzirVar.zza));
                    }
                    if (zzj19 == i26) {
                        return zzj19;
                    }
                    throw zzkp.zzf();
                }
                if (i5 == 0) {
                    zzkg zzkgVar4 = (zzkg) zzkmVar;
                    int zzj20 = zzis.zzj(bArr, i, zzirVar);
                    zzkgVar4.zzh(zzji.zzb(zzirVar.zza));
                    while (zzj20 < i2) {
                        int zzj21 = zzis.zzj(bArr, zzj20, zzirVar);
                        if (i3 != zzirVar.zza) {
                            return zzj20;
                        }
                        zzj20 = zzis.zzj(bArr, zzj21, zzirVar);
                        zzkgVar4.zzh(zzji.zzb(zzirVar.zza));
                    }
                    return zzj20;
                }
                break;
            case 34:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                if (i5 == 2) {
                    zzlb zzlbVar5 = (zzlb) zzkmVar;
                    int zzj22 = zzis.zzj(bArr, i, zzirVar);
                    int i27 = zzirVar.zza + zzj22;
                    while (zzj22 < i27) {
                        zzj22 = zzis.zzm(bArr, zzj22, zzirVar);
                        zzlbVar5.zzg(zzji.zzc(zzirVar.zzb));
                    }
                    if (zzj22 == i27) {
                        return zzj22;
                    }
                    throw zzkp.zzf();
                }
                if (i5 == 0) {
                    zzlb zzlbVar6 = (zzlb) zzkmVar;
                    int zzm3 = zzis.zzm(bArr, i, zzirVar);
                    zzlbVar6.zzg(zzji.zzc(zzirVar.zzb));
                    while (zzm3 < i2) {
                        int zzj23 = zzis.zzj(bArr, zzm3, zzirVar);
                        if (i3 != zzirVar.zza) {
                            return zzm3;
                        }
                        zzm3 = zzis.zzm(bArr, zzj23, zzirVar);
                        zzlbVar6.zzg(zzji.zzc(zzirVar.zzb));
                    }
                    return zzm3;
                }
                break;
            default:
                if (i5 == 3) {
                    zzlx zzE = zzE(i6);
                    int i28 = (i3 & (-8)) | 4;
                    int zzc = zzis.zzc(zzE, bArr, i, i2, i28, zzirVar);
                    zzkmVar.add(zzirVar.zzc);
                    while (zzc < i2) {
                        int zzj24 = zzis.zzj(bArr, zzc, zzirVar);
                        if (i3 != zzirVar.zza) {
                            return zzc;
                        }
                        zzc = zzis.zzc(zzE, bArr, zzj24, i2, i28, zzirVar);
                        zzkmVar.add(zzirVar.zzc);
                    }
                    return zzc;
                }
                break;
        }
        return i;
    }

    private final int zzw(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, 0);
    }

    private final int zzx(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, i2);
    }

    private final int zzy(int i) {
        return this.zzc[i + 2];
    }

    private final int zzz(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final int zza(Object obj) {
        return this.zzi ? zzq(obj) : zzp(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final int zzb(Object obj) {
        int i;
        int zzc;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzB = zzB(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & zzB;
            int i5 = 37;
            switch (zzA(zzB)) {
                case 0:
                    i = i2 * 53;
                    zzc = zzkn.zzc(Double.doubleToLongBits(zzmy.zza(obj, j)));
                    i2 = i + zzc;
                    break;
                case 1:
                    i = i2 * 53;
                    zzc = Float.floatToIntBits(zzmy.zzb(obj, j));
                    i2 = i + zzc;
                    break;
                case 2:
                    i = i2 * 53;
                    zzc = zzkn.zzc(zzmy.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 3:
                    i = i2 * 53;
                    zzc = zzkn.zzc(zzmy.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 4:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 5:
                    i = i2 * 53;
                    zzc = zzkn.zzc(zzmy.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 6:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 7:
                    i = i2 * 53;
                    zzc = zzkn.zza(zzmy.zzw(obj, j));
                    i2 = i + zzc;
                    break;
                case 8:
                    i = i2 * 53;
                    zzc = ((String) zzmy.zzf(obj, j)).hashCode();
                    i2 = i + zzc;
                    break;
                case 9:
                    Object zzf = zzmy.zzf(obj, j);
                    if (zzf != null) {
                        i5 = zzf.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzc = zzmy.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 11:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 12:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 13:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 14:
                    i = i2 * 53;
                    zzc = zzkn.zzc(zzmy.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 15:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 16:
                    i = i2 * 53;
                    zzc = zzkn.zzc(zzmy.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 17:
                    Object zzf2 = zzmy.zzf(obj, j);
                    if (zzf2 != null) {
                        i5 = zzf2.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    i = i2 * 53;
                    zzc = zzmy.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 50:
                    i = i2 * 53;
                    zzc = zzmy.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(Double.doubleToLongBits(zzn(obj, j)));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = Float.floatToIntBits(zzo(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(zzC(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(zzC(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case AuthBrowserView.TOOLBAR_HEIGHT_DP /* 56 */:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(zzC(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zza(zzY(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = ((String) zzmy.zzf(obj, j)).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzmy.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzmy.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(zzC(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(zzC(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzmy.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.zzn.zzd(obj).hashCode();
        if (!this.zzh) {
            return hashCode;
        }
        this.zzo.zza(obj);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0429, code lost:
    
        if (r6 == 1048575) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x042b, code lost:
    
        r28.putInt(r12, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0431, code lost:
    
        r3 = r9.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0435, code lost:
    
        if (r3 >= r9.zzl) goto L221;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0437, code lost:
    
        r4 = r9.zzj[r3];
        r5 = r9.zzc[r4];
        r5 = com.google.android.gms.internal.measurement.zzmy.zzf(r12, r9.zzB(r4) & 1048575);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0449, code lost:
    
        if (r5 != null) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0450, code lost:
    
        if (r9.zzD(r4) != null) goto L220;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0455, code lost:
    
        r5 = (com.google.android.gms.internal.measurement.zzlg) r5;
        r0 = (com.google.android.gms.internal.measurement.zzlf) r9.zzF(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x045d, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0452, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x045e, code lost:
    
        if (r7 != 0) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0462, code lost:
    
        if (r0 != r33) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0469, code lost:
    
        throw com.google.android.gms.internal.measurement.zzkp.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0470, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x046c, code lost:
    
        if (r0 > r33) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x046e, code lost:
    
        if (r1 != r7) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0475, code lost:
    
        throw com.google.android.gms.internal.measurement.zzkp.zze();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final int zzc(java.lang.Object r30, byte[] r31, int r32, int r33, int r34, com.google.android.gms.internal.measurement.zzir r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1180
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlp.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzir):int");
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final Object zze() {
        return ((zzkf) this.zzg).zzbA();
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final void zzf(Object obj) {
        if (zzW(obj)) {
            if (obj instanceof zzkf) {
                zzkf zzkfVar = (zzkf) obj;
                zzkfVar.zzbM(Integer.MAX_VALUE);
                zzkfVar.zzb = 0;
                zzkfVar.zzbK();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzB = zzB(i);
                long j = 1048575 & zzB;
                int zzA = zzA(zzB);
                if (zzA != 9) {
                    switch (zzA) {
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                            this.zzm.zza(obj, j);
                            break;
                        case 50:
                            Unsafe unsafe = zzb;
                            Object object = unsafe.getObject(obj, j);
                            if (object != null) {
                                ((zzlg) object).zzc();
                                unsafe.putObject(obj, j, object);
                                break;
                            } else {
                                break;
                            }
                    }
                }
                if (zzT(obj, i)) {
                    zzE(i).zzf(zzb.getObject(obj, j));
                }
            }
            this.zzn.zzg(obj);
            if (this.zzh) {
                this.zzo.zzb(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final void zzg(Object obj, Object obj2) {
        zzJ(obj);
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzB = zzB(i);
            long j = 1048575 & zzB;
            int i2 = this.zzc[i];
            switch (zzA(zzB)) {
                case 0:
                    if (zzT(obj2, i)) {
                        zzmy.zzo(obj, j, zzmy.zza(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzT(obj2, i)) {
                        zzmy.zzp(obj, j, zzmy.zzb(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzT(obj2, i)) {
                        zzmy.zzr(obj, j, zzmy.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzT(obj2, i)) {
                        zzmy.zzr(obj, j, zzmy.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzT(obj2, i)) {
                        zzmy.zzr(obj, j, zzmy.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzT(obj2, i)) {
                        zzmy.zzm(obj, j, zzmy.zzw(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzT(obj2, i)) {
                        zzmy.zzs(obj, j, zzmy.zzf(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzK(obj, obj2, i);
                    break;
                case 10:
                    if (zzT(obj2, i)) {
                        zzmy.zzs(obj, j, zzmy.zzf(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzT(obj2, i)) {
                        zzmy.zzr(obj, j, zzmy.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzT(obj2, i)) {
                        zzmy.zzr(obj, j, zzmy.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzK(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    this.zzm.zzb(obj, obj2, j);
                    break;
                case 50:
                    zzlz.zzaa(this.zzq, obj, obj2, j);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                case 53:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                case 55:
                case AuthBrowserView.TOOLBAR_HEIGHT_DP /* 56 */:
                case 57:
                case 58:
                case 59:
                    if (zzX(obj2, i2, i)) {
                        zzmy.zzs(obj, j, zzmy.zzf(obj2, j));
                        zzN(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzL(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case 63:
                case 64:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzX(obj2, i2, i)) {
                        zzmy.zzs(obj, j, zzmy.zzf(obj2, j));
                        zzN(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzL(obj, obj2, i);
                    break;
            }
        }
        zzlz.zzF(this.zzn, obj, obj2);
        if (this.zzh) {
            zzlz.zzE(this.zzo, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzir zzirVar) throws IOException {
        if (this.zzi) {
            zzu(obj, bArr, i, i2, zzirVar);
        } else {
            zzc(obj, bArr, i, i2, 0, zzirVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final void zzi(Object obj, zzng zzngVar) throws IOException {
        if (!this.zzi) {
            zzQ(obj, zzngVar);
            return;
        }
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzB = zzB(i);
            int i2 = this.zzc[i];
            switch (zzA(zzB)) {
                case 0:
                    if (zzT(obj, i)) {
                        zzngVar.zzf(i2, zzmy.zza(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzT(obj, i)) {
                        zzngVar.zzo(i2, zzmy.zzb(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzT(obj, i)) {
                        zzngVar.zzt(i2, zzmy.zzd(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzT(obj, i)) {
                        zzngVar.zzJ(i2, zzmy.zzd(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzT(obj, i)) {
                        zzngVar.zzr(i2, zzmy.zzc(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzT(obj, i)) {
                        zzngVar.zzm(i2, zzmy.zzd(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzT(obj, i)) {
                        zzngVar.zzk(i2, zzmy.zzc(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzT(obj, i)) {
                        zzngVar.zzb(i2, zzmy.zzw(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzT(obj, i)) {
                        zzZ(i2, zzmy.zzf(obj, zzB & 1048575), zzngVar);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (zzT(obj, i)) {
                        zzngVar.zzv(i2, zzmy.zzf(obj, zzB & 1048575), zzE(i));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzT(obj, i)) {
                        zzngVar.zzd(i2, (zzje) zzmy.zzf(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzT(obj, i)) {
                        zzngVar.zzH(i2, zzmy.zzc(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzT(obj, i)) {
                        zzngVar.zzi(i2, zzmy.zzc(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzT(obj, i)) {
                        zzngVar.zzw(i2, zzmy.zzc(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzT(obj, i)) {
                        zzngVar.zzy(i2, zzmy.zzd(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzT(obj, i)) {
                        zzngVar.zzA(i2, zzmy.zzc(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzT(obj, i)) {
                        zzngVar.zzC(i2, zzmy.zzd(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzT(obj, i)) {
                        zzngVar.zzq(i2, zzmy.zzf(obj, zzB & 1048575), zzE(i));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzlz.zzJ(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 19:
                    zzlz.zzN(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 20:
                    zzlz.zzQ(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 21:
                    zzlz.zzY(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 22:
                    zzlz.zzP(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 23:
                    zzlz.zzM(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 24:
                    zzlz.zzL(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 25:
                    zzlz.zzH(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 26:
                    zzlz.zzW(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar);
                    break;
                case 27:
                    zzlz.zzR(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, zzE(i));
                    break;
                case 28:
                    zzlz.zzI(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    zzlz.zzX(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 30:
                    zzlz.zzK(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 31:
                    zzlz.zzS(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 32:
                    zzlz.zzT(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 33:
                    zzlz.zzU(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 34:
                    zzlz.zzV(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                    break;
                case 35:
                    zzlz.zzJ(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 36:
                    zzlz.zzN(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 37:
                    zzlz.zzQ(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 38:
                    zzlz.zzY(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 39:
                    zzlz.zzP(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 40:
                    zzlz.zzM(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 41:
                    zzlz.zzL(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 42:
                    zzlz.zzH(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 43:
                    zzlz.zzX(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 44:
                    zzlz.zzK(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 45:
                    zzlz.zzS(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 46:
                    zzlz.zzT(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case 47:
                    zzlz.zzU(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    zzlz.zzV(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zzlz.zzO(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, zzE(i));
                    break;
                case 50:
                    zzR(zzngVar, i2, zzmy.zzf(obj, zzB & 1048575), i);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzf(i2, zzn(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzo(i2, zzo(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzt(i2, zzC(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzJ(i2, zzC(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzr(i2, zzr(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case AuthBrowserView.TOOLBAR_HEIGHT_DP /* 56 */:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzm(i2, zzC(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzk(i2, zzr(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzb(i2, zzY(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzX(obj, i2, i)) {
                        zzZ(i2, zzmy.zzf(obj, zzB & 1048575), zzngVar);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzv(i2, zzmy.zzf(obj, zzB & 1048575), zzE(i));
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzd(i2, (zzje) zzmy.zzf(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzH(i2, zzr(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzi(i2, zzr(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzw(i2, zzr(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzy(i2, zzC(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzA(i2, zzr(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzC(i2, zzC(obj, zzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzX(obj, i2, i)) {
                        zzngVar.zzq(i2, zzmy.zzf(obj, zzB & 1048575), zzE(i));
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzmo zzmoVar = this.zzn;
        zzmoVar.zzi(zzmoVar.zzd(obj), zzngVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final boolean zzj(Object obj, Object obj2) {
        boolean zzZ;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzB = zzB(i);
            long j = zzB & 1048575;
            switch (zzA(zzB)) {
                case 0:
                    if (zzS(obj, obj2, i) && Double.doubleToLongBits(zzmy.zza(obj, j)) == Double.doubleToLongBits(zzmy.zza(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzS(obj, obj2, i) && Float.floatToIntBits(zzmy.zzb(obj, j)) == Float.floatToIntBits(zzmy.zzb(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzS(obj, obj2, i) && zzmy.zzd(obj, j) == zzmy.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzS(obj, obj2, i) && zzmy.zzd(obj, j) == zzmy.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzS(obj, obj2, i) && zzmy.zzd(obj, j) == zzmy.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzS(obj, obj2, i) && zzmy.zzw(obj, j) == zzmy.zzw(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzS(obj, obj2, i) && zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzS(obj, obj2, i) && zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzS(obj, obj2, i) && zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzS(obj, obj2, i) && zzmy.zzd(obj, j) == zzmy.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzS(obj, obj2, i) && zzmy.zzd(obj, j) == zzmy.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzS(obj, obj2, i) && zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zzZ = zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j));
                    break;
                case 50:
                    zzZ = zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                case 53:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                case 55:
                case AuthBrowserView.TOOLBAR_HEIGHT_DP /* 56 */:
                case 57:
                case 58:
                case 59:
                case 60:
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case 63:
                case 64:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                case 68:
                    long zzy = zzy(i) & 1048575;
                    if (zzmy.zzc(obj, zzy) == zzmy.zzc(obj2, zzy) && zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzZ) {
                return false;
            }
        }
        if (!this.zzn.zzd(obj).equals(this.zzn.zzd(obj2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzo.zza(obj);
        this.zzo.zza(obj2);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final boolean zzk(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzk) {
            int i6 = this.zzj[i5];
            int i7 = this.zzc[i6];
            int zzB = zzB(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(obj, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & zzB) != 0 && !zzU(obj, i6, i, i2, i10)) {
                return false;
            }
            int zzA = zzA(zzB);
            if (zzA != 9 && zzA != 17) {
                if (zzA != 27) {
                    if (zzA == 60 || zzA == 68) {
                        if (zzX(obj, i7, i6) && !zzV(obj, zzB, zzE(i6))) {
                            return false;
                        }
                    } else if (zzA != 49) {
                        if (zzA == 50 && !((zzlg) zzmy.zzf(obj, zzB & 1048575)).isEmpty()) {
                            throw null;
                        }
                    }
                }
                List list = (List) zzmy.zzf(obj, zzB & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzlx zzE = zzE(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzE.zzk(list.get(i11))) {
                            return false;
                        }
                    }
                }
            } else if (zzU(obj, i6, i, i2, i10) && !zzV(obj, zzB, zzE(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzo.zza(obj);
        throw null;
    }
}
