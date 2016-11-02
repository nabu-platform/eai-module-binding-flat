package be.nabu.eai.module.binding.flat;

import be.nabu.eai.repository.api.Repository;
import be.nabu.eai.repository.artifacts.jaxb.JAXBArtifact;
import be.nabu.libs.resources.api.ResourceContainer;
import be.nabu.libs.types.binding.flat.FlatBindingConfig;

public class FlatBindingArtifact extends JAXBArtifact<FlatBindingConfig> {

	public FlatBindingArtifact(String id, ResourceContainer<?> directory, Repository repository) {
		super(id, directory, repository, "flat-binding.xml", FlatBindingConfig.class);
	}

}
