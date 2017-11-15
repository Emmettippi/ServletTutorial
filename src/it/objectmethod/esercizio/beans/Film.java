package it.objectmethod.esercizio.beans;

public class Film {
	private int id;
	private String title;
	private int releaseYear;
	private int language;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear=releaseYear;
	}
	
	public int getLang() {
		return language;
	}
	public void setLang(int lang) {
		this.language=lang;
	}
}
