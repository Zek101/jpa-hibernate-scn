package blackbelt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id @GeneratedValue
	private Long id;
	
	private String title;
	private String description;
	
	@ManyToOne
	private Category parent;
	
	@OneToMany
	private Set<Category> children = new HashSet<Category>();

	
	public Category() {
	
	}
	

	public Category(String title) {
		super();
		this.title = title;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}
	
	public void addChildren(Category ... category){
		for(int i = 0; i<category.length; i++){
			children.add(category[i]);
		}
	}
	
	@Override
	public String toString() {
		return this.getTitle();
	}
}
