package pl.devcrowd.app.models;

public class Presentation {

	private String hourStart;
	private String hourEnd;
	private String room;
	private String description;
	private String title;
	private String speaker;

	public Presentation() {
		this("", "", "", "", "", "");
	}

	public Presentation(String hourStart, String hourEnd, String room,
			String description, String title, String speaker) {
		super();
		this.hourStart = hourStart;
		this.hourEnd = hourEnd;
		this.room = room;
		this.description = description;
		this.title = title;
		this.speaker = speaker;
	}

	public String getHourStart() {
		return hourStart;
	}

	public void setHourStart(String hourStart) {
		this.hourStart = hourStart;
	}

	public String getHourEnd() {
		return hourEnd;
	}

	public void setHourEnd(String hourEnd) {
		this.hourEnd = hourEnd;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	@Override
	public String toString() {
		return "Presentation [hourStart=" + hourStart + ", hourEnd=" + hourEnd
				+ ", room=" + room + ", description=" + description
				+ ", title=" + title + ", speaker=" + speaker + "]";
	}
	
	

}
