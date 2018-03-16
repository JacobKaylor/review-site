package org.wecancodeit.reviewsite;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private TagRepository tagRepo;

	@RequestMapping(value = "categories")
	public String getAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return "categories";
	}

	@RequestMapping(value = "category")
	public String getACategory(@RequestParam("id") long id, Model model) {
		model.addAttribute("category", categoryRepo.findOne(id));
		return "category";
	}

	@RequestMapping("review")
	public String getAReview(@RequestParam("id") long id, Model model) {
		model.addAttribute("review", reviewRepo.findOne(id));
		return "review";
	}

	@RequestMapping(value = "reviews")
	public String getAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}

	@RequestMapping(value = "reviewbycategory")
	public String getACategory(@RequestParam Long id, Model model) {
		model.addAttribute("reviews", reviewRepo.findAllByCategory(categoryRepo.findOne(id)));
		return "category";
	}

	@RequestMapping(value = "tag")
	public String getATag(@RequestParam("id") long id, Model model) {
		model.addAttribute("tag", tagRepo.findOne(id));
		return "tag";
	}

	@RequestMapping(value = "tags")
	public String getAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return "tags";
	}

	@RequestMapping("/add-tag")
	public String addTag(@RequestParam(value = "id") Long id, String tag) {
		if (!tag.equals("")) {
			Tag tagCreation = tagRepo.findByTag(tag);
			if (tagCreation == null) {
				tagCreation = new Tag(tag);
				tagRepo.save(tagCreation);
			}
			Review review = reviewRepo.findOne(id);
			Collection<Tag> reviewTags = review.getTags();
			if (!reviewTags.contains(tagCreation)) {
				review.addTag(tagCreation);
				reviewRepo.save(review);
			}
		}
		return "redirect:/review?id=" + id;
	}

	@RequestMapping("/remove-tag")
	public String removeTag(@RequestParam Long tagId, @RequestParam Long reviewId) {
		Tag deleteTag = tagRepo.findOne(tagId);
		Review review = reviewRepo.findOne(reviewId);
		review.removeTag(deleteTag);
		reviewRepo.save(review);
		return "redirect:/review?id=" + reviewId;
	}

}
