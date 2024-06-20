package com.training.account_api.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public MinioClient minioClient(
            @Value("${spring.minio.url}") String url,
            @Value("${spring.minio.access-key}") String accessKey,
            @Value("${spring.minio.storageAccountKey}") String storageAccountKey){
        try {
            return MinioClient.builder()
                    .endpoint(url)
                    .credentials(accessKey, storageAccountKey)
                    .build();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
