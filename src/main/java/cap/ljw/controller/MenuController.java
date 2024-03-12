package cap.ljw.controller;

import cap.ljw.annotation.Log;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Menu;
import cap.ljw.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Ljw
 * 中心控制器
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

//    @ApiOperation("新增")
    @PostMapping
    @Log(value = "执行add方法")
    public ResponseDTO add(@RequestBody Menu menu) {
        return this.menuService.add(menu);
    }

//    @ApiOperation("逻辑删除")
    @Log(value = "执行deleteById方法")
    @DeleteMapping("/{id}")
    public ResponseDTO deleteById(@PathVariable Integer id) {
        return this.menuService.deleteById(id);
    }

//    @ApiOperation("批量逻辑删除")
    @DeleteMapping("/batch/{ids}")
    @Log(value = "执行deleteBatch方法")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.menuService.deleteBatch(ids);
    }

//    @ApiOperation("编辑更新")
    @PutMapping
    @Log(value = "执行edit方法")
    public ResponseDTO edit(@RequestBody Menu menu) {
        return this.menuService.edit(menu);
    }

//    @ApiOperation("查询")
    @Log(value = "执行findById方法")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.menuService.findById(id);
    }

//    @ApiOperation("查询所有")
    @Log(value = "执行findAll方法")
    @GetMapping("/all")
    public ResponseDTO findAll(){
        return this.menuService.findAll();
    }

//    @ApiOperation("分页条件查询")
    @GetMapping
    @Log(value = "执行list方法")
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, String name) {
        return this.menuService.list(current, size, name);
    }

//    @ApiOperation("数据导出接口")
    @GetMapping("/export")
    @Log(value = "执行export方法")
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        return this.menuService.export(response);
    }

//    @ApiOperation("数据导入接口")
    @PostMapping("/import")
    @Log(value = "执行imp方法")
    public ResponseDTO imp(MultipartFile file) throws IOException {
        return this.menuService.imp(file);
    }

//    @ApiOperation("获取员工的菜单")
    @GetMapping("/staff/{id}")
    @Log(value = "执行getStaffMenu")
    public ResponseDTO getStaffMenu(@PathVariable Integer id){
        return this.menuService.getStaffMenu(id);
    }


}
