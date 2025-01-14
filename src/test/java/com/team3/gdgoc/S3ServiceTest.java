package com.team3.gdgoc;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class S3ServiceTest {

    @Test
    public void testBucketAccess() {

        String region = System.getenv("AWS_REGION");
        String bucket = System.getenv("AWS_BUCKET");

        String accessKey = System.getenv("AWS_ACCESS_KEY");
        String secretKey = System.getenv("AWS_SECRET_KEY");

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);

        // 예외 발생 여부를 테스트
        assertDoesNotThrow(() -> {
            try (S3Client s3 = S3Client.builder()
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                    .build()) {

                Path filePath = Paths.get("src/main/resources/test-file.txt");
                String keyName = "test-file.txt";

                // 파일을 S3에 업로드
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(keyName)
                        .build();

                s3.putObject(putObjectRequest, RequestBody.fromFile(filePath));

                // 파일 다운로드 요청
                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(keyName)
                        .build();

                // 파일 다운로드 실행
                s3.getObject(getObjectRequest);

                System.out.println("File uploaded and downloaded successfully.");
            }
        }, "S3 Exception occurred! The test failed.");
    }

}
