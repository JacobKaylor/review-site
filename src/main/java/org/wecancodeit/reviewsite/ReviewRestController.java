package org.wecancodeit.reviewsite;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class ReviewRestController {

	@Resource
	private ReviewRepository repo;

	@RequestMapping("")
	public Iterable<Review> findAllCourses(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "") String advanced) throws InterruptedException {
		if (search.isEmpty()) {
			if (advanced.isEmpty()) {
				return repo.findAll();
			}
			return repo.findByDescriptionIgnoreCaseLike(advanced.replace('*', '%'));
		}

		return repo.findByDescriptionIgnoreCaseContains(search);
	}

	@RequestMapping("/{id}")
	public Review findOneCourse(@PathVariable long id) {
		return repo.findOne(id);
	}
}
