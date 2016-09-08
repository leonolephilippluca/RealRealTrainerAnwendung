package dataAccessObjects.interfaces;

import java.util.List;

import businessObjects.ITrainer;



public interface ITrainerDao {
	
	/*
	 * Interface Trainer Data Object
	 */
	public ITrainer create();
	public void delete(ITrainer trainer);
	public ITrainer first();
	public ITrainer last();
	public ITrainer next(ITrainer trainer);
	public ITrainer previous(ITrainer trainer);
	public void save(ITrainer trainer);
	public void select(List<ITrainer> trainerList);
	public ITrainer select(int id); 
	
}
