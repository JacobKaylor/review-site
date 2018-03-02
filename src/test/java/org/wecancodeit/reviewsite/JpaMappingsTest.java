package org.wecancodeit.reviewsite;

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
}
