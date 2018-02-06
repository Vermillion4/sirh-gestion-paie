package dev.paie.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class ProfilRemuneration {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String code;

	/**
	 * Table sur Many to Many a besoin de jointures pous ne pas genere de SqlException, causees par des transactions.
	 */
	@ManyToMany
	@JoinTable(name="ProfilRem_CotisationNImp",
	joinColumns=@JoinColumn(name="ID_RMPR", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="ID_COTIS", referencedColumnName="id"))
	private List<Cotisation> cotisationsNonImposables;
	
	@ManyToMany
	@JoinTable(name="REMUNPROFIL_COTISATION_NON_IMPOSABLE",
	joinColumns=@JoinColumn(name="ID_RMPR", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="ID_COTIS", referencedColumnName="id"))
	private List<Cotisation> cotisationsImposables;
	
	@ManyToMany
	@JoinTable(name="REMUNPROFIL_AVANTAGE",
	joinColumns=@JoinColumn(name="ID_RMPR", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="ID_AVANTAGE", referencedColumnName="id"))
	private List<Avantage> avantages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Cotisation> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(List<Cotisation> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}

	public List<Cotisation> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(List<Cotisation> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public List<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(List<Avantage> avantages) {
		this.avantages = avantages;
	}

}
