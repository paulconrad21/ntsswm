package data;

public class Workout {
	//Enthält alle Infos die für ein Workout hinterlegt werden müssen, sowie Getter & Setter
	private int trainingszeitWoche;
	private int trainingszeitSession;
	private int uebungenProWorkout;	
	private int wochenNachPlan;
	private boolean verletzungen;
	private String vorliebeGerat1; //Ab hier nur noch kategorische variablen?
	private String vorliebeGeraet2;
	private String hassGeraet1;
	private String hassGeraet2;
	private String vorhandeneGeraete;
	private String trainingsmethode;
	private String zielmuskulatur;
	private String intensitaet;
	private Athlete athlete;
	private double similarity;
	private long id;
	
	
	
	public Workout(int trainingszeitWoche, int trainingszeitSession, int uebungenProWorkout, int wochenNachPlan,
			boolean verletzungen, String vorliebeGerat1, String vorliebeGeraet2, String hassGeraet1, String hassGeraet2,
			String vorhandeneGeraete, String trainingsmethode, String zielmuskulatur, String intensitaet, Athlete athlete) {
		super();
		this.trainingszeitWoche = trainingszeitWoche;
		this.trainingszeitSession = trainingszeitSession;
		this.uebungenProWorkout = uebungenProWorkout;
		this.wochenNachPlan = wochenNachPlan;
		this.verletzungen = verletzungen;
		this.vorliebeGerat1 = vorliebeGerat1;
		this.vorliebeGeraet2 = vorliebeGeraet2;
		this.hassGeraet1 = hassGeraet1;
		this.hassGeraet2 = hassGeraet2;
		this.vorhandeneGeraete = vorhandeneGeraete;
		this.trainingsmethode = trainingsmethode;
		this.zielmuskulatur = zielmuskulatur;
		this.intensitaet = intensitaet;
		this.athlete = athlete;	}
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public double getSimilarity() {
		return similarity;
	}



	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}



	public int getTrainingszeitWoche() {
		return trainingszeitWoche;
	}
	public int getTrainingszeitSession() {
		return trainingszeitSession;
	}
	public int getUebungenProWorkout() {
		return uebungenProWorkout;
	}
	public int getWochenNachPlan() {
		return wochenNachPlan;
	}
	public boolean isVerletzungen() {
		return verletzungen;
	}
	public String getVorliebeGerat1() {
		return vorliebeGerat1;
	}
	public String getVorliebeGeraet2() {
		return vorliebeGeraet2;
	}
	public String getHassGeraet1() {
		return hassGeraet1;
	}
	public String getHassGeraet2() {
		return hassGeraet2;
	}
	public String getVorhandeneGeraete() {
		return vorhandeneGeraete;
	}
	public String getTrainingsmethode() {
		return trainingsmethode;
	}
	public String getZielmuskulatur() {
		return zielmuskulatur;
	}
	public String getIntensitaet() {
		return intensitaet;
	}
	public void setTrainingszeitWoche(int trainingszeitWoche) {
		this.trainingszeitWoche = trainingszeitWoche;
	}
	public void setTrainingszeitSession(int trainingszeitSession) {
		this.trainingszeitSession = trainingszeitSession;
	}
	public void setUebungenProWorkout(int uebungenProWorkout) {
		this.uebungenProWorkout = uebungenProWorkout;
	}
	public void setWochenNachPlan(int wochenNachPlan) {
		this.wochenNachPlan = wochenNachPlan;
	}
	public void setVerletzungen(boolean verletzungen) {
		this.verletzungen = verletzungen;
	}
	public void setVorliebeGerat1(String vorliebeGerat1) {
		this.vorliebeGerat1 = vorliebeGerat1;
	}
	public void setVorliebeGeraet2(String vorliebeGeraet2) {
		this.vorliebeGeraet2 = vorliebeGeraet2;
	}
	public void setHassGeraet1(String hassGeraet1) {
		this.hassGeraet1 = hassGeraet1;
	}
	public void setHassGeraet2(String hassGeraet2) {
		this.hassGeraet2 = hassGeraet2;
	}
	public void setVorhandeneGeraete(String vorhandeneGeraete) {
		this.vorhandeneGeraete = vorhandeneGeraete;
	}
	public void setTrainingsmethode(String trainingsmethode) {
		this.trainingsmethode = trainingsmethode;
	}
	public void setZielmuskulatur(String zielmuskulatur) {
		this.zielmuskulatur = zielmuskulatur;
	}
	public void setIntensitaet(String intensitaet) {
		this.intensitaet = intensitaet;
	}
	public Athlete getAthlete() {
		return athlete;
	}
	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}
	
	
	
}
