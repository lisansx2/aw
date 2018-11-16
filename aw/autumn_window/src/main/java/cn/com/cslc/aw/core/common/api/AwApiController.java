package cn.com.cslc.aw.core.common.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.com.cslc.aw.core.common.exception.DataNotFoundException;

@RestController
@RequestMapping("/api")
public class AwApiController {
	
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, String> getMapExampleAll() {
		Map<String, String> exampleMap = new HashMap<String, String>();
		exampleMap.put("a", "123");
		return exampleMap;
	}
	
/*	@RequestMapping(value="/{id}",method = RequestMethod.GET, produces="application/json")
	public Map<String, String> getMapExampleById(@PathVariable long id) {
		throw new DataNotFoundException(id);
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error dataNotFound(DataNotFoundException e){
		long id = e.getId();
		return new Error(1,id + "数据未找到");
	}*/
}
