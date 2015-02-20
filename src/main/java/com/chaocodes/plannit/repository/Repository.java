package com.chaocodes.plannit.repository;

import com.chaocodes.plannit.model.Model;

// Repository implementing CRUD operations for the various models. To be used by controllers to pass models to views
public interface Repository
{
	public void create(Model model);

	public Model read(int id);

	public void update(int id, Model model);

	public void delete(int id);
}
