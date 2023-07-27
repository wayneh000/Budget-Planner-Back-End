package wayneh000.budgetplanner.dao;

import wayneh000.budgetplanner.entity.Income;

public class IncomeDAO {

	private Long incomeId;
	private BudgetDAO budgetDAO;
	private String name;
	private String category;
	private Double amount;
	private Integer period;
	
	public Long getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(Long incomeId) {
		this.incomeId = incomeId;
	}

	public BudgetDAO getBudgetDAO() {
		return budgetDAO;
	}

	public void setBudgetDAO(BudgetDAO budgetDAO) {
		this.budgetDAO = budgetDAO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "IncomeDAO [incomeId=" + incomeId + ", budgetDAO=" + budgetDAO + ", name=" + name + ", category="
				+ category + ", amount=" + amount + ", period=" + period + "]";
	}
	
	public static Income toEntity(IncomeDAO incomeDAO) {
		Income income = new Income();
		income.setIncomeId(incomeDAO.getIncomeId());
		income.setBudget(BudgetDAO.toEntity(incomeDAO.getBudgetDAO()));
		income.setName(incomeDAO.getName());
		income.setCategory(incomeDAO.getCategory());
		income.setAmount(incomeDAO.getAmount());
		income.setPeriod(incomeDAO.getPeriod());
		return income;
	}
	
	public static IncomeDAO fromEntity(Income income) {
		IncomeDAO incomeDAO = new IncomeDAO();
		incomeDAO.setIncomeId(income.getIncomeId());
		incomeDAO.setBudgetDAO(BudgetDAO.fromEntity(income.getBudget()));
		incomeDAO.setName(income.getName());
		incomeDAO.setCategory(income.getCategory());
		incomeDAO.setAmount(income.getAmount());
		incomeDAO.setPeriod(income.getPeriod());
		return incomeDAO;
	}
}
