package com.salesforce.marketingcloud;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.PowerManager;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.lang3.time.DateUtils;

/* loaded from: classes2.dex */
abstract class c extends Service {
    static final String h = com.salesforce.marketingcloud.g.a("JobIntentService");
    static final Object i = new Object();
    static final HashMap<ComponentName, h> j = new HashMap<>();
    final ArrayList<d> a = null;
    b b;
    h c;
    a d;
    boolean e;
    boolean f;
    boolean g;

    final class a extends AsyncTask<Void, Void, Void> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            e a;
            try {
                com.salesforce.marketingcloud.g.a(c.h, "Starting to dequeue work...", new Object[0]);
                while (!isCancelled() && (a = c.this.a()) != null) {
                    String str = c.h;
                    com.salesforce.marketingcloud.g.a(str, "Processing next work: %s", a);
                    c.this.a(a.b());
                    com.salesforce.marketingcloud.g.a(str, "Completing work: %s", a);
                    a.a();
                }
                com.salesforce.marketingcloud.g.a(c.h, "Done processing work!", new Object[0]);
                return null;
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(c.h, e, "Exception thrown by JobIntentService", new Object[0]);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onCancelled(Void r1) {
            c.this.e();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            c.this.e();
        }
    }

    interface b {
        e a();

        IBinder b();
    }

    final class d implements e {
        final Intent a;
        final int b;

        d(Intent intent, int i) {
            this.a = intent;
            this.b = i;
        }

        @Override // com.salesforce.marketingcloud.c.e
        public void a() {
            com.salesforce.marketingcloud.g.a(c.h, "Stopping self: #%d", Integer.valueOf(this.b));
            c.this.stopSelf(this.b);
        }

        @Override // com.salesforce.marketingcloud.c.e
        public Intent b() {
            return this.a;
        }
    }

    interface e {
        void a();

        Intent b();
    }

    static final class f extends JobServiceEngine implements b {
        static final String d = com.salesforce.marketingcloud.g.a("JobServiceEngineImpl");
        final c a;
        final Object b;
        JobParameters c;

        final class a implements e {
            final JobWorkItem a;

            a(JobWorkItem jobWorkItem) {
                this.a = jobWorkItem;
            }

            @Override // com.salesforce.marketingcloud.c.e
            public void a() {
                synchronized (f.this.b) {
                    JobParameters jobParameters = f.this.c;
                    if (jobParameters != null) {
                        jobParameters.completeWork(this.a);
                    }
                }
            }

            @Override // com.salesforce.marketingcloud.c.e
            public Intent b() {
                return this.a.getIntent();
            }
        }

        f(c cVar) {
            super(cVar);
            this.b = new Object();
            this.a = cVar;
        }

        @Override // com.salesforce.marketingcloud.c.b
        public e a() {
            synchronized (this.b) {
                JobParameters jobParameters = this.c;
                if (jobParameters == null) {
                    return null;
                }
                JobWorkItem dequeueWork = jobParameters.dequeueWork();
                if (dequeueWork == null) {
                    return null;
                }
                dequeueWork.getIntent().setExtrasClassLoader(this.a.getClassLoader());
                return new a(dequeueWork);
            }
        }

        @Override // com.salesforce.marketingcloud.c.b
        public IBinder b() {
            return getBinder();
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            com.salesforce.marketingcloud.g.a(d, "onStartJob: %s", jobParameters);
            this.c = jobParameters;
            this.a.a(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            com.salesforce.marketingcloud.g.a(d, "onStartJob: %s", jobParameters);
            boolean b = this.a.b();
            synchronized (this.b) {
                this.c = null;
            }
            return b;
        }
    }

    static final class g extends h {
        private final JobInfo d;
        private final JobScheduler e;

        g(Context context, ComponentName componentName, int i) {
            super(componentName);
            a(i);
            this.d = new JobInfo.Builder(i, this.a).setOverrideDeadline(0L).build();
            this.e = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        @Override // com.salesforce.marketingcloud.c.h
        void a(Intent intent) {
            com.salesforce.marketingcloud.g.a(c.h, "Enqueueing work: %s", intent);
            try {
                this.e.enqueue(this.d, new JobWorkItem(intent));
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(c.h, e, "Unable to enqueue %s for work %s", Integer.valueOf(this.c), intent);
            }
        }
    }

    static abstract class h {
        final ComponentName a;
        boolean b;
        int c;

        h(ComponentName componentName) {
            this.a = componentName;
        }

        public void a() {
        }

        abstract void a(Intent intent);

        public void b() {
        }

        public void c() {
        }

        void a(int i) {
            if (!this.b) {
                this.b = true;
                this.c = i;
            } else {
                if (this.c == i) {
                    return;
                }
                throw new IllegalArgumentException("Given job ID " + i + " is different than previous " + this.c);
            }
        }
    }

    public static void a(Context context, Class cls, int i2, Intent intent) {
        a(context, new ComponentName(context, (Class<?>) cls), i2, intent);
    }

    protected abstract void a(Intent intent);

    public void b(boolean z) {
        this.e = z;
    }

    public boolean c() {
        return this.f;
    }

    public boolean d() {
        return true;
    }

    void e() {
        ArrayList<d> arrayList = this.a;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.d = null;
                ArrayList<d> arrayList2 = this.a;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    a(false);
                } else if (!this.g) {
                    this.c.a();
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        b bVar = this.b;
        if (bVar == null) {
            return null;
        }
        IBinder b2 = bVar.b();
        com.salesforce.marketingcloud.g.a(h, "Returning engine: %s", b2);
        return b2;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        com.salesforce.marketingcloud.g.a(h, "CREATING: %s", this);
        this.b = new f(this);
        this.c = null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        ArrayList<d> arrayList = this.a;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.g = true;
                this.c.a();
            }
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (this.a == null) {
            com.salesforce.marketingcloud.g.a(h, "Ignoring start command: %s", intent);
            return 2;
        }
        this.c.c();
        com.salesforce.marketingcloud.g.a(h, "Received compat start command #%d: %s", Integer.valueOf(i3), intent);
        synchronized (this.a) {
            ArrayList<d> arrayList = this.a;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new d(intent, i3));
            a(true);
        }
        return 3;
    }

    public static void a(Context context, ComponentName componentName, int i2, Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("work must not be null");
        }
        synchronized (i) {
            h a2 = a(context, componentName, true, i2);
            a2.a(i2);
            a2.a(intent);
        }
    }

    boolean b() {
        a aVar = this.d;
        if (aVar != null) {
            aVar.cancel(this.e);
        }
        this.f = true;
        return d();
    }

    static h a(Context context, ComponentName componentName, boolean z, int i2) {
        HashMap<ComponentName, h> hashMap = j;
        h hVar = hashMap.get(componentName);
        if (hVar != null) {
            return hVar;
        }
        if (z) {
            g gVar = new g(context, componentName, i2);
            hashMap.put(componentName, gVar);
            return gVar;
        }
        throw new IllegalArgumentException("Can't be here without a job id");
    }

    /* renamed from: com.salesforce.marketingcloud.c$c, reason: collision with other inner class name */
    static final class C0050c extends h {
        private final Context d;
        private final PowerManager.WakeLock e;
        private final PowerManager.WakeLock f;
        boolean g;
        boolean h;

        C0050c(Context context, ComponentName componentName) {
            super(componentName);
            this.d = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.e = newWakeLock;
            newWakeLock.setReferenceCounted(false);
            PowerManager.WakeLock newWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.f = newWakeLock2;
            newWakeLock2.setReferenceCounted(false);
        }

        @Override // com.salesforce.marketingcloud.c.h
        void a(Intent intent) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(this.a);
            com.salesforce.marketingcloud.g.a(c.h, "Starting service for work: %s", intent);
            if (this.d.startService(intent2) != null) {
                synchronized (this) {
                    if (!this.g) {
                        this.g = true;
                        if (!this.h) {
                            this.e.acquire(DateUtils.MILLIS_PER_MINUTE);
                        }
                    }
                }
            }
        }

        @Override // com.salesforce.marketingcloud.c.h
        public void b() {
            synchronized (this) {
                if (!this.h) {
                    this.h = true;
                    this.f.acquire(600000L);
                    this.e.release();
                }
            }
        }

        @Override // com.salesforce.marketingcloud.c.h
        public void c() {
            synchronized (this) {
                this.g = false;
            }
        }

        @Override // com.salesforce.marketingcloud.c.h
        public void a() {
            synchronized (this) {
                if (this.h) {
                    if (this.g) {
                        this.e.acquire(DateUtils.MILLIS_PER_MINUTE);
                    }
                    this.h = false;
                    this.f.release();
                }
            }
        }
    }

    void a(boolean z) {
        if (this.d == null) {
            this.d = new a();
            h hVar = this.c;
            if (hVar != null && z) {
                hVar.b();
            }
            com.salesforce.marketingcloud.g.a(h, "Starting processor: %s", this.d);
            this.d.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    e a() {
        b bVar = this.b;
        if (bVar != null) {
            return bVar.a();
        }
        synchronized (this.a) {
            if (this.a.size() <= 0) {
                return null;
            }
            return this.a.remove(0);
        }
    }
}
