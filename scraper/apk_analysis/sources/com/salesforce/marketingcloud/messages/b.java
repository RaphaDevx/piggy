package com.salesforce.marketingcloud.messages;

import android.text.TextUtils;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.storage.h;
import com.salesforce.marketingcloud.storage.i;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class b {
    private static final String a = d.B;

    private b() {
    }

    static void a(Message message, h hVar) throws Exception {
        Date date = new Date();
        com.salesforce.marketingcloud.internal.h.a(message, date);
        com.salesforce.marketingcloud.internal.h.c(message, com.salesforce.marketingcloud.internal.h.e(message) + 1);
        int b = b(message);
        if (b > -1 && message.numberOfPeriods() > -1 && message.periodType() != 0) {
            com.salesforce.marketingcloud.internal.h.b(message, com.salesforce.marketingcloud.internal.h.d(message) + 1);
            if (com.salesforce.marketingcloud.internal.h.d(message) >= message.messagesPerPeriod()) {
                int periodType = message.periodType();
                com.salesforce.marketingcloud.internal.h.b(message, new Date(date.getTime() + (message.numberOfPeriods() * (periodType != 1 ? periodType != 2 ? periodType != 3 ? periodType != 4 ? periodType != 5 ? 0L : TimeUnit.HOURS.toMillis(1L) : TimeUnit.DAYS.toMillis(1L) : TimeUnit.DAYS.toMillis(7L) : TimeUnit.DAYS.toMillis(Calendar.getInstance().getActualMaximum(5)) : TimeUnit.DAYS.toMillis(Calendar.getInstance().getActualMaximum(6))))));
                if (!message.isRollingPeriod()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(com.salesforce.marketingcloud.internal.h.b(message).getTime());
                    calendar.set(14, 0);
                    calendar.set(13, 0);
                    int periodType2 = message.periodType();
                    if (periodType2 == 1) {
                        calendar.set(2, 0);
                        calendar.set(5, 1);
                        calendar.set(10, 0);
                        calendar.set(12, 0);
                    } else if (periodType2 == 2) {
                        calendar.set(5, 1);
                        calendar.set(10, 0);
                        calendar.set(12, 0);
                    } else if (periodType2 == 3) {
                        calendar.set(7, 1);
                        calendar.set(10, 0);
                        calendar.set(12, 0);
                    } else if (periodType2 == 4) {
                        calendar.set(10, 0);
                        calendar.set(12, 0);
                    } else if (periodType2 == 5) {
                        calendar.set(12, 0);
                    }
                    com.salesforce.marketingcloud.internal.h.b(message, calendar.getTime());
                }
            }
        }
        if (com.salesforce.marketingcloud.internal.h.d(message) > -1 && b > -1 && com.salesforce.marketingcloud.internal.h.d(message) > b) {
            com.salesforce.marketingcloud.internal.h.b(message, 0);
        }
        hVar.n().a(message, hVar.b());
    }

    private static int b(Message message) {
        int messagesPerPeriod = message.messagesPerPeriod();
        if (messagesPerPeriod > 0 || message.numberOfPeriods() <= 0 || message.periodType() == 0) {
            return messagesPerPeriod;
        }
        return 1;
    }

    static boolean c(Message message) {
        try {
            if (TextUtils.isEmpty(message.alert().trim())) {
                g.a(a, "Message (%s) was tripped, but does not have an alert message", message.id());
                return false;
            }
            Date date = new Date();
            if (message.endDateUtc() != null && message.endDateUtc().before(date)) {
                g.a(a, "Message (%s) was tripped, but has expired.", message.id());
                return false;
            }
            if (message.startDateUtc() != null && message.startDateUtc().after(date)) {
                g.a(a, "Message (%s) was tripped, but has not started", message.id());
                return false;
            }
            if (message.messageLimit() > -1 && com.salesforce.marketingcloud.internal.h.e(message) >= message.messageLimit()) {
                g.a(a, "Message (%s) was tripped, but has met its message limit.", message.id());
                return false;
            }
            int b = b(message);
            if (b > -1 && com.salesforce.marketingcloud.internal.h.d(message) >= b && com.salesforce.marketingcloud.internal.h.b(message) != null && date.before(com.salesforce.marketingcloud.internal.h.b(message))) {
                g.a(a, "Message (%s) was tripped, but has met its message per period limit", message.id());
                return false;
            }
            if (com.salesforce.marketingcloud.internal.h.b(message) == null || !date.before(com.salesforce.marketingcloud.internal.h.b(message))) {
                return true;
            }
            g.a(a, "Message (%s) was tripped, but was before its next allowed show time.", message.id());
            return false;
        } catch (Exception e) {
            g.b(a, e, "Failed to determine is message should be shown.", new Object[0]);
            return false;
        }
    }

    public static void a(Message message, i iVar, Crypto crypto) {
        Message a2 = iVar.a(message.id(), crypto);
        if (a2 != null) {
            com.salesforce.marketingcloud.internal.h.a(message, com.salesforce.marketingcloud.internal.h.a(a2));
            com.salesforce.marketingcloud.internal.h.c(message, com.salesforce.marketingcloud.internal.h.e(a2));
            if (message.periodType() == a2.periodType()) {
                com.salesforce.marketingcloud.internal.h.b(message, com.salesforce.marketingcloud.internal.h.d(a2));
                com.salesforce.marketingcloud.internal.h.b(message, com.salesforce.marketingcloud.internal.h.b(a2));
            }
        }
    }

    public static boolean a(Message message) {
        Date endDateUtc = message.endDateUtc();
        return endDateUtc == null || endDateUtc.getTime() >= System.currentTimeMillis();
    }
}
