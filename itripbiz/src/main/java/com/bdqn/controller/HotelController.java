package com.bdqn.controller;

import cn.itrip.common.Page;
import cn.itrip.dao.SearchHotelRoomVO;
import cn.itrip.dao.ValidationToken;
import cn.itrip.dao.itripComment.ItripCommentMapper;
import cn.itrip.dao.itripHotel.ItripHotelMapper;
import cn.itrip.dao.itripHotelTempStore.ItripHotelTempStoreMapper;
import cn.itrip.dao.itripImage.ItripImageMapper;
import cn.itrip.dao.itripLabelDic.ItripLabelDicMapper;
import cn.itrip.dao.itripUserLinkUser.ItripUserLinkUserMapper;
import cn.itrip.pojo.*;
import cn.itrip.pojo.userinfo.ItripAddUserLinkUserVO;
import com.bdqn.biz.HotelServer;
import com.sun.media.jfxmedia.logging.Logger;
import org.apache.ibatis.annotations.Param;
import org.apache.tools.ant.taskdefs.Get;
import org.apache.zookeeper.data.Id;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.rmi.MarshalledObject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/api")
public class HotelController {
    @Resource
    HotelServer biz;
    @Resource
    ItripHotelMapper dao;
    @Resource
    ItripCommentMapper dao1;
    @Resource
    ItripLabelDicMapper dao2;

    @Resource
    ItripHotelTempStoreMapper dao3;
    @Resource
    ItripImageMapper dao4;
    @Resource
    ItripUserLinkUserMapper dao5;



    //热门城市
    @RequestMapping(value = "/hotel/queryhotcity/{type}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
     public Dto GerCity(@PathVariable("type")int type) throws Exception {
        List<ItripAreaDic> Dto=biz.GerDate(type);
     if(Dto!=null){
         return DtoUtil.returnDataSuccess(Dto);
     }
return  DtoUtil.returnFail("数据访问失败","10002");
         }
    // 查询酒店特色列表
    @RequestMapping(value = "/hotel/queryhotelfeature",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public Dto GetHotel () throws Exception {
        List<ItripLabelDic> Doto = biz.GetFirstDate();
        if (Doto != null) {
            return DtoUtil.returnDataSuccess(Doto);
        }
        return DtoUtil.returnFail("数据访问失败", "10002");
    }
//查询房间
    //hotelroom/queryhotelroombyhotel
    @RequestMapping(value = "/hotelroom/queryhotelroombyhotel",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Dto<List<ItripHotelRoomVO>>  getHotelRoom(@RequestBody SearchHotelRoomVO vo){

        try {
            List<ItripHotelRoomVO> list=biz.getHotelRoom(vo);
            List<List<ItripHotelRoomVO>> hotelRoomVOList = new ArrayList<List<ItripHotelRoomVO>>();
            for (ItripHotelRoomVO roomVO : list){
                List<ItripHotelRoomVO> tempList = new ArrayList<ItripHotelRoomVO>();
                tempList.add(roomVO);
                hotelRoomVOList.add(tempList);
            }

            return DtoUtil.returnSuccess("成功",hotelRoomVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //查询图片
    @RequestMapping(value = "/hotelroom/getimg/{targetId}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public Dto getPig(@PathVariable("targetId") String targetId){
    List<ItripImageVO> list= biz.getPig(targetId);
    return DtoUtil.returnSuccess("成功！！！！",list);

    }
//    查询商圈
    @RequestMapping(value = "/hotel/querytradearea/{cityId}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public  Dto getSQ(@PathVariable("cityId") Integer cityId){
List<ItripAreaDicVO> list =biz.getSQ(cityId);
return  DtoUtil.returnDataSuccess(list);
    }
//根据酒店id查询酒店特色和介绍
    @RequestMapping(value = "/hotel/queryhoteldetails/{id}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public Dto queryHotelDetails(@PathVariable("id")Integer id){
        ItripSearchDetailsHotelVO vo = new ItripSearchDetailsHotelVO();
        List<ItripSearchDetailsHotelVO> list = dao.queryHotelDetails(id);
        vo.setName("酒店介绍");
        vo.setDescription(dao.getItripHotelById(id).getDetails());
        list.add(vo);

        return  DtoUtil.returnDataSuccess(list);
    }
    //根据酒店ID查询评分
    @RequestMapping(value = "/comment/gethotelscore/{hotelId}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public Dto getScore (@PathVariable("hotelId")String id){
        return DtoUtil.returnSuccess("获取评分成功",dao1.getAvgScore(id));
    }
    //查询酒店设施/hotel/queryhotelfacilities/{id}
    @RequestMapping(value = "/hotel/queryhotelfacilities/{id}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public Dto queryHotelFacilities(@PathVariable("id")Integer id){
        return DtoUtil.returnDataSuccess(dao.queryHotelFacilities(id).getFacilities());
    }
    //查询酒店政策 /hotel/queryhotelpolicy/{id}
    @RequestMapping(value = " /hotel/queryhotelpolicy/{id}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public  Dto queryhotelpolicy(@PathVariable("id")Integer id){
        return DtoUtil.returnDataSuccess(dao.queryhotelpolicy(id).getHotelPolicy());
    }
    //3.3.2. 查询酒店房间床型列表
    //GET /api/hotelroom/queryhotelroombed
    @RequestMapping(value = " /hotelroom/queryhotelroombed",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public Dto queryhotelroombed(){
        return DtoUtil.returnSuccess("获取床型成功",dao2.getChuang());
    }
//3.4.7. 获取酒店相关信息（酒店名称、酒店星级）
////    GET /api/comment/gethoteldesc/{hotelId}
//    @RequestMapping(value = "/comment/gethoteldesc/{hotelId}",method = RequestMethod.GET,produces = "application/json")
//    @ResponseBody
//    public Dto getHotelDesc(@PathVariable("hotelId")String hotelId){
//    return DtoUtil.returnDataSuccess(dao.getHotelDesc(hotelId));
//    }

//3.1.3. 根据酒店id查询酒店特色、商圈、酒店名称
//    GET /api/hotel/getvideodesc/{hotelId}
@RequestMapping(value = "/hotel/getvideodesc/{hotelId}",method = RequestMethod.GET,produces = "application/json")
@ResponseBody
    public Dto getVideodesc(@PathVariable("hotelId")String hotelId){
        HotelVideoDescVO vo=new HotelVideoDescVO();

        vo.setHotelName(dao.getHotelDesc(hotelId).getHotelName());
        List<String> list=new ArrayList<>();
        list.add(dao.getAreDicById(hotelId).getName());
        vo.setTradingAreaNameList(list);
        List<ItripLabelDic> dList=dao.getF(hotelId);
        List<String> Tlist=new ArrayList<>();
        for (ItripLabelDic itripLabelDic:dList){
            Tlist.add(itripLabelDic.getName());

        }
        vo.setHotelFeatureList(Tlist);
        return DtoUtil.returnSuccess("成功！",vo);
    }
//3.4.9. 根据酒店id查询各类评论数量
//    GET /api/comment/getcount/{hotelId}
    @RequestMapping(value = "/comment/getcount/{hotelId}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public Dto getCount (@PathVariable("hotelId")String hotelId ){
        Map<String,Object> countMap=new HashMap<>();
        Map<String,Object> count=new HashMap<>();
        Map<String,Object> count1=new HashMap<>();
        Map<String,Object> count2=new HashMap<>();
        countMap.put("allcomment",dao1.getALLCommentCount(hotelId));
        count.put("hotelId",hotelId);
        count.put("isOk",1);
        countMap.put("isok",dao1.getIsOkCommentCount(count));
//        improve
        count1.put("hotelId",hotelId);
        count1.put("isOk",0);
        countMap.put("improve",dao1.getIsOkCommentCount(count1));
//        isHavingImg
        count2.put("hotelId",hotelId);
        count2.put("isHavingImg",1);
        countMap.put("havingimg",dao1.getIsHavingImgCommentCount(count2));
        return DtoUtil.returnSuccess("返回数据成功",countMap);
    }

//    3.4.6. 根据评论类型查询评论列表，并分页显示
//    POST /api/comment/getcommentlist
@RequestMapping(value = "/comment/getcommentlist",method = RequestMethod.POST,produces = "application/json")
@ResponseBody
    public Dto getCommentList(@RequestBody  ItripSearchCommentVO vo){
        Dto<Object> dto=new Dto<Object>();
        int pageBegin=(vo.getPageNo()-1)*vo.getPageSize();
        Page page=new Page(vo.getPageNo(),vo.getPageSize(),10);
        Map<String,Object> param=new HashMap<>();
        List<ItripListCommentVO> list;
        if(vo.getIsOk()==-1&&vo.getIsHavingImg()==-1){
        param.put("hotelId",vo.getHotelId());
        param.put("beginpage",pageBegin);
        param.put("pageSize",vo.getPageSize());
        list=dao1.getCommentList(param);
        page.setRows(list);
         }
        if(vo.getIsOk()==-1&&vo.getIsHavingImg()==1){
        param.put("hotelId",vo.getHotelId());
        param.put("isHavingImg",1);
        param.put("isOk",null);
        param.put("beginpage",pageBegin);
        param.put("pageSize",vo.getPageSize());
        list=dao1.getCommentList(param);
        page.setRows(list);

    } if(vo.getIsOk()==1&&vo.getIsHavingImg()==-1){
        param.put("hotelId",vo.getHotelId());
        param.put("isOk",1);
        param.put("isHavingImg",null);
        param.put("beginpage",pageBegin);
        param.put("pageSize",vo.getPageSize());
        list=dao1.getCommentList(param);
        page.setRows(list);

    }if(vo.getIsOk()==0&&vo.getIsHavingImg()==-1){
        param.put("hotelId",vo.getHotelId());
        param.put("isOk",0);
        param.put("isHavingImg",null);
        param.put("beginpage",pageBegin);
        param.put("pageSize",vo.getPageSize());
        list=dao1.getCommentList(param);
        page.setRows(list);

    }
         return DtoUtil.returnSuccess("",page);
    }
//    POST /api/hotelorder/getpreorderinfo
    @RequestMapping(value = "/hotelorder/getpreorderinfo",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Dto getPreorderInfo(@RequestBody ValidateRoomStoreVO vo){
//        #{hotelId},#{roomID},#{SD},#{}
        Map<String,Object> param=new HashMap<>();
        param.put("hotelId",vo.getHotelId());
        param.put("roomID",vo.getRoomId());
        param.put("SD",vo.getCheckInDate());
        param.put("ED",vo.getCheckOutDate());
        dao3.in_date(param);


        Map<String,Object> param1=new HashMap<>();
        param1.put("rid",vo.getRoomId());
        param1.put("sd",vo.getCheckInDate());
        param1.put("ed",vo.getCheckOutDate());
        List<StoreVO>list=dao3.getList(param1);

        RoomStoreVO rvo=new RoomStoreVO();
        rvo.setHotelName("房间名字");
        rvo.setStore(list.get(0).getStore());
        return DtoUtil.returnSuccess("成功了吗？",rvo);
    }
//    3.4.2. 根据targetId查询评论照片(type=2)
//    GET /api/comment/getimg/{targetId}
    @RequestMapping(value = "/comment/getimg/{targetId}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public Dto getImg(@PathVariable("targetId") String targetId){
        Map<String,Object> param=new HashMap<>();
        param.put("type",2);
        param.put("targetId",targetId);
        try {
            List<ItripImageVO> list=dao4.getItripImageListByMap(param);
            return DtoUtil.returnSuccess("",list);

        } catch (Exception e) {
            e.printStackTrace();
            return  DtoUtil.returnFail("错误","1002");
        }

    }
//3.5.1. 新增常用联系人接口
//    POST /api/userinfo/adduserlinkuser
    @RequestMapping(value = "/userinfo/adduserlinkuser",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Dto addUserLinkUser(@RequestBody  ItripAddUserLinkUserVO vo, HttpServletRequest request){
//{userId}#{linkUserName},#{linkIdCard},#{linkPhone},#{linkIdCardType}
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Map<String,Object> map=new HashMap<>();
        map.put("userId",1);
        map.put("linkUserName",vo.getLinkUserName());
        map.put("linkIdCard",vo.getLinkIdCard());
        map.put("linkPhone",vo.getLinkPhone());
        map.put("linkIdCardType",0);
        map.put("creationDate",df.format(new Date()));
        dao5.insertUserLinkUser(map);
        return DtoUtil.returnSuccess("添加成功");
    }




    }
