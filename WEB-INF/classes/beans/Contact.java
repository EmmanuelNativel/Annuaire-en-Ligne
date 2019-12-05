package beans;

public class Contact {
	
	private String nom;
	private String prenom;
	private String id;
	private String telephone;
	private String adresse;
	private String email;
	
	//Accesseurs 
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public String getType(String t) {
		switch(t) {
		case "nom":
			return getNom();
		case "prenom":
			return getPrenom();
		case "telephone":
			return getTelephone();
		case "adresse":
			return getAdresse();
		case "email":
			return getEmail();
			
		default:
			return null;
		}
	}
}
