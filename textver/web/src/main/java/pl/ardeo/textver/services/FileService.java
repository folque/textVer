package pl.ardeo.textver.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.ardeo.textver.form.FileForm;
import pl.ardeo.textver.model.Document;
import pl.ardeo.textver.model.Version;
import pl.ardeo.textver.services.dao.DocumentDAO;
import pl.ardeo.textver.services.dao.UserDAO;
import pl.ardeo.textver.services.dao.VersionDAO;

@Service
public class FileService {

	@Autowired
	DocumentDAO documentDAO;

	@Autowired
	VersionDAO versionDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	CurrentUser currentUser;

	public void insert(FileForm fileForm){
		Document document = new Document();
		if(documentDAO.findLast() instanceof Document){
			document.setId(documentDAO.findLast().getId() + 1);
		}
		else {
			document.setId(1);
		}
		document.setName(fileForm.getName());
		Version version = new Version();
		if(versionDAO.findLast() instanceof Version){
			version.setId(versionDAO.findLast().getId() + 1);
		}
		else{
			version.setId(1);
		}
		version.setDocument_id(document);
		version.setVersion_name(fileForm.getVersion());
		version.setModified(new Timestamp(System.currentTimeMillis()));
		version.setUser_id(userDAO.findByUsername(currentUser.getUser()));
		version.setDescription(fileForm.getDescription());
		version.setTitle(fileForm.getTitle());
		version.setContent(fileForm.getContent());

		documentDAO.insertOrUpdate(document);
		versionDAO.insertOrUpdate(version);
	}

	public void update(FileForm old, FileForm fileForm, long id){
		Document document = documentDAO.findById(id);
		document.setName(fileForm.getName());
		Version version = new Version();
		if(fileForm.getVersion().equals(old.getVersion())){
			version.setId(documentDAO.findVersionByName(document, fileForm.getVersion()).getId());
		}
		else{
			if(versionDAO.findLast() instanceof Version){
			version.setId(versionDAO.findLast().getId() + 1);
			}
			else{
				version.setId(1);
			}
		}
		version.setDocument_id(document);
		version.setVersion_name(fileForm.getVersion());
		version.setModified(new Timestamp(System.currentTimeMillis()));
		version.setUser_id(userDAO.findByUsername(currentUser.getUser()));
		version.setDescription(fileForm.getDescription());
		version.setTitle(fileForm.getTitle());
		version.setContent(fileForm.getContent());

		documentDAO.insertOrUpdate(document);
		versionDAO.insertOrUpdate(version);
	}

	public FileForm populate(FileForm fileForm, long id, String ver){
		Document document = documentDAO.findById(id);
		Version version = documentDAO.findVersionByName(document, ver);
		fileForm.setDocumentId(document.getId());
		fileForm.setName(document.getName());
		fileForm.setTitle(version.getTitle());
		fileForm.setContent(version.getContent());
		fileForm.setVersion(version.getVersion_name());
		fileForm.setDescription(version.getDescription());
		return fileForm;
	}

	public List<FileForm> getFileFormsForDocuments(List<Document> documents){
		if(documents.isEmpty())
			return null;
		List<FileForm> forms = new ArrayList<FileForm>();
		for(Document d: documents){
			FileForm fileForm = new FileForm();
			fileForm.setDocumentId(d.getId());
			fileForm.setName(d.getName());
			Version v = versionDAO.findLastModifiedforDocument(d.getId());
			fileForm.setTitle(v.getTitle());
			fileForm.setContent(v.getContent());
			fileForm.setVersion(v.getVersion_name());
			fileForm.setDescription(v.getDescription());
			forms.add(fileForm);
		}
		return forms;
	}
}