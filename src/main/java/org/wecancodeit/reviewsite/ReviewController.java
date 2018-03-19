package org.wecancodeit.reviewsite;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Resource
	private CommentRepository commentRepo;

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

	@RequestMapping("/add-comment")
	public String addComment(@RequestParam(value = "id") Long id, String comment) {
		Review currentReview = reviewRepo.findOne(id);
		if (!comment.equals("")) {
			Comment commentCreation = commentRepo.findByComment(comment);
			if (commentCreation == null) {
				commentCreation = new Comment(currentReview, comment);
				commentRepo.save(commentCreation);
				
			}
			Review review = reviewRepo.findOne(id);
			Collection<Comment> reviewComments = review.getComments();
			if (!reviewComments.contains(commentCreation)) {
				review.addComment(commentCreation);
				reviewRepo.save(review);
			}
		}
	
		return "redirect:/review?id=" + id;
	}
	@RequestMapping("/remove-comment")
	public String deleteComment(@PathVariable Long id, Long commentId) {
		commentRepo.delete(commentId);
		return "redirect:/review/{id}";
	}

}
