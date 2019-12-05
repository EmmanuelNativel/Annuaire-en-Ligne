package beans;

import java.util.ArrayList;

public class UtilisateurData {
	
	private ArrayList<Utilisateur> listeUtilisateur;
	private int maxIdUser;
	
	public UtilisateurData() {
		listeUtilisateur = new ArrayList<Utilisateur>();
		maxIdUser=0;
	}
	
	public void setMaxIdUser(int n) {
		maxIdUser = n;
	}
	
	public int getMaxIdUser() {
		return maxIdUser;
	}
	
	public ArrayList<Utilisateur> getListeUtilisateur(){
		return listeUtilisateur;
	}
	
	public void addUtilisateur(Utilisateur u) {
		if( !listeUtilisateur.contains(u) ){
			u.setId(maxIdUser);
			listeUtilisateur.add(u);
			maxIdUser+=1;
		}
	}
	
	public void addUtilisateur(int id, String login, String pass) {
		Utilisateur u = new Utilisateur();
		u.setId(id);
		u.setLogin(login);
		u.setPass(pass);
		if( !listeUtilisateur.contains(u) ){
			listeUtilisateur.add(u);
		}
	}
	
	public boolean verifierAuthentification(String login, String pass){
		for(int i=0; i<listeUtilisateur.size();i++) {
			if(listeUtilisateur.get(i).getLogin().equals(login)) {
				if( listeUtilisateur.get(i).getPass().equals(pass) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Utilisateur getUtilisateur(String login) {
		for(int i=0; i<listeUtilisateur.size();i++) {
			if(listeUtilisateur.get(i).getLogin().equals(login)) {
					return listeUtilisateur.get(i);
				}
		}
		return null;
	}
	
}
