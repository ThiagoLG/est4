package com.estagio.bean;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.estagio.dao.AlunoDao;
import com.estagio.model.Aluno;

public class testedorelatorio {

	public static void main(String[] args) {

		List<Aluno> lstAlunos = new ArrayList<Aluno>();
		List<Aluno> pendentes = new ArrayList<Aluno>();
		AlunoDao dao = new AlunoDao();

		try {
			lstAlunos = dao.pesquisarNome("");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (Aluno a : lstAlunos) {

			String re1 = a.getRel1();
			String re2 = a.getRel2();
			String re3 = a.getRel3();
			String re4 = a.getRel4();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			try {
				Date termino = a.getTermino();
				Date r1 = sdf.parse(re1);
				Date r2 = sdf.parse(re2);
				Date r3 = sdf.parse(re3);
				Date r4 = sdf.parse(re4);

				if (r1.after(termino) || r1.equals(termino)) {
					a.setRel1(sdf.format(termino));
					a.setRel2("");
					a.setRel3("");
					a.setRel4("");

				} else if (r2.after(termino) || r2.equals(termino)) {
					a.setRel2(sdf.format(termino));
					a.setRel3("");
					a.setRel4("");
				} else if (r3.after(termino) || r3.equals(termino)) {
					a.setRel3(sdf.format(termino));
					a.setRel4("");
				} else if (r4.after(termino) || r4.equals(termino)) {
					a.setRel4(sdf.format(termino));
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

			if (a.getRel1().contains("01/2018") && a.isRelatorio1() == false) {
				a.setDtShow(a.getRel1());
				pendentes.add(a);
			} else if (a.getRel2().contains("01/2018") && a.isRelatorio2() == false) {
				a.setDtShow(a.getRel2());
				pendentes.add(a);
			} else if (a.getRel3().contains("01/2018") && a.isRelatorio3() == false) {
				a.setDtShow(a.getRel3());
				pendentes.add(a);
			} else if (a.getRel4().contains("01/2018") && a.isRelatorio4() == false) {
				a.setDtShow(a.getRel4());
				pendentes.add(a);
			}
		}

		for (Aluno a : pendentes) {
			System.out.println(a.getRa() + " - " + a.getNome() + " - " + a.getDtShow());
		}

	}

}
