package cn.itcast.controllor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.pojo.Items;
import cn.itcast.pojo.QueryVo;
import cn.itcast.service.ItemsService;

@Controller
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	@RequestMapping("/list")
	public ModelAndView itemsList(ModelAndView mav) throws Exception {
		List<Items> list = itemsService.findAllItems();
		mav.addObject("itemList", list);
		mav.setViewName("item/itemList");
		return mav;

	}

	/**
	 * 跳转到修改界面
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/itemEdit")
	public String findItemsById(HttpServletRequest request, Model model) throws Exception {
		Integer id = Integer.valueOf(request.getParameter("id"));

		Items items = itemsService.findItemById(id);

		model.addAttribute("item", items);

		return "item/editItem";
	}

	/*
	 * 修改保存 使用java的简单数据类型
	 * 
	 * @return
	 * 
	 * @RequestMapping("/updateitem") public String updateItemsByItems(Integer
	 * id,
	 * 
	 * 
	 * @RequestParam(required=true,value="namename",defaultValue="名字")String
	 * name, Float price, String detail) throws Exception { Items items = new
	 * Items(); items.setId(id); items.setName(name); items.setPrice(price);
	 * items.setDetail(detail); itemsService.updateItemsByItems(items); return
	 * "item/success"; }
	 */

	/**
	 * 使用POJO类型
	 * 
	 * @param items
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateitem")
	public String updateItemsByItems(Items items) throws Exception {
		itemsService.updateItemsByItems(items);
		return "item/success";
	}

	/**
	 * 查询 使用pojo的包装类
	 * 
	 * @return
	 */
	@RequestMapping("/queryitem")
	public String queryitem(QueryVo vo, Model model) {
		return "item/success";
	}

}
