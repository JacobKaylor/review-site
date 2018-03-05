package org.wecancodeit.reviewsite;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JpaMappingsTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private TagRepository tagRepo;

	@Test
	public void shouldSaveAndLoadCourse() {
		// more concise to do:
		Review review = reviewRepo.save(new Review("its name"));
		review = reviewRepo.save(review);
		long reviewId = review.getId();

		entityManager.flush(); // forces pending stuff to happen
		entityManager.clear(); // detaches all entities, forces jpa to hit the db when we try to find an entity

		review = reviewRepo.findOne(reviewId);

		assertThat(review.getName(), is("its name"));
	}

	@Test
	public void shouldSaveReviewToCategoryRelationship() {

		Category category = new Category("its name");
		// first we save the thing that does NOT own the relationship (so that we have
		// an id to be used as a foreign key)
		categoryRepo.save(category);
		long categoryId = category.getId();

		Review first = new Review(category, "Pink Floyd", "Relaxing", "");
		reviewRepo.save(first);

		Review second = new Review(category, "Tom Petty", "Connection", "");
		reviewRepo.save(second);

		entityManager.flush();
		entityManager.clear();

		category = categoryRepo.findOne(categoryId);
		assertThat(category.getReviews(), containsInAnyOrder(first, second));
	}

	@Test
	public void shouldSaveAndLoadTag() {
		Tag tag = tagRepo.save(new Tag("its name"));
		long tagId = tag.getId();

		entityManager.flush();
		entityManager.clear();

		tag = tagRepo.findOne(tagId);
		assertThat(tag.getName(), is("its name"));
	}

	@Test
	public void shouldEstablishCourseToTagsRelationships() {

		Tag sports = tagRepo.save(new Tag("Sports"));
		Tag design = tagRepo.save(new Tag("Design"));

		Review review = new Review("Tags", sports, design);
		review = reviewRepo.save(review);
		long tagsId = review.getId();

		entityManager.flush();
		entityManager.clear();

		review = reviewRepo.findOne(tagsId);
		assertThat(review.getTags(), containsInAnyOrder(sports, design));

	}

	@Test
	public void shouldEstablishTagToCoursesRelationship() {
		Tag tag = tagRepo.save(new Tag("Sports"));
		long tagId = tag.getId();

		Review sports = new Review("Sports", tag);
		sports = reviewRepo.save(sports);

		Review design = new Review("Design", tag);
		design = reviewRepo.save(design);

		entityManager.flush();
		entityManager.clear();

		tag = tagRepo.findOne(tagId);
		assertThat(tag.getReviews(), containsInAnyOrder(sports, design));
	}
}
