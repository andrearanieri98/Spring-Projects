package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.dtmo.jfiglet.FigFontResources;
import com.github.dtmo.jfiglet.FigletRenderer;
import com.github.dtmo.jfiglet.*;
import java.io.IOException;
import org.springframework.http.MediaType;


@SpringBootApplication
@RestController
public class DemoApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)

	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
/*
		// name of the font to be used
		final String font ="big";

		// render to write ASCII-art with the given font
		final FigletRenderer figletRenderer = new FigletRenderer(FigFontResources.loadFigFontResource(font));

		// ASCII-art
		final String output = figletRenderer.renderText("Hello, world!");

		// write to the console
		//System.out.printf("%s%n", output);
		return output;*/
		//return FigletFont.convertOneLine("Hello, " + name);
        final FigletRenderer figletRenderer = new FigletRenderer(FigFontResources.loadFigFontResource(FigFontResources.BANNER_FLF ));
        //System.out.println(figletRenderer.renderText("Hello world"));
		return figletRenderer.renderText(String.format("Hello %s!", name));
		//return String.format("Hello %s!", name);
	}
}

