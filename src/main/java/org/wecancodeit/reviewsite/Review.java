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

	public Review(Category category, String name, String description, String image, Tag...tags) {
		this.name = name;
		this.category = category;
		this.description = description;
		this.image = image;
		this.tags = new HashSet<>(asList(tags));
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

	public String getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	@SuppressWarnings("unused")
	private Review() {
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

	public void addTag(Tag tag) {
		tags.add(tag);
		
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
		
	}

}
