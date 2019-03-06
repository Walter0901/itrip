package com.bdqn.entity;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import sun.misc.PostVMInitHook;

import java.io.IOException;
import java.util.List;

public class HotelEntity {
    public String getAdddress() {
        return address;
    }

    public void setAdddress(String adddress) {
        this.address = adddress;
    }
    @Field
    private String id;
    @Field
    private String address;
    @Field
    private String hotelName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public static void main(String[] args) throws IOException, SolrServerException {
        String url="http://localhost:8080/solr/hotel-core";
        HttpSolrClient httpSolrClient=new HttpSolrClient(url);
        httpSolrClient.setParser(new XMLResponseParser());
        httpSolrClient.setConnectionTimeout(500);
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.setQuery("*:*");
        QueryResponse queryResponse=httpSolrClient.query(solrQuery);
        List<HotelEntity> list=queryResponse.getBeans(HotelEntity.class);
        for (HotelEntity hotelEntity:list){
            System.out.println(hotelEntity.getHotelName()+":"+hotelEntity.getId()+":"+hotelEntity.getAdddress());
        }
    }
}
