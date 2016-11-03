package be.nabu.eai.module.binding.flat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.nabu.eai.developer.ComplexContentEditor.AddHandler;
import be.nabu.eai.developer.ComplexContentEditor.ValueWrapper;
import be.nabu.eai.developer.MainController;
import be.nabu.eai.developer.managers.base.BaseJAXBComplexGUIManager;
import be.nabu.eai.repository.resources.RepositoryEntry;
import be.nabu.jfx.control.tree.TreeItem;
import be.nabu.libs.property.api.Value;
import be.nabu.libs.types.api.ComplexContent;
import be.nabu.libs.types.binding.flat.FlatBindingConfig;
import be.nabu.libs.types.binding.flat.FlatBindingConfig.Field;
import be.nabu.libs.types.binding.flat.FlatBindingConfig.Record;
import be.nabu.libs.types.java.BeanInstance;

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

	@Override
	protected List<AddHandler> getAddHandlers() {
		List<AddHandler> handlers = new ArrayList<AddHandler>();
		handlers.add(new AddHandler() {
			@Override
			public String getName() {
				return "Add Record";
			}
			@Override
			public boolean affects(TreeItem<ValueWrapper> item) {
				return item.itemProperty().get().getElement().getName().equals("children");
			}
			@Override
			public ComplexContent newInstance(TreeItem<ValueWrapper> item) {
				return new BeanInstance<Record>(new Record());
			}
		});
		handlers.add(new AddHandler() {
			@Override
			public String getName() {
				return "Add Field";
			}
			@Override
			public boolean affects(TreeItem<ValueWrapper> item) {
				return item.itemProperty().get().getElement().getName().equals("children");
			}
			@Override
			public ComplexContent newInstance(TreeItem<ValueWrapper> item) {
				return new BeanInstance<Field>(new Field());
			}
		});
		return handlers;
	}
	
}
