package solidYoutube.LSP;

public class LegalCustomer extends Customer {
	
	private String taxNumber;
	private String company;
	
	public LegalCustomer() {
		super();
	}

	public LegalCustomer(int id,String taxNumber, String company) {
		super(id);
		this.taxNumber = taxNumber;
		this.company = company;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
}
