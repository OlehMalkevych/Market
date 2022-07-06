package com.logos.market.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

import static com.logos.market.tools.FileTool.IMG_DIR;

@Configuration
public class StaticResourcesConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/*")
				.addResourceLocations(Paths.get(IMG_DIR).toUri().toString());
	}
}
