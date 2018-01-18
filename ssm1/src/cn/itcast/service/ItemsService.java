package cn.itcast.service;

import java.util.List;

import cn.itcast.pojo.Items;

public interface ItemsService {

	/**
	 * 查询列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Items> findAllItems() throws Exception;

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Items findItemById(Integer id);

	/**
	 * 修改Itmes的信息
	 * 
	 * @param items
	 */
	public void updateItemsByItems(Items items);

}
