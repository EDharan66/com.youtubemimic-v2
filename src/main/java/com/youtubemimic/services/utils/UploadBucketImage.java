
package com.youtubemimic.services.utils;

import com.google.cloud.storage.*;

import java.util.Base64;

import static com.youtubemimic.auth.UserPropertyConstant.bucketName;
import static com.youtubemimic.auth.UserPropertyConstant.projectId;

public class UploadBucketImage {


    public static UploadBucketImage getInstance() {
        return new UploadBucketImage();
    }

    public String uploadImage(String fileName, String base64String) {
        String[] strings = base64String.split(",");
        String contentType;
        switch (strings[0]) {
            case "data:image/png;base64":
                contentType = "image/png";
                break;
            case "data:image/jpeg;base64":
                contentType = "image/jpeg";
                break;
            default:
                contentType = "jpg";
                break;
        }

        byte[] data = Base64.getDecoder().decode(strings[1]);
        String name = System.nanoTime() + fileName;
        return uploadToCloudStorage(name, data, contentType);
    }

//    public String uploadJson(String fileName, String jsonDetails) throws UnsupportedEncodingException {
//
//        byte[] data = jsonDetails.getBytes(UTF_8);
//        return uploadToCloudStorageForJson(fileName, data);
//    }

//    public String downloadJson(String fileName) {
//        return downloadFromCloudStorage(fileName);
//    }

//    private static String uploadToCloudStorageForJson(String fileName, byte[] fileInputStream) {
//        try {
//            Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
//			BlobId blobId = BlobId.of(bucketName, fileName);
//			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//			storage.create(blobInfo, fileInputStream);
//            System.out.println("success.. blob");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		return "success";
//    }

    private static String uploadToCloudStorage(String fileName, byte[] fileInputStream, String contentType) {

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        Blob blob = storage.create(blobInfo, fileInputStream);
        return blob.getMediaLink();
    }
//
//    private static String downloadFromCloudStorage(String fileName) {
//
//        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
//        Blob blob = storage.get(bucketName, fileName);
//        String value = new String(blob.getContent());
//        System.out.println("value = " + value);
//        return "";
//    }


}
