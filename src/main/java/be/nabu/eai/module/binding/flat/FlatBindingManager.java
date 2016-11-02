package be.nabu.eai.module.binding.flat;

import be.nabu.eai.repository.api.Repository;
import be.nabu.eai.repository.managers.base.JAXBArtifactManager;
import be.nabu.libs.resources.api.ResourceContainer;
import be.nabu.libs.types.binding.flat.FlatBindingConfig;

public class FlatBindingManager extends JAXBArtifactManager<FlatBindingConfig, FlatBindingArtifact> {

	public FlatBindingManager() {
		super(FlatBindingArtifact.class);
	}

	@Override
	protected FlatBindingArtifact newInstance(String id, ResourceContainer<?> container, Repository repository) {
		return new FlatBindingArtifact(id, container, repository);
	}

}
