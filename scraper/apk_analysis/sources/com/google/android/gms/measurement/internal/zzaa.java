package com.google.android.gms.measurement.internal;

import java.util.BitSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes3.dex */
final class zzaa extends zzkh {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    zzaa(zzkt zzktVar) {
        super(zzktVar);
    }

    private final zzu zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return (zzu) this.zzc.get(num);
        }
        zzu zzuVar = new zzu(this, this.zza, null);
        this.zzc.put(num, zzuVar);
        return zzuVar;
    }

    private final boolean zzf(int i, int i2) {
        BitSet bitSet;
        zzu zzuVar = (zzu) this.zzc.get(Integer.valueOf(i));
        if (zzuVar == null) {
            return false;
        }
        bitSet = zzuVar.zze;
        return bitSet.get(i2);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:(6:19|20|21|22|23|(21:(7:25|26|27|28|(1:30)(3:519|(1:521)(1:523)|522)|31|(1:34)(1:33))|(1:36)|37|38|39|40|41|42|(3:44|(1:46)|47)(4:477|(6:478|479|480|481|482|(1:485)(1:484))|(1:487)|488)|48|(1:50)(6:285|(6:287|288|289|290|291|(1:(3:293|(1:295)|296))(1:462))(1:476)|305|(10:308|(3:312|(4:315|(5:317|318|(1:320)(1:324)|321|322)(1:325)|323|313)|326)|327|(3:331|(4:334|(3:339|340|341)|342|332)|345)|346|(3:348|(6:351|(2:353|(3:355|356|357))(1:360)|358|359|357|349)|361)|362|(3:371|(8:374|(1:376)|377|(1:379)|380|(3:382|383|384)(1:386)|385|372)|387)|388|306)|394|395)|51|(3:182|(4:185|(10:187|188|(1:190)(1:282)|191|(10:193|194|195|196|197|198|199|200|(4:202|(11:203|204|205|206|207|208|209|(3:211|212|213)(1:256)|214|215|(1:218)(1:217))|(1:220)|221)(2:263|264)|222)(1:281)|223|(4:226|(3:244|245|246)(6:228|229|(2:230|(2:232|(1:234)(2:235|236))(2:242|243))|(1:238)|239|240)|241|224)|247|248|249)(1:283)|250|183)|284)|53|54|(3:81|(6:84|(7:86|87|88|89|90|(3:(9:92|93|94|95|96|(1:98)(1:157)|99|100|(1:103)(1:102))|(1:105)|106)(2:164|165)|107)(1:180)|108|(2:109|(2:111|(3:147|148|149)(6:113|(2:114|(4:116|(3:118|(1:120)(1:143)|121)(1:144)|122|(1:1)(2:126|(1:128)(2:129|130)))(2:145|146))|(2:135|134)|132|133|134))(0))|150|82)|181)|56|57|(9:60|61|62|63|64|65|(2:67|68)(1:70)|69|58)|78|79)(1:527))|41|42|(0)(0)|48|(0)(0)|51|(0)|53|54|(0)|56|57|(1:58)|78|79) */
    /* JADX WARN: Can't wrap try/catch for region: R(27:0|1|(2:2|(2:4|(2:6|7)(1:543))(2:544|545))|8|(3:10|11|12)|16|(6:19|20|21|22|23|(21:(7:25|26|27|28|(1:30)(3:519|(1:521)(1:523)|522)|31|(1:34)(1:33))|(1:36)|37|38|39|40|41|42|(3:44|(1:46)|47)(4:477|(6:478|479|480|481|482|(1:485)(1:484))|(1:487)|488)|48|(1:50)(6:285|(6:287|288|289|290|291|(1:(3:293|(1:295)|296))(1:462))(1:476)|305|(10:308|(3:312|(4:315|(5:317|318|(1:320)(1:324)|321|322)(1:325)|323|313)|326)|327|(3:331|(4:334|(3:339|340|341)|342|332)|345)|346|(3:348|(6:351|(2:353|(3:355|356|357))(1:360)|358|359|357|349)|361)|362|(3:371|(8:374|(1:376)|377|(1:379)|380|(3:382|383|384)(1:386)|385|372)|387)|388|306)|394|395)|51|(3:182|(4:185|(10:187|188|(1:190)(1:282)|191|(10:193|194|195|196|197|198|199|200|(4:202|(11:203|204|205|206|207|208|209|(3:211|212|213)(1:256)|214|215|(1:218)(1:217))|(1:220)|221)(2:263|264)|222)(1:281)|223|(4:226|(3:244|245|246)(6:228|229|(2:230|(2:232|(1:234)(2:235|236))(2:242|243))|(1:238)|239|240)|241|224)|247|248|249)(1:283)|250|183)|284)|53|54|(3:81|(6:84|(7:86|87|88|89|90|(3:(9:92|93|94|95|96|(1:98)(1:157)|99|100|(1:103)(1:102))|(1:105)|106)(2:164|165)|107)(1:180)|108|(2:109|(2:111|(3:147|148|149)(6:113|(2:114|(4:116|(3:118|(1:120)(1:143)|121)(1:144)|122|(1:1)(2:126|(1:128)(2:129|130)))(2:145|146))|(2:135|134)|132|133|134))(0))|150|82)|181)|56|57|(9:60|61|62|63|64|65|(2:67|68)(1:70)|69|58)|78|79)(1:527))|542|38|39|40|41|42|(0)(0)|48|(0)(0)|51|(0)|53|54|(0)|56|57|(1:58)|78|79|(5:(0)|(0)|(0)|(0)|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0a39, code lost:
    
        r0 = r63.zzt.zzay().zzk();
        r6 = com.google.android.gms.measurement.internal.zzeh.zzn(r63.zza);
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0a4d, code lost:
    
        if (r8.zzj() == false) goto L413;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0a4f, code lost:
    
        r7 = java.lang.Integer.valueOf(r8.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0a59, code lost:
    
        r0.zzc("Invalid property filter ID. appId, id", r6, java.lang.String.valueOf(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0a58, code lost:
    
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0949, code lost:
    
        if (r13 == null) goto L377;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0923, code lost:
    
        r13.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0921, code lost:
    
        if (r13 != null) goto L365;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x07ac, code lost:
    
        if (r5 != null) goto L292;
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x077c, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x077a, code lost:
    
        if (r5 != null) goto L292;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x02bf, code lost:
    
        if (r5 != null) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:0x02c1, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x02f1, code lost:
    
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r13);
        r1 = new androidx.collection.ArrayMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x0300, code lost:
    
        if (r13.isEmpty() == false) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x0302, code lost:
    
        r20 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x0446, code lost:
    
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:396:0x0306, code lost:
    
        r3 = r13.keySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:398:0x0312, code lost:
    
        if (r3.hasNext() == false) goto L547;
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:0x0314, code lost:
    
        r4 = (java.lang.Integer) r3.next();
        r4.intValue();
        r5 = (com.google.android.gms.internal.measurement.zzgi) r13.get(r4);
        r6 = (java.util.List) r0.get(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x0329, code lost:
    
        if (r6 == null) goto L549;
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x032f, code lost:
    
        if (r6.isEmpty() == false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:403:0x0333, code lost:
    
        r16 = r0;
        r0 = r63.zzf.zzu().zzq(r5.zzk(), r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:404:0x0347, code lost:
    
        if (r0.isEmpty() != false) goto L546;
     */
    /* JADX WARN: Code restructure failed: missing block: B:406:0x0431, code lost:
    
        r0 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:410:0x0349, code lost:
    
        r7 = (com.google.android.gms.internal.measurement.zzgh) r5.zzby();
        r7.zzf();
        r7.zzb(r0);
        r17 = r3;
        r0 = r63.zzf.zzu().zzq(r5.zzn(), r6);
        r7.zzh();
        r7.zzd(r0);
        com.google.android.gms.internal.measurement.zzoc.zzc();
        r20 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:411:0x037d, code lost:
    
        if (r63.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzas) == false) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:412:0x037f, code lost:
    
        r0 = new java.util.ArrayList();
        r3 = r5.zzj().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:414:0x0390, code lost:
    
        if (r3.hasNext() == false) goto L554;
     */
    /* JADX WARN: Code restructure failed: missing block: B:415:0x0392, code lost:
    
        r8 = (com.google.android.gms.internal.measurement.zzfr) r3.next();
        r22 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x03a8, code lost:
    
        if (r6.contains(java.lang.Integer.valueOf(r8.zza())) != false) goto L556;
     */
    /* JADX WARN: Code restructure failed: missing block: B:417:0x03aa, code lost:
    
        r0.add(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:419:0x03ad, code lost:
    
        r3 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:422:0x03b1, code lost:
    
        r7.zze();
        r7.zza(r0);
        r0 = new java.util.ArrayList();
        r3 = r5.zzm().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:424:0x03c8, code lost:
    
        if (r3.hasNext() == false) goto L557;
     */
    /* JADX WARN: Code restructure failed: missing block: B:425:0x03ca, code lost:
    
        r5 = (com.google.android.gms.internal.measurement.zzgk) r3.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:0x03dc, code lost:
    
        if (r6.contains(java.lang.Integer.valueOf(r5.zzb())) != false) goto L560;
     */
    /* JADX WARN: Code restructure failed: missing block: B:428:0x03de, code lost:
    
        r0.add(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x03e2, code lost:
    
        r7.zzg();
        r7.zzc(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:434:0x0427, code lost:
    
        r1.put(r4, (com.google.android.gms.internal.measurement.zzgi) r7.zzaC());
     */
    /* JADX WARN: Code restructure failed: missing block: B:435:0x043e, code lost:
    
        r0 = r16;
        r3 = r17;
        r8 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:437:0x03e9, code lost:
    
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:439:0x03ee, code lost:
    
        if (r0 >= r5.zza()) goto L562;
     */
    /* JADX WARN: Code restructure failed: missing block: B:441:0x0400, code lost:
    
        if (r6.contains(java.lang.Integer.valueOf(r5.zze(r0).zza())) == false) goto L564;
     */
    /* JADX WARN: Code restructure failed: missing block: B:442:0x0402, code lost:
    
        r7.zzi(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:444:0x0405, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:447:0x0408, code lost:
    
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:449:0x040d, code lost:
    
        if (r0 >= r5.zzc()) goto L565;
     */
    /* JADX WARN: Code restructure failed: missing block: B:451:0x041f, code lost:
    
        if (r6.contains(java.lang.Integer.valueOf(r5.zzi(r0).zzb())) == false) goto L567;
     */
    /* JADX WARN: Code restructure failed: missing block: B:452:0x0421, code lost:
    
        r7.zzj(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:454:0x0424, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:458:0x0435, code lost:
    
        r16 = r0;
        r17 = r3;
        r20 = r8;
        r1.put(r4, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:463:0x02c9, code lost:
    
        if (r5 != null) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:466:0x02ee, code lost:
    
        if (r5 == null) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:507:0x0222, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:508:0x0223, code lost:
    
        r18 = "audience_id";
     */
    /* JADX WARN: Code restructure failed: missing block: B:515:0x022c, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:516:0x022d, code lost:
    
        r18 = "audience_id";
        r19 = "data";
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:517:0x0228, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:518:0x0229, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:528:0x014f, code lost:
    
        if (r5 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:529:0x0151, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:532:0x0173, code lost:
    
        if (r5 == null) goto L58;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0606  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01ae A[Catch: SQLiteException -> 0x0222, all -> 0x0b1e, TRY_LEAVE, TryCatch #5 {SQLiteException -> 0x0222, blocks: (B:42:0x01a8, B:44:0x01ae, B:477:0x01be, B:478:0x01c3, B:480:0x01cd, B:481:0x01dd, B:497:0x01ec), top: B:41:0x01a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:470:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:477:0x01be A[Catch: SQLiteException -> 0x0222, all -> 0x0b1e, TRY_ENTER, TryCatch #5 {SQLiteException -> 0x0222, blocks: (B:42:0x01a8, B:44:0x01ae, B:477:0x01be, B:478:0x01c3, B:480:0x01cd, B:481:0x01dd, B:497:0x01ec), top: B:41:0x01a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:493:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0a92  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x085f  */
    /* JADX WARN: Type inference failed for: r0v210, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r13v45 */
    /* JADX WARN: Type inference failed for: r13v48 */
    /* JADX WARN: Type inference failed for: r4v25, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v5, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v53, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r5v54 */
    /* JADX WARN: Type inference failed for: r5v55, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v8, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final java.util.List zza(java.lang.String r64, java.util.List r65, java.util.List r66, java.lang.Long r67, java.lang.Long r68) {
        /*
            Method dump skipped, instructions count: 2854
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaa.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long):java.util.List");
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    protected final boolean zzb() {
        return false;
    }
}
