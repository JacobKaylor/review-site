package org.wecancodeit.reviewsite;

import static java.lang.String.format;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {

	@Id
	@GeneratedValue

	private long id;
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private Collection<Review> reviews;
	@ManyToMany
	private Collection<Tag> tags;

	public Collection<Review> getReviews() {
		return reviews;
	}
	
//	public Collection<String> getCoursesUrls(){
//		Collection<String> urls = new ArrayList<>();
//		for(Review r : reviews) {
//			urls.add(format("/reviews/%d", r.getId()));
//		}
//		return urls;
//	}

	public Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}


	@SuppressWarnings("unused")
	private Category() {
	}

	public Category(String name, Tag... tags) {
		this.name = name;
		this.tags = new HashSet<>(asList(tags));
	}

	@Override
	public String toString() {
		return name;
	}

}
