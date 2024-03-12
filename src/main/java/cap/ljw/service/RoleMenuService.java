package cap.ljw.service;

import cap.ljw.dto.Response;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.RoleMenu;
import cap.ljw.enums.BusinessStatusEnum;
import cap.ljw.exception.ServiceException;
import cap.ljw.mapper.RoleMenuMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ljw
 * 2023.7.18
 * 角色菜单服务类
 */
@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> {

    //为角色设置菜单
    @Transactional
    public ResponseDTO setMenu(Integer roleId, List<Integer> menuIds) {
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        List<RoleMenu> list = list(wrapper);
        // 先禁用不需要的菜单
        for (RoleMenu roleMenu : list) {
            if (menuIds.contains(roleMenu.getMenuId())) {
                roleMenu.setStatus(1); // 启用
            } else {
                roleMenu.setStatus(0); // 禁用
            }
            updateById(roleMenu);
        }

        for (Integer menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setStatus(1);
            QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper();
            queryWrapper.eq("role_id", roleId).eq("menu_id", menuId);
            // 若角色拥有此菜单，则只需将status设为1即可；若不拥有此菜单，则新增，并将status设为1。
            if (!saveOrUpdate(roleMenu, queryWrapper)) {
                throw new ServiceException(BusinessStatusEnum.ERROR);
            }
        }
        return Response.success();
    }

    //获取角色的菜单
    public ResponseDTO getMenu(Integer roleId) {
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId).eq("status", 1);
        List<RoleMenu> list = list(wrapper);
        return Response.success(list);
    }


}




