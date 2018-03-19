package org.wecancodeit.reviewsite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue
	private long id;
	private String comment;
	
	@ManyToOne
	private Review review;
	
	public Comment(Review review, String comment) {
		this.review = review;
		this.comment = comment;
	}

	public Review getReview() {
		return review;
	}
	
	public String getComment() {
		return comment;
	}
	public long getId() {
		return id;
	}
	@SuppressWarnings("unused")
	private Comment() {
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
		return id == ((Comment) obj).id;
	}
}
