package cap.ljw.service;

import cap.ljw.dto.Response;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Docs;
import cap.ljw.enums.BusinessStatusEnum;
import cap.ljw.exception.ServiceException;
import cap.ljw.mapper.DocsMapper;
import cap.ljw.util.HutoolExcelUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
 import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ljw
 * 2023.7.17
 */
@Service
public class DocsService extends ServiceImpl<DocsMapper, Docs> {

    @Value("${files.upload.path}") // 引入上传文件的存储路径
    private String fileUploadPath;

    //上传文件

    public ResponseDTO upload(MultipartFile uploadFile) throws IOException {
        File fold = new File(fileUploadPath);
        // 若存储上传文件的文件夹不存在，则创建
        if (!fold.exists()) {
            fold.mkdirs();
        }
        // 判断上传的文件是否为空
        if (!uploadFile.isEmpty()) {
            String originalFilename = uploadFile.getOriginalFilename(); // 获取文件的原名称
            String extName = FileUtil.extName(originalFilename); // 获取文件的后缀名
            String filename = IdUtil.fastSimpleUUID().substring(2, 22) + "." + extName; // 文件名

            // 获取文件的md5信息
            String md5 = SecureUtil.md5(uploadFile.getInputStream());
            List<Docs> docsList = list(new QueryWrapper<Docs>().eq("md5", md5));
            if (docsList != null && docsList.size() > 0) {
                filename = docsList.get(0).getName();
            } else {
                File file = new File(fileUploadPath + filename);
                // 将文件存储到磁盘
                uploadFile.transferTo(file);
            }
            // 将文件数据保存到数据库
            Docs docs = new Docs();
            docs.setName(filename);
            docs.setType(extName);
            docs.setOldName(originalFilename);
            docs.setMd5(md5);
            docs.setSize(uploadFile.getSize() / 1024); // KB
            if (save(docs)) {
                return Response.success("文件上传成功！", docs);
            }
            throw new ServiceException(BusinessStatusEnum.ERROR);
        }
        throw new ServiceException(BusinessStatusEnum.NOT_EXIST);
    }


    //文件下载

    public ResponseDTO download(String filename, HttpServletResponse response) throws IOException {
        // 通知浏览器以下载的方式打开
        response.addHeader("Content-Type", "application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));//编码格式
        // 通过文件流读取文件
        File downloadFile = new File(fileUploadPath + filename);
        OutputStream out = response.getOutputStream();
        // 读取文件的字节流
        out.write(FileUtil.readBytes(downloadFile));
        out.flush();
        out.close();
        return Response.success();
    }


    public ResponseDTO add(Docs docs) {
        if (save(docs)) {
            return Response.success();
        }
        return Response.error();
    }

    public ResponseDTO deleteById(Integer id) {
        if (removeById(id)) {
            return Response.success();
        }
        return Response.error();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO deleteBatch(List<Integer> ids) {
        if (removeBatchByIds(ids)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO edit(Docs docs) {
        if (updateById(docs)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        Docs docs = getById(id);
        if (docs != null) {
            return Response.success(docs);
        }
        return Response.error();
    }


    public ResponseDTO list(Integer current, Integer size, String name) {
        IPage<Docs> config = new Page<>(current, size);
        QueryWrapper<Docs> wrapper = null;
        if (name != "" && name != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("name", name);
        }
        IPage<Docs> page = page(config, wrapper);
        // 将响应数据填充到map中
        Map map = new HashMap();
        map.put("pages", page.getPages());
        map.put("total", page.getTotal());
        map.put("list", page.getRecords());
        return Response.success(map);
    }

    public ResponseDTO export(HttpServletResponse response) throws IOException {
        List<Docs> list = list();
        HutoolExcelUtil.writeExcel(response, list, "文件信息表", Docs.class);
        return Response.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Docs> list = HutoolExcelUtil.readExcel(inputStream, 1, Docs.class);
        // IService接口中的方法.批量插入数据
        if (saveBatch(list)) {
            return Response.success();
        }
        return Response.error();
    }


}
