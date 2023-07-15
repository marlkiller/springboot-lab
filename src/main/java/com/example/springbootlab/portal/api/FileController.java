package com.example.springbootlab.portal.api;

import com.example.springbootlab.common.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author artemis
 * @date 2023/5/26 15:45
 */
@RestController
@RequestMapping("file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/upload")
    public ResultVo<String> upload(@RequestParam("file") MultipartFile file) {
        return ResultVo.ok(file.getOriginalFilename());
    }

    /**
     * Resource表示一个可以获得输入流的资源,它是所有Spring IO资源实现的一个接口。比较常用的实现类包括:
     * <p>
     * FileSystemResource: 从文件系统目录获取资源
     * UrlResource: 从URL地址获取资源
     * ByteArrayResource: 从字节数组中获取资源
     * InputStreamResource: 从输入流中获取资源
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> download(String fileName) throws IOException {
        // 从指定位置获取文件 
        File file = new File(fileName);
        Resource resource = new UrlResource(file.toURI());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(file.getName(), StandardCharsets.UTF_8))
                .body(resource);
    }
}
