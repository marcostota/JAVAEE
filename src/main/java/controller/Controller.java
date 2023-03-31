package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			insert(request, response);
		} else if (action.equals("/select")) {
			selectContatos(request, response);
		} else if (action.equals("/update")) {
			updateContato(request, response);
		} else if (action.equals("/delete")) {
			deleteContato(request, response);
		} else if (action.equals("/report")) {
			gerarRel(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> contatosList = dao.listarContatos();
		request.setAttribute("contatos", contatosList);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("nome"));
		contato.setNome(request.getParameter("nome"));
		contato.setEmail(request.getParameter("email"));
		contato.setFone(request.getParameter("fone"));
		dao.inserirContato(contato);

		response.sendRedirect("main");
	}

	protected void selectContatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contato.setIdcon(request.getParameter("idcon"));
		dao.selecionarContato(contato);
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getNome());
		request.setAttribute("email", contato.getEmail());
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	protected void updateContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		dao.updateContato(contato);
		response.sendRedirect("main");
	}

	protected void deleteContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contato.setIdcon(request.getParameter("idcon"));
		dao.deleteContato(contato);
		response.sendRedirect("main");
	}

	protected void gerarRel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document doc = new Document();
		try {
			response.setContentType("apllication/pdf");
			response.addHeader("Content-Disposition", "inline; filename=contatos.pdf");
			PdfWriter.getInstance(doc, response.getOutputStream());
			doc.open();
			doc.add(new Paragraph("Lista de contatos:"));
			doc.add(new Paragraph(" "));
			PdfPTable tabela = new PdfPTable(3);

			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);

			ArrayList<JavaBeans> lista = dao.listarContatos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());

			}
			doc.add(tabela);
			doc.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
