package cap.ljw.controller;

import cap.ljw.annotation.Log;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.StaffLeave;
import cap.ljw.service.StaffLeaveService;
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
 * 职工请假控制器
 */
@RestController
@RequestMapping("/staff-leave")
public class StaffLeaveController {

    @Resource
    private StaffLeaveService staffLeaveService;

//    @ApiOperation("新增")
    @PostMapping
    public ResponseDTO add(@RequestBody StaffLeave staffLeave) {
        return this.staffLeaveService.add(staffLeave);
    }

//    @ApiOperation("逻辑删除")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.staffLeaveService.deleteById(id);
    }

//    @ApiOperation("批量逻辑删除")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.staffLeaveService.deleteBatch(ids);
    }

//    @ApiOperation("编辑更新")
    @PutMapping
    public ResponseDTO edit(@RequestBody StaffLeave staffLeave) {
        return this.staffLeaveService.edit(staffLeave);
    }


//    @ApiOperation("查询")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.staffLeaveService.findById(id);
    }

//    @ApiOperation("分页条件查询")
    @GetMapping
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, String name) {
        return this.staffLeaveService.list(current, size, name);
    }

//    @ApiOperation("数据导出接口")
    @GetMapping("/export")
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        return this.staffLeaveService.export(response);
    }

//    @ApiOperation("数据导入接口")
    @PostMapping("/import")
    public ResponseDTO imp(MultipartFile file) throws IOException {
        return this.staffLeaveService.imp(file);
    }

//    @ApiOperation("分页")
    @GetMapping("/staff")
    public ResponseDTO findByStaffId(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, Integer id) {
        return this.staffLeaveService.findByStaffId(current, size, id);
    }

//    @ApiOperation("查询未被审核的请假")
    @Log(value = "查询还没有审核的请假")
    @GetMapping("/staff/{id}")
    public ResponseDTO findUnauditedByStaffId(@PathVariable Integer id) {
        return this.staffLeaveService.findUnauditedByStaffId(id);
    }

}

