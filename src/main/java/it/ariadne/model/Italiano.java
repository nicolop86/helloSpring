package it.ariadne.model;

public class Italiano implements Language {

	 @Override
	 public String getGreeting() {
	     return "Ciao";
	 }
	 
	 @Override
	 public String getBye() {
	     return "Arrivederci";
	 }

}
