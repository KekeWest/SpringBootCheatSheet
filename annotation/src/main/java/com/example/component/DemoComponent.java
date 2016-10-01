package com.example.component;

import org.springframework.stereotype.Component;

import com.example.annotation.DemoAnno;

@Component
public class DemoComponent {

	@DemoAnno
	public String printStr(String str) throws Exception {
		return str;
	}

}