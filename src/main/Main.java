package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import GUI.GraphicsMain;
import backend.MLcompiler.MLcompiler;
import backend.controllers.FileController;
import backend.controllers.QueryController;
import backend.controllers.SchemaController;
import backend.repositories.GraphRepository;
import queryButtonsLogic.GraphQueryButton;
import queryProcessing.XPathQuery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.ParserConfigurationException;

@SpringBootApplication
public class Main {

	public static void main(String args[]) throws ParserConfigurationException, FileNotFoundException {
		//new GraphicsMain();
		Class[] primarySources = { Main.class, QueryController.class, SchemaController.class, FileController.class };
		SpringApplication.run(primarySources, args);
		//MLcompiler compiler = new MLcompiler();
		//compiler.runSML("datatype ('o,'a) Cat = cat of ('a -> 'o) * ('a -> 'o) * ('o -> 'a) * (('a * 'a) -> 'a);");	
	}
}
