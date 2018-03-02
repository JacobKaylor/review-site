package org.wecancodeit.reviewsite;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner{
	
	@Resource
	private ReviewRepository reviewRepo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
