package kodlamaIo4GunOdev3.entitiy;

import java.time.LocalDate;

public class Campaign {
	private int id;
	private String campaignName;
	private int discount;
	private LocalDate startDate;
	private LocalDate finishDate;
	
	public Campaign() {}
	
	public Campaign(int id, String campaignName, int discount, LocalDate startDate, LocalDate finishDate) {
		super();
		this.id = id;
		this.campaignName = campaignName;
		this.discount = discount;
		this.startDate = startDate;
		this.finishDate = finishDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}

}
