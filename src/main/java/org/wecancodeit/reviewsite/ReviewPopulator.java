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
//		Tag sports = new Tag("Sports");
//		sports = tagRepo.save(sports);
//		Tag design = new Tag("Design");
//		design = tagRepo.save(design);
		// we save on the *one* side of the relationship first
		Category music = categoryRepo.save(new Category("Music"));
		Category movies = categoryRepo.save(new Category("Movie"));
		Category videogames = categoryRepo.save(new Category("Video Game"));
		Category books = categoryRepo.save(new Category("Book"));
		
		// then the things on the *many* side.
		reviewRepo.save(new Review(music,"Pink Floyd", "Are we on the dark side of the moon yet?" , "imageUrl"));
		reviewRepo.save(new Review(music,"Tom Petty", "He was a better solo artist" , "imageUrl"));
		reviewRepo.save(new Review(movies,"Talladega Nights: The Ballad of Ricky Bobby", "I like to go fast"  , "imageUrl"));
		reviewRepo.save(new Review(videogames,"Mario Kart", "Best selling game circa 2001"  , "imageUrl"));
		reviewRepo.save(new Review(books,"Rocketboys", "The best thing to come out of West Virginia"  , "imageUrl"));
		
		
		
	}

}
