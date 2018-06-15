package com.fwd.movie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.nio.file.Paths;

@RestController
public class MovieController {
	
	@Value("${json.data.folder}")
    String jsonConfigFolder;
	
	List<Movie> movies = new ArrayList<Movie>();
	
	@RequestMapping(value="addmovie",method = RequestMethod.PUT)
	public void addMovie(@RequestBody Movie movie){

		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(jsonConfigFolder);
		
		try {
			movies = objectMapper.readValue(file,new TypeReference<List<Movie>>() {});
			
			movies.add(movie);
			String json = new Gson().toJson(movies);
			
			try (FileWriter jsonFile = new FileWriter(jsonConfigFolder)) {
				jsonFile.write(json);
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	 
	@RequestMapping("getmovie")
	public @ResponseBody  List<Movie> getMovieList(){
		
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(jsonConfigFolder);
		
		try {
			movies = objectMapper.readValue(file,new TypeReference<List<Movie>>() {});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return movies;
		
	}
}
