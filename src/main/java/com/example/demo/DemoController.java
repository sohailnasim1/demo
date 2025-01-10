package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.DemoProperties;

@RestController
public class DemoController {
	private final DemoProperties props;
	
	public DemoController(DemoProperties props) {
		this.props = props;
	}
	@GetMapping("/")
	public String getGreetings() {
		return props.getGreeting();
	}
}
