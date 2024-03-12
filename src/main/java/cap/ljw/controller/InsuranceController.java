package cap.ljw.controller;

import cap.ljw.annotation.Log;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Insurance;
import cap.ljw.service.InsuranceService;
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
 * 保险的控制器
 */
@RestController
@RequestMapping("/insurance")
public class InsuranceController {
    @Resource
    private InsuranceService insuranceService;

//    @ApiOperation("新增")
    @Log(value = "调用新增保险")
    @PostMapping
    public ResponseDTO add(@RequestBody Insurance insurance) {
        return this.insuranceService.add(insurance);
    }

//    @ApiOperation("逻辑删除")
    @Log(value = "对保险进行逻辑删除")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.insuranceService.deleteById(id);
    }

//    @ApiOperation("批量逻辑删除")
    @Log(value = "对保险进行批量逻辑删除")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.insuranceService.deleteBatch(ids);
    }

//    @ApiOperation("编辑更新")
    @Log(value = "对保险进行编辑更新操作")
    @PutMapping
    public ResponseDTO edit(@RequestBody Insurance insurance) {
        return this.insuranceService.edit(insurance);
    }

//    @ApiOperation("查询")
    @Log(value = "对保险进行查询操作")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.insuranceService.findById(id);
    }

//    @ApiOperation("查询")
    @Log(value = "通过职工的id对保险进行查询")
    @GetMapping("/staff/{id}")
    public ResponseDTO findByStaffId(@PathVariable Integer id) {
        return this.insuranceService.findByStaffId(id);
    }

//    @ApiOperation("分页条件查询")
    @Log(value = "对保险进行分页条件查询")
    @GetMapping
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String name) {
        return this.insuranceService.list(current, size, name);
    }

//    @ApiOperation("数据导出接口")
    @Log(value = "保险数据导出")
    @GetMapping("/export")
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        return this.insuranceService.export(response);
    }

    @ApiOperation("数据导入接口")
    @PostMapping("/import")
    public ResponseDTO imp(MultipartFile file) throws IOException {
        return this.insuranceService.imp(file);
    }

    @ApiOperation("为员工设置社保")
    @PostMapping("/set")
    public ResponseDTO setInsurance(@RequestBody Insurance insurance) {
        return this.insuranceService.setInsurance(insurance);
    }
}
