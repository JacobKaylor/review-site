package org.wecancodeit.reviewsite;

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

//		@RequestMapping(value = "reviews")
//		public String getAllReviews(Model model) {
//			model.addAttribute("reviews", reviewRepo.findAll());
//			return "reviews";
//		}
		
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

//		@RequestMapping("review")
//		public String getAReview(@RequestParam Long id, Model model) {
//			model.addAttribute("reviews", reviewRepo.findReview(id));
//			return "review";
//		}
	}

