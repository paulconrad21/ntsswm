package data;

public class Athlete {
	//Enthält alle Infos die für einen Atleten hinterlegt werden müssen, sowie Getter & Setter
	private int koerpergroesse;
	private double gewicht;
	private int erfahrungInJahre;
	private double koerperfettanteil;
	private int ruhepuls;
	private int maxHerzfrequenz;
	private String trainingszustand; //ab hier nur noch kategorische Variablen
	private String geschlecht;
	private String ziel;
	private String sportart;
	private String regenerationsfaehigkeit;
	private String motivationsfaktor;
	private double similarity;
	
	public Athlete() {
		
	}
		
	
	public Athlete(int koerpergroesse, double gewicht, int erfahrungInJahre, double koerperfettanteil, int ruhepuls,
			int maxHerzfrequenz, String trainingszustand, String geschlecht, String ziel, String sportart,
			String regenerationsfähigkeit, String motivationsfaktor) {
		super();
		this.koerpergroesse = koerpergroesse;
		this.gewicht = gewicht;
		this.erfahrungInJahre = erfahrungInJahre;
		this.koerperfettanteil = koerperfettanteil;
		this.ruhepuls = ruhepuls;
		this.maxHerzfrequenz = maxHerzfrequenz;
		this.trainingszustand = trainingszustand;
		this.geschlecht = geschlecht;
		this.ziel = ziel;
		this.sportart = sportart;
		this.regenerationsfaehigkeit = regenerationsfähigkeit;
		this.motivationsfaktor = motivationsfaktor;
	}

	


	public double getSimilarity() {
		return similarity;
	}


	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}


	//Methoden
	public int getKoerpergroesse() {
		return koerpergroesse;
	}
	public double getGewicht() {
		return gewicht;
	}
	public int getErfahrungInJahre() {
		return erfahrungInJahre;
	}
	public double getKoerperfettanteil() {
		return koerperfettanteil;
	}
	public int getRuhepuls() {
		return ruhepuls;
	}
	public int getMaxHerzfrequenz() {
		return maxHerzfrequenz;
	}
	public String getTrainingszustand() {
		return trainingszustand;
	}
	public String getGeschlecht() {
		return geschlecht;
	}
	public String getZiel() {
		return ziel;
	}
	public String getSportart() {
		return sportart;
	}
	public String getRegenerationsfaehigkeit() {
		return regenerationsfaehigkeit;
	}
	public String getMotivationsfaktor() {
		return motivationsfaktor;
	}
	public void setKoerpergroesse(int koerpergroesse) {
		this.koerpergroesse = koerpergroesse;
	}
	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}
	public void setErfahrungInJahre(int erfahrungInJahre) {
		this.erfahrungInJahre = erfahrungInJahre;
	}
	public void setKoerperfettanteil(double koerperfettanteil) {
		this.koerperfettanteil = koerperfettanteil;
	}
	public void setRuhepuls(int ruhepuls) {
		this.ruhepuls = ruhepuls;
	}
	public void setMaxHerzfrequenz(int maxHerzfrequenz) {
		this.maxHerzfrequenz = maxHerzfrequenz;
	}
	public void setTrainingszustand(String trainingszustand) {
		this.trainingszustand = trainingszustand;
	}
	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}
	public void setZiel(String ziel) {
		this.ziel = ziel;
	}
	public void setSportart(String sportart) {
		this.sportart = sportart;
	}
	public void setRegenerationsfaehigkeit(String regenerationsfaehigkeit) {
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
	}
	public void setMotivationsfaktor(String motivationsfaktor) {
		this.motivationsfaktor = motivationsfaktor;
	}
	
	
	
}

