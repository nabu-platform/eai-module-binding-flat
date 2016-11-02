package be.nabu.eai.module.binding.flat;

import java.io.IOException;

import be.nabu.eai.developer.MainController;
import be.nabu.eai.developer.managers.base.BaseJAXBComplexGUIManager;
import be.nabu.eai.repository.resources.RepositoryEntry;
import be.nabu.libs.property.api.Value;
import be.nabu.libs.types.binding.flat.FlatBindingConfig;

public class FlatBindingGUIManager extends BaseJAXBComplexGUIManager<FlatBindingConfig, FlatBindingArtifact> {

	public FlatBindingGUIManager() {
		super("Flat Dictionary", FlatBindingArtifact.class, new FlatBindingManager(), FlatBindingConfig.class);
	}

	@Override
	protected FlatBindingArtifact newInstance(MainController controller, RepositoryEntry entry, Value<?>... values) throws IOException {
		return new FlatBindingArtifact(entry.getId(), entry.getContainer(), entry.getRepository());
	}

	@Override
	public String getCategory() {
		return "Data";
	}
}
