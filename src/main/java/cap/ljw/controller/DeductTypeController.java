package cap.ljw.controller;

import cap.ljw.annotation.Log;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.DeductType;
import cap.ljw.service.DeductTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ljw
 * 2023.7.19
 * 圈钱类型控制器
 */
@RestController
@RequestMapping("/deduct-type")
public class DeductTypeController {
    @Resource
    private DeductTypeService deductTypeService;

//    @ApiOperation("新增")
    @Log(value = "执行了新增部门类型操作")
    @PostMapping
    public ResponseDTO add(@RequestBody DeductType deductType) {
        return this.deductTypeService.add(deductType);
    }
//    @ApiOperation("逻辑删除")
    @Log(value = "执行了逻辑删除部门类型操作")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.deductTypeService.deleteById(id);
    }

//    @ApiOperation("批量逻辑删除")
    @Log(value = "执行了部门的批量逻辑类型的操作")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.deductTypeService.deleteBatch(ids);
    }

//    @ApiOperation("编辑更新")
    @Log(value = "执行了部门编辑更新类型操作")
    @PutMapping
    public ResponseDTO edit(@RequestBody DeductType deductType) {
        return this.deductTypeService.edit(deductType);
    }

//    @ApiOperation("查询")
    @Log(value = "执行了部门数据类型查询操作")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.deductTypeService.findById(id);
    }

//    @ApiOperation("查询所有")
    @Log(value = "执行了查询所有部门类型的操作")
    @GetMapping("/all")
    public ResponseDTO findAll() {
        return this.deductTypeService.findAll();
    }
}