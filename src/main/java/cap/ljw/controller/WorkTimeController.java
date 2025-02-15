package cap.ljw.controller;

import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.WorkTime;
import cap.ljw.service.WorkTimeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ljw
 * 2023.7.19
 * 工作时间表
 * 前端控制器 for Work TIme fuck!!!!!!22:00
 */
@RestController
@RequestMapping("/work-time")
public class WorkTimeController {
    @Resource
    private WorkTimeService workTimeService;

//    @ApiOperation("新增")
    @PostMapping
    public ResponseDTO add(@RequestBody WorkTime workTime) {
        return this.workTimeService.add(workTime);
    }

//    @ApiOperation("逻辑删除")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.workTimeService.deleteById(id);
    }

//    @ApiOperation("批量逻辑删除")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.workTimeService.deleteBatch(ids);
    }

//    @ApiOperation("编辑更新")
    @PutMapping
    public ResponseDTO edit(@RequestBody WorkTime workTime) {
        return this.workTimeService.edit(workTime);
    }

//    @ApiOperation("查询")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.workTimeService.findById(id);
    }


//    @ApiOperation("设置工作时间")
    @PostMapping("/set")
    public ResponseDTO setWorkTime(@RequestBody WorkTime workTime) {
        return this.workTimeService.setWorkTime(workTime);
    }

}

