package org.wecancodeit.reviewsite;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue
	
	@Column(name = "category")
	
	private long id;
	private String name;
	private String image;

	@OneToMany(mappedBy = "category")
	private Collection<Review> reviews;
	@ManyToMany
	private Collection<Tag> tags;

	public Collection<Review> getReviews() {
		return reviews;
	}

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
