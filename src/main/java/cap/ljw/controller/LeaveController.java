package cap.ljw.controller;

import cap.ljw.annotation.Log;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Leave;
import cap.ljw.service.LeaveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ljw
 * 2023.7.19
 * 请假的控制器
 */
@RestController
@RequestMapping("/leave")
public class LeaveController {
    @Resource
    private LeaveService leaveService;

//    @ApiOperation("新增")
    @Log(value = "执行新增请假数据")
    @PostMapping
    public ResponseDTO add(@RequestBody Leave leave) {
        return this.leaveService.add(leave);
    }

//    @ApiOperation("逻辑删除")
    @Log(value = "执行请假的逻辑删除")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.leaveService.deleteById(id);
    }

//    @ApiOperation("批量逻辑删除")
    @Log(value = "执行批量逻辑删除请假")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.leaveService.deleteBatch(ids);
    }

//    @ApiOperation("编辑更新")
    @Log(value = "执行编辑更新请假数据")
    @PutMapping
    public ResponseDTO edit(@RequestBody Leave leave) {
        return this.leaveService.edit(leave);
    }

//    @ApiOperation("查询")
    @Log(value = "根据部门查询请假数据")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.leaveService.findById(id);
    }


//    @ApiOperation("获取")
    @Log(value = "获取员工请假数据")
    @GetMapping("/{deptId}/{typeId}")
    public ResponseDTO find(@PathVariable Integer deptId, @PathVariable Integer typeId) {
        return this.leaveService.find(deptId, typeId);
    }

//    @ApiOperation("设置假期")
    @Log(value = "设定假期时间")
    @PostMapping("/set")
    public ResponseDTO setLeave(@RequestBody Leave leave) {
        return this.leaveService.setLeave(leave);
    }


//    @ApiOperation("查询")
    @Log(value = "根据部门和类型进行查询")
    @GetMapping("/dept/{id}")
    public ResponseDTO findByDeptId(@PathVariable Integer id) {
        return this.leaveService.findByDeptId(id);
    }

}
