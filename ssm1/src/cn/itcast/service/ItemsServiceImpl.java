package cn.itcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.ItemsMapper;
import cn.itcast.pojo.Items;

@Service
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsMapper itemsMapper;

	/**
	 * 列表展示
	 */
	@Override
	public List<Items> findAllItems() throws Exception {
		List<Items> list = itemsMapper.selectByExampleWithBLOBs(null);
		return list;
	}

	/**
	 * 根据id查询
	 */
	@Override
	public Items findItemById(Integer id) {
		return itemsMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 修改保存itmes信息
	 */
	@Override
	public void updateItemsByItems(Items items) {
		itemsMapper.updateByPrimaryKeySelective(items);
		
	}

}
