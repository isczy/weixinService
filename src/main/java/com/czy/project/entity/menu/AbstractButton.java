package com.czy.project.entity.menu;



public abstract class AbstractButton {
	
	private String name;

	public AbstractButton(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AbstractButton [name=" + name + "]";
	}
	
	
}
