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
	private String name;

	@OneToMany
	private Set<Category> categoryChildren = new HashSet<Category>();
	
	@ManyToOne
	private Category categoryParent;
	
	
	
	public Category() {
		
	}

	public Category(String name, Category categoryParent) {
		this.name = name;
		this.categoryParent = categoryParent;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Category> getCategoryChildren() {
		return categoryChildren;
	}

	public void setCategoryChildren(Set<Category> categoryChildren) {
		this.categoryChildren = categoryChildren;
	}

	public Category getCategoryParent() {
		return categoryParent;
	}

	public void setCategoryParent(Category categoryParent) {
		this.categoryParent = categoryParent;
	}
	
	public void setCategoryChildren(Category ... category){
		for (int i = 0; i < category.length; i++)
			categoryChildren.add(category[i]);

	}
	
}
