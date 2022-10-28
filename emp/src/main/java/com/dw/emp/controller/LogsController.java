package com.dw.emp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.emp.sevice.LogsService;
import com.dw.emp.vo.LogsVO;
import com.github.pagehelper.PageInfo;
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class LogsController {
	@Autowired
	private LogsService logsService;
	@GetMapping("/logs")
	public PageInfo <Map<String, Object>> CallLogs(@RequestParam int page){
		List<Map<String, Object>>list = logsService.getAllLogs(page);
		int navigatePages = 5;//한 블럭에 보여줄 페이지 수(네이버웹툰은 1블록에 10페이지
		return new PageInfo<Map<String, Object>>(list,navigatePages);
	}
	@GetMapping("/logs/{logId}")
	public LogsVO callLogs(@PathVariable int logId)
	{
		return logsService.getLogs(logId);
	}
	
}
