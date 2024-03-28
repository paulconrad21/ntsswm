package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cbr.CbrAgent;
import data.Athlete;
import data.Ergebnis;
import data.Workout;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.similarity.Similarity;
import de.dfki.mycbr.util.Pair;

@WebServlet("/WorkoutServlet")
public class WorkoutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	//Workout Objekt zum abspeichern nach Post-Request
	Workout workout;
	
	CbrAgent cbrAgent;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Von hier läuft die Kommunikation zum Frontend ab
		//Zudem: Interagieren mit MyCBR		
		List<Pair<Instance, Similarity>> athletenResult;
		ArrayList<Athlete> resultingAthletes; 
		
		List<Pair<Instance, Similarity>> workoutResult;
		ArrayList<Workout> resultingWorkouts; 
		
		//1. Daten aus dem Frontend einlesen
		String inputKoerpergroesse = request.getParameter("koerpergroesse");
		String inputKoerpergewicht = request.getParameter("koepergewicht");
		String inputTrainingserfahrung = request.getParameter("trainingserfahrung");
		String inputKoerperfettanteil = request.getParameter("koerperfettanteil");
		String inputRuhepuls = request.getParameter("ruhepuls");
		String inputMaxHerzfrequenz = request.getParameter("maxherzfrequenz");
		String inputWorkoutsProWoche = request.getParameter("trainingszeitWoche");
		String inputTrainingszeitProSession = request.getParameter("trainingszeitSession");
		String inputUebungenProWorkout = request.getParameter("uebungenProWorkout");
		String inputWochenNachPlan = request.getParameter("wochenNachPlan");
		String inputVerletzungen = request.getParameter("verletzungen");
		
		String inputTrainingszustand = request.getParameter("trainingszustand");
		String inputGeschlecht = request.getParameter("geschlecht");
		String inputZiel = request.getParameter("ziel");
		String inputSportart = request.getParameter("sportart");
		String inputRegenerationsfaehigkeit = request.getParameter("regenerationsfaehigkeit");
		String inputMotivationsfaktor = request.getParameter("motivationsfaktor");
		String inputVorliebeGeraet1 = request.getParameter("vorliebeGerat1");
		String inputVorliebeGeraet2 = request.getParameter("vorliebeGerat2");
		String inputHassGeraet1 = request.getParameter("hassGeraet1");
		String inputHassGeraet2 = request.getParameter("hassGeraet2");
		String inputVorhandeneGeraete = request.getParameter("vorhandeneGeraete");
		String inputTrainingsmethode = request.getParameter("trainingsmethode");
		String inputZielmuskulatur = request.getParameter("zielmuskulatur");
		String inputIntensitaet = request.getParameter("intensitaet");
		
		

		try {
			
			int inputKoerpergroesseParsed = 0;
			double inputKoerpergewichtParsed = 0;
			int inputTrainingserfahrungParsed = 0;
			double inputKoerperfettanteilParsed = 0;
			int inputRuhepulsParsed = 0;
			int inputMaxHerzfrequenzParsed = 0;
			int inputWorkoutsProWocheParsed = 0;
			int inputTrainingszeitProSessionParsed = 0;
			int inputUebungenProWorkoutParsed = 0;
			int inputWochenNachPlanParsed = 0;
			boolean inputVerletzungenParsed = false;
			
			try {
			//Daten parsen zu korrektem Datentyp
			inputKoerpergroesseParsed = Integer.parseInt(inputKoerpergroesse);
			inputKoerpergewichtParsed = Double.parseDouble(inputKoerpergewicht);
			inputTrainingserfahrungParsed = Integer.parseInt(inputTrainingserfahrung);
			inputKoerperfettanteilParsed = Double.parseDouble(inputKoerperfettanteil);
			inputRuhepulsParsed = Integer.parseInt(inputRuhepuls);
			inputMaxHerzfrequenzParsed = Integer.parseInt(inputMaxHerzfrequenz);
			inputWorkoutsProWocheParsed = Integer.parseInt(inputWorkoutsProWoche);
			inputTrainingszeitProSessionParsed = Integer.parseInt(inputTrainingszeitProSession);
			inputUebungenProWorkoutParsed = Integer.parseInt(inputUebungenProWorkout);
			inputWochenNachPlanParsed = Integer.parseInt(inputWochenNachPlan);
			if(inputVerletzungen.equalsIgnoreCase("Ja")) {
				inputVerletzungenParsed = true;
			} else {
				inputVerletzungenParsed = false;
			}
			} catch(Exception e) {
				System.out.println("Fehler beim Parsen der Eingaben");
				e.printStackTrace();
			}
			//Athleten anlegen
			Athlete queryAthlete = new Athlete(inputKoerpergroesseParsed,inputKoerpergewichtParsed,inputTrainingserfahrungParsed,
					inputKoerperfettanteilParsed,inputRuhepulsParsed,inputMaxHerzfrequenzParsed, inputTrainingszustand,
					inputGeschlecht,inputZiel,inputSportart,inputRegenerationsfaehigkeit,inputMotivationsfaktor,"");
			
			//Neuen CBR Agenten anlegen (Dabei CaseBases initialisieren)
			cbrAgent = new CbrAgent();		
			System.out.println("Größe der Athleten CaseBase: " + cbrAgent.getAthletenCaseBaseSize());
			System.out.println("Größe der Workout CaseBase: " + cbrAgent.getWorkoutCaseBaseSize());
			//Query lossenden für Athleten
			athletenResult = cbrAgent.startAthletenQuery(queryAthlete);
			
			resultingAthletes = cbrAgent.printAthlete(athletenResult, 1);
			
			//Erstellen eines Workout-Objekts und Frontend Daten und Athlete übergeben
			workout = new Workout(inputWorkoutsProWocheParsed,inputTrainingszeitProSessionParsed,inputUebungenProWorkoutParsed,
				inputWochenNachPlanParsed,inputVerletzungenParsed,inputVorliebeGeraet1,inputVorliebeGeraet2,inputHassGeraet1,inputHassGeraet2,
				inputVorhandeneGeraete,inputTrainingsmethode,inputZielmuskulatur,inputIntensitaet,resultingAthletes.get(0).getAthletenBezeichnung());
			  
			//Query für Workout lossenden
			workoutResult = cbrAgent.startWorkoutQuery(workout);
			
			resultingWorkouts = cbrAgent.printWorkout(workoutResult, 1);
			
			//Ergebnis auswerten in Print funktion und ID des ähnlichsten Workouts abspeichern
			Workout mostSimilarWorkout = resultingWorkouts.get(0);
			
			//Aus Workouts.csv Workout basierend auf ID auslesen
			Ergebnis workoutErgebnis = cbrAgent.getWorkout(mostSimilarWorkout.getId());
			
			
			//Daten im request hinterlegen und weiterleiten auf extra Seite
			request.setAttribute("uebung1", workoutErgebnis.getUebung1());
		    request.setAttribute("uebung2", workoutErgebnis.getUebung2());
			request.setAttribute("uebung3", workoutErgebnis.getUebung3());
			request.setAttribute("uebung4", workoutErgebnis.getUebung4());
			request.setAttribute("uebung5", workoutErgebnis.getUebung5());
			request.setAttribute("zeit1wdh1", workoutErgebnis.getZeit1wdh1());
			request.setAttribute("zeit2wdh2", workoutErgebnis.getZeit2wdh2());
			request.setAttribute("zeit3wdh3", workoutErgebnis.getZeit3wdh3());
			request.setAttribute("zeit4wdh4", workoutErgebnis.getZeit4wdh4());
			request.setAttribute("zeit5wdh5", workoutErgebnis.getZeit5wdh5());
			request.setAttribute("pauseInMin", workoutErgebnis.getPauseInMin());
			request.setAttribute("kategorie", workoutErgebnis.getKategorie());
			
					
			
			request.getRequestDispatcher("result.jsp").forward(request, response);

			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		//Angepasstes Workout entgegennehmen
		String inputUebung1 = request.getParameter("uebung1");
		String inputZeit1wdh1 = request.getParameter("zeit1wdh1");
		String inputUebung2 = request.getParameter("uebung2");
		String inputZeit2wdh2 = request.getParameter("zeit2wdh2");
		String inputUebung3 = request.getParameter("uebung3");
		String inputZeit3wdh3 = request.getParameter("zeit3wdh3");
		String inputUebung4 = request.getParameter("uebung4");
		String inputZeit4wdh4 = request.getParameter("zeit4wdh4");
		String inputUebung5 = request.getParameter("uebung5");
		String inputZeit5wdh5 = request.getParameter("zeit5wdh5");
		String inputPauseInMin = request.getParameter("pauseInMin");
		String inputKategorie = request.getParameter("kategorie");
		
		//id abfragen
		int newId = cbrAgent.getWorkoutCaseBaseSize() + 1;
		
		//Abspeichern als Workout in Workout.csv
		Ergebnis ergebnis = new Ergebnis(newId, inputUebung1, inputZeit1wdh1, inputUebung2, inputZeit2wdh2, inputUebung3, inputZeit3wdh3,
					inputUebung4, inputZeit4wdh4, inputUebung5, inputZeit5wdh5, Double.parseDouble(inputPauseInMin), inputKategorie);
		
		cbrAgent.saveWorkout(ergebnis);
		
		//Abspeichern der eingaben in workout casebase und verknüpfung zur workout.csv erstellen
		workout.setId(newId);
		cbrAgent.saveWorkoutInCaseBase(workout);
		
	}
}
