package cap.ljw.controller;

import cap.ljw.annotation.Log;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Dept;
import cap.ljw.service.DeptService;
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
 * 圈钱控制器
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private DeptService deptService;

//    @ApiOperation("新增")
    @Log(value = "执行了新增部门数据操作")
    @PostMapping
    public ResponseDTO add(@RequestBody Dept dept) {
        return this.deptService.add(dept);
    }

//    @ApiOperation("逻辑删除")
    @Log(value = "执行了部门逻辑删除操作")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.deptService.deleteById(id);
    }

//    @ApiOperation("批量逻辑删除")
    @Log(value = "执行了批量逻辑删除部门操纵")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.deptService.deleteBatch(ids);
    }

//    @ApiOperation("编辑更新")
    @Log(value ="执行了编辑更新部门数据操作")
    @PutMapping
    public ResponseDTO edit(@RequestBody Dept dept) {
        return this.deptService.edit(dept);
    }

//    @ApiOperation("查询")
    @Log(value = "执行了查询部门数据操作")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.deptService.findById(id);
    }

//    @ApiOperation("查询所有")
    @Log(value = "执行了查询所有的部门数据操作")
    @GetMapping("/all")
    public ResponseDTO findAll(){
        return this.deptService.findAll();
    }

//    @ApiOperation("查询所有子部门")
    @Log(value = "执行了查询所有子部门操作")
    @GetMapping("/all/sub")
    public ResponseDTO findAllSubDept(){
        return this.deptService.findAllSubDept();
    }

//    @ApiOperation("分页条件查询")
    @Log(value = "执行了部门分页条件查询操作")
    @GetMapping
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, String name) {
        return this.deptService.list(current, size, name);
    }

//    @ApiOperation("数据导出接口").
    @Log(value = "执行了部门数据导出操作")
    @GetMapping("/export")
    public ResponseDTO export(HttpServletResponse response) throws Exception {
        return this.deptService.export(response);
    }

//    @ApiOperation("数据导入接口")
    @Log(value = "执行了部门数据导入的操作")
    @PostMapping("/import")
    public ResponseDTO imp(MultipartFile file) throws IOException {
        return this.deptService.imp(file);
    }


}
