package cap.ljw.controller;

import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.OverTimeType;
import cap.ljw.service.OvertimeTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ljw
 * 2023.7.19
 * tnnd 老板压榨员工加班的类型 种种借口
 */
@RestController
@RequestMapping("/overtime-type")
public class OvertimeTypeController {
    @Resource
    private OvertimeTypeService overtimeTypeService;

//    @ApiOperation("新增")
    @PostMapping
    public ResponseDTO add(@RequestBody OverTimeType overtimeType) {
        return this.overtimeTypeService.add(overtimeType);
    }

//    @ApiOperation("逻辑删除")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.overtimeTypeService.deleteById(id);
    }

//    @ApiOperation("批量逻辑删除")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.overtimeTypeService.deleteBatch(ids);
    }

//    @ApiOperation("编辑更新")
    @PutMapping
    public ResponseDTO edit(@RequestBody OverTimeType overtimeType) {
        return this.overtimeTypeService.edit(overtimeType);
    }

//    @ApiOperation("查询")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.overtimeTypeService.findById(id);
    }

//    @ApiOperation("查询所有")
    @GetMapping("/all")
    public ResponseDTO findAll() {
        return this.overtimeTypeService.findAll();
    }

}
