package com.bdqn.service.impl;

import com.bdqn.dao.*;
import com.bdqn.entity.*;
import com.bdqn.service.CheckoutService;
import com.bdqn.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

    @Resource
    private CheckoutMapper checkoutMapper;

    @Resource
    private CheckinMapper checkinMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Resource
    private RoomMapper roomMapper;

    /**
     * 添加退房记录
     *
     * @param checkout
     * @return
     */
    public int addCheckout(Checkout checkout) {
        try {
            //#1.新增一条退房记录
            checkout.setCreateDate(new Date());//创建时间（什么时候操作了办理退房）
            checkout.setCheckOutNumber(UUIDUtils.randomUUID());
            //调用新增退房记录的方法
            int count = checkoutMapper.addCheckout(checkout);
            if(count > 0){

                //#2.修改t_checkin中status状态，修改成2(已离店)
                Checkin checkin = checkinMapper.findById(checkout.getCheckInId());
                checkin.setStatus(2);//已离店
                //调用修改入住订单的方法
                checkinMapper.updateCheckin(checkin);

                //#3.修改t_orders表中的status状态，修改成4(订单已完成)
                Orders orders = new Orders();
                orders.setStatus(4);//订单已完成
                orders.setId(checkin.getOrdersid());
                //调用修改订单的方法
                ordersMapper.updateOrders(orders);

                //#4.修改t_room_type表中的可用房间数(+1),已入住房间数-1
                RoomType roomType = roomTypeMapper.findById(checkin.getRoomTypeId());
                roomType.setAvailableRooms(roomType.getAvailableRooms() + 1);
                roomType.setCurrentOccupancy(roomType.getCurrentOccupancy() - 1);
                roomTypeMapper.updateRoomType(roomType);

                //#5.修改t_room表中的状态，修改成空闲
                Room room = roomMapper.findById(checkin.getRoomId());
                room.setStatus("空闲");
                roomMapper.updateRoom(room);
            }

            return count;
        } catch (Exception e) {
            // Handle exception appropriately, e.g., log it and rethrow or handle it as needed
            e.printStackTrace();
            return 0;
        }
    }
}
