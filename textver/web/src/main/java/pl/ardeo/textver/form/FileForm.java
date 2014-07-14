package pl.ardeo.textver.form;


public class FileForm {
	private long documentId;
	private String name, description, title, content, version;
	
	public FileForm() {
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	public String getVersion(){
		return version;
	}
	
	public long getDocumentId(){
		return documentId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	
	public void setDocumentId(long documentId){
		this.documentId = documentId;
	}
	
	@Override
	public String toString(){
		return "name = " + getName() + " title = " + getTitle() + " content = " + getContent() + " version = " + getVersion() + " description = " + getDescription()
				+ " documentId = " + getDocumentId();
	}
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof FileForm))
			return false;
		FileForm otherFile = (FileForm)other;
		
		return this.name.equals(otherFile.name) && this.title.equals(otherFile.title)
				&& this.content.equals(otherFile.content) && this.version.equals(otherFile.version)
				&& this.description.equals(otherFile.description);
	}
	
	
}
