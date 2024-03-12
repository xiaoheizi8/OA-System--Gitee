package cap.ljw.controller;

import cap.ljw.annotation.Log;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Docs;
import cap.ljw.service.DocsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Ljw
 * 2023.7.19
 * 文档上传的接口
 */
@RestController
@RequestMapping("/docs")
public class DocsController {
    @Resource
    private DocsService docsService;

//    @ApiOperation("新增")
    @Log(value = "执行了新增文档操作")
    @PostMapping
    public ResponseDTO add(@RequestBody Docs docs) {
        return this.docsService.add(docs);
    }

//    @ApiOperation("逻辑删除")
    @Log(value = "执行了文档的逻辑删除")
    @DeleteMapping("/{id}")
    public ResponseDTO deleteById(@PathVariable Integer id) {
        return this.docsService.deleteById(id);
    }

//    @ApiOperation("批量逻辑删除")
    @Log(value = "执行了批量逻辑删除文件")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.docsService.deleteBatch(ids);
    }

//    @ApiOperation("编辑更新")
    @Log(value = "执行了编辑更新文件的操作")
    @PutMapping
    public ResponseDTO edit(@RequestBody Docs docs) {
        return this.docsService.edit(docs);
    }

//    @ApiOperation("查询")
    @Log(value = "执行文档查询操作")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.docsService.findById(id);
    }

//    @ApiOperation("分页条件查询")
    @Log(value = "执行文档分页查询操作")
    @GetMapping
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, String name) {
        return this.docsService.list(current, size, name);
    }

//    @ApiOperation("数据导出接口")
    @Log(value = "执行文档数据导出")
    @GetMapping("/export")
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        return this.docsService.export(response);
    }

//    @ApiOperation("数据导入接口")
    @Log(value = "执行文档数据导入")
    @PostMapping("/import")
    public ResponseDTO imp(MultipartFile file) throws IOException {
        return this.docsService.imp(file);
    }


//    @ApiOperation("文件上传")
    @Log(value = "执行文件上传")
    @PostMapping("/upload")
    public ResponseDTO upload(MultipartFile file) throws IOException {
        return this.docsService.upload(file);
    }

//    @ApiOperation("文件下载")
    @Log("执行文件下载操作")
    @GetMapping("/download/{filename}")
    public ResponseDTO download(@PathVariable String filename, HttpServletResponse response) throws IOException {
        return this.docsService.download(filename, response);
    }
}
