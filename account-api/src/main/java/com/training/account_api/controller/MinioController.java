package com.training.account_api.controller;

import com.training.account_api.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/minio/v1")
@RequiredArgsConstructor
public class MinioController {

    private final MinioService minioService;

    @GetMapping("/list-bucket")
    public ResponseEntity<List<String>> getAllBucket() throws Exception {
        List<String> bucketNameList = this.minioService.listBucket();
        return ResponseEntity.ok(bucketNameList);
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(name = "bucketName", defaultValue = "mybucket") String bucketName
            ) throws Exception {

//        Path path = Path.of(file.getOriginalFilename());
        Path path = Paths.get(file.getOriginalFilename());
        System.out.println("path : " + path);

        Map<String, String> fileUploadedUrl = new HashMap();

        String url = this.minioService.saveFile(path, file.getInputStream(), bucketName);

        fileUploadedUrl.put("imageUrl", url);

        return ResponseEntity.ok(fileUploadedUrl);
    }

    @GetMapping(name = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> download(
            @RequestParam("fileName") String fileName,
            @RequestParam("bucketName") String bucketName
    ) throws Exception {
        InputStream fileStream = this.minioService.getFile(fileName, bucketName);
        InputStreamResource streamResource = new InputStreamResource(fileStream);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(streamResource);
    }

    @GetMapping("/get-url")
    public ResponseEntity<String> getUrlFile(
            @RequestParam("fileName") String fileName,
            @RequestParam(name = "bucketName", defaultValue = "mybucket") String bucketName
    ) throws Exception {
        String url = this.minioService.getUrlFile(fileName, bucketName);
        return ResponseEntity.ok(url);
    }

}
