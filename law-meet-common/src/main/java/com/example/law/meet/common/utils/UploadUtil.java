package com.example.law.meet.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadUtil {

    public static String uploadFile(MultipartFile file, String uploadDir, String customFileName) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        // 创建上传目录（如果不存在）
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // 使用自定义文件名
        File dest = new File(directory, customFileName);
        try (FileOutputStream fos = new FileOutputStream(dest)) {
            fos.write(file.getBytes());
        }
        return customFileName;
    }

    public static List<String> uploadFiles(MultipartFile[] files, String uploadDir) throws IOException {
        List<String> uploadedFileNames = new ArrayList<>();
        if (files == null || files.length == 0) {
            return uploadedFileNames;
        }
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFileName = System.currentTimeMillis() + fileExtension;
            File dest = new File(directory, uniqueFileName);
            try (FileOutputStream fos = new FileOutputStream(dest)) {
                fos.write(file.getBytes());
                uploadedFileNames.add(uniqueFileName);
            }
        }
        return uploadedFileNames;
    }

}
