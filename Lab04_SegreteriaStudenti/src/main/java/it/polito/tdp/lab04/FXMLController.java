/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */
package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
		Model model;
		

		    @FXML
		    private ResourceBundle resources;

		    @FXML
		    private URL location;

		    @FXML
		    private ComboBox<Corso> choiceCorso;

		    @FXML
		    private Button btnCercaIscritti;

		    @FXML
		    private TextField txtMatricola;

		    @FXML
		    private Button btnPrendiMAtricola;

		    @FXML
		    private TextField txtNome;

		    @FXML
		    private TextField txtCognome;

		    @FXML
		    private Button btnCercaCorsi;

		    @FXML
		    private Button btnIscrivi;

		    @FXML
		    private TextArea txtResult;

		    @FXML
		    private Button btnReset;

		    @FXML
		    void doCercaCorsi(ActionEvent event) {
		    	
		    	List<Corso> lCorso=new ArrayList<Corso>();
		    	int matricola;
		    	try {
			    	matricola=Integer.parseInt(txtMatricola.getText());
			    	lCorso= model.getCorsiDiStudente(Integer.parseInt(txtMatricola.getText()));
			    	String s="";
			    	for (Corso c:lCorso) {
			    		if (c!=null) {
			    			s+=String.format("%-20s %-20s %-20s %-20s ",c.getCodins(),c.getCrediti(),c.getNome(),c.getPd())+"\n";
			    			txtResult.setText(s);	
			    		
			    		}
			    	}
			        }
			        catch(NumberFormatException e){
			        	txtResult.setText("inserisci matricola valida!\n");
			        }
		  
		    }

		    @FXML
		    void doCercaIscrittiCorso(ActionEvent event) {
		    	Corso c=choiceCorso.getValue();
		    	try {
		    	List<Studente> lStud=model.getStudentiIscrittiAlCorso(c);
		    	String s="";
		    	for (Studente t:lStud) {
		    		if (t!=null) {
		    			s+=String.format("%-20s %-20s %-20s %-20s ",t.getMatricola(),t.getNome(),t.getCognome(),t.getCDS())+"\n";
		    			txtResult.setText(s);		    			
		    		}
		    	}
		    	
		    }
		    	catch(NullPointerException e) {
		    		txtResult.setText("SCEGLIERE UN CORSO\n");
		    	}
		    }

		    @FXML
		    void doIscrivi(ActionEvent event) {
		    	Corso c;choiceCorso.getValue();
		    	int matricola;
		    	try {
		    		c=choiceCorso.getValue();
		    		matricola=Integer.parseInt(txtMatricola.getText());
		    		Studente s=model.getNomeECognome(matricola);
		    		if (model.inscriviStudenteACorso(s, c)==true)
		    			txtResult.setText("Studente iscritto al corso!");
		    		if (model.inscriviStudenteACorso(s,c)==false) {
		    		txtResult.setText("studente non iscritto al corso!");
		    	}
		    	}
		    	catch (NullPointerException e) {
		    		txtResult.setText("Devi scegliere un corso");
		    	}
		    	catch(NumberFormatException e1) {
		    		txtResult.setText("Devi inserire una matricola composta di numeri");
		    	}
		    	

		    }

		    @FXML
		    void doNomeECognome(ActionEvent event) {
		    	 int matricola=0;
			    	try {
			    	matricola=Integer.parseInt(txtMatricola.getText());
			        }
			        catch(NumberFormatException e){
			        	txtNome.setText("INSERISCI NUMERO!");
			        	txtCognome.setText("INSERISCI NUMERO!");
			        	return;
			        	
			        }
			      	Studente s=this.model.getNomeECognome(matricola);
			    	if (s!=null) {
			    	txtNome.setText(s.getNome());
			    	txtCognome.setText(s.getCognome());
			    	}
			     	
			    	
			    	else {
			    		 txtNome.setText("matricola non trovata");
			    		 txtCognome.setText("matricola non trovata");
			    		 return;
			    	}

		    }
		    public Model getModel() {
				return model;
			}

			public void setModel(Model model) {
				this.model = model;
				List<Corso> corso=this.model.getTuttiICorsi();
				List<String> nomeCorso=new ArrayList<String>();
				Corso c=new Corso(null,0,"",0);
				corso.add(c);
				choiceCorso.getItems().addAll(corso);
			}

		    @FXML
		    void doReset(ActionEvent event) {
		    	txtMatricola.clear();
		    	txtNome.clear();
		    	txtCognome.clear();
		    	txtResult.clear();
		    	choiceCorso.setValue(null);
		    }

		    @FXML
		    void initialize() {
		        assert choiceCorso != null : "fx:id=\"choiceCorso\" was not injected: check your FXML file 'Scene.fxml'.";
		        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
		        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
		        assert btnPrendiMAtricola != null : "fx:id=\"btnPrendiMAtricola\" was not injected: check your FXML file 'Scene.fxml'.";
		        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
		        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
		        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
		        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
		        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
		        Model model=new Model();

		    }
		}

		

