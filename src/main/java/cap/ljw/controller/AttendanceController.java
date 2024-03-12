package cap.ljw.controller;

import cap.ljw.annotation.Log;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Attendance;
import cap.ljw.service.AttendanceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Ljw
 * 2023.7.18
 * 考勤前端控制器
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Resource
    private AttendanceService attendanceService;

//    @ApiOperation("新增")
    @PostMapping
    @Log(value = "执行了新增考勤的操作")
    public ResponseDTO add(@RequestBody Attendance attendance) {
        return this.attendanceService.add(attendance);
    }

//    @ApiOperation("逻辑删除")
    @DeleteMapping("/{id}")
    @Log(value = "执行了逻辑删除")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.attendanceService.deleteById(id);
    }

//    @ApiOperation("批量逻辑删除")
    @DeleteMapping("/batch/{ids}")
    @Log(value = "执行了考勤批量逻辑删除")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.attendanceService.deleteBatch(ids);
    }

//    @ApiOperation("编辑更新")
    @PutMapping
    @Log(value = "执行了编辑更新的操作")
    public ResponseDTO edit(@RequestBody Attendance attendance) {
        return this.attendanceService.edit(attendance);
    }

//    @ApiOperation("查询")
    @GetMapping("/{id}")
    @Log(value = "执行了查询的操作")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.attendanceService.findById(id);
    }

//    @ApiOperation("分页条件查询")
    @GetMapping
    @Log(value = "执行了分页条件查询的操作")
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, String name, String month) {
        return this.attendanceService.list(current, size, name, month);
    }

//    @ApiOperation("数据导出接口")
    @GetMapping("/export/{month}")
    @Log(value = "执行了数据导出的操作")
    public ResponseDTO export(HttpServletResponse response, @PathVariable String month) throws IOException {
        return this.attendanceService.export(response, month);
    }

//    @ApiOperation("数据导入接口")
    @PostMapping("/import")
    @Log(value = "执行了数据导入的操作")
    public ResponseDTO imp(MultipartFile file) throws IOException {
        return this.attendanceService.imp(file);
    }

//    @ApiOperation("查询")
    @GetMapping("/staff/{id}")
    @Log(value = "执行了查询的操作")
    public ResponseDTO findByStaffId(@PathVariable Integer id) {
        return this.attendanceService.findByStaffId(id);
    }

//    @ApiOperation("编辑更新")
    @PutMapping("/set")
    @Log(value = "执行了编辑更新的操作")
    public ResponseDTO setAttendance(@RequestBody Attendance attendance) {
        return this.attendanceService.setAttendance(attendance);
    }


}

