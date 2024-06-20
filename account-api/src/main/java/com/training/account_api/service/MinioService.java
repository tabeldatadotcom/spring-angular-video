package com.training.account_api.service;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;

    public List<String> listBucket() throws Exception {
        try {
            List<Bucket> bucketList = this.minioClient.listBuckets();

            List<String> bucketNameList = new ArrayList<>();
            for (Bucket bucket : bucketList) {
                bucketNameList.add(bucket.name());
                System.out.println(bucket.creationDate() + ", " + bucket.name());
            }
            return bucketNameList;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    public String saveFile(Path source, InputStream file, String bucketName) throws Exception {
        try {
            boolean isBucketExist = this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            if (!isBucketExist){
                this.minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                System.out.println("create bucket : " + bucketName);
            }

            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(source.toString())
                    .stream(file, file.available(), -1)
                    .build();

            this.minioClient.putObject(args);

            String url = this.getUrlFile(source.toFile().getName(), bucketName);
            return url;

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    public String getUrlFile(String fileName, String bucketName) throws Exception {
        try {
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(fileName)
                    .expiry(2, TimeUnit.HOURS)
                    .build();

            return this.minioClient.getPresignedObjectUrl(args);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public InputStream getFile(String fileName, String bucketName) throws Exception {
        try {
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build();

            return this.minioClient.getObject(args);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

}
