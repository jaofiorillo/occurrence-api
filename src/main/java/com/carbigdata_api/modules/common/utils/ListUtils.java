package com.carbigdata_api.modules.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ListUtils {

    public static boolean isEmpty(List<MultipartFile> value) {
        if (value == null || value.isEmpty()) return false;
        return value.stream().allMatch(MultipartFile::isEmpty);
    }
}
