package primitivas.common;

import java.util.ArrayList;

public class ColaStrings {

	ArrayList<String> cola;
	
	public ColaStrings(){
		cola = new ArrayList<String>();
	}
	
	public void add(String c){
		cola.add(c);
	}
	
	public String get(){
		if (cola.size()==0) return "";
		return cola.remove(0);
	}
	
	public int size(){
		return cola.size();
	}
}
