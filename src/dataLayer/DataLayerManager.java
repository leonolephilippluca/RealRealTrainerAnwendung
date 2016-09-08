package dataLayer;


import dataAccessObjects.interfaces.ITrainerDao;
import dataLayer.Settings.SettingsManager;

@SuppressWarnings("unused")
public class DataLayerManager {
	private static DataLayerManager instance = null;
	
	private static IDataLayer dataLayer = null;
	
	private DataLayerManager(){
		
	}
	
	public static DataLayerManager getDataLayerManager(){
		if(instance == null){
			instance = new DataLayerManager();
		}
		return instance;
	}
	
	public static IDataLayer getDataLayer(){
		SettingsManager sm = SettingsManager.getInstance(); 
		if(dataLayer == null){
			//dataLayer = new DataLayer //TODO: Abhängig von Settings
		}
		return null;
	}
}
