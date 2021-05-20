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

public class FilmeListSerializer extends StdSerializer<Set<Filme>> {
	private static final long serialVersionUID = 1L;
	public FilmeListSerializer() {
		this(null);
	}
	public FilmeListSerializer(Class<Set<Filme>> t) {
		super(t);
	}
	@Override
	public void serialize(Set<Filme> filmes, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Map<String, Object> filmeMap = new HashMap<>();
		List<Map<String, Object>> filmeList = new ArrayList<>();
		for (Filme f : filmes) {
			filmeMap = new HashMap<>();
			filmeMap.put("id", f.getId());
			filmeMap.put("nome", f.getNome());
			filmeList.add(filmeMap);
		}
		generator.writeObject(filmeList);
	}
//	@Override
	public void serialize_simples_exemplo(Set<Filme> filmes, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		List<String> listaSerializada = new ArrayList<>();
		for (Filme f : filmes)
			listaSerializada.add(f.getNome());
		generator.writeObject(listaSerializada);
	}
}
