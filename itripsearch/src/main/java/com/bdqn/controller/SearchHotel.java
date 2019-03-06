package com.bdqn.controller;

import cn.itrip.common.Page;
import cn.itrip.pojo.*;
import com.bdqn.biz.SearchBiz;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")

public class SearchHotel
{

    @RequestMapping(value="/hotellist/searchItripHotelListByHotCity",method= RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Dto getSearchHotel(@RequestBody SearchHotCityVO vo){
        SearchBiz biz=new SearchBiz();
        List<ItripHotelVO> list=biz.getList(vo.getCityId(),vo.getCount());
        return DtoUtil.returnDataSuccess(list);
    }

    @RequestMapping(value="/hotellist/searchItripHotelPage",method= RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Dto getSearchHotel1(@RequestBody SearchHotelVO searchHotelVO) {
        SearchBiz biz=new SearchBiz();
        if (searchHotelVO.getPageNo()==null){
            searchHotelVO.setPageNo(1);
        searchHotelVO.setPageSize(6);}

    Page<ItripHotelVO> list=biz.getPageList(searchHotelVO,searchHotelVO.getPageNo(),searchHotelVO.getPageSize());
    return DtoUtil.returnDataSuccess(list);
    }
}
//        String url="http://localhost:8080/solr/hotel-core";
//        HttpSolrClient httpSolrClient = new HttpSolrClient(url);
//        httpSolrClient.setParser(new XMLResponseParser()); // 设置响应解析器
//        httpSolrClient.setConnectionTimeout(500); // 建立连接的最长时间
//
//        if(searchHotelVO.getPageNo()==null){
//            searchHotelVO.setPageNo(1);
//        }
//
//        SolrQuery query=new SolrQuery("*:*");
//        query.setStart((searchHotelVO.getPageNo()-1)*6);
//        query.setRows(6);
//        query.setSort("id",SolrQuery.ORDER.asc);
//
//        StringBuilder str=new StringBuilder();
//        str.append("*:*");
//        if(searchHotelVO.getKeywords()!=null)
//        {
//            str.append(" and keyword:"+searchHotelVO.getKeywords());
//
//        }
//        if(searchHotelVO.getMinPrice()!=null)
//        {
//            str.append(" and minPrice:"+"["+ searchHotelVO.getMinPrice()+" TO *]");
//        }
//        if (searchHotelVO.getMaxPrice()!=null)
//        {
//            str.append(" and maxPrice:"+"[* TO "+searchHotelVO.getMaxPrice()+"]");
//        }
//        if(searchHotelVO.getDestination()!=null){
//            str.append("and destination:"+searchHotelVO.getDestination());
//        }
//
////        if(searchHotelVO.getFeatureIds()!=null) {
////            //   ,17,115,116,117,
////            String[] substr = searchHotelVO.getFeatureIds().split(",");
////
////            //    featureIds:[*17*]
////            for (String i : substr) {
////                str.append(" and featureIds:[*" + i + "*]");
////            }
////        }
//
//
//
//
//
//
//
//        query.addFilterQuery(str.toString());
//
//
//
//
//
//        try {
//            QueryResponse response=httpSolrClient.query(query);
//            List<ItripHotelVO> list=response.getBeans(ItripHotelVO.class);
//            SolrDocumentList sdl = response.getResults();
//            Page<ItripHotelVO> listpage = new Page<>(searchHotelVO.getPageNo(),6,new Long(sdl.getNumFound()).intValue());
//            listpage.setRows(list);
//            return DtoUtil.returnDataSuccess(listpage);
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//            return  null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return  null;
//        } finally {
//        }

//    }

