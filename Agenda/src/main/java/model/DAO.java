package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	// Módulo de conexão
	// parametros para conexão

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";

	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "backend@24";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Metodo de conexão
	private Connection conectar() {
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
			// TODO: handle exception
		}
	}

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	// CRUD -> CREATE
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contato(nome, fone, email) values (?,?,?)";

		try {
			// Abrir conexão
			Connection con = conectar();
			// Preparar a consulta (query) para execuçâo no Banco de Dados:
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo dos atributos da classe JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// Executar a query (Ctrl + enter)
			pst.executeUpdate();
			// Encerrar a conexão com o Banco de Dados
			con.close();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

	}
	// CRUD -> READ

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {

		// Criando o objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList();
		String read = "select * from contato";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do Banco
				int idcon = rs.getInt(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;

		} catch (Exception e) {
			System.out.println(e);
			return null;

		}

	}

	// CRUD UPDATE
	/**
	 * Selecao contato.
	 *
	 * @param contato the contato
	 */
	// Selecionar contato
	public void selecaoContato(JavaBeans contato) {
		String read2 = "select * from contato where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);

			pst.setInt(1, contato.getIdcon());

			ResultSet rs = pst.executeQuery();

			// Laço do While para receber dados do BD e enviar para a classe JavaBeans
			while (rs.next()) {
				// Setar as variaveis JavaBeans
				contato.setIdcon(rs.getInt(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	public void alterarContato(JavaBeans contato) {
		String atualizar = "update contato set nome=?,fone=?,email=? where idcon=?";

		try {
			// Abrir conexão
			Connection con = conectar();
			// Preparar a consulta (query) para execuçâo no Banco de Dados:
			PreparedStatement pst = con.prepareStatement(atualizar);
			// Substituir os parâmetros (?) pelo conteúdo dos atributos da classe JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setInt(4, contato.getIdcon());
			// Executar a query (Ctrl + enter)
			pst.executeUpdate();
			// Encerrar a conexão com o Banco de Dados
			con.close();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}
	
	/**
	 * Excluir.
	 *
	 * @param contato the contato
	 */
	public void excluir(JavaBeans contato) {
		//pst
		String read4 = "delete from contato where idcon=?;";
		
		try {
			Connection con = conectar();
			
			PreparedStatement pst = con.prepareStatement(read4);
			pst.setString(1, Integer.toString(contato.getIdcon()));
			
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// teste de conexão

	/*
	 * public void testedeconexao() { try { Connection con = conectar();
	 * System.out.println(con); con.close(); } catch (Exception e) { // TODO: handle
	 * exception System.out.println(e); } }
	 */

}
