package cn.com.cslc.aw.core.common.datatables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * DT请求参数 未实现列排序
 * 
 * @author tianlu
 *
 */
public class DataTablesRequest {

	/**
	 * 绘制计数器。这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）
	 */
	private Integer draw;
	/**
	 * 
	 * 第一条数据的起始位置，比如0代表第一条数据
	 */
	private Integer start;

	/**
	 * 告诉服务器每页显示的条数
	 */
	private Integer length;
	
	/**
	 * key:orderColumnIndex
	 * value:Map(key:"orderColumn",value: ; key:"orderDirection",value:)
	 */
	private Map<Integer,Map<String,String>> orderMap = new HashMap<Integer,Map<String,String>>();


	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	public Pageable getPageRequest() {
		int page = start / length;
		int pageSize = length;
		if(length == -1){
			pageSize = Integer.MAX_VALUE;
		}
		return new PageRequest(page, pageSize,this.getPageSort());
	}

	/**
	 * 根据DT的排序字段排序
	 */
	private Sort getPageSort(){
		List<Order> orderList =new ArrayList<Order>();
		 //排序
	    Set<Entry<Integer,Map<String,String>>> orderMapEntrySet = this.orderMap.entrySet();
	    List<Entry<Integer, Map<String,String>>> orderedEntryList = new ArrayList<Map.Entry<Integer, Map<String,String>>>(
	    		orderMapEntrySet);
        Collections.sort(orderedEntryList, new Comparator<Entry<Integer, Map<String,String>>>() {
			@Override
			public int compare(Entry<Integer, Map<String, String>> o1, Entry<Integer, Map<String, String>> o2) {
				 if (o1.getKey() < o2.getKey())
	                    return -1;
	                else if (o1.getKey() > o2.getKey())
	                    return 1;
	                return 0;
			}});
        
       for(Entry<Integer, Map<String,String>> entry : orderedEntryList){
    	    Map<String,String> orderMap = entry.getValue();
  	    	String orderColumn = orderMap.get("orderColumn");
  	    	String orderDirection = orderMap.get("orderDirection");
  	    	Order order = null;
  	    	if(orderDirection.equals("asc")){
  	    		 order = new Order(Direction.ASC,orderColumn);
  	    	}else{
  	    		order = new Order(Direction.DESC,orderColumn);
  	    	}
  	    	orderList.add(order);
  	    }
        

	    return new Sort(orderList);
	}

	public Map<Integer, Map<String,String>> getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map<Integer, Map<String,String>> orderMap) {
		this.orderMap = orderMap;
	}
	
	
}
