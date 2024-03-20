package data;

public class Ergebnis {
	private int id;
	private String uebung1;
	private String zeit1wdh1;
	private String uebung2;
	private String zeit2wdh2;
	private String uebung3;
	private String zeit3wdh3;
	private String uebung4;
	private String zeit4wdh4;
	private String uebung5;
	private String zeit5wdh5;
	private double pauseInMin;
	private String kategorie;
	
	
	public Ergebnis(int id, String uebung1, String zeit1wdh1, String uebung2, String zeit2wdh2, String uebung3,
			String zeit3wdh3, String uebung4, String zeit4wdh4, String uebung5, String zeit5wdh5, double pauseInMin, String kategorie) {
		super();
		this.id = id;
		this.uebung1 = uebung1;
		this.zeit1wdh1 = zeit1wdh1;
		this.uebung2 = uebung2;
		this.zeit2wdh2 = zeit2wdh2;
		this.uebung3 = uebung3;
		this.zeit3wdh3 = zeit3wdh3;
		this.uebung4 = uebung4;
		this.zeit4wdh4 = zeit4wdh4;
		this.uebung5 = uebung5;
		this.zeit5wdh5 = zeit5wdh5;
		this.pauseInMin = pauseInMin;
		this.kategorie = kategorie;
	}


	public int getId() {
		return id;
	}


	public String getUebung1() {
		return uebung1;
	}


	public String getZeit1wdh1() {
		return zeit1wdh1;
	}


	public String getUebung2() {
		return uebung2;
	}


	public String getZeit2wdh2() {
		return zeit2wdh2;
	}


	public String getUebung3() {
		return uebung3;
	}


	public String getZeit3wdh3() {
		return zeit3wdh3;
	}


	public String getUebung4() {
		return uebung4;
	}


	public String getZeit4wdh4() {
		return zeit4wdh4;
	}


	public String getUebung5() {
		return uebung5;
	}


	public String getZeit5wdh5() {
		return zeit5wdh5;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setUebung1(String uebung1) {
		this.uebung1 = uebung1;
	}


	public void setZeit1wdh1(String zeit1wdh1) {
		this.zeit1wdh1 = zeit1wdh1;
	}


	public void setUebung2(String uebung2) {
		this.uebung2 = uebung2;
	}


	public void setZeit2wdh2(String zeit2wdh2) {
		this.zeit2wdh2 = zeit2wdh2;
	}


	public void setUebung3(String uebung3) {
		this.uebung3 = uebung3;
	}


	public void setZeit3wdh3(String zeit3wdh3) {
		this.zeit3wdh3 = zeit3wdh3;
	}


	public void setUebung4(String uebung4) {
		this.uebung4 = uebung4;
	}


	public void setZeit4wdh4(String zeit4wdh4) {
		this.zeit4wdh4 = zeit4wdh4;
	}


	public void setUebung5(String uebung5) {
		this.uebung5 = uebung5;
	}


	public void setZeit5wdh5(String zeit5wdh5) {
		this.zeit5wdh5 = zeit5wdh5;
	}


	public double getPauseInMin() {
		return pauseInMin;
	}


	public String getKategorie() {
		return kategorie;
	}


	public void setPauseInMin(double pauseInMin) {
		this.pauseInMin = pauseInMin;
	}


	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}
	
	
	
	
}
