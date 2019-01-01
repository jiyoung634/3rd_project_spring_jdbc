package com.test.dao;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.test.util.InterparkAPI;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

		InterparkAPI api = new InterparkAPI();
		
		String temp = api.bookXml("9791162240489");
		
		System.out.println(temp);
		
	}

}
