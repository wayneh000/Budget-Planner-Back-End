package wayneh000.budgetplanner.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "budget")
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "budget_id")
	private Integer budgetId;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	@Column(name = "name")
	private String name;
	
	@Lob
	@Column(name = "description")
	private String description;
	
	@Column(name = "period")
	private Integer period;
	
	@Column(name = "date_created")
	private LocalDateTime dateCreated;
	
	@Column(name = "date_last_edited")
	private LocalDateTime dateLastEdited;

	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateLastEdited() {
		return dateLastEdited;
	}

	public void setDateLastEdited(LocalDateTime dateLastEdited) {
		this.dateLastEdited = dateLastEdited;
	}

	@Override
	public String toString() {
		return "Budget [budgetId=" + budgetId + ", account=" + account + ", name=" + name + ", description="
				+ description + ", period=" + period + ", dateCreated=" + dateCreated + ", dateLastEdited="
				+ dateLastEdited + "]";
	}
}
