package cap.ljw.controller;

import cap.ljw.annotation.Log;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.City;
import cap.ljw.service.CityService;
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
 * 城市的控制器crud传输前端
 */
@RestController
@RequestMapping("/city")
public class CityController {
    @Resource
    private CityService cityService;
//    @ApiOperation("新增")
    @PostMapping
    @Log(value = "执行了新增操作")
    public ResponseDTO add(@RequestBody City city) {
        return this.cityService.add(city);
    }

//    @ApiOperation("逻辑删除")
        @Log(value = "执行了逻辑删除")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.cityService.deleteById(id);
    }

//    @ApiOperation("批量逻辑删除")
    @Log(value = "执行了批量逻辑删除")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.cityService.deleteBatch(ids);
    }

//    @ApiOperation("编辑更新")
    @Log(value = "执行了编辑更新操作")
    @PutMapping
    public ResponseDTO edit(@RequestBody City city) {
        return this.cityService.edit(city);
    }

//    @ApiOperation("查询")
    @Log(value = "执行了查询操作")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.cityService.findById(id);
    }

//    @ApiOperation("查询所有")
    @Log(value ="执行了查询所有城市数据操作")
    @GetMapping("/all")
    public ResponseDTO findAll(){
        return this.cityService.findAll();
    }

//    @ApiOperation("分页条件查询")
    @Log(value = "执行了分页条件查询")
    @GetMapping
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, String name) {
        return this.cityService.list(current, size, name);
    }

//    @ApiOperation("数据导出接口")
    @Log(value = "执行了响应数据导出操作")
    @GetMapping("/export")
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        return this.cityService.export(response);
    }

//    @ApiOperation("数据导入接口")
    @Log(value = "执行了数据导入操作")
    @PostMapping("/import")
    public ResponseDTO imp(MultipartFile file) throws IOException {
        return this.cityService.imp(file);
    }

}
