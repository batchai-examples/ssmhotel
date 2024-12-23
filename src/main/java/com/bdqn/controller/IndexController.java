package com.bdqn.controller;

import com.bdqn.entity.Floor;
import com.bdqn.entity.Room;
import com.bdqn.entity.RoomType;
import com.bdqn.service.FloorService;
import com.bdqn.service.RoomService;
import com.bdqn.service.RoomTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {


    @Resource
    private RoomTypeService roomTypeService;

    @Resource
    private FloorService floorService;

    @Resource
    private RoomService roomService;


    @RequestMapping("/index.html")
    public String index(Model model){
        //调用查询房型列表的方法
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(null);
        //调用查询所有楼层列表的方法
        List<Floor> floorList = floorService.findFloorList(null);
        //调用查询每个楼层的房间列表
        List<Room> roomList = roomService.findRoomListByFloorId(1); // 假设提供一个默认参数
        //将数据放到模型中
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("floorList",floorList);
        model.addAttribute("roomList",roomList);
        return "forward:/home.jsp";
    }

}
