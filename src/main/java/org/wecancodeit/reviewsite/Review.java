package org.wecancodeit.reviewsite;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	public Review(Category category, String name, String description, String image) {
		this.name = name;
		this.category = category;
		this.description = description;
		this.image = image;
	}

	public Review(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	private long id;
	private String name;
	@Lob
	private String description;
	@Lob
	private String image;

	@ManyToOne
	private Category category;

	@ManyToMany
	private Collection<Tag> tags;

	public Collection<Tag> getTags() {
		return tags;
	}
	public Category getCategory() {
		return category;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public String getImageUrl() {
		return image;
	}
	public String getDescription() {
		return description;
	}

	@SuppressWarnings("unused")
	private Review() {
	}

	public Review(String name, Tag... tags) {
		this.name = name;
		this.tags = new HashSet<>(asList(tags));
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		return id == ((Review) obj).id;
	}

}
