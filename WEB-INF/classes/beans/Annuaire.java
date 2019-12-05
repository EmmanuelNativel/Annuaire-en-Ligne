package beans;

import java.util.ArrayList;

public class Annuaire {
	
	private ArrayList<Contact> listeContact;
	private int nbContact;
	private int maxId;
	
	public Annuaire() {
		listeContact = new ArrayList<Contact>();
		nbContact=0;
		maxId=0;
	}
	
	public int getMaxId() {
		return maxId;
	}
	
	public void setMaxId(int n) {
		maxId = n;
	}
	
	public void setNbContact(int n) {
		nbContact = n;
	}
	
	public int getNbContact() {
		return nbContact;
	}
	
	public ArrayList<Contact> getListeContact(){
		return listeContact;
	}
	
	public void addContact(String nom, String prenom, String telephone, String adresse, String email) {
		Contact c = new Contact();
		c.setId(String.valueOf(maxId));
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setTelephone(telephone);
		c.setAdresse(adresse);
		c.setEmail(email);
		listeContact.add(c);
		maxId++;
		nbContact++;
	}
	
	public void addContact(int id, String nom, String prenom, String telephone, String adresse, String email) {
		Contact c = new Contact();
		c.setId(String.valueOf(id));
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setTelephone(telephone);
		c.setAdresse(adresse);
		c.setEmail(email);
		listeContact.add(c);
	}
	
	public String afficher(int i, ArrayList<Contact> l) {
		return l.get(i).getNom();
	}
	
	public void supprimerContact(String id) {
		for(int i=0; i<listeContact.size(); i++) {
			if(listeContact.get(i).getId().equals(id)) {
				listeContact.remove(listeContact.get(i));
				nbContact--;
			}
		}
	}
	
	public void modifierContact(String id, String nom, String prenom, String telephone, String adresse, String email) {
		for(int i=0; i<listeContact.size(); i++) {
			if(listeContact.get(i).getId().equals(id)) {
				listeContact.get(i).setNom(nom);
				listeContact.get(i).setPrenom(prenom);
				listeContact.get(i).setTelephone(telephone);
				listeContact.get(i).setAdresse(adresse);
				listeContact.get(i).setEmail(email);
			}
		}
	}
	
	public ArrayList<Contact> rechercherContact(String mot, String t){
		ArrayList<Contact> l = new ArrayList<Contact>();
		for(int i=0;i<listeContact.size();i++) {
			if(mot.equalsIgnoreCase(listeContact.get(i).getType(t)) || listeContact.get(i).getType(t).toLowerCase().contains(mot.toLowerCase())) {
				l.add(listeContact.get(i));
			}
		}
		return l;
	}
	
	public Contact getContact(String id) {
		for(int i=0;i<listeContact.size();i++) {
			if(listeContact.get(i).getId().equals(id)) return listeContact.get(i);
		}
		return null;
	}
}
