package com.in28minutes.learnspringframework.test;

import java.util.ArrayList;

public class ExploringVar {
	
	//ERROR: var Something = new ArrayList<String>();

	public static void main(String[] args) {
		
		var myList = new ArrayList<String>();
		
		//var var = new ArrayList<String>();
		
		myList.add("someString");
		
		for(var element:myList) {
			System.out.println(element);
		}
				
		System.out.println(myList.getClass());

	}

}
