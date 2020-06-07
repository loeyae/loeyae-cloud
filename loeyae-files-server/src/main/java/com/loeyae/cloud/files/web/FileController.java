package com.loeyae.cloud.files.web;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.files.server.IFastDFS;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * FileController
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/6 18:41
 */
@RestController
public class FileController {
    @Autowired
    private IFastDFS fdfsClient;

    @Autowired
    private HttpServletRequest request;

    /**
     * 上传文件
     * @param file 文件
     * @return 文件地址
     */
    @PostMapping(value = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResult<String> upload(@RequestPart("file") MultipartFile file) throws IOException {
        String url = fdfsClient.uploadFile(file);
        return ApiResult.ok(url);
    }

    /**
     * 删除文件
     * @param fileUrl
     * @return
     */
    @PostMapping(value = "/file/delete")
    ApiResult<Boolean> deleteFile(@RequestParam("file_url") String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return ApiResult.ok(Boolean.FALSE);
        }
        try{
            fdfsClient.deleteFile(fileUrl);
        }catch (Exception e){
            e.printStackTrace();
            return ApiResult.failed("delete file failed："+e.getMessage());
        }
        return ApiResult.ok(Boolean.TRUE);
    }

    /**
     * 删除文件
     * @param fileUrlList
     * @return
     */
    @PostMapping(value = "/file/delete/batch")
    ApiResult<Boolean> deleteFileBatch(@RequestParam("file_url_list") List<String> fileUrlList) {
        if (CollectionUtils.isEmpty(fileUrlList)) {
            return ApiResult.ok(Boolean.FALSE);
        }
        try{
            fileUrlList.parallelStream().forEach(fileUrl -> {
                fdfsClient.deleteFile(fileUrl);
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResult.ok(Boolean.TRUE);
    }

    /**
     * 文件下载
     * @param fileUrl  url 开头从组名开始
     * @param response
     * @throws Exception
     */
    @RequestMapping("/file/download")
    public void  download(String fileUrl, HttpServletResponse response) throws Exception{
        byte[] data = fdfsClient.download(fileUrl);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("test.jpg", "UTF-8"));

        // 写出
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.write(data, outputStream);
    }
}
