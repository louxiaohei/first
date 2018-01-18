package cn.itcast.controllor;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.pojo.Items;
import cn.itcast.pojo.QueryVo;
import cn.itcast.service.ItemsService;

@Controller
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	@RequestMapping("/list")
	public ModelAndView itemsList(ModelAndView mav) throws Exception {
		List<Items> list = itemsService.findAllItems();
		mav.addObject("itemList", list);
		mav.setViewName("item/itemList");
		/*
		 * if (1 > 0) { MyException me = new MyException();
		 * 
		 * me.setMessage("违规操作,谨慎."); throw me; }
		 */
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

	/**
	 * 使用POJO类型
	 * 
	 * @param items
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateitem")
	public String updateItemsByItems(MultipartFile pictureFile, Items items, Model model) throws Exception {
		// 获取原文件的名称
		String oldName = pictureFile.getOriginalFilename();
		// 设置新的名称
		String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
		// 保存到磁盘上
		pictureFile.transferTo(new File("E:\\视频讲义\\SpringMvc\\03_SpringMVC第一天\\资料\\image\\" + newName));
		// 为对象你给设置属性
		items.setPic("http://localhost:8080/pic/" + newName);
		itemsService.updateItemsByItems(items);
		model.addAttribute("id", items.getId());
		return "redirect:itemEdit.action";
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

	/**
	 * 批量删除,直接传递数组
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteItem")
	public String deleteItem(@RequestParam(value = "ids") Integer[] idsss) throws Exception {

		return "item/success";
	}

	/**
	 * 异步 发送json字符串
	 * 
	 * @return
	 */
	@RequestMapping("/sendJson")
	@ResponseBody
	public String sendJson(@RequestBody Items item) {
		item.setDetail("test Json");
		return "aaaaaaaa";
	}

	/**
	 * 跳转到批量修改界面
	 * 
	 * @return
	 */
	@RequestMapping("/preUpdateItems")
	public String updateItems(Model model) throws Exception {
		List<Items> list = itemsService.findAllItems();

		model.addAttribute("itemList", list);

		return "item/itemListForUpdate";

	}

	/**
	 * 批量修改保存
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateItems", method = RequestMethod.POST)
	public String updateItems(QueryVo vo) throws Exception {
		List<Items> list = vo.getUpdateList();

		return "item/success";
	}

}
