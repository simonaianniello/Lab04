package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.sun.glass.ui.EventLoop.State;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	public Studente getNomeECognome(int matricola) {

		final String sql = "SELECT *\r\n" + 
				"FROM studente\r\n" + 
				"WHERE matricola= ?";
		Studente s=null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,matricola);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String cognome= rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS=rs.getString("CDS");
					
				s=new Studente (matricola,cognome,nome,CDS);
			}
			conn.close();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return s;
	}
	public List<Corso> getCorsiDiStudente(int matricola) {
		final String sql= "SELECT c.codins, c.crediti, c.nome, c.pd \r\n" + 
				"FROM studente s,iscrizione i, corso c\r\n" + 
				"WHERE i.matricola=s.matricola\r\n" + 
				 "AND c.codins=i.codins\r\n"+
				"AND i.matricola= ?";
		List<Corso> listaC=new ArrayList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,matricola);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String codins=rs.getString("codins");
				int crediti=rs.getInt("crediti");
				String nome=rs.getString("nome");
				int pd=rs.getInt("pd");
				Corso c=new Corso(codins,crediti,nome,pd);
			   listaC.add(c);
			}
			conn.close();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	
		return listaC;
	}
}
