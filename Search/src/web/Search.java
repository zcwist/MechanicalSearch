package web;

public class Search {
	private String ExtendWordsXML;

	public String getExtendWordsXML() {
		return ExtendWordsXML;
	}

	public void setExtendWordsXML(String extendWordsXML) {
		ExtendWordsXML = extendWordsXML;
	}
	
	public String execute(){
		ExtendWordsXML = "";
		ExtendWordsXML ="<?xml version='1.0' encoding='utf-8'?>"+
						"<dataset>"+
						"<record><word>Gear</word><descn>center</descn></record>"+
						"<record><word>Herringbone_Gear</word><descn>SubClass</descn></record>"+
						"<record><word>Internal_Gear</word><descn>SubClass</descn></record>"+
						"<record><word>Link</word><descn>SuperClass</descn></record>"+
						"<record><word>Worm_Gear</word><descn>SubClass</descn></record>"+
						"<record><word>Bevel_Gear</word><descn>SubClass</descn></record>"+
						"<record><word>Worm</word><descn>SubClass</descn></record>"+
						"<record><word>Rack</word><descn>SubClass</descn></record>"+
						"<record><word>Helical_Gear</word><descn>SubClass</descn></record>"+
						"<record><word>External_Gear</word><descn>SubClass</descn></record>"+
						"</dataset>";
		return "success";
	}

}
