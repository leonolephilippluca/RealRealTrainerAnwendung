package dataLayer.Settings;
import java.util.prefs.*;
public class SettingsManager {
	private static SettingsManager instance = null;
	private static PersistenceSettings persistenceSettingsInstance = null;
	
	private static final String persistanceValue = "USER_PERVAL";
	
	private SettingsManager(){
		loadUserSettings();
	}
	
	private void loadUserSettings() {
		// TODO: Hier User Settings laden
	}
	
	public static SettingsManager getInstance(){
		if(instance == null) {
			instance = new SettingsManager();
		}
		return instance;
	}
	
	public static PersistenceSettings getPersistenceSettings(){
		if(persistenceSettingsInstance == null) {
			persistenceSettingsInstance = new PersistenceSettings();
		}
		return persistenceSettingsInstance;
	}
}
