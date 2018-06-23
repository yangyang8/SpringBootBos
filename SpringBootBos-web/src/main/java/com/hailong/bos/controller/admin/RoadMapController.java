package com.hailong.bos.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/road")
public class RoadMapController {
	
	@RequestMapping("/toRoadMap.action")
	public ModelAndView toRoadMapPage(){
		return new ModelAndView("map/RoadMap");
	}
}
