package org.wecancodeit.reviewsite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Review {
	public Review(String name) {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
}
