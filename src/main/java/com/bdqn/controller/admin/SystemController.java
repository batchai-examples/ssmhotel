package com.bdqn.controller.admin;

import com.bdqn.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 * 负责跳转页面(后台管理页面)
 */
@Controller
@RequestMapping("/admin")
public class SystemController {


    /**
     * 去到登录页面
     * @return
     */
    @RequestMapping("/login.html")
    public String login(){
        return "admin/login";
    }


    /**
     * 去到后台首页
     * @return
     */
    @RequestMapping("/home.html")
    public String home(){
        return "admin/home";
    }


    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //清空session
        session.removeAttribute(SystemConstant.LOGINUSER);
        //session.invalidate();
        //重定向到登录页面
        return "redirect:/admin/login.html";
    }

    /**
     * 去到部门管理页面
     * @return
     */
    @RequestMapping("/toDeptManager")
    public String toDeptManager(){
        return "admin/dept/deptManager";
    }


    /**
     * 去到角色管理页面
     * @return
     */
    @RequestMapping("/toRoleManager")
    public String toRoleManager(){
        return "admin/role/roleManager";
    }


    /**
     * 去到员工管理页面
     * @return
     */
    @RequestMapping("/toEmployeeManager")
    public String toEmployeeManager(){
        return "admin/employee/employeeManager";
    }

    /**
     * 去到菜单管理页面
     * @return
     */
    @RequestMapping("/toMenuManager")
    public String toMenuManager(){
        return "admin/menu/menuManager";
    }

    /**
     * 去到楼层管理页面
     * @return
     */
    @RequestMapping("/toFloorManager")
    public String toFloorManager(){
        return "admin/floor/floorManager";
    }

    /**
     * 去到房型管理页面
     * @return
     */
    @RequestMapping("/toRoomTypeManager")
    public String toRoomTypeManager(){
        return "admin/roomType/roomTypeManager";
    }

    /**
     * 去到入住管理页面
     * @return
     */
    @RequestMapping("/toCheckinManager")
    public String toCheckinManager(){
        return "admin/checkin/checkinManager";
    }


    /**
     * 去到年度营业额报表统计分析页面
     * @return
     */
    @RequestMapping("/toYearTotalPriceManager")
    public String toYearTotalPriceManager(){
        return "admin/charts/yearTotalPriceCharts";
    }


    /**
     * 去到月营业额报表统计分析页面
     * @return
     */
    @RequestMapping("/toYearOfMonthCharts")
    public String toYearOfMonthCharts(){
        return "admin/charts/yearOfMonthCharts";
    }

    /**
     * 去到季度营业额报表统计分析页面
     * @return
     */
    @RequestMapping("/toQuarterTotalPriceCharts")
    public String toQuarterTotalPriceCharts(){
        return "admin/charts/quarterTotalPriceCharts";
    }

    /**
     * 去到房型预订报表统计分析页面
     * @return
     */
    @RequestMapping("/toRoomTypePieCharts")
    public String toRoomTypePieCharts(){
        return "admin/charts/roomTypePieCharts";
    }

    /**
     * 当日开房报表
     * @return
     */
    @RequestMapping({"/toCurrentDateCheckinCharts"})
    public String toCurrentDateCheckinCharts(){
        return "admin/charts/currentDateCheckinCharts";
    }


}
