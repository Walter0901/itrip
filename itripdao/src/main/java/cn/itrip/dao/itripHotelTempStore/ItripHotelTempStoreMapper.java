package cn.itrip.dao.itripHotelTempStore;
import cn.itrip.pojo.ItripHotelTempStore;
import cn.itrip.pojo.StoreVO;
import org.apache.ibatis.annotations.Param;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

public interface ItripHotelTempStoreMapper {

	public ItripHotelTempStore getItripHotelTempStoreById(@Param(value = "id") Long id)throws Exception;

	public List<ItripHotelTempStore>	getItripHotelTempStoreListByMap(Map<String, Object> param)throws Exception;

	public Integer getItripHotelTempStoreCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception;

	public Integer updateItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception;

	public Integer deleteItripHotelTempStoreById(@Param(value = "id") Long id)throws Exception;

	public Integer in_date(Map<String,Object> param);

	public List<StoreVO> getList(Map<String,Object> param);

}