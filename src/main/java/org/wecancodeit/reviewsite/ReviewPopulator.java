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
		reviewRepo.save(new Review(music,"Pink Floyd", "Are we on the dark side of the moon yet?" , "/images/The Wall.jpg"));
		reviewRepo.save(new Review(music,"Tom Petty", "He was a better solo artist" , "/images/Tom Petty.jpg"));
		reviewRepo.save(new Review(music,"Lumineers", "Life, man" , "/images/Cleopatra.jpg"));
		reviewRepo.save(new Review(movies,"Talladega Nights: The Ballad of Ricky Bobby", "I like to go fast"  , "/images/Ricky Bobby.jpg"));
		reviewRepo.save(new Review(movies,"Ted", "Not what you think it is, although the main character is a teddy bear"  , "/images/Ted.jpg"));
		reviewRepo.save(new Review(movies,"Hot Rod", "My name's Rod and I like to party!"  , "/images/Hot Rod.jpg"));
		reviewRepo.save(new Review(videogames,"Mario Kart", "N64 game only"  , "/images/MarioKart.jpg"));
		reviewRepo.save(new Review(videogames,"Call of Duty: whatever the newest version is called", "10th prestige is the middle/high schoolers dream"  , "/images/COD.jpg"));
		reviewRepo.save(new Review(videogames,"Guitar Hero 17", "I feel like I can play an actual guitar after playing this game "  , "/images/GuitarHero.jpg"));
		reviewRepo.save(new Review(books,"Rocketboys", "The best thing to come out of West Virginia"  , "/images/RocketBoys.jpg"));
		reviewRepo.save(new Review(books,"Harry Potter and the sorcerers stone", "It's hard to believe that this is the best harry potter book"  , "/images/HarryPotter.jpg"));
		reviewRepo.save(new Review(books,"Elon Musk", "This book was written 3 years ago and it is too outdated"  , "/images/Elon.jpg"));
		
		
		
	}

}
