package com.bdqn.biz;

import cn.itrip.common.Constants;
import cn.itrip.common.DateUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.dao.SearchHotelRoomVO;
import cn.itrip.dao.itripAreaDic.ItripAreaDicMapper;
import cn.itrip.dao.itripComment.ItripCommentMapper;
import cn.itrip.dao.itripHotelRoom.ItripHotelRoomMapper;
import cn.itrip.dao.itripImage.ItripImageMapper;
import cn.itrip.dao.itripLabelDic.ItripLabelDicMapper;
import cn.itrip.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelServer {
    @Resource
    ItripAreaDicMapper dao;

    @Resource
        ItripLabelDicMapper dic;
        @Resource
        ItripHotelRoomMapper hoteDao;
        @Resource
        ItripImageMapper dia;
    @Resource
    ItripAreaDicMapper daoSQ;


        public List<ItripAreaDic> GerDate(int type){
            return dao.getItripAreaDicByType(type);
        }

        public List<ItripLabelDic> GetFirstDate() throws Exception {
            return  dic.getFirstDate();
        }
        public List<ItripHotelRoomVO> getHotelRoom(SearchHotelRoomVO vo) throws Exception {
            List list=DateUtil.getBetweenDates(vo.getStartDate(),vo.getEndDate());
            long hotelId=vo.getHotelId();
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("timesList", list);
            param.put("hotelId", vo.getHotelId());

            return  hoteDao.getItripHotelRoomSearch(param);
    }
    public List<ItripImageVO> getPig(String a){
      return   dia.get_1(a);
    }
    public List<ItripAreaDicVO> getSQ(Integer a){
           return daoSQ.getItripAreaDicList(a);
    }


}
