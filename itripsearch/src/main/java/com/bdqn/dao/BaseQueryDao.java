package com.bdqn.dao;

import cn.itrip.common.Page;
import com.bdqn.entity.HotelEntity;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.List;

public class BaseQueryDao<T> {
    private  static  String url="http://localhost:8080/solr/hotel-core/";
    HttpSolrClient httpSolrClient;
    public BaseQueryDao(){
        httpSolrClient=new HttpSolrClient(url);
        httpSolrClient.setParser(new XMLResponseParser());
        httpSolrClient.setConnectionTimeout(500);
    }
    public List<T> getHotel(SolrQuery solrQuery,int count,Class c){
        try {
    solrQuery.setSort("id", SolrQuery.ORDER.desc);
    solrQuery.setStart(0);
    solrQuery.setRows(count);
    QueryResponse queryResponse=null;
    queryResponse=httpSolrClient.query(solrQuery);
    List<T> list=queryResponse.getBeans(c);
    return  list;
        } catch (SolrServerException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }
public Page<T> getPageList1(SolrQuery solrQuery,int pageindex,int PageSize,Class c){
    try {
    solrQuery.setSort("id", SolrQuery.ORDER.desc);
    solrQuery.setStart((pageindex-1)*PageSize);
    solrQuery.setRows(PageSize);
    QueryResponse queryResponse=null;
    queryResponse=httpSolrClient.query(solrQuery);
    List<T> list=queryResponse.getBeans(c);
        SolrDocumentList docs=queryResponse.getResults();
    Page<T> listPage=new Page<>(pageindex,PageSize,new Long(docs.getNumFound()).intValue());
    listPage.setRows(list);
    return  listPage;
} catch (SolrServerException e) {
        e.printStackTrace();
        return null;
    } catch (IOException e) {
        e.printStackTrace();
        return  null;
    }
}

}
