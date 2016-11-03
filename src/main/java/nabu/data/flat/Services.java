package nabu.data.flat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.validation.constraints.NotNull;

import be.nabu.eai.module.binding.flat.FlatBindingArtifact;
import be.nabu.eai.repository.EAIResourceRepository;
import be.nabu.libs.types.ComplexContentWrapperFactory;
import be.nabu.libs.types.api.ComplexContent;
import be.nabu.libs.types.binding.api.Window;
import be.nabu.libs.types.binding.flat.FlatBinding;

@WebService
public class Services {

	@WebResult(name = "unmarshalled")
	public Object unmarshal(@WebParam(name = "input") @NotNull InputStream input, @WebParam(name = "dictionary") @NotNull String dictionary, @WebParam(name = "record") String record, @WebParam(name = "charset") Charset charset, @WebParam(name = "windows") List<Window> windows) throws IOException, ParseException {
		FlatBindingArtifact artifact = (FlatBindingArtifact) EAIResourceRepository.getInstance().resolve(dictionary);
		FlatBinding binding = new FlatBinding(artifact.getConfig(), charset == null ? Charset.defaultCharset() : charset);
		if (record != null) {
			binding = binding.getNamedBinding(record);
		}
		return binding.unmarshal(input, windows == null || windows.isEmpty() ? new Window[0] : windows.toArray(new Window[windows.size()]));
	}
	
	@SuppressWarnings("unchecked")
	@WebResult(name = "marshalled")
	public InputStream marshal(@WebParam(name = "data") @NotNull Object data, @WebParam(name = "dictionary") @NotNull String dictionary, @WebParam(name = "record") String record, @WebParam(name = "charset") Charset charset) throws IOException {
		FlatBindingArtifact artifact = (FlatBindingArtifact) EAIResourceRepository.getInstance().resolve(dictionary);
		FlatBinding binding = new FlatBinding(artifact.getConfig(), charset == null ? Charset.defaultCharset() : charset);
		if (record != null) {
			binding = binding.getNamedBinding(record);
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		binding.marshal(output, data instanceof ComplexContent ? (ComplexContent) data : ComplexContentWrapperFactory.getInstance().getWrapper().wrap(data));
		return new ByteArrayInputStream(output.toByteArray());
	}
}
