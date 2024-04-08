package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
//conectar o url do controller com o main
/**
 * The Class Controle.
 */
//mapeamento
@WebServlet(urlPatterns = { "/Controller", "/main", "/main2", "/insert", "/select", "/update", "/delete", "/report" })
//@WebServlet(urlPatterns= {"/controller","/main2"})

public class Controle extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controle.
	 */
	public Controle() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// teste de conexão
		// dao.testedeconexao();

		String requisicao = request.getServletPath();
		System.out.println(requisicao);

		if (requisicao.equals("/main")) {
			contatos(request, response);
		}

		else if (requisicao.equals("/main2")) {
			listacontatos(request, response);
		}

		else if (requisicao.equals("/insert")) {
			novoContato(request, response);
		}

		else if (requisicao.equals("/select")) {
			selecionarContato(request, response);
		}

		else if (requisicao.equals("/update")) {
			editarContato(request, response);
		}
		else if (requisicao.equals("/delete")) {
			removerContato(request, response);
		}
		
		else if (requisicao.equals("/report")) {
			gerarRelatorio(request, response);
		}

		else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp");
		// Criando um objeto que ira receber os dados da Classe JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		// Encaminhamento do objeto lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

		// Teste de recebimento de lista
		// for(int i=0; i<lista.size(); i++) {
		// System.out.println(lista.get(i).getIdcon());
		// System.out.println(lista.get(i).getNome());
		// System.out.println(lista.get(i).getFone());
		// System.out.println(lista.get(i).getEmail());
		// }
	}

	/**
	 * Listacontatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listacontatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("listadecontatos.jsp");
	}

	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento de dados do formulario novo.html
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("fone"));
		// System.out.println(request.getParameter("email"));

		// Setar os atributos da Classe JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// Invocar o método inserirContato passando o objeto contato
		dao.inserirContato(contato);
		// Redirecionar para a página agenda.jsp
		response.sendRedirect("main");

	}

	/**
	 * Selecionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar Contato
	protected void selecionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id de contato que será editado
		String idcon = request.getParameter("idcon");

		// System.out.println(idcon);

		contato.setIdcon(Integer.parseInt(idcon));
		// Executar o Método selecionarContato pelo DAO
		dao.selecaoContato(contato);

		// teste de recebimento
		// System.out.println(contato.getIdcon());
		// System.out.println(contato.getNome());
		// System.out.println(contato.getFone());
		// System.out.println(contato.getEmail());

		// Configurar os atributos ao formulário com o conteúdoda Classe JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());

		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Teste de recebimento para edição após pressionar o botão Salvar
		//System.out.println(request.getParameter("idcon"));
		//System.out.println(request.getParameter("nome"));
		//System.out.println(request.getParameter("fone"));
		//System.out.println(request.getParameter("email"));
		
		//Settar os atributos do JavaBeans
		contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//executar o método alterarContato da classe DAO
		dao.alterarContato(contato);
		//redirecionar para o documento agenda.jsp (salvar alterações)
		response.sendRedirect("main");
													
	}
	
	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		//System.out.println(idcon);
		
		if(idcon != null) {
			contato.setIdcon(Integer.parseInt(idcon));
			dao.excluir(contato);
			response.sendRedirect("main");
		}else {
			response.sendRedirect("main");
		}
	}
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Gerar Relatório em PDF
		Document documento = new Document();
		
		try {
			//Tipo de conteúdo
			response.setContentType("application/pdf");
			//Nome do documento
			response.addHeader("Content=Disposition", "inline; filename="+"contatos.pdf");
			//Criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			//Abrir o documento -> conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de Contatos: "));
			//Listagem de contato
			documento.add(new Paragraph(" "));
			//Criar uma tabela no pdf
			PdfPTable tabela = new PdfPTable(4);
			//Cabecalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Idcon"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			//Popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for(int i=0; i<lista.size(); i++) {
				tabela.addCell(Integer.toString(lista.get(i).getIdcon()));
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			
			documento.add(tabela);
			documento.close();
			} catch (Exception e) {
			documento.close();
		}
		
	}
	
	

}
