package example.jpa.model;

import javax.persistence.Basic;
import javax.persistence.Entity;


@Entity @SuppressWarnings("serial")
public class Message extends AbstractEntity {
	
	@Basic
	private String caption;

	@Basic
	private String text;

	
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}