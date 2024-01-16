package co.edu.uptc.model;

import java.util.ArrayList;

public class Brand {
	private String name;
	private ArrayList<Line> lines;

	public Brand(String name) {
		this.name = name;
		lines = new ArrayList<Line>();
	}

	public Line searchLine(String name) {
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).getName().equals(name)) {
				return lines.get(i);
			}
		}
		return null;
	}

	public void addLine(Line line) {
		lines.add(line);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

}
