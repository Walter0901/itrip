package com.bdqn.biz;

import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.pojo.ItripHotelVO;
import cn.itrip.pojo.SearchHotCityVO;
import cn.itrip.pojo.SearchHotelVO;
import com.bdqn.dao.BaseQueryDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchBiz {

    public List<ItripHotelVO> getList(int city,int count){
        BaseQueryDao<ItripHotelVO> baseQueryDao=new BaseQueryDao<>();
        SolrQuery query=new SolrQuery("*:*");
        query.addFilterQuery("cityId:"+city);
        return  baseQueryDao.getHotel(query,count,ItripHotelVO.class);
    }
    public Page<ItripHotelVO> getPageList(SearchHotelVO vo,int pageIndex,int pageSize){
  BaseQueryDao<ItripHotelVO> baseQueryDao=new BaseQueryDao<ItripHotelVO>();
            SolrQuery query=new SolrQuery("*:*");
//            StringBuilder str=new StringBuilder();
//            str.append("*:*");
        if(vo.getDestination()!=null)
        {
            query.addFilterQuery(" destination:" + vo.getDestination());
        }
            if (vo.getKeywords()!=null&&vo.getKeywords()!=""){
                query.addFilterQuery("keyword:"+vo.getKeywords());

    }
            if(vo.getMinPrice()!=null){
                query.addFilterQuery("minPrice:"+"["+vo.getMinPrice()+"TO *]");
            }
            if (vo.getMaxPrice()!=null){
                query.addFilterQuery("maxPrice:"+"[ * TO"+vo.getMaxPrice()+"]");
            }

            if(vo.getFeatureIds()!=null&&vo.getFeatureIds()!=""){
                String [] subStr=vo.getFeatureIds().split(",");
                for (String i:subStr){
                    query.addFilterQuery("featureIds:[*"+i+"* ]");
                }
            }
            if(vo.getTradeAreaIds()!=null&&vo.getTradeAreaIds()!=""){
                query.addFilterQuery("tradingAreaIds:*,"+vo.getTradeAreaIds()+",*");
            }
//        query.addFilterQuery(str.toString());


        return  baseQueryDao.getPageList1(query,pageIndex,pageSize,ItripHotelVO.class);
//        query.addFilterQuery("*:*");
//        if(searchHotelVO.getKeywords()!=null)
//        {
//            //str.append(" and keyword:"+searchHotelVO.getKeywords());
//            query.addFilterQuery("keyword:"+searchHotelVO.getKeywords());
//        }
//        if(searchHotelVO.getMinPrice()!=null)
//        {
//            //str.append(" and minPrice:["+ searchHotelVO.getMinPrice()+" TO *]");
//            query.addFilterQuery("minPrice:["+ searchHotelVO.getMinPrice()+" TO *]");
//        }
//        if (searchHotelVO.getMaxPrice()!=null)
//        {
//            //str.append(" and maxPrice:[* TO "+searchHotelVO.getMaxPrice()+"]");
//            query.addFilterQuery("maxPrice:[* TO "+searchHotelVO.getMaxPrice()+"]");
//        }
//
//        if(searchHotelVO.getFeatureIds()!=null&&searchHotelVO.getFeatureIds()!="") {
//            //   ,17,115,116,117,
//            String[] substr = searchHotelVO.getFeatureIds().split(",");
//
//            //    featureIds:[*17*]
//            for (String i : substr) {
//                //str.append(" and featureIds:[*" + i + "*]");
//                query.addFilterQuery("featureIds:*," + i + ",*");
//            }
//        }
//
//        if(searchHotelVO.getHotelLevel()!=null){
//            query.addFilterQuery("hotelLevel:"+searchHotelVO.getHotelLevel());
//        }
//
//        if(searchHotelVO.getDescSort()!=""&&searchHotelVO.getDescSort()!=null&&searchHotelVO.getDescSort().equals("avgScore")){
//            query.setSort("avgScore",SolrQuery.ORDER.desc);
//        }
//        if(searchHotelVO.getDescSort()!=""&&searchHotelVO.getDescSort()!=null&&searchHotelVO.getDescSort().equals("minPrice")){
//            query.setSort("minPrice",SolrQuery.ORDER.desc);
//        }
//        if(searchHotelVO.getDescSort()!=""&&searchHotelVO.getDescSort()!=null&&searchHotelVO.getDescSort().equals("hotelLevel")){
//            query.setSort("hotelLevel",SolrQuery.ORDER.desc);
//        }
//        if(searchHotelVO.getAscSort()!=""&&searchHotelVO.getAscSort()!=null&&searchHotelVO.getAscSort().equals("isOkCount")){
//            query.setSort("isOkCount",SolrQuery.ORDER.asc);
//        }
//        return  baseQueryDao.getPageList1(query,pageIndex,pageSize,ItripHotelVO.class);

//        SolrQuery query = new SolrQuery("*:*");
//        StringBuffer tempQuery = new StringBuffer();
//        int tempFlag = 0;
//        if (EmptyUtils.isNotEmpty(vo)) {
//            if (EmptyUtils.isNotEmpty(vo.getDestination())) {
//                tempQuery.append(" destination :" + vo.getDestination());
//                tempFlag = 1;
//            }
//            if (EmptyUtils.isNotEmpty(vo.getHotelLevel())) {
//                query.addFilterQuery("hotelLevel:" + vo.getHotelLevel() + "");
//            }
//            if (EmptyUtils.isNotEmpty(vo.getKeywords())) {
//                if (tempFlag == 1) {
//                    tempQuery.append(" AND keyword :" + vo.getKeywords());
//                } else {
//                    tempQuery.append(" keyword :" + vo.getKeywords());
//                }
//            }
//            if (EmptyUtils.isNotEmpty(vo.getFeatureIds())) {
//                StringBuffer buffer = new StringBuffer("(");
//                int flag = 0;
//                String featureIdArray[] = vo.getFeatureIds().split(",");
//                for (String featureId : featureIdArray) {
//                    if (flag == 0) {
//                        buffer.append(" featureIds:" + "*," + featureId + ",*");
//                    } else {
//                        buffer.append(" OR featureIds:" + "*," + featureId + ",*");
//                    }
//                    flag++;
//                }
//                buffer.append(")");
//                query.addFilterQuery(buffer.toString());
//            }
//            if (EmptyUtils.isNotEmpty(vo.getTradeAreaIds())) {
//                StringBuffer buffer = new StringBuffer("(");
//                int flag = 0;
//                String tradeAreaIdArray[] = vo.getTradeAreaIds().split(",");
//                for (String tradeAreaId : tradeAreaIdArray) {
//                    if (flag == 0) {
//                        buffer.append(" tradingAreaIds:" + "*," + tradeAreaId + ",*");
//                    } else {
//                        buffer.append(" OR tradingAreaIds:" + "*," + tradeAreaId + ",*");
//                    }
//                    flag++;
//                }
//                buffer.append(")");
//                query.addFilterQuery(buffer.toString());
//            }
//            if (EmptyUtils.isNotEmpty(vo.getMaxPrice())) {
//                query.addFilterQuery("minPrice:" + "[* TO " + vo.getMaxPrice() + "]");
//            }
//            if (EmptyUtils.isNotEmpty(vo.getMinPrice())) {
//                query.addFilterQuery("minPrice:" + "[" + vo.getMinPrice() + " TO *]");
//            }
//
//            if (EmptyUtils.isNotEmpty(vo.getAscSort())) {
//                query.addSort(vo.getAscSort(), SolrQuery.ORDER.asc);
//            }
//
//            if (EmptyUtils.isNotEmpty(vo.getDescSort())) {
//                query.addSort(vo.getDescSort(), SolrQuery.ORDER.desc);
//            }
//        }
//        if (EmptyUtils.isNotEmpty(tempQuery.toString())) {
//            query.setQuery(tempQuery.toString());
//        }
//       // Page<ItripHotelVO> page = itripHotelVOBaseQuery.queryPage(query, pageNo, pageSize, ItripHotelVO.class);
//         return  baseQueryDao.getPageList1(query,pageIndex,pageSize,ItripHotelVO.class);


    }
    }

