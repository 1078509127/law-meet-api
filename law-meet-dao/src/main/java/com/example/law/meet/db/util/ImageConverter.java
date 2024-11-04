package com.example.law.meet.db.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;


public class ImageConverter implements Converter<String> {
    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public WriteCellData convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws IOException {
        if (value == null || value.isEmpty()) {
            return new WriteCellData();
        }
        //只转换字符串url中的中文
        int startIndex = value.lastIndexOf("-") + 1;
        int endIndex = value.lastIndexOf(".jpg");
        String substring = value.substring(startIndex, endIndex);
        String encodedUrl = URLEncoder.encode(substring, "UTF-8");
        StringBuilder sb = new StringBuilder(value);
        StringBuilder replace = sb.replace(startIndex, endIndex, encodedUrl);

        URL url = new URL(replace.toString());
        try (InputStream inputStream = url.openStream();
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length))!= -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return new WriteCellData(buffer.toByteArray());
        }
    }

}
