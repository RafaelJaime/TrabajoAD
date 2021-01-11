package com.medicos.interfaceService;

import java.util.List;
import java.util.Optional;

import com.medicos.model.Medico;

public interface IMedicoService {
	 public List<Medico>list();
	 public Optional<Medico>listId(int id);
	 public int save(Medico m);
	 public void delete(int id);
}
