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
import data.Workout;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.similarity.Similarity;
import de.dfki.mycbr.util.Pair;

@WebServlet("/WorkoutServlet")
public class WorkoutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	//Workout Objekt zum abspeichern nach Post-Request
	Workout workout;
	
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
			//Daten parsen zu korrektem Datentyp
			int inputKoerpergroesseParsed = Integer.parseInt(inputKoerpergroesse);
			double inputKoerpergewichtParsed = Double.parseDouble(inputKoerpergewicht);
			int inputTrainingserfahrungParsed = Integer.parseInt(inputTrainingserfahrung);
			double inputKoerperfettanteilParsed = Double.parseDouble(inputKoerperfettanteil);
			int inputRuhepulsParsed = Integer.parseInt(inputRuhepuls);
			int inputMaxHerzfrequenzParsed = Integer.parseInt(inputMaxHerzfrequenz);
			int inputWorkoutsProWocheParsed = Integer.parseInt(inputWorkoutsProWoche);
			int inputTrainingszeitProSessionParsed = Integer.parseInt(inputTrainingszeitProSession);
			int inputUebungenProWorkoutParsed = Integer.parseInt(inputUebungenProWorkout);
			int inputWochenNachPlanParsed = Integer.parseInt(inputWochenNachPlan);
			boolean inputVerletzungenParsed;
			if(inputVerletzungen.equalsIgnoreCase("Ja")) {
				inputVerletzungenParsed = true;
			} else {
				inputVerletzungenParsed = false;
			}
			
			//Athleten anlegen
			Athlete queryAthlete = new Athlete(inputKoerpergroesseParsed,inputKoerpergewichtParsed,inputTrainingserfahrungParsed,
					inputKoerperfettanteilParsed,inputRuhepulsParsed,inputMaxHerzfrequenzParsed, inputTrainingszustand,
					inputGeschlecht,inputZiel,inputSportart,inputRegenerationsfaehigkeit,inputMotivationsfaktor);
			
			//Neuen CBR Agenten anlegen (Dabei CaseBases initialisieren)
			CbrAgent cbrAgent = new CbrAgent();		
		
			//Query lossenden für Athleten
			athletenResult = cbrAgent.startAthletenQuery(queryAthlete);
			
			resultingAthletes = cbrAgent.printAthlete(athletenResult, 3);
			
			//Erstellen eines Workout-Objekts und Frontend Daten und Athlete übergeben
			workout = new Workout(inputWorkoutsProWocheParsed,inputTrainingszeitProSessionParsed,inputUebungenProWorkoutParsed,
					inputWochenNachPlanParsed,inputVerletzungenParsed,inputVorliebeGeraet1,inputVorliebeGeraet2,inputHassGeraet1,inputHassGeraet2,
					inputVorhandeneGeraete,inputTrainingsmethode,inputZielmuskulatur,inputIntensitaet,resultingAthletes.get(0));
			
			//Query für Workout lossenden
			workoutResult = cbrAgent.startWorkoutQuery(workout);
			
			resultingWorkouts = cbrAgent.printWorkout(workoutResult, 3);
			
			//Ergebnis auswerten in Print funktion und ID des ähnlichsten Workouts abspeichern
			Workout mostSimilarWorkout = resultingWorkouts.get(0);
			
			//Aus Workouts.csv Workout basierend auf ID auslesen
			
			//Daten ans Frontend weiterschicken und weiterleiten auf extra Seite
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		//Angepasstes Workout entgegennehmen
		
		//Abspeichern als Workout in Workout.csv
		
		//Abspeichern der eingaben in workout casebase und verknüpfung zur workout.csv erstellen
		
	}
}
