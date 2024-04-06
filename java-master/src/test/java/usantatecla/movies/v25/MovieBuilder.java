package usantatecla.movies.v25;

public class MovieBuilder {

	private String title;

	public MovieBuilder() {
		title = "movieName";
	}
	
	public MovieBuilder title(String title) {
		this.title = title;
		return this;
	}

	public Movie build() {
		return new Movie(title);
	}
}
