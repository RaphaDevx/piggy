package com.google.android.gms.libs.identity;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzad;
import com.google.android.gms.location.zzo;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes3.dex */
public final class zzdz extends GmsClient {
    public static final /* synthetic */ int zze = 0;
    private final SimpleArrayMap zzf;
    private final SimpleArrayMap zzg;
    private final SimpleArrayMap zzh;
    private final SimpleArrayMap zzi;

    public zzdz(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 23, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzf = new SimpleArrayMap();
        this.zzg = new SimpleArrayMap();
        this.zzh = new SimpleArrayMap();
        this.zzi = new SimpleArrayMap();
    }

    private final boolean zzG(Feature feature) {
        Feature feature2;
        Feature[] availableFeatures = getAvailableFeatures();
        if (availableFeatures != null) {
            int i = 0;
            while (true) {
                if (i >= availableFeatures.length) {
                    feature2 = null;
                    break;
                }
                feature2 = availableFeatures[i];
                if (feature.getName().equals(feature2.getName())) {
                    break;
                }
                i++;
            }
            if (feature2 != null && feature2.getVersion() >= feature.getVersion()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return queryLocalInterface instanceof zzv ? (zzv) queryLocalInterface : new zzu(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return zzo.zzp;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 11717000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void onConnectionSuspended(int i) {
        super.onConnectionSuspended(i);
        synchronized (this.zzf) {
            this.zzf.clear();
        }
        synchronized (this.zzg) {
            this.zzg.clear();
        }
        synchronized (this.zzh) {
            this.zzh.clear();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }

    public final void zzA(Location location, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzh)) {
            ((zzv) getService()).zzB(location, new zzdj(null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzA(location);
            taskCompletionSource.setResult(null);
        }
    }

    public final void zzB(TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzv) getService()).zzC(new zzdn(null, taskCompletionSource));
    }

    public final void zzC(ListenerHolder listenerHolder, DeviceOrientationRequest deviceOrientationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ListenerHolder.ListenerKey listenerKey = (ListenerHolder.ListenerKey) Objects.requireNonNull(listenerHolder.getListenerKey());
        synchronized (this.zzh) {
            zzdq zzdqVar = (zzdq) this.zzh.get(listenerKey);
            if (zzdqVar == null) {
                zzdqVar = new zzdq(listenerHolder);
                this.zzh.put(listenerKey, zzdqVar);
            } else {
                zzdqVar.zzc(listenerHolder);
            }
            ((zzv) getService()).zzF(new zzj(1, new zzh(deviceOrientationRequest, zzh.zza, null), zzdqVar, new zzdn(null, taskCompletionSource)));
        }
    }

    public final void zzD(ListenerHolder.ListenerKey listenerKey, TaskCompletionSource taskCompletionSource) throws RemoteException {
        synchronized (this.zzh) {
            zzdq zzdqVar = (zzdq) this.zzh.remove(listenerKey);
            if (zzdqVar == null) {
                taskCompletionSource.setResult(Boolean.FALSE);
            } else {
                zzdqVar.zze();
                ((zzv) getService()).zzF(new zzj(2, null, zzdqVar, new zzdn(Boolean.TRUE, taskCompletionSource)));
            }
        }
    }

    public final void zzE(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzn)) {
            ((zzv) getService()).zze(geofencingRequest, pendingIntent, new zzdj(null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzd(geofencingRequest, pendingIntent, new zzdg(taskCompletionSource));
        }
    }

    public final void zzF(zzem zzemVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzn)) {
            ((zzv) getService()).zzg(zzemVar, new zzdj(null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzf(zzemVar, new zzdg(taskCompletionSource));
        }
    }

    public final void zzp(zzad zzadVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzo(zzadVar, new zzee(5, null, new zzdl(taskCompletionSource), null, null));
        } else {
            taskCompletionSource.setResult(((zzv) getService()).zzp(getContext().getPackageName()));
        }
    }

    public final void zzq(LastLocationRequest lastLocationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzq(lastLocationRequest, zzee.zzd(new zzdk(taskCompletionSource)));
        } else if (zzG(zzo.zzf)) {
            ((zzv) getService()).zzr(lastLocationRequest, new zzdk(taskCompletionSource));
        } else {
            taskCompletionSource.setResult(((zzv) getService()).zzs());
        }
    }

    public final void zzr(CurrentLocationRequest currentLocationRequest, CancellationToken cancellationToken, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            final ICancelToken zzt = ((zzv) getService()).zzt(currentLocationRequest, zzee.zzd(new zzdk(taskCompletionSource)));
            if (cancellationToken != null) {
                cancellationToken.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.gms.internal.location.zzed
                    @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                    public final /* synthetic */ void onCanceled() {
                        int i = zzdz.zze;
                        try {
                            ICancelToken.this.cancel();
                        } catch (RemoteException unused) {
                        }
                    }
                });
                return;
            }
            return;
        }
        if (zzG(zzo.zze)) {
            final ICancelToken zzu = ((zzv) getService()).zzu(currentLocationRequest, new zzdk(taskCompletionSource));
            if (cancellationToken != null) {
                cancellationToken.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.gms.internal.location.zzeb
                    @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                    public final /* synthetic */ void onCanceled() {
                        int i = zzdz.zze;
                        try {
                            ICancelToken.this.cancel();
                        } catch (RemoteException unused) {
                        }
                    }
                });
                return;
            }
            return;
        }
        ListenerHolder createListenerHolder = ListenerHolders.createListenerHolder(new zzdh(this, taskCompletionSource), zzfc.zza(), "GetCurrentLocation");
        final ListenerHolder.ListenerKey listenerKey = (ListenerHolder.ListenerKey) Objects.requireNonNull(createListenerHolder.getListenerKey());
        zzdi zzdiVar = new zzdi(this, createListenerHolder, taskCompletionSource);
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        LocationRequest.Builder builder = new LocationRequest.Builder(currentLocationRequest.getPriority(), 0L);
        builder.setMinUpdateIntervalMillis(0L);
        builder.setDurationMillis(currentLocationRequest.getDurationMillis());
        builder.setGranularity(currentLocationRequest.getGranularity());
        builder.setMaxUpdateAgeMillis(currentLocationRequest.getMaxUpdateAgeMillis());
        builder.zzb(currentLocationRequest.zza());
        builder.zza(currentLocationRequest.zzb());
        builder.setWaitForAccurateLocation(true);
        builder.zzc(currentLocationRequest.zzc());
        zzt(zzdiVar, builder.build(), taskCompletionSource2);
        taskCompletionSource2.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.gms.internal.location.zzea
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final /* synthetic */ void onComplete(Task task) {
                int i = zzdz.zze;
                if (task.isSuccessful()) {
                    return;
                }
                TaskCompletionSource.this.trySetException((Exception) Objects.requireNonNull(task.getException()));
            }
        });
        if (cancellationToken != null) {
            cancellationToken.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.gms.internal.location.zzec
                @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                public final /* synthetic */ void onCanceled() {
                    try {
                        zzdz.this.zzw(listenerKey, true, new TaskCompletionSource());
                    } catch (RemoteException unused) {
                    }
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0040 A[Catch: all -> 0x0078, TryCatch #0 {, blocks: (B:4:0x001d, B:8:0x002b, B:10:0x0040, B:11:0x0076, B:15:0x0057, B:16:0x0031), top: B:3:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0057 A[Catch: all -> 0x0078, TryCatch #0 {, blocks: (B:4:0x001d, B:8:0x002b, B:10:0x0040, B:11:0x0076, B:15:0x0057, B:16:0x0031), top: B:3:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzs(com.google.android.gms.libs.identity.zzdr r18, com.google.android.gms.location.LocationRequest r19, com.google.android.gms.tasks.TaskCompletionSource r20) throws android.os.RemoteException {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            r2 = r20
            com.google.android.gms.common.api.internal.ListenerHolder r3 = r18.zza()
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r4 = r3.getListenerKey()
            java.lang.Object r4 = java.util.Objects.requireNonNull(r4)
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r4 = (com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey) r4
            com.google.android.gms.common.Feature r5 = com.google.android.gms.location.zzo.zzj
            boolean r5 = r0.zzG(r5)
            androidx.collection.SimpleArrayMap r6 = r0.zzf
            monitor-enter(r6)
            androidx.collection.SimpleArrayMap r7 = r0.zzf     // Catch: java.lang.Throwable -> L78
            java.lang.Object r7 = r7.get(r4)     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzdy r7 = (com.google.android.gms.libs.identity.zzdy) r7     // Catch: java.lang.Throwable -> L78
            r8 = 0
            if (r7 == 0) goto L31
            if (r5 == 0) goto L2b
            goto L31
        L2b:
            r7.zzc(r3)     // Catch: java.lang.Throwable -> L78
            r12 = r7
            r7 = r8
            goto L3e
        L31:
            com.google.android.gms.internal.location.zzdy r3 = new com.google.android.gms.internal.location.zzdy     // Catch: java.lang.Throwable -> L78
            r9 = r18
            r3.<init>(r9)     // Catch: java.lang.Throwable -> L78
            androidx.collection.SimpleArrayMap r9 = r0.zzf     // Catch: java.lang.Throwable -> L78
            r9.put(r4, r3)     // Catch: java.lang.Throwable -> L78
            r12 = r3
        L3e:
            if (r5 == 0) goto L57
            android.os.IInterface r0 = r17.getService()     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzv r0 = (com.google.android.gms.libs.identity.zzv) r0     // Catch: java.lang.Throwable -> L78
            java.lang.String r3 = r4.toIdString()     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzee r3 = com.google.android.gms.libs.identity.zzee.zza(r7, r12, r3)     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzdj r4 = new com.google.android.gms.internal.location.zzdj     // Catch: java.lang.Throwable -> L78
            r4.<init>(r8, r2)     // Catch: java.lang.Throwable -> L78
            r0.zzw(r3, r1, r4)     // Catch: java.lang.Throwable -> L78
            goto L76
        L57:
            android.os.IInterface r0 = r17.getService()     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzv r0 = (com.google.android.gms.libs.identity.zzv) r0     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzeg r11 = com.google.android.gms.libs.identity.zzeg.zza(r8, r1)     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzdo r15 = new com.google.android.gms.internal.location.zzdo     // Catch: java.lang.Throwable -> L78
            r15.<init>(r2, r12)     // Catch: java.lang.Throwable -> L78
            java.lang.String r16 = r4.toIdString()     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzei r1 = new com.google.android.gms.internal.location.zzei     // Catch: java.lang.Throwable -> L78
            r13 = 0
            r14 = 0
            r10 = 1
            r9 = r1
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)     // Catch: java.lang.Throwable -> L78
            r0.zzv(r1)     // Catch: java.lang.Throwable -> L78
        L76:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L78
            return
        L78:
            r0 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L78
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.libs.identity.zzdz.zzs(com.google.android.gms.internal.location.zzdr, com.google.android.gms.location.LocationRequest, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0040 A[Catch: all -> 0x0078, TryCatch #0 {, blocks: (B:4:0x001d, B:8:0x002b, B:10:0x0040, B:11:0x0076, B:15:0x0057, B:16:0x0031), top: B:3:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0057 A[Catch: all -> 0x0078, TryCatch #0 {, blocks: (B:4:0x001d, B:8:0x002b, B:10:0x0040, B:11:0x0076, B:15:0x0057, B:16:0x0031), top: B:3:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzt(com.google.android.gms.libs.identity.zzdr r18, com.google.android.gms.location.LocationRequest r19, com.google.android.gms.tasks.TaskCompletionSource r20) throws android.os.RemoteException {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            r2 = r20
            com.google.android.gms.common.api.internal.ListenerHolder r3 = r18.zza()
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r4 = r3.getListenerKey()
            java.lang.Object r4 = java.util.Objects.requireNonNull(r4)
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r4 = (com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey) r4
            com.google.android.gms.common.Feature r5 = com.google.android.gms.location.zzo.zzj
            boolean r5 = r0.zzG(r5)
            androidx.collection.SimpleArrayMap r6 = r0.zzg
            monitor-enter(r6)
            androidx.collection.SimpleArrayMap r7 = r0.zzg     // Catch: java.lang.Throwable -> L78
            java.lang.Object r7 = r7.get(r4)     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzdv r7 = (com.google.android.gms.libs.identity.zzdv) r7     // Catch: java.lang.Throwable -> L78
            r8 = 0
            if (r7 == 0) goto L31
            if (r5 == 0) goto L2b
            goto L31
        L2b:
            r7.zzc(r3)     // Catch: java.lang.Throwable -> L78
            r13 = r7
            r7 = r8
            goto L3e
        L31:
            com.google.android.gms.internal.location.zzdv r3 = new com.google.android.gms.internal.location.zzdv     // Catch: java.lang.Throwable -> L78
            r9 = r18
            r3.<init>(r9)     // Catch: java.lang.Throwable -> L78
            androidx.collection.SimpleArrayMap r9 = r0.zzg     // Catch: java.lang.Throwable -> L78
            r9.put(r4, r3)     // Catch: java.lang.Throwable -> L78
            r13 = r3
        L3e:
            if (r5 == 0) goto L57
            android.os.IInterface r0 = r17.getService()     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzv r0 = (com.google.android.gms.libs.identity.zzv) r0     // Catch: java.lang.Throwable -> L78
            java.lang.String r3 = r4.toIdString()     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzee r3 = com.google.android.gms.libs.identity.zzee.zzb(r7, r13, r3)     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzdj r4 = new com.google.android.gms.internal.location.zzdj     // Catch: java.lang.Throwable -> L78
            r4.<init>(r8, r2)     // Catch: java.lang.Throwable -> L78
            r0.zzw(r3, r1, r4)     // Catch: java.lang.Throwable -> L78
            goto L76
        L57:
            android.os.IInterface r0 = r17.getService()     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzv r0 = (com.google.android.gms.libs.identity.zzv) r0     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzeg r11 = com.google.android.gms.libs.identity.zzeg.zza(r8, r1)     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzdd r15 = new com.google.android.gms.internal.location.zzdd     // Catch: java.lang.Throwable -> L78
            r15.<init>(r2, r13)     // Catch: java.lang.Throwable -> L78
            java.lang.String r16 = r4.toIdString()     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.location.zzei r1 = new com.google.android.gms.internal.location.zzei     // Catch: java.lang.Throwable -> L78
            r12 = 0
            r14 = 0
            r10 = 1
            r9 = r1
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)     // Catch: java.lang.Throwable -> L78
            r0.zzv(r1)     // Catch: java.lang.Throwable -> L78
        L76:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L78
            return
        L78:
            r0 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L78
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.libs.identity.zzdz.zzt(com.google.android.gms.internal.location.zzdr, com.google.android.gms.location.LocationRequest, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    public final void zzu(PendingIntent pendingIntent, LocationRequest locationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzw(zzee.zzc(pendingIntent), locationRequest, new zzdj(null, taskCompletionSource));
            return;
        }
        zzv zzvVar = (zzv) getService();
        zzeg zza = zzeg.zza(null, locationRequest);
        zzdn zzdnVar = new zzdn(null, taskCompletionSource);
        int hashCode = pendingIntent.hashCode();
        StringBuilder sb = new StringBuilder(String.valueOf(hashCode).length() + 14);
        sb.append("PendingIntent@");
        sb.append(hashCode);
        zzvVar.zzv(new zzei(1, zza, null, null, pendingIntent, zzdnVar, sb.toString()));
    }

    public final void zzv(ListenerHolder.ListenerKey listenerKey, boolean z, TaskCompletionSource taskCompletionSource) throws RemoteException {
        synchronized (this.zzf) {
            zzdy zzdyVar = (zzdy) this.zzf.remove(listenerKey);
            if (zzdyVar == null) {
                taskCompletionSource.setResult(Boolean.FALSE);
                return;
            }
            zzdyVar.zzf();
            if (!z) {
                taskCompletionSource.setResult(Boolean.TRUE);
            } else if (zzG(zzo.zzj)) {
                zzv zzvVar = (zzv) getService();
                int identityHashCode = System.identityHashCode(zzdyVar);
                StringBuilder sb = new StringBuilder(String.valueOf(identityHashCode).length() + 18);
                sb.append("ILocationListener@");
                sb.append(identityHashCode);
                zzvVar.zzx(zzee.zza(null, zzdyVar, sb.toString()), new zzdj(Boolean.TRUE, taskCompletionSource));
            } else {
                ((zzv) getService()).zzv(new zzei(2, null, zzdyVar, null, null, new zzdn(Boolean.TRUE, taskCompletionSource), null));
            }
        }
    }

    public final void zzw(ListenerHolder.ListenerKey listenerKey, boolean z, TaskCompletionSource taskCompletionSource) throws RemoteException {
        synchronized (this.zzg) {
            zzdv zzdvVar = (zzdv) this.zzg.remove(listenerKey);
            if (zzdvVar == null) {
                taskCompletionSource.setResult(Boolean.FALSE);
                return;
            }
            zzdvVar.zzg();
            if (!z) {
                taskCompletionSource.setResult(Boolean.TRUE);
            } else if (zzG(zzo.zzj)) {
                zzv zzvVar = (zzv) getService();
                int identityHashCode = System.identityHashCode(zzdvVar);
                StringBuilder sb = new StringBuilder(String.valueOf(identityHashCode).length() + 18);
                sb.append("ILocationCallback@");
                sb.append(identityHashCode);
                zzvVar.zzx(zzee.zzb(null, zzdvVar, sb.toString()), new zzdj(Boolean.TRUE, taskCompletionSource));
            } else {
                ((zzv) getService()).zzv(new zzei(2, null, null, zzdvVar, null, new zzdn(Boolean.TRUE, taskCompletionSource), null));
            }
        }
    }

    public final void zzx(PendingIntent pendingIntent, TaskCompletionSource taskCompletionSource, Object obj) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzx(zzee.zzc(pendingIntent), new zzdj(null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzv(new zzei(2, null, null, null, pendingIntent, new zzdn(null, taskCompletionSource), null));
        }
    }

    public final void zzy(TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzg)) {
            ((zzv) getService()).zzz(true, new zzdj(null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzy(true);
            taskCompletionSource.setResult(null);
        }
    }

    public final void zzz(TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzg)) {
            ((zzv) getService()).zzz(false, new zzdj(true, taskCompletionSource));
        } else {
            ((zzv) getService()).zzy(false);
            taskCompletionSource.setResult(true);
        }
    }
}
