package com.cafe24.shoppingmall.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);

	@ExceptionHandler( Exception.class )
	public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception{
		System.out.println("------------------------");
		e.printStackTrace();
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		LOGGER.error(errors.toString());
		System.out.println("------------------------");
		System.out.println(errors.toString());
		System.out.println("------------------------");
		
		//json 응답
		response.setStatus(HttpServletResponse.SC_OK);
		
		JSONResult jsonResult = JSONResult.fail(errors.toString());
		String result = new ObjectMapper().writeValueAsString(jsonResult);
		
		System.out.println(result);
		OutputStream os = response.getOutputStream();
		os.write(result.getBytes("UTF-8"));
		os.flush();
		os.close();

	}

}
