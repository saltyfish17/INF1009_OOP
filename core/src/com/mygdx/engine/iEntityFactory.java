package com.mygdx.engine;

public interface iEntityFactory<T extends ParentEntity, E extends EntityManager> {
	void createEntity(int i); // create i number of entities
}
