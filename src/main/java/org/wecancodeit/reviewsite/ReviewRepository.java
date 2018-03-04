package org.wecancodeit.reviewsite;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
@Component
public interface ReviewRepository extends CrudRepository<Review, Long>{
	
	List<Review> findByName(String name);
	
	Collection<Review> findAllByCategory(Category category); 

}
