package it.objectmethod.esercizio.db;

public class Actor {
	private String firstname;
	private String lastname;
	
	Actor(){
		firstname="";
		lastname="";
	}
	
	Actor(String first,String last){
		firstname=first;
		lastname=last;
	}
	
	public void SetFirstName(String first){
		firstname=first;
	}
	
	public void SetLastName(String last){
		lastname=last;
	}
	
	public String GetFirstName(){
		return firstname;
	}
	
	public String GetLastName(){
		return lastname;
	}
	
	public void SetBoth(String first, String last){
		firstname=first;
		lastname=last;
		
	}
}
