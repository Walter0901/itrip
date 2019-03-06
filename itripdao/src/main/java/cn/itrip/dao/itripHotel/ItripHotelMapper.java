package cn.itrip.dao.itripHotel;
import cn.itrip.pojo.*;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripHotelMapper {

	public ItripHotel getItripHotelById(@Param(value = "id") Integer id);

	public List<ItripHotel>	getItripHotelListByMap(Map<String, Object> param)throws Exception;

	public Integer getItripHotelCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertItripHotel(ItripHotel itripHotel)throws Exception;

	public Integer updateItripHotel(ItripHotel itripHotel)throws Exception;

	public Integer deleteItripHotelById(@Param(value = "id") Long id)throws Exception;
	public List<ItripSearchDetailsHotelVO> queryHotelDetails (@Param(value = "id") Integer id);
	public ItripSearchFacilitiesHotelVO queryHotelFacilities (@Param(value = "id") Integer id);
	public ItripSearchPolicyHotelVO queryhotelpolicy (@Param(value = "id")Integer id);
	public ItripHotel getHotelDesc(@Param(value = "id")String id);
	public  ItripAreaDic getAreDicById(@Param(value = "id")String id);
	public  List<ItripLabelDic> getF(@Param(value = "id")String id);
}
