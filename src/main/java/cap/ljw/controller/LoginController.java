package cap.ljw.controller;

import cap.ljw.annotation.Log;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Staff;
import cap.ljw.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ljw
 * 2023.7.19
 * 登录以及注册
 */
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;
    @Log(value = "执行登录操作")
    @PostMapping("/login")
    public ResponseDTO login(@RequestBody Staff staff) {
        return this.loginService.login(staff);
    }

    //通过账号查找员工
    @Log(value = "正在查找员工账号")
    @GetMapping("/find")
    public ResponseDTO findByCode(String code){
        return this.loginService.findByCode(code);
    }

}
