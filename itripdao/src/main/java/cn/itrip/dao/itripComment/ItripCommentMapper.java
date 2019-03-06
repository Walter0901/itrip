package cn.itrip.dao.itripComment;
import cn.itrip.pojo.ItripComment;
import cn.itrip.pojo.ItripListCommentVO;
import cn.itrip.pojo.ItripScoreCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripCommentMapper {

	public ItripComment getItripCommentById(@Param(value = "id") Long id)throws Exception;

	public List<ItripComment>	getItripCommentListByMap(Map<String, Object> param)throws Exception;

	public Integer getItripCommentCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertItripComment(ItripComment itripComment)throws Exception;

	public Integer updateItripComment(ItripComment itripComment)throws Exception;

	public Integer deleteItripCommentById(@Param(value = "id") Long id)throws Exception;
	public ItripScoreCommentVO getAvgScore(@Param(value = "hotelId")String id);
    public Integer  getALLCommentCount(@Param(value = "id")String id);

    public Integer getIsOkCommentCount(Map<String, Object> param);
    public Integer getIsHavingImgCommentCount(Map<String, Object> param);

    public List<ItripListCommentVO> getCommentList(Map<String, Object> param);
}