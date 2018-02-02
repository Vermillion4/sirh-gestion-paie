package dev.paie.service;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {
	
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		String sql = "INSERT INTO Grade (Code,NbHeuresBase,TauxBase) VALUES(?,?,?)";
		String sqlTest="SELECT COUNT(1) FROM Grade WHERE Code LIKE '"+nouveauGrade.getCode()+"'";
		if(this.jdbcTemplate.queryForObject(sqlTest,Integer.class).equals(1)){
			mettreAJour(nouveauGrade);
			return;
		}
		this.jdbcTemplate.update(sql,nouveauGrade.getCode(),nouveauGrade.getNbHeuresBase(),nouveauGrade.getTauxBase());		
	}

	@Override
	public void mettreAJour(Grade grade) {
		String sql = "UPDATE Grade SET NbHeuresBase= ? ,TauxBase = ? WHERE Code = ?";
		this.jdbcTemplate.update(sql,grade.getNbHeuresBase(),grade.getTauxBase(),grade.getCode());
	}

	@Override
	public List<Grade> lister() {
		
		RowMapper<Grade> mapGrade = (ResultSet rs, int rowNum) -> {
			Grade g = new Grade();
			g.setCode(rs.getString("Code"));
			g.setNbHeuresBase(rs.getBigDecimal("NbHeuresBase"));
			g.setTauxBase(rs.getBigDecimal("TauxBase"));
			return g;
		};
		String sql="SELECT * FROM Grade";
		return this.jdbcTemplate.query(sql, mapGrade);
	}

	@Override
	public Grade consulter(String code) {
		RowMapper<Grade> mapGrade = (ResultSet rs, int rowNum) -> {
			Grade g = new Grade();
			g.setCode(rs.getString("Code"));
			g.setNbHeuresBase(rs.getBigDecimal("NbHeuresBase"));
			g.setTauxBase(rs.getBigDecimal("TauxBase"));
			return g;
		};
		String sql="SELECT * FROM Grade WHERE Code Like'"+code+"' LIMIT 1";
		return this.jdbcTemplate.queryForObject(sql,mapGrade);
	}
	
}
