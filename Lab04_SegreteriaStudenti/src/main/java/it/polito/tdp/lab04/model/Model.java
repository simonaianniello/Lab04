package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
CorsoDAO dao=new CorsoDAO();
StudenteDAO dai=new StudenteDAO();

 public List<Corso> getTuttiICorsi(){
	return dao.getTuttiICorsi();
 }
 public Corso getCorso(String codins) {
	 return dao.getCorso(codins);
 }
 public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
	 return dao.getStudentiIscrittiAlCorso(corso);
 }
public Studente getNomeECognome(int matricola) {
	// TODO Auto-generated method stub
	return dai.getNomeECognome(matricola);
}
public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
	return dao.inscriviStudenteACorso(studente, corso);
}
public List<Corso> getCorsiDiStudente(int matricola){
	return dai.getCorsiDiStudente(matricola);
}

}
