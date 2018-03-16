package org.wecancodeit.reviewsite;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Tag findByTag(String tag);

}
