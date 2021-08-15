package kodlamaIo4GunOdev3.concrete;

import kodlamaIo4GunOdev3.Abstract.CampaignService;
import kodlamaIo4GunOdev3.entitiy.Campaign;

public class CampaignManager implements CampaignService{

	@Override
	public void add(Campaign campaign) {
		System.out.println(campaign.getCampaignName()+ " kampanya adiyla sisteme eklendi.");
		
	}

	@Override
	public void update(Campaign campaign) {
		System.out.println(campaign.getCampaignName()+ " kampanya sistemden guncellendi.");
		
	}

	@Override
	public void delete(Campaign campaign) {
		System.out.println(campaign.getCampaignName()+ " kampanya sistemden silindi.");
		
	}

}
