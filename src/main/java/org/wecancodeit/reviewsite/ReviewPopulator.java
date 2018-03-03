package org.wecancodeit.reviewsite;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner{
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource 
	private TagRepository tagRepo;
	
	@Resource
	private CategoryRepository categoryRepo;

	@Override
	public void run(String... args) throws Exception {
		Tag sports = new Tag("Sports");
		sports = tagRepo.save(sports);
		Tag design = new Tag("Design");
		design = tagRepo.save(design);
		// we save on the *one* side of the relationship first
		Category music = categoryRepo.save(new Category("Music"));
		Category movies = categoryRepo.save(new Category("Movies"));
		Category videogames = categoryRepo.save(new Category("Video Games"));
		Category books = categoryRepo.save(new Category("Books"));
		
		// then the things on the *many* side.
		reviewRepo.save(new Review(music,"Pink Floyd", "Are we on the dark side of the moon yet?" , "imageUrl"));
		reviewRepo.save(new Review(movies,"Talladega Nights: The Ballad of Ricky Bobby", "I like to go fast"  , "imageUrl"));
		
		
		
	}

}
