package cn.itcast.pojo;

import java.util.List;

public class QueryVo {

	private Items items;

	private Integer[] idss;
	
	private List<Items> updateList;

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public Integer[] getIdss() {
		return idss;
	}

	public void setIdss(Integer[] idss) {
		this.idss = idss;
	}

	public List<Items> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<Items> updateList) {
		this.updateList = updateList;
	}

	
}
