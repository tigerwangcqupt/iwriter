package com.yryz.writer.common.web.wrapper;

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * 
 * @author wangfei
 *
 */
public class MappingJackson2HttpMessageConverterWrapper extends MappingJackson2HttpMessageConverter {

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		super.writeInternal(object, outputMessage);
	}

}
