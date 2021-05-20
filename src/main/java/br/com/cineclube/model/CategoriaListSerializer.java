package br.com.cineclube.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CategoriaListSerializer extends StdSerializer<Set<Categoria>> {
	private static final long serialVersionUID = 1L;
	public CategoriaListSerializer() {
		this(null);
	}
	public CategoriaListSerializer(Class<Set<Categoria>> t) {
		super(t);
	}
	@Override
	public void serialize(Set<Categoria> categorias, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Map<String, Object> categoriaMap = new HashMap<>();
		List<Map<String, Object>> categoriaList = new ArrayList<>();
		for (Categoria c : categorias) {
			categoriaMap = new HashMap<>();
			categoriaMap.put("id", c.getId());
			categoriaMap.put("nome", c.getNome());
			categoriaList.add(categoriaMap);
		}
		generator.writeObject(categoriaList);
	}
}