package javax.ws.rs.client;

import java.util.concurrent.CompletionStage;
import javax.ws.rs.core.GenericType;

/* loaded from: classes3.dex */
public interface CompletionStageRxInvoker extends RxInvoker<CompletionStage> {
    @Override // javax.ws.rs.client.RxInvoker
    CompletionStage delete();

    @Override // javax.ws.rs.client.RxInvoker
    <T> CompletionStage delete(Class<T> cls);

    @Override // javax.ws.rs.client.RxInvoker
    <T> CompletionStage delete(GenericType<T> genericType);

    @Override // javax.ws.rs.client.RxInvoker
    CompletionStage get();

    @Override // javax.ws.rs.client.RxInvoker
    <T> CompletionStage get(Class<T> cls);

    @Override // javax.ws.rs.client.RxInvoker
    <T> CompletionStage get(GenericType<T> genericType);

    @Override // javax.ws.rs.client.RxInvoker
    CompletionStage head();

    @Override // javax.ws.rs.client.RxInvoker
    CompletionStage method(String str);

    @Override // javax.ws.rs.client.RxInvoker
    <T> CompletionStage method(String str, Class<T> cls);

    @Override // javax.ws.rs.client.RxInvoker
    /* renamed from: method, reason: avoid collision after fix types in other method */
    CompletionStage method2(String str, Entity<?> entity);

    @Override // javax.ws.rs.client.RxInvoker
    /* renamed from: method, reason: avoid collision after fix types in other method */
    <T> CompletionStage method2(String str, Entity<?> entity, Class<T> cls);

    @Override // javax.ws.rs.client.RxInvoker
    /* renamed from: method, reason: avoid collision after fix types in other method */
    <T> CompletionStage method2(String str, Entity<?> entity, GenericType<T> genericType);

    @Override // javax.ws.rs.client.RxInvoker
    <T> CompletionStage method(String str, GenericType<T> genericType);

    @Override // javax.ws.rs.client.RxInvoker
    CompletionStage options();

    @Override // javax.ws.rs.client.RxInvoker
    <T> CompletionStage options(Class<T> cls);

    @Override // javax.ws.rs.client.RxInvoker
    <T> CompletionStage options(GenericType<T> genericType);

    @Override // javax.ws.rs.client.RxInvoker
    /* renamed from: post, reason: avoid collision after fix types in other method */
    CompletionStage post2(Entity<?> entity);

    @Override // javax.ws.rs.client.RxInvoker
    /* renamed from: post, reason: avoid collision after fix types in other method */
    <T> CompletionStage post2(Entity<?> entity, Class<T> cls);

    @Override // javax.ws.rs.client.RxInvoker
    /* renamed from: post, reason: avoid collision after fix types in other method */
    <T> CompletionStage post2(Entity<?> entity, GenericType<T> genericType);

    @Override // javax.ws.rs.client.RxInvoker
    /* renamed from: put, reason: avoid collision after fix types in other method */
    CompletionStage put2(Entity<?> entity);

    @Override // javax.ws.rs.client.RxInvoker
    /* renamed from: put, reason: avoid collision after fix types in other method */
    <T> CompletionStage put2(Entity<?> entity, Class<T> cls);

    @Override // javax.ws.rs.client.RxInvoker
    /* renamed from: put, reason: avoid collision after fix types in other method */
    <T> CompletionStage put2(Entity<?> entity, GenericType<T> genericType);

    @Override // javax.ws.rs.client.RxInvoker
    CompletionStage trace();

    @Override // javax.ws.rs.client.RxInvoker
    <T> CompletionStage trace(Class<T> cls);

    @Override // javax.ws.rs.client.RxInvoker
    <T> CompletionStage trace(GenericType<T> genericType);

    @Override // javax.ws.rs.client.RxInvoker
    /* bridge */ /* synthetic */ default CompletionStage method(String str, Entity entity) {
        return method2(str, (Entity<?>) entity);
    }

    @Override // javax.ws.rs.client.RxInvoker
    /* bridge */ /* synthetic */ default CompletionStage method(String str, Entity entity, Class cls) {
        return method2(str, (Entity<?>) entity, cls);
    }

    @Override // javax.ws.rs.client.RxInvoker
    /* bridge */ /* synthetic */ default CompletionStage method(String str, Entity entity, GenericType genericType) {
        return method2(str, (Entity<?>) entity, genericType);
    }

    @Override // javax.ws.rs.client.RxInvoker
    /* bridge */ /* synthetic */ default CompletionStage post(Entity entity) {
        return post2((Entity<?>) entity);
    }

    @Override // javax.ws.rs.client.RxInvoker
    /* bridge */ /* synthetic */ default CompletionStage post(Entity entity, Class cls) {
        return post2((Entity<?>) entity, cls);
    }

    @Override // javax.ws.rs.client.RxInvoker
    /* bridge */ /* synthetic */ default CompletionStage post(Entity entity, GenericType genericType) {
        return post2((Entity<?>) entity, genericType);
    }

    @Override // javax.ws.rs.client.RxInvoker
    /* bridge */ /* synthetic */ default CompletionStage put(Entity entity) {
        return put2((Entity<?>) entity);
    }

    @Override // javax.ws.rs.client.RxInvoker
    /* bridge */ /* synthetic */ default CompletionStage put(Entity entity, Class cls) {
        return put2((Entity<?>) entity, cls);
    }

    @Override // javax.ws.rs.client.RxInvoker
    /* bridge */ /* synthetic */ default CompletionStage put(Entity entity, GenericType genericType) {
        return put2((Entity<?>) entity, genericType);
    }
}
