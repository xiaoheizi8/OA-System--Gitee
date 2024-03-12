package cap.ljw.controller;

import cap.ljw.annotation.Log;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.LeaveType;
import cap.ljw.service.LeaveTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Ljw
 * 2023.7.19
 * 请假类型
 */
@RestController
@RequestMapping("/leave-type")
public class LeaveTypeController {
    @Resource
    private LeaveTypeService leaveTypeService;

//    @ApiOperation("新增")
    @Log(value = "新增请假数据类型")
    @PostMapping
    public ResponseDTO add(@RequestBody LeaveType leaveType) {
        return this.leaveTypeService.add(leaveType);
    }

//    @ApiOperation("逻辑删除")
    @Log(value = "删除请假类型")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.leaveTypeService.deleteById(id);
    }

//    @ApiOperation("编辑更新")
    @Log(value = "对请假类型实现编辑更新操作")
    @PutMapping
    public ResponseDTO edit(@RequestBody LeaveType leaveType) {
        return this.leaveTypeService.edit(leaveType);
    }

//    @ApiOperation("查询")
    @Log(value = "对请假类型执行查询操作")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.leaveTypeService.findById(id);
    }

//    @ApiOperation("查询所有")
    @Log(value = "查询所有请假的类型")
    @GetMapping("/all")
    public ResponseDTO findAll() {
        return this.leaveTypeService.findAll();
    }

}
