package com.capacitorjs.plugins.camera;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Base64;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import com.capacitorjs.plugins.camera.CameraBottomSheetDialogFragment;
import com.getcapacitor.FileUtils;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.config.a;
import io.sentry.protocol.App;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;

@CapacitorPlugin(name = "Camera", permissions = {@Permission(alias = CameraPlugin.CAMERA, strings = {"android.permission.CAMERA"}), @Permission(alias = CameraPlugin.PHOTOS, strings = {}), @Permission(alias = CameraPlugin.SAVE_GALLERY, strings = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}), @Permission(alias = CameraPlugin.READ_EXTERNAL_STORAGE, strings = {"android.permission.READ_EXTERNAL_STORAGE"})})
/* loaded from: classes3.dex */
public class CameraPlugin extends Plugin {
    static final String CAMERA = "camera";
    private static final String IMAGE_EDIT_ERROR = "Unable to edit image";
    private static final String IMAGE_FILE_SAVE_ERROR = "Unable to create photo on disk";
    private static final String IMAGE_GALLERY_SAVE_ERROR = "Unable to save the image in the gallery";
    private static final String IMAGE_PROCESS_NO_FILE_ERROR = "Unable to process image, file not found on disk";
    private static final String INVALID_RESULT_TYPE_ERROR = "Invalid resultType option";
    private static final String NO_CAMERA_ACTIVITY_ERROR = "Unable to resolve camera activity";
    private static final String NO_CAMERA_ERROR = "Device doesn't have a camera available";
    private static final String NO_PHOTO_ACTIVITY_ERROR = "Unable to resolve photo activity";
    private static final String PERMISSION_DENIED_ERROR_CAMERA = "User denied access to camera";
    static final String PHOTOS = "photos";
    static final String READ_EXTERNAL_STORAGE = "readExternalStorage";
    static final String SAVE_GALLERY = "saveGallery";
    private static final String UNABLE_TO_PROCESS_IMAGE = "Unable to process image";
    private static final String USER_CANCELLED = "User cancelled photos app";
    private String imageEditedFileSavePath;
    private String imageFileSavePath;
    private Uri imageFileUri;
    private Uri imagePickedContentUri;
    private boolean isEdited = false;
    private boolean isFirstRequest = true;
    private boolean isSaved = false;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMultipleMedia = null;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia = null;
    private final AtomicInteger mNextLocalRequestCode = new AtomicInteger();
    private CameraSettings settings = new CameraSettings();

    @Override // com.getcapacitor.Plugin
    public void load() {
        super.load();
    }

    @PluginMethod
    public void getPhoto(PluginCall pluginCall) {
        this.isEdited = false;
        this.settings = getSettings(pluginCall);
        doShow(pluginCall);
    }

    @PluginMethod
    public void pickImages(PluginCall pluginCall) {
        this.settings = getSettings(pluginCall);
        openPhotos(pluginCall, true);
    }

    @PluginMethod
    public void pickLimitedLibraryPhotos(PluginCall pluginCall) {
        pluginCall.unimplemented("not supported on android");
    }

    @PluginMethod
    public void getLimitedLibraryPhotos(PluginCall pluginCall) {
        pluginCall.unimplemented("not supported on android");
    }

    /* renamed from: com.capacitorjs.plugins.camera.CameraPlugin$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$capacitorjs$plugins$camera$CameraSource;

        static {
            int[] iArr = new int[CameraSource.values().length];
            $SwitchMap$com$capacitorjs$plugins$camera$CameraSource = iArr;
            try {
                iArr[CameraSource.CAMERA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$capacitorjs$plugins$camera$CameraSource[CameraSource.PHOTOS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void doShow(PluginCall pluginCall) {
        int i = AnonymousClass1.$SwitchMap$com$capacitorjs$plugins$camera$CameraSource[this.settings.getSource().ordinal()];
        if (i == 1) {
            showCamera(pluginCall);
        } else if (i == 2) {
            showPhotos(pluginCall);
        } else {
            showPrompt(pluginCall);
        }
    }

    private void showPrompt(final PluginCall pluginCall) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(pluginCall.getString("promptLabelPhoto", "From Photos"));
        arrayList.add(pluginCall.getString("promptLabelPicture", "Take Picture"));
        CameraBottomSheetDialogFragment cameraBottomSheetDialogFragment = new CameraBottomSheetDialogFragment();
        cameraBottomSheetDialogFragment.setTitle(pluginCall.getString("promptLabelHeader", "Photo"));
        cameraBottomSheetDialogFragment.setOptions(arrayList, new CameraBottomSheetDialogFragment.BottomSheetOnSelectedListener() { // from class: com.capacitorjs.plugins.camera.CameraPlugin$$ExternalSyntheticLambda3
            @Override // com.capacitorjs.plugins.camera.CameraBottomSheetDialogFragment.BottomSheetOnSelectedListener
            public final void onSelected(int i) {
                CameraPlugin.this.lambda$showPrompt$0(pluginCall, i);
            }
        }, new CameraBottomSheetDialogFragment.BottomSheetOnCanceledListener() { // from class: com.capacitorjs.plugins.camera.CameraPlugin$$ExternalSyntheticLambda4
            @Override // com.capacitorjs.plugins.camera.CameraBottomSheetDialogFragment.BottomSheetOnCanceledListener
            public final void onCanceled() {
                PluginCall.this.reject(CameraPlugin.USER_CANCELLED);
            }
        });
        cameraBottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), "capacitorModalsActionSheet");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPrompt$0(PluginCall pluginCall, int i) {
        if (i == 0) {
            this.settings.setSource(CameraSource.PHOTOS);
            openPhotos(pluginCall);
        } else if (i == 1) {
            this.settings.setSource(CameraSource.CAMERA);
            openCamera(pluginCall);
        }
    }

    private void showCamera(PluginCall pluginCall) {
        if (!getContext().getPackageManager().hasSystemFeature("android.hardware.camera.any")) {
            pluginCall.reject(NO_CAMERA_ERROR);
        } else {
            openCamera(pluginCall);
        }
    }

    private void showPhotos(PluginCall pluginCall) {
        openPhotos(pluginCall);
    }

    private boolean checkCameraPermissions(PluginCall pluginCall) {
        boolean z = !isPermissionDeclared(CAMERA) || getPermissionState(CAMERA) == PermissionState.GRANTED;
        getPermissionState(SAVE_GALLERY);
        PermissionState permissionState = PermissionState.GRANTED;
        if (z) {
            return true;
        }
        requestPermissionForAlias(CAMERA, pluginCall, "cameraPermissionsCallback");
        return false;
    }

    @PermissionCallback
    private void cameraPermissionsCallback(PluginCall pluginCall) {
        if (pluginCall.getMethodName().equals("pickImages")) {
            openPhotos(pluginCall, true);
            return;
        }
        if (this.settings.getSource() == CameraSource.CAMERA && getPermissionState(CAMERA) != PermissionState.GRANTED) {
            Logger.debug(getLogTag(), "User denied camera permission: " + getPermissionState(CAMERA).toString());
            pluginCall.reject(PERMISSION_DENIED_ERROR_CAMERA);
            return;
        }
        doShow(pluginCall);
    }

    @Override // com.getcapacitor.Plugin
    protected void requestPermissionForAliases(String[] strArr, PluginCall pluginCall, String str) {
        int i = 0;
        if (Build.VERSION.SDK_INT >= 33) {
            while (i < strArr.length) {
                if (strArr[i].equals(SAVE_GALLERY)) {
                    strArr[i] = PHOTOS;
                }
                i++;
            }
        } else if (Build.VERSION.SDK_INT >= 30) {
            while (i < strArr.length) {
                if (strArr[i].equals(SAVE_GALLERY)) {
                    strArr[i] = READ_EXTERNAL_STORAGE;
                }
                i++;
            }
        }
        super.requestPermissionForAliases(strArr, pluginCall, str);
    }

    private CameraSettings getSettings(PluginCall pluginCall) {
        CameraSettings cameraSettings = new CameraSettings();
        cameraSettings.setResultType(getResultType(pluginCall.getString("resultType")));
        cameraSettings.setSaveToGallery(pluginCall.getBoolean("saveToGallery", false).booleanValue());
        cameraSettings.setAllowEditing(pluginCall.getBoolean("allowEditing", false).booleanValue());
        cameraSettings.setQuality(pluginCall.getInt("quality", 90).intValue());
        cameraSettings.setWidth(pluginCall.getInt("width", 0).intValue());
        cameraSettings.setHeight(pluginCall.getInt("height", 0).intValue());
        cameraSettings.setShouldResize(cameraSettings.getWidth() > 0 || cameraSettings.getHeight() > 0);
        cameraSettings.setShouldCorrectOrientation(pluginCall.getBoolean("correctOrientation", true).booleanValue());
        try {
            cameraSettings.setSource(CameraSource.valueOf(pluginCall.getString("source", CameraSource.PROMPT.getSource())));
        } catch (IllegalArgumentException unused) {
            cameraSettings.setSource(CameraSource.PROMPT);
        }
        return cameraSettings;
    }

    private CameraResultType getResultType(String str) {
        if (str == null) {
            return null;
        }
        try {
            return CameraResultType.valueOf(str.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException unused) {
            Logger.debug(this.getLogTag(), "Invalid result type \"" + str + "\", defaulting to base64");
            return CameraResultType.BASE64;
        }
    }

    public void openCamera(PluginCall pluginCall) {
        if (checkCameraPermissions(pluginCall)) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                try {
                    String appId = getAppId();
                    File createImageFile = CameraUtils.createImageFile(getActivity());
                    this.imageFileSavePath = createImageFile.getAbsolutePath();
                    Uri uriForFile = FileProvider.getUriForFile(getActivity(), appId + ".fileprovider", createImageFile);
                    this.imageFileUri = uriForFile;
                    intent.putExtra("output", uriForFile);
                    startActivityForResult(pluginCall, intent, "processCameraImage");
                    return;
                } catch (Exception e) {
                    pluginCall.reject(IMAGE_FILE_SAVE_ERROR, e);
                    return;
                }
            }
            pluginCall.reject(NO_CAMERA_ACTIVITY_ERROR);
        }
    }

    public void openPhotos(PluginCall pluginCall) {
        openPhotos(pluginCall, false);
    }

    private <I, O> ActivityResultLauncher<I> registerActivityResultLauncher(ActivityResultContract<I, O> activityResultContract, ActivityResultCallback<O> activityResultCallback) {
        String str = "cap_activity_rq#" + this.mNextLocalRequestCode.getAndIncrement();
        if (this.bridge.getFragment() != null) {
            Object host = this.bridge.getFragment().getHost();
            if (host instanceof ActivityResultRegistryOwner) {
                return ((ActivityResultRegistryOwner) host).getActivityResultRegistry().register(str, activityResultContract, activityResultCallback);
            }
            return this.bridge.getFragment().requireActivity().getActivityResultRegistry().register(str, activityResultContract, activityResultCallback);
        }
        return this.bridge.getActivity().getActivityResultRegistry().register(str, activityResultContract, activityResultCallback);
    }

    private ActivityResultContract<PickVisualMediaRequest, List<Uri>> getContractForCall(PluginCall pluginCall) {
        int intValue = pluginCall.getInt("limit", 0).intValue();
        if (intValue > 1) {
            return new ActivityResultContracts.PickMultipleVisualMedia(intValue);
        }
        return new ActivityResultContracts.PickMultipleVisualMedia();
    }

    private void openPhotos(final PluginCall pluginCall, boolean z) {
        try {
            if (z) {
                ActivityResultLauncher<PickVisualMediaRequest> registerActivityResultLauncher = registerActivityResultLauncher(getContractForCall(pluginCall), new ActivityResultCallback() { // from class: com.capacitorjs.plugins.camera.CameraPlugin$$ExternalSyntheticLambda5
                    @Override // androidx.activity.result.ActivityResultCallback
                    public final void onActivityResult(Object obj) {
                        CameraPlugin.this.lambda$openPhotos$3(pluginCall, (List) obj);
                    }
                });
                this.pickMultipleMedia = registerActivityResultLauncher;
                registerActivityResultLauncher.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
            } else {
                ActivityResultLauncher<PickVisualMediaRequest> registerActivityResultLauncher2 = registerActivityResultLauncher(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback() { // from class: com.capacitorjs.plugins.camera.CameraPlugin$$ExternalSyntheticLambda6
                    @Override // androidx.activity.result.ActivityResultCallback
                    public final void onActivityResult(Object obj) {
                        CameraPlugin.this.lambda$openPhotos$4(pluginCall, (Uri) obj);
                    }
                });
                this.pickMedia = registerActivityResultLauncher2;
                registerActivityResultLauncher2.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
            }
        } catch (ActivityNotFoundException unused) {
            pluginCall.reject(NO_PHOTO_ACTIVITY_ERROR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$openPhotos$3(final PluginCall pluginCall, final List list) {
        if (!list.isEmpty()) {
            Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.capacitorjs.plugins.camera.CameraPlugin$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    CameraPlugin.this.lambda$openPhotos$2(list, pluginCall);
                }
            });
        } else {
            pluginCall.reject(USER_CANCELLED);
        }
        this.pickMultipleMedia.unregister();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$openPhotos$2(List list, PluginCall pluginCall) {
        JSObject processPickedImages;
        JSObject jSObject = new JSObject();
        JSArray jSArray = new JSArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                processPickedImages = processPickedImages((Uri) it.next());
            } catch (SecurityException unused) {
                pluginCall.reject("SecurityException");
            }
            if (processPickedImages.getString("error") != null && !processPickedImages.getString("error").isEmpty()) {
                pluginCall.reject(processPickedImages.getString("error"));
                return;
            }
            jSArray.put(processPickedImages);
        }
        jSObject.put(PHOTOS, (Object) jSArray);
        pluginCall.resolve(jSObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$openPhotos$4(PluginCall pluginCall, Uri uri) {
        if (uri != null) {
            this.imagePickedContentUri = uri;
            processPickedImage(uri, pluginCall);
        } else {
            pluginCall.reject(USER_CANCELLED);
        }
        this.pickMedia.unregister();
    }

    @ActivityCallback
    public void processCameraImage(PluginCall pluginCall, ActivityResult activityResult) {
        this.settings = getSettings(pluginCall);
        if (this.imageFileSavePath == null) {
            pluginCall.reject(IMAGE_PROCESS_NO_FILE_ERROR);
            return;
        }
        File file = new File(this.imageFileSavePath);
        BitmapFactory.Options options = new BitmapFactory.Options();
        Uri fromFile = Uri.fromFile(file);
        Bitmap decodeFile = BitmapFactory.decodeFile(this.imageFileSavePath, options);
        if (decodeFile == null) {
            pluginCall.reject(USER_CANCELLED);
        } else {
            returnResult(pluginCall, decodeFile, fromFile);
        }
    }

    public void processPickedImage(PluginCall pluginCall, ActivityResult activityResult) {
        this.settings = getSettings(pluginCall);
        Intent data = activityResult.getData();
        if (data == null) {
            pluginCall.reject(USER_CANCELLED);
            return;
        }
        Uri data2 = data.getData();
        this.imagePickedContentUri = data2;
        processPickedImage(data2, pluginCall);
    }

    private ArrayList<Parcelable> getLegacyParcelableArrayList(Bundle bundle, String str) {
        return bundle.getParcelableArrayList(str);
    }

    private void processPickedImage(Uri uri, PluginCall pluginCall) {
        InputStream openInputStream;
        Bitmap decodeStream;
        InputStream inputStream = null;
        try {
            try {
                try {
                    try {
                        openInputStream = getContext().getContentResolver().openInputStream(uri);
                        decodeStream = BitmapFactory.decodeStream(openInputStream);
                    } catch (FileNotFoundException e) {
                        pluginCall.reject("No such image found", e);
                        if (0 == 0) {
                            return;
                        } else {
                            inputStream.close();
                        }
                    }
                } catch (OutOfMemoryError unused) {
                    pluginCall.reject("Out of memory");
                    if (0 == 0) {
                        return;
                    } else {
                        inputStream.close();
                    }
                }
                if (decodeStream != null) {
                    returnResult(pluginCall, decodeStream, uri);
                    if (openInputStream != null) {
                        openInputStream.close();
                        return;
                    }
                    return;
                }
                pluginCall.reject("Unable to process bitmap");
                if (openInputStream != null) {
                    try {
                        openInputStream.close();
                    } catch (IOException e2) {
                        Logger.error(getLogTag(), UNABLE_TO_PROCESS_IMAGE, e2);
                    }
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        Logger.error(getLogTag(), UNABLE_TO_PROCESS_IMAGE, e3);
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            Logger.error(getLogTag(), UNABLE_TO_PROCESS_IMAGE, e4);
        }
    }

    private JSObject processPickedImages(Uri uri) {
        JSObject jSObject = new JSObject();
        InputStream inputStream = null;
        try {
            try {
                try {
                    try {
                        InputStream openInputStream = getContext().getContentResolver().openInputStream(uri);
                        Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream);
                        if (decodeStream == null) {
                            jSObject.put("error", "Unable to process bitmap");
                            if (openInputStream != null) {
                                try {
                                    openInputStream.close();
                                } catch (IOException e) {
                                    Logger.error(getLogTag(), UNABLE_TO_PROCESS_IMAGE, e);
                                }
                            }
                            return jSObject;
                        }
                        ExifWrapper exifData = ImageUtils.getExifData(getContext(), decodeStream, uri);
                        try {
                            Bitmap prepareBitmap = prepareBitmap(decodeStream, uri, exifData);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            prepareBitmap.compress(Bitmap.CompressFormat.JPEG, this.settings.getQuality(), byteArrayOutputStream);
                            Uri tempImage = getTempImage(uri, byteArrayOutputStream);
                            exifData.copyExif(tempImage.getPath());
                            if (tempImage != null) {
                                jSObject.put("format", "jpeg");
                                jSObject.put("exif", (Object) exifData.toJson());
                                jSObject.put(a.j, tempImage.toString());
                                jSObject.put("webPath", FileUtils.getPortablePath(getContext(), this.bridge.getLocalUrl(), tempImage));
                            } else {
                                jSObject.put("error", UNABLE_TO_PROCESS_IMAGE);
                            }
                            if (openInputStream != null) {
                                try {
                                    openInputStream.close();
                                } catch (IOException e2) {
                                    Logger.error(getLogTag(), UNABLE_TO_PROCESS_IMAGE, e2);
                                }
                            }
                            return jSObject;
                        } catch (IOException unused) {
                            jSObject.put("error", UNABLE_TO_PROCESS_IMAGE);
                            if (openInputStream != null) {
                                try {
                                    openInputStream.close();
                                } catch (IOException e3) {
                                    Logger.error(getLogTag(), UNABLE_TO_PROCESS_IMAGE, e3);
                                }
                            }
                            return jSObject;
                        }
                    } catch (IOException e4) {
                        Logger.error(getLogTag(), UNABLE_TO_PROCESS_IMAGE, e4);
                        return jSObject;
                    }
                } catch (FileNotFoundException e5) {
                    jSObject.put("error", "No such image found");
                    Logger.error(getLogTag(), "No such image found", e5);
                    if (0 != 0) {
                        inputStream.close();
                    }
                    return jSObject;
                }
            } catch (OutOfMemoryError unused2) {
                jSObject.put("error", "Out of memory");
                if (0 != 0) {
                    inputStream.close();
                }
                return jSObject;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e6) {
                    Logger.error(getLogTag(), UNABLE_TO_PROCESS_IMAGE, e6);
                }
            }
            throw th;
        }
    }

    @ActivityCallback
    private void processEditedImage(PluginCall pluginCall, ActivityResult activityResult) {
        this.isEdited = true;
        this.settings = getSettings(pluginCall);
        if (activityResult.getResultCode() == 0) {
            Uri uri = this.imagePickedContentUri;
            if (uri != null) {
                processPickedImage(uri, pluginCall);
                return;
            } else {
                processCameraImage(pluginCall, activityResult);
                return;
            }
        }
        processPickedImage(pluginCall, activityResult);
    }

    private Uri saveImage(Uri uri, InputStream inputStream) throws IOException {
        File file;
        if (uri.getScheme().equals(FirebaseAnalytics.Param.CONTENT)) {
            file = getTempFile(uri);
        } else {
            file = new File(uri.getPath());
        }
        try {
            writePhoto(file, inputStream);
        } catch (FileNotFoundException unused) {
            file = getTempFile(uri);
            writePhoto(file, inputStream);
        }
        return Uri.fromFile(file);
    }

    private void writePhoto(File file, InputStream inputStream) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.close();
                return;
            }
        }
    }

    private File getTempFile(Uri uri) {
        String lastPathSegment = Uri.parse(Uri.decode(uri.toString())).getLastPathSegment();
        if (!lastPathSegment.contains(".jpg") && !lastPathSegment.contains(".jpeg")) {
            lastPathSegment = lastPathSegment + "." + new Date().getTime() + ".jpeg";
        }
        return new File(getContext().getCacheDir(), lastPathSegment);
    }

    private void returnResult(PluginCall pluginCall, Bitmap bitmap, Uri uri) {
        String str;
        ExifWrapper exifData = ImageUtils.getExifData(getContext(), bitmap, uri);
        try {
            Bitmap prepareBitmap = prepareBitmap(bitmap, uri, exifData);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            prepareBitmap.compress(Bitmap.CompressFormat.JPEG, this.settings.getQuality(), byteArrayOutputStream);
            if (this.settings.isAllowEditing() && !this.isEdited) {
                editImage(pluginCall, uri, byteArrayOutputStream);
                return;
            }
            if (pluginCall.getBoolean("saveToGallery", false).booleanValue() && ((str = this.imageEditedFileSavePath) != null || this.imageFileSavePath != null)) {
                this.isSaved = true;
                if (str == null) {
                    try {
                        str = this.imageFileSavePath;
                    } catch (FileNotFoundException e) {
                        this.isSaved = false;
                        Logger.error(getLogTag(), IMAGE_GALLERY_SAVE_ERROR, e);
                    } catch (IOException e2) {
                        this.isSaved = false;
                        Logger.error(getLogTag(), IMAGE_GALLERY_SAVE_ERROR, e2);
                    }
                }
                File file = new File(str);
                ContentResolver contentResolver = getContext().getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put("_display_name", file.getName());
                contentValues.put("mime_type", "image/jpeg");
                contentValues.put("relative_path", Environment.DIRECTORY_DCIM);
                Uri insert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                if (insert == null) {
                    throw new IOException("Failed to create new MediaStore record.");
                }
                OutputStream openOutputStream = contentResolver.openOutputStream(insert);
                if (openOutputStream == null) {
                    throw new IOException("Failed to open output stream.");
                }
                boolean compress = prepareBitmap.compress(Bitmap.CompressFormat.JPEG, this.settings.getQuality(), openOutputStream);
                Boolean.valueOf(compress).getClass();
                if (!compress) {
                    this.isSaved = false;
                }
            }
            if (this.settings.getResultType() == CameraResultType.BASE64) {
                returnBase64(pluginCall, exifData, byteArrayOutputStream);
            } else if (this.settings.getResultType() == CameraResultType.URI) {
                returnFileURI(pluginCall, exifData, prepareBitmap, uri, byteArrayOutputStream);
            } else if (this.settings.getResultType() == CameraResultType.DATAURL) {
                returnDataUrl(pluginCall, exifData, byteArrayOutputStream);
            } else {
                pluginCall.reject(INVALID_RESULT_TYPE_ERROR);
            }
            if (this.settings.getResultType() != CameraResultType.URI) {
                deleteImageFile();
            }
            this.imageFileSavePath = null;
            this.imageFileUri = null;
            this.imagePickedContentUri = null;
            this.imageEditedFileSavePath = null;
        } catch (IOException unused) {
            pluginCall.reject(UNABLE_TO_PROCESS_IMAGE);
        }
    }

    private void deleteImageFile() {
        if (this.imageFileSavePath == null || this.settings.isSaveToGallery()) {
            return;
        }
        File file = new File(this.imageFileSavePath);
        if (file.exists()) {
            file.delete();
        }
    }

    private void returnFileURI(PluginCall pluginCall, ExifWrapper exifWrapper, Bitmap bitmap, Uri uri, ByteArrayOutputStream byteArrayOutputStream) {
        Uri tempImage = getTempImage(uri, byteArrayOutputStream);
        exifWrapper.copyExif(tempImage.getPath());
        if (tempImage != null) {
            JSObject jSObject = new JSObject();
            jSObject.put("format", "jpeg");
            jSObject.put("exif", (Object) exifWrapper.toJson());
            jSObject.put(a.j, tempImage.toString());
            jSObject.put("webPath", FileUtils.getPortablePath(getContext(), this.bridge.getLocalUrl(), tempImage));
            jSObject.put("saved", this.isSaved);
            pluginCall.resolve(jSObject);
            return;
        }
        pluginCall.reject(UNABLE_TO_PROCESS_IMAGE);
    }

    private Uri getTempImage(Uri uri, ByteArrayOutputStream byteArrayOutputStream) {
        ByteArrayInputStream byteArrayInputStream;
        Uri uri2 = null;
        uri2 = null;
        uri2 = null;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Logger.error(getLogTag(), UNABLE_TO_PROCESS_IMAGE, e);
            }
        } catch (IOException unused) {
            byteArrayInputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            uri2 = saveImage(uri, byteArrayInputStream);
            byteArrayInputStream.close();
        } catch (IOException unused2) {
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            return uri2;
        } catch (Throwable th2) {
            th = th2;
            byteArrayInputStream2 = byteArrayInputStream;
            if (byteArrayInputStream2 != null) {
                try {
                    byteArrayInputStream2.close();
                } catch (IOException e2) {
                    Logger.error(getLogTag(), UNABLE_TO_PROCESS_IMAGE, e2);
                }
            }
            throw th;
        }
        return uri2;
    }

    private Bitmap prepareBitmap(Bitmap bitmap, Uri uri, ExifWrapper exifWrapper) throws IOException {
        if (this.settings.isShouldCorrectOrientation()) {
            bitmap = replaceBitmap(bitmap, ImageUtils.correctOrientation(getContext(), bitmap, uri, exifWrapper));
        }
        return this.settings.isShouldResize() ? replaceBitmap(bitmap, ImageUtils.resize(bitmap, this.settings.getWidth(), this.settings.getHeight())) : bitmap;
    }

    private Bitmap replaceBitmap(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap != bitmap2) {
            bitmap.recycle();
        }
        return bitmap2;
    }

    private void returnDataUrl(PluginCall pluginCall, ExifWrapper exifWrapper, ByteArrayOutputStream byteArrayOutputStream) {
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        JSObject jSObject = new JSObject();
        jSObject.put("format", "jpeg");
        jSObject.put("dataUrl", "data:image/jpeg;base64," + encodeToString);
        jSObject.put("exif", (Object) exifWrapper.toJson());
        pluginCall.resolve(jSObject);
    }

    private void returnBase64(PluginCall pluginCall, ExifWrapper exifWrapper, ByteArrayOutputStream byteArrayOutputStream) {
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        JSObject jSObject = new JSObject();
        jSObject.put("format", "jpeg");
        jSObject.put("base64String", encodeToString);
        jSObject.put("exif", (Object) exifWrapper.toJson());
        pluginCall.resolve(jSObject);
    }

    @Override // com.getcapacitor.Plugin
    @PluginMethod
    public void requestPermissions(PluginCall pluginCall) {
        List list;
        if (isPermissionDeclared(CAMERA)) {
            super.requestPermissions(pluginCall);
            return;
        }
        JSArray array = pluginCall.getArray(App.JsonKeys.APP_PERMISSIONS);
        if (array != null) {
            try {
                list = array.toList();
            } catch (JSONException unused) {
            }
            if (list == null && list.size() == 1 && (list.contains(CAMERA) || list.contains(PHOTOS))) {
                checkPermissions(pluginCall);
                return;
            } else {
                requestPermissionForAlias(SAVE_GALLERY, pluginCall, "checkPermissions");
            }
        }
        list = null;
        if (list == null) {
        }
        requestPermissionForAlias(SAVE_GALLERY, pluginCall, "checkPermissions");
    }

    @Override // com.getcapacitor.Plugin
    public Map<String, PermissionState> getPermissionStates() {
        Map<String, PermissionState> permissionStates = super.getPermissionStates();
        if (!isPermissionDeclared(CAMERA)) {
            permissionStates.put(CAMERA, PermissionState.GRANTED);
        }
        if (permissionStates.containsKey(PHOTOS)) {
            permissionStates.put(PHOTOS, PermissionState.GRANTED);
        }
        if (Build.VERSION.SDK_INT >= 30 && permissionStates.containsKey(READ_EXTERNAL_STORAGE)) {
            permissionStates.put(SAVE_GALLERY, permissionStates.get(READ_EXTERNAL_STORAGE));
        }
        return permissionStates;
    }

    private void editImage(PluginCall pluginCall, Uri uri, ByteArrayOutputStream byteArrayOutputStream) {
        try {
            Intent createEditIntent = createEditIntent(getTempImage(uri, byteArrayOutputStream));
            if (createEditIntent != null) {
                startActivityForResult(pluginCall, createEditIntent, "processEditedImage");
            } else {
                pluginCall.reject(IMAGE_EDIT_ERROR);
            }
        } catch (Exception e) {
            pluginCall.reject(IMAGE_EDIT_ERROR, e);
        }
    }

    private Intent createEditIntent(Uri uri) {
        List<ResolveInfo> legacyQueryIntentActivities;
        PackageManager.ResolveInfoFlags of;
        try {
            File file = new File(uri.getPath());
            Uri uriForFile = FileProvider.getUriForFile(getActivity(), getContext().getPackageName() + ".fileprovider", file);
            Intent intent = new Intent("android.intent.action.EDIT");
            intent.setDataAndType(uriForFile, "image/*");
            this.imageEditedFileSavePath = file.getAbsolutePath();
            intent.addFlags(3);
            intent.putExtra("output", uriForFile);
            if (Build.VERSION.SDK_INT >= 33) {
                PackageManager packageManager = getContext().getPackageManager();
                of = PackageManager.ResolveInfoFlags.of(65536L);
                legacyQueryIntentActivities = packageManager.queryIntentActivities(intent, of);
            } else {
                legacyQueryIntentActivities = legacyQueryIntentActivities(intent);
            }
            Iterator<ResolveInfo> it = legacyQueryIntentActivities.iterator();
            while (it.hasNext()) {
                getContext().grantUriPermission(it.next().activityInfo.packageName, uriForFile, 3);
            }
            return intent;
        } catch (Exception unused) {
            return null;
        }
    }

    private List<ResolveInfo> legacyQueryIntentActivities(Intent intent) {
        return getContext().getPackageManager().queryIntentActivities(intent, 65536);
    }

    @Override // com.getcapacitor.Plugin
    protected Bundle saveInstanceState() {
        Bundle saveInstanceState = super.saveInstanceState();
        if (saveInstanceState != null) {
            saveInstanceState.putString("cameraImageFileSavePath", this.imageFileSavePath);
        }
        return saveInstanceState;
    }

    @Override // com.getcapacitor.Plugin
    protected void restoreState(Bundle bundle) {
        String string = bundle.getString("cameraImageFileSavePath");
        if (string != null) {
            this.imageFileSavePath = string;
        }
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnDestroy() {
        ActivityResultLauncher<PickVisualMediaRequest> activityResultLauncher = this.pickMedia;
        if (activityResultLauncher != null) {
            activityResultLauncher.unregister();
        }
        ActivityResultLauncher<PickVisualMediaRequest> activityResultLauncher2 = this.pickMultipleMedia;
        if (activityResultLauncher2 != null) {
            activityResultLauncher2.unregister();
        }
    }
}
