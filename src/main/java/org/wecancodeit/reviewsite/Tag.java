package org.wecancodeit.reviewsite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tag {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	public Tag(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public Tag() {
		
	}
}
