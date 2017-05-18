package com.cduestc.tyr.online_shopping.dao;

public interface ICollectionCommEntityDao {
	/**
	 * 查询当前商品实体id是否被用户收藏
	 * @author tangyanrentyr
	 * @2017年5月17日 2017年5月17日
	 * @param commEntityId
	 * @return 
	 */
	public Long queryCommEnIsCollect(int commEntityId, int userId);
	/**
	 * 将指定的ID的商品实体加入收藏
	 * @author tangyanrentyr
	 * @2017年5月17日 2017年5月17日
	 * @param commEntityId
	 * @param userId
	 * @return true:该商加入收藏成功
	 */
	public boolean addCollectionCommEn(int commEntityId, int userId);
	/**
	 * 方法addCollectionCommEn(int commEntityId, int userId)的重载
	 * @author tangyanrentyr
	 * @2017年5月17日 2017年5月17日
	 * @param commEntityId
	 * @param userId
	 * @return true:该商加入收藏成功
	 */
	public void addCollectionCommEn(Integer[] commEntitiesId, int userId);
	/**
	 * 将指定的ID的商品实体移除收藏
	 * @author tangyanrentyr
	 * @2017年5月17日 2017年5月17日
	 * @param commEntityId
	 * @param userId
	 * @return true:删除操作成功
	 */
	public boolean removeCollectionCommEn(int commEntityId, int userId);
	/**
	 * 方法removeCollectionCommEn(int commEntityId, int userId);的重载
	 * @author tangyanrentyr
	 * @2017年5月17日 2017年5月17日
	 * @param commEntitiesId
	 * @param userId
	 * @return
	 */
	public boolean removeCollectionCommEn(int[] commEntitiesId, int userId);
}
