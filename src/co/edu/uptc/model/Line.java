package co.edu.uptc.model;

import java.util.ArrayList;

public class Line {

	private String name;
	private ArrayList<Model> models;

	public Line(String name) {
		this.name = name;
		models = new ArrayList<Model>();
	}

	public Model searchModel(int year) {
		for (int i = 0; i < models.size(); i++) {
			if (models.get(i).getYear() == year) {
				return models.get(i);
			}
		}
		return null;
	}

	public void addModel(Model model) {
		models.add(model);
	}

	public ArrayList<Model> getModels() {
		return models;
	}

	public String getName() {
		return name;
	}

}
