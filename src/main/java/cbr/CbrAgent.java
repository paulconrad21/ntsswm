package cbr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import data.Athlete;
import data.Ergebnis;
import data.Workout;
import de.dfki.mycbr.core.ICaseBase;
import de.dfki.mycbr.core.Project;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.BooleanDesc;
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.core.model.DoubleDesc;
import de.dfki.mycbr.core.model.IntegerDesc;
import de.dfki.mycbr.core.model.StringDesc;
import de.dfki.mycbr.core.model.SymbolDesc;
import de.dfki.mycbr.core.retrieval.Retrieval;
import de.dfki.mycbr.core.retrieval.Retrieval.RetrievalMethod;
import de.dfki.mycbr.core.similarity.AmalgamationFct;
import de.dfki.mycbr.core.similarity.DoubleFct;
import de.dfki.mycbr.core.similarity.IntegerFct;
import de.dfki.mycbr.core.similarity.Similarity;
import de.dfki.mycbr.core.similarity.SymbolFct;
import de.dfki.mycbr.core.similarity.config.AmalgamationConfig;
import de.dfki.mycbr.core.similarity.config.NumberConfig;
import de.dfki.mycbr.core.similarity.config.StringConfig;
import de.dfki.mycbr.util.Pair;

public class CbrAgent {
	//Enthält alle Funktionen für die Arbeit mit MyCBR.
	//Initialisieren der CaseBases
	//Laden der Cases aus CSV Dateien
	//Durchführen der Query
	//Diese Klasse wird aus dem Servlet aus aufgerufen
	
	
	//Attribute für CBR
	private Project project;
	private Concept athletenConcept;
	private Concept workoutConcept;
	private ICaseBase athletenCaseBase;
	private ICaseBase workoutCaseBase;
	private AmalgamationFct athletenGlobalSim;
	private AmalgamationFct workoutGlobalSim;
	private Retrieval athletenRetrieve;
	private Retrieval workoutRetrieve;
	
	//Attribute für Athleten
	private IntegerDesc koerpergroesseDesc;
	private DoubleDesc gewichtDesc;
	private IntegerDesc erfahrungInJahreDesc;
	private DoubleDesc koerperfettanteilDesc;
	private IntegerDesc ruhepulsDesc;
	private IntegerDesc maxHerfrequenzDesc;
	private SymbolDesc trainingszustandDesc;
	private SymbolDesc geschlechtDesc;
	private SymbolDesc zielDesc;
	private SymbolDesc sportartDesc;
	private SymbolDesc regenerationsfaehigkeitDesc;
	private SymbolDesc motivationsfaktorDesc;	
	private StringDesc athletenBezeichnungDesc;
	
	
	//Attribute für Workout
	private IntegerDesc trainingszeitWocheDesc;
	private IntegerDesc trainingszeitSessionDesc;
	private IntegerDesc uebungenProWorkoutDesc;	
	private IntegerDesc wochenNachPlanDesc;
	private BooleanDesc verletzungenDesc;
	private SymbolDesc vorliebeGeraet1Desc;
	private SymbolDesc vorliebeGeraet2Desc;
	private SymbolDesc hassGeraet1Desc;
	private SymbolDesc hassGeraet2Desc;
	private SymbolDesc vorhandeneGeraeteDesc;
	private SymbolDesc trainingsmethodeDesc;
	private SymbolDesc zielmuskulaturDesc;
	private SymbolDesc intensitaetDesc;
	private StringDesc athleteDesc;
	
	
	
	
	//Konstruktor:
	public CbrAgent() {
		
		initProject();
		
		initAthletenConcept();
		initWorkoutConcept();
		
		initAthletenCaseBase();
		initWorkoutCaseBase();
		
	}
	
	private void initProject() {
		try {
			project = new Project();
			project.setName("Workout Planner");
			project.setAuthor("Jegor, Paul");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void initAthletenConcept() {
		try {
			athletenConcept = project.createTopConcept("Athleten");
			athletenGlobalSim = athletenConcept.addAmalgamationFct(AmalgamationConfig.WEIGHTED_SUM, "athletenSimFct", true);
			
			//INTEGER:
			koerpergroesseDesc = new IntegerDesc(athletenConcept, "koerpergroesse", 0, 250);
			IntegerFct koerpergroesseFct = koerpergroesseDesc.addIntegerFct("koerpergroesseFct", true);
			koerpergroesseFct.setFunctionTypeL(NumberConfig.POLYNOMIAL_WITH);
			koerpergroesseFct.setFunctionTypeR(NumberConfig.POLYNOMIAL_WITH);
			koerpergroesseFct.setFunctionParameterR(2);
			koerpergroesseFct.setFunctionParameterL(2);
			athletenGlobalSim.setWeight("koerpergroesse", 2);
			
			erfahrungInJahreDesc = new IntegerDesc(athletenConcept, "erfahrungInJahre", 0, 100);
			IntegerFct erfahrungInJahreFct = erfahrungInJahreDesc.addIntegerFct("erfahrungInJahrFct", true);
			erfahrungInJahreFct.setFunctionTypeL(NumberConfig.POLYNOMIAL_WITH);
			erfahrungInJahreFct.setFunctionTypeR(NumberConfig.POLYNOMIAL_WITH);
			erfahrungInJahreFct.setFunctionParameterR(2);
			erfahrungInJahreFct.setFunctionParameterL(2);
			athletenGlobalSim.setWeight("erfahrungInJahre", 2);
			
			ruhepulsDesc = new IntegerDesc(athletenConcept, "ruhepuls", 0, 200);
			IntegerFct ruhepulsFct = ruhepulsDesc.addIntegerFct("ruhepulsFct", true);
			ruhepulsFct.setFunctionTypeL(NumberConfig.POLYNOMIAL_WITH);
			ruhepulsFct.setFunctionTypeR(NumberConfig.POLYNOMIAL_WITH);
			ruhepulsFct.setFunctionParameterR(2);
			ruhepulsFct.setFunctionParameterL(2);
			athletenGlobalSim.setWeight("ruhepuls", 2);
			
			maxHerfrequenzDesc = new IntegerDesc(athletenConcept, "maxHerzfrequenz", 50, 250);
			IntegerFct maxHerzfrequenzFct = maxHerfrequenzDesc.addIntegerFct("maxHerzfrequenzFct", true);
			maxHerzfrequenzFct.setFunctionTypeL(NumberConfig.POLYNOMIAL_WITH);
			maxHerzfrequenzFct.setFunctionTypeR(NumberConfig.POLYNOMIAL_WITH);
			maxHerzfrequenzFct.setFunctionParameterR(2);
			maxHerzfrequenzFct.setFunctionParameterL(2);
			athletenGlobalSim.setWeight("maxHerzfrequenz", 2);
			
			
			//DOUBLE:
			gewichtDesc = new DoubleDesc(athletenConcept, "gewicht", 0, 500);
			DoubleFct gewichtFct = gewichtDesc.addDoubleFct("gewichtFct", true);			
			gewichtFct.setFunctionTypeL(NumberConfig.POLYNOMIAL_WITH);
			gewichtFct.setFunctionTypeR(NumberConfig.POLYNOMIAL_WITH);
			gewichtFct.setFunctionParameterR(2);
			gewichtFct.setFunctionParameterL(2);
			athletenGlobalSim.setWeight("gewicht", 2);
			
			koerperfettanteilDesc = new DoubleDesc(athletenConcept, "koerperfettanteil", 0, 100);
			DoubleFct koerperfettanteilFct = koerperfettanteilDesc.addDoubleFct("koerperfettanteilFct", true);			
			koerperfettanteilFct.setFunctionTypeL(NumberConfig.POLYNOMIAL_WITH);
			koerperfettanteilFct.setFunctionTypeR(NumberConfig.POLYNOMIAL_WITH);
			koerperfettanteilFct.setFunctionParameterR(2);
			koerperfettanteilFct.setFunctionParameterL(2);
			athletenGlobalSim.setWeight("koerperfettanteil", 2);
			
			
			//SYMBOL:
			trainingszustandDesc = new SymbolDesc(athletenConcept, "trainingszustand", null);
			trainingszustandDesc.addSymbol("Gut");
			trainingszustandDesc.addSymbol("Mittelmaeßig");
			trainingszustandDesc.addSymbol("Schlecht");
			SymbolFct trainingszustandFct = trainingszustandDesc.addSymbolFct("trainingszustandFct", true);
			trainingszustandFct.setSimilarity("Gut", "Mittelmaeßig", 0.40);
			trainingszustandFct.setSimilarity("Gut", "Schlecht", 0.20);
			trainingszustandFct.setSimilarity("Mittelmaeßig", "Gut", 0.40);
			trainingszustandFct.setSimilarity("Mittelmaeßig", "Schlecht", 0.40);
			trainingszustandFct.setSimilarity("Schlecht", "Mittelmaeßig", 0.40);
			trainingszustandFct.setSimilarity("Schlecht", "Gut", 0.20);
			athletenGlobalSim.setWeight("trainingszustand", 2);
						
			geschlechtDesc = new SymbolDesc(athletenConcept, "geschlecht", null);
			geschlechtDesc.addSymbol("Mann");
			geschlechtDesc.addSymbol("Frau");
			SymbolFct geschlechtFct = geschlechtDesc.addSymbolFct("geschlechtFct", true);
			geschlechtFct.setSimilarity("Mann", "Frau", 0.20);
			geschlechtFct.setSimilarity("Frau", "Mann", 0.20);			
			athletenGlobalSim.setWeight("geschlecht", 2);
			
			zielDesc = new SymbolDesc(athletenConcept, "ziel", null);
			zielDesc.addSymbol("Muskelaufbau");
			zielDesc.addSymbol("Fettabbau");
			zielDesc.addSymbol("Ausdauer verbessern");
			SymbolFct zielFct = trainingszustandDesc.addSymbolFct("zielFct", true);
			zielFct.setSimilarity("Muskelaufbau", "Ausdauer verbessern", 0.30);
			zielFct.setSimilarity("Muskelaufbau", "Fettabbau", 0.40);
			zielFct.setSimilarity("Fettabbau", "Ausdauer verbessern", 0.60);
			zielFct.setSimilarity("Fettabbau", "Muskelaufbau", 0.40);
			zielFct.setSimilarity("Ausdauer verbessern", "Fettabbau", 0.60);
			zielFct.setSimilarity("Ausdauer verbessern", "Muskelaufbau", 0.30);
			athletenGlobalSim.setWeight("ziel", 2);
			
			sportartDesc = new SymbolDesc(athletenConcept, "sportart", null);
			sportartDesc.addSymbol("Krafttraining");
			sportartDesc.addSymbol("Laufen");
			sportartDesc.addSymbol("Fahrrad");
			SymbolFct sportartFct = trainingszustandDesc.addSymbolFct("sportartFct", true);
			sportartFct.setSimilarity("Krafttraining", "Fahrrad", 0.20);
			sportartFct.setSimilarity("Krafttraining", "Laufen", 0.20);
			sportartFct.setSimilarity("Laufen", "Fahrrad", 0.60);
			sportartFct.setSimilarity("Laufen", "Krafttraining", 0.20);
			sportartFct.setSimilarity("Fahrrad", "Krafttraining", 0.20);
			sportartFct.setSimilarity("Fahrrad", "Laufen", 0.60);
			athletenGlobalSim.setWeight("sportart", 2);
						
			regenerationsfaehigkeitDesc = new SymbolDesc(athletenConcept, "regenerationsfaehigkeit", null);
			regenerationsfaehigkeitDesc.addSymbol("Sehr gut");
			regenerationsfaehigkeitDesc.addSymbol("Gut");
			regenerationsfaehigkeitDesc.addSymbol("Durchschnittlich");
			regenerationsfaehigkeitDesc.addSymbol("Unterdurchschnittlich");
			SymbolFct regenerationsfaehigkeitFct = trainingszustandDesc.addSymbolFct("regenerationsfaehigkeitFct", true);
			regenerationsfaehigkeitFct.setSimilarity("Sehr gut", "Gut", 0.60);
			regenerationsfaehigkeitFct.setSimilarity("Sehr gut", "Durchschnittlich", 0.40);
			regenerationsfaehigkeitFct.setSimilarity("Sehr gut", "Unterdurchschnittlich", 0.10);
			regenerationsfaehigkeitFct.setSimilarity("Gut", "Sehr gut", 0.60);
			regenerationsfaehigkeitFct.setSimilarity("Gut", "Durchschnittlich", 0.40);
			regenerationsfaehigkeitFct.setSimilarity("Gut", "Unterdurchschnittlich", 0.20);
			regenerationsfaehigkeitFct.setSimilarity("Durchschnittlich", "Sehr gut", 0.40);
			regenerationsfaehigkeitFct.setSimilarity("Durchschnittlich", "Gut", 0.40);
			regenerationsfaehigkeitFct.setSimilarity("Durchschnittlich", "Unterdurchschnittlich", 0.60);
			regenerationsfaehigkeitFct.setSimilarity("Unterdurchschnittlich", "Sehr gut", 0.10);
			regenerationsfaehigkeitFct.setSimilarity("Unterdurchschnittlich", "Gut", 0.20);
			regenerationsfaehigkeitFct.setSimilarity("Unterdurchschnittlich", "Durchschnittlich", 0.60);
			athletenGlobalSim.setWeight("regenerationsfaehigkeit", 2);
			
			motivationsfaktorDesc = new SymbolDesc(athletenConcept, "motivationsfaktor", null);
			motivationsfaktorDesc.addSymbol("Sehr hoch");
			motivationsfaktorDesc.addSymbol("Hoch");
			motivationsfaktorDesc.addSymbol("Durchschnittlich");
			motivationsfaktorDesc.addSymbol("Unterdurchschnittlich");
			SymbolFct motivationsfaktorFct = trainingszustandDesc.addSymbolFct("motivationsfaktorFct", true);
			motivationsfaktorFct.setSimilarity("Sehr hoch", "Hoch", 0.60);
			motivationsfaktorFct.setSimilarity("Sehr hoch", "Durchschnittlich", 0.40);
			motivationsfaktorFct.setSimilarity("Sehr hoch", "Unterdurchschnittlich", 0.10);
			motivationsfaktorFct.setSimilarity("Hoch", "Sehr hoch", 0.60);
			motivationsfaktorFct.setSimilarity("Hoch", "Durchschnittlich", 0.40);
			motivationsfaktorFct.setSimilarity("Hoch", "Unterdurchschnittlich", 0.20);
			motivationsfaktorFct.setSimilarity("Durchschnittlich", "Sehr hoch", 0.40);
			motivationsfaktorFct.setSimilarity("Durchschnittlich", "Hoch", 0.40);
			motivationsfaktorFct.setSimilarity("Durchschnittlich", "Unterdurchschnittlich", 0.60);
			motivationsfaktorFct.setSimilarity("Unterdurchschnittlich", "Sehr hoch", 0.10);
			motivationsfaktorFct.setSimilarity("Unterdurchschnittlich", "Hoch", 0.20);
			motivationsfaktorFct.setSimilarity("Unterdurchschnittlich", "Durchschnittlich", 0.60);
			athletenGlobalSim.setWeight("motivationsfaktor", 2);	
			
			//String
			athletenBezeichnungDesc = new StringDesc(athletenConcept, "AthleteBezeichnung");
			athletenBezeichnungDesc.addStringFct(StringConfig.LEVENSHTEIN, "athleteBezeichnungFct", true);
			workoutGlobalSim.setWeight("AthleteBezeichnung", 2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initWorkoutConcept() {
		try {
            workoutConcept = project.createTopConcept("Workout");
            workoutGlobalSim = workoutConcept.addAmalgamationFct(AmalgamationConfig.WEIGHTED_SUM, "workoutSimFct", true);
            
            //INTEGER:
            trainingszeitWocheDesc = new IntegerDesc(workoutConcept, "trainingsZeitProWoche", 1, 25);
            IntegerFct trainingszeitWocheFct = trainingszeitWocheDesc.addIntegerFct("trainingszeitWocheFct", true);
            trainingszeitWocheFct.setFunctionTypeL(NumberConfig.POLYNOMIAL_WITH);
            trainingszeitWocheFct.setFunctionTypeR(NumberConfig.POLYNOMIAL_WITH);
            trainingszeitWocheFct.setFunctionParameterR(2);
            trainingszeitWocheFct.setFunctionParameterL(2);
            workoutGlobalSim.setWeight("trainingsZeitProWoche", 2);
            
            trainingszeitSessionDesc = new IntegerDesc(workoutConcept, "trainingszeitProWorkout", 1, 300);
            IntegerFct trainingszeitSessionFct = trainingszeitSessionDesc.addIntegerFct("trainingszeitSessionFct", true);
            trainingszeitSessionFct.setFunctionTypeL(NumberConfig.POLYNOMIAL_WITH);
            trainingszeitSessionFct.setFunctionTypeR(NumberConfig.POLYNOMIAL_WITH);
            trainingszeitSessionFct.setFunctionParameterR(2);
            trainingszeitSessionFct.setFunctionParameterL(2);
            workoutGlobalSim.setWeight("trainingszeitProWorkout", 2);
            
            uebungenProWorkoutDesc = new IntegerDesc(workoutConcept, "uebungenProWorkout", 1, 10);
            IntegerFct uebungenProWorkoutFct = uebungenProWorkoutDesc.addIntegerFct("uebungenProWorkoutFct", true);
            uebungenProWorkoutFct.setFunctionTypeL(NumberConfig.POLYNOMIAL_WITH);
            uebungenProWorkoutFct.setFunctionTypeR(NumberConfig.POLYNOMIAL_WITH);
            uebungenProWorkoutFct.setFunctionParameterR(2);
            uebungenProWorkoutFct.setFunctionParameterL(2);
            workoutGlobalSim.setWeight("uebungenProWorkout", 2);
            
            wochenNachPlanDesc = new IntegerDesc(workoutConcept, "wochenNachPlan", 1, 20);
            IntegerFct wochenNachPlanFct = wochenNachPlanDesc.addIntegerFct("wochenNachPlanFct", true);
            wochenNachPlanFct.setFunctionTypeL(NumberConfig.POLYNOMIAL_WITH);
            wochenNachPlanFct.setFunctionTypeR(NumberConfig.POLYNOMIAL_WITH);
            wochenNachPlanFct.setFunctionParameterR(2);
            wochenNachPlanFct.setFunctionParameterL(2);
            workoutGlobalSim.setWeight("wochenNachPlan", 2);
            
                              
            
            //BOOLEAN
            verletzungenDesc = new BooleanDesc(workoutConcept, "verletzungen");
            verletzungenDesc.addBooleanFct("verletzungenDesc", true);
            workoutGlobalSim.setWeight("verletzungen", 1);
            
            //SYMBOL:
            vorliebeGeraet1Desc = new SymbolDesc(workoutConcept, "vorliebeGeraet1", null);
            vorliebeGeraet1Desc.addSymbol("Brustpresse");
            vorliebeGeraet1Desc.addSymbol("Latzug");
            vorliebeGeraet1Desc.addSymbol("Rudern");
            SymbolFct vorliebeGeraet1Fct = vorliebeGeraet1Desc.addSymbolFct("vorliebeGeraet1Fct", true);
            vorliebeGeraet1Fct.setSimilarity("Brustpresse", "Latzug", 0.50);
            vorliebeGeraet1Fct.setSimilarity("Brustpresse", "Rudern", 0.50);
            vorliebeGeraet1Fct.setSimilarity("Latzug", "Rudern", 0.20);
            vorliebeGeraet1Fct.setSimilarity("Latzugg", "Brustpresse", 0.50);
            vorliebeGeraet1Fct.setSimilarity("Rudern", "Latzug", 0.20);
            vorliebeGeraet1Fct.setSimilarity("Rudern", "Brustpresse", 0.50);
            workoutGlobalSim.setWeight("vorliebeGeraet1", 2);
                        
            vorliebeGeraet2Desc = new SymbolDesc(workoutConcept, "vorliebeGeraet2", null);
            vorliebeGeraet2Desc.addSymbol("Beinpresse");
            vorliebeGeraet2Desc.addSymbol("Beinbizeps");
            vorliebeGeraet2Desc.addSymbol("Quadrizeps");
            SymbolFct vorliebeGeraet2Fct = vorliebeGeraet2Desc.addSymbolFct("vorliebeGeraet2Fct", true);
            vorliebeGeraet2Fct.setSimilarity("Beinpresse", "Beinbizeps", 0.20);
            vorliebeGeraet2Fct.setSimilarity("Beinpresse", "Quadrizeps", 0.20);
            vorliebeGeraet2Fct.setSimilarity("Beinbizeps", "Beinpresse", 0.20);
            vorliebeGeraet2Fct.setSimilarity("Beinbizeps", "Quadrizeps", 0.40);
            vorliebeGeraet2Fct.setSimilarity("Quadrizeps", "Beinpresse", 0.20);
            vorliebeGeraet2Fct.setSimilarity("Quadrizeps", "Beinbizeps", 0.40);
            workoutGlobalSim.setWeight("vorliebeGeraet2", 2);
            
            hassGeraet1Desc = new SymbolDesc(workoutConcept, "hassGeraet1", null);
            hassGeraet1Desc.addSymbol("Brustpresse");
            hassGeraet1Desc.addSymbol("Latzug");
            hassGeraet1Desc.addSymbol("Rudern");
            SymbolFct hassGeraet1Fct = hassGeraet1Desc.addSymbolFct("hassGeraet1Fct", true);
            hassGeraet1Fct.setSimilarity("Brustpresse", "Latzug", 0.50);
            hassGeraet1Fct.setSimilarity("Brustpresse", "Rudern", 0.50);
            hassGeraet1Fct.setSimilarity("Latzug", "Rudern", 0.20);
            hassGeraet1Fct.setSimilarity("Latzugg", "Brustpresse", 0.50);
            hassGeraet1Fct.setSimilarity("Rudern", "Latzug", 0.20);
            hassGeraet1Fct.setSimilarity("Rudern", "Brustpresse", 0.50);
            workoutGlobalSim.setWeight("hassGeraet1", 2);
            
            hassGeraet2Desc = new SymbolDesc(workoutConcept, "hassGeraet2", null);
            hassGeraet2Desc.addSymbol("Beinpresse");
            hassGeraet2Desc.addSymbol("Beinbizeps");
            hassGeraet2Desc.addSymbol("Quadrizeps");
            SymbolFct hassGeraet2Fct = hassGeraet2Desc.addSymbolFct("hassGeraet2Fct", true);
            hassGeraet2Fct.setSimilarity("Beinpresse", "Beinbizeps", 0.20);
            hassGeraet2Fct.setSimilarity("Beinpresse", "Quadrizeps", 0.20);
            hassGeraet2Fct.setSimilarity("Beinbizeps", "Beinpresse", 0.20);
            hassGeraet2Fct.setSimilarity("Beinbizeps", "Quadrizeps", 0.40);
            hassGeraet2Fct.setSimilarity("Quadrizeps", "Beinpresse", 0.20);
            hassGeraet2Fct.setSimilarity("Quadrizeps", "Beinbizeps", 0.40);
            workoutGlobalSim.setWeight("hassGeraet2", 2);
                        
            vorhandeneGeraeteDesc = new SymbolDesc(workoutConcept, "vorhandeneGeraete", null);
            vorhandeneGeraeteDesc.addSymbol("Ja");
            vorhandeneGeraeteDesc.addSymbol("Nein");
            SymbolFct vorhandeneGeraeteFct = vorhandeneGeraeteDesc.addSymbolFct("vorhandeneGeraeteFct", true);
            vorhandeneGeraeteFct.setSimilarity("Ja", "Nein", 0.20);
            vorhandeneGeraeteFct.setSimilarity("Nein", "Ja", 0.20);
            workoutGlobalSim.setWeight("vorhandeneGeraete", 2);
            
            trainingsmethodeDesc = new SymbolDesc(workoutConcept, "trainingsmethode", null);
            trainingsmethodeDesc.addSymbol("Kraftsport");
            trainingsmethodeDesc.addSymbol("Körpergewicht");
            trainingsmethodeDesc.addSymbol("Cardio");
            SymbolFct trainingsmethodeFct = trainingszustandDesc.addSymbolFct("trainingsmethodeFct", true);
            trainingsmethodeFct.setSimilarity("Kraftsport", "Körpergewicht", 0.60);
            trainingsmethodeFct.setSimilarity("Kraftsport", "Cardio", 0.40);
            trainingsmethodeFct.setSimilarity("Körpergewicht", "Kraftsport", 0.10);
            trainingsmethodeFct.setSimilarity("Körpergewicht", "Cardio", 0.60);
            trainingsmethodeFct.setSimilarity("Cardio", "Kraftsport", 0.40);
            trainingsmethodeFct.setSimilarity("Cardio", "Körpergewicht", 0.20);
            workoutGlobalSim.setWeight("trainingsmethode", 2);    
            
            zielmuskulaturDesc = new SymbolDesc(workoutConcept, "zielmuskulatur", null);
            zielmuskulaturDesc.addSymbol("Oberkoerper");
            zielmuskulaturDesc.addSymbol("Unterkoerper");
            SymbolFct zielmuskulaturFct = zielmuskulaturDesc.addSymbolFct("zielmuskulaturFct", true);
            zielmuskulaturFct.setSimilarity("Oberkoerper", "Unterkoerper", 0.50);
            zielmuskulaturFct.setSimilarity("Unterkoerper", "Oberkoerper", 0.50);
            workoutGlobalSim.setWeight("zielmuskulatur", 2);
            
            intensitaetDesc = new SymbolDesc(workoutConcept, "intensitaet", null);
            intensitaetDesc.addSymbol("Hoch");
            intensitaetDesc.addSymbol("Mittel");
            intensitaetDesc.addSymbol("Niedrig");
            SymbolFct intensitaetFct = intensitaetDesc.addSymbolFct("intensitaetFct", true);
            intensitaetFct.setSimilarity("Hoch", "Mittel", 0.20);
            intensitaetFct.setSimilarity("Hoch", "Niedrig", 0.20);
            intensitaetFct.setSimilarity("Mittel", "Niedrig", 0.20);
            intensitaetFct.setSimilarity("Mittel", "Hoch", 0.20);
            intensitaetFct.setSimilarity("Niedrig", "Mittel", 0.20);
            intensitaetFct.setSimilarity("Niedrig", "Hoch", 0.20);
            workoutGlobalSim.setWeight("intensitaet", 2);
            
            //String
            athleteDesc = new StringDesc(workoutConcept, "Athlete");
            athleteDesc.addStringFct(StringConfig.LEVENSHTEIN, "athleteFct", true);
			workoutGlobalSim.setWeight("Athlete", 2);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void initAthletenCaseBase() {
		//Einlesen der CSV Datei und abspeichern als CaseBase
		
		try {
			athletenCaseBase = project.createDefaultCB("athletenCaseBase");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Einlesen aus der CSV Datei
		try {

			Instance instance;
			String line = "";
			String cvsSplitBy = ",";

			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Paul\\eclipse-workspace\\Ntswwm-Workout-Planner\\src\\main\\java\\cbr\\Athletes_CaseBase.csv"));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				String[] athlete = line.split(cvsSplitBy); // use comma as separator
				System.out.println("Einlese-Iteration der Athleten Casebase");
			
				try {
					instance = athletenConcept.addInstance(athlete[0]);
					instance.addAttribute(koerpergroesseDesc, athlete[1]);
					instance.addAttribute(gewichtDesc, athlete[2]);
					instance.addAttribute(erfahrungInJahreDesc, athlete[3]);
					instance.addAttribute(koerperfettanteilDesc, athlete[4]);
					instance.addAttribute(ruhepulsDesc, athlete[5]);
					instance.addAttribute(maxHerfrequenzDesc, athlete[6]);
					instance.addAttribute(trainingszustandDesc, athlete[7]);
					instance.addAttribute(geschlechtDesc, athlete[8]);
					instance.addAttribute(zielDesc, athlete[9]);
					instance.addAttribute(sportartDesc, athlete[10]);
					instance.addAttribute(regenerationsfaehigkeitDesc, athlete[11]);
					instance.addAttribute(motivationsfaktorDesc, athlete[12]);
					instance.addAttribute(athletenBezeichnungDesc, athlete[13]);
					athletenCaseBase.addCase(instance);
				} catch (Exception e) {
					System.out.println("Konnte Athleten nicht einlesen aus der CSV");
					System.out.println(e);
					e.printStackTrace();
				}

			}
			br.close();
		} catch (IOException e) {
			System.err.println("CbrAgent.java: Fehler beim Hinzufuegen der Athleten-Cases.");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	private void initWorkoutCaseBase() {
		//Einlesen der CSV Datei und abspeichern als CaseBase	
	    try {
            workoutCaseBase = project.createDefaultCB("workoutCaseBase");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Einlesen aus der CSV Datei
        try {

            Instance instance;
            String line = "";
            String cvsSplitBy = ",";

            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Paul\\eclipse-workspace\\Ntswwm-Workout-Planner\\src\\main\\java\\cbr\\Workout_CaseBase.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] workout = line.split(cvsSplitBy); // use comma as separator

            
                try {
                    instance = workoutConcept.addInstance(workout[0]);
                    instance.addAttribute(trainingszeitWocheDesc, workout[1]);
                    instance.addAttribute(trainingszeitSessionDesc, workout[2]);
                    instance.addAttribute(uebungenProWorkoutDesc, workout[3]);
                    instance.addAttribute(wochenNachPlanDesc, workout[4]);
                    instance.addAttribute(verletzungenDesc, workout[5]);
                    instance.addAttribute(vorliebeGeraet1Desc, workout[6]);
                    instance.addAttribute(vorliebeGeraet2Desc, workout[7]);
                    instance.addAttribute(hassGeraet1Desc, workout[8]);
                    instance.addAttribute(hassGeraet2Desc, workout[9]);
                    instance.addAttribute(vorhandeneGeraeteDesc, workout[10]);
                    instance.addAttribute(trainingsmethodeDesc, workout[11]);
                    instance.addAttribute(zielmuskulaturDesc, workout[12]);
                    instance.addAttribute(intensitaetDesc, workout[13]);
                    instance.addAttribute(athleteDesc, workout[13]);
                    workoutCaseBase.addCase(instance);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            br.close();
        } catch (IOException e) {
            System.err.println("CbrAgent.java: Fehler beim Hinzufuegen der Workout-Cases.");
            e.printStackTrace();
        }
	}
	
	public List<Pair<Instance, Similarity>> startAthletenQuery(Athlete athlete) {
		// Get the values of the request
				koerpergroesseDesc = (IntegerDesc) this.athletenConcept.getAllAttributeDescs().get("koerpergroesse");
				gewichtDesc = (DoubleDesc) this.athletenConcept.getAllAttributeDescs().get("gewicht");
				erfahrungInJahreDesc = (IntegerDesc) this.athletenConcept.getAllAttributeDescs().get("erfahrungInJahre");
				koerperfettanteilDesc = (DoubleDesc) this.athletenConcept.getAllAttributeDescs().get("koerperfettanteil");
				ruhepulsDesc = (IntegerDesc) this.athletenConcept.getAllAttributeDescs().get("ruhepuls");
				maxHerfrequenzDesc = (IntegerDesc) this.athletenConcept.getAllAttributeDescs().get("maxHerzfrequenz");
				trainingszustandDesc = (SymbolDesc) this.athletenConcept.getAllAttributeDescs().get("trainingszustand");
				geschlechtDesc = (SymbolDesc) this.athletenConcept.getAllAttributeDescs().get("geschlecht");
				zielDesc = (SymbolDesc) this.athletenConcept.getAllAttributeDescs().get("ziel");
				sportartDesc = (SymbolDesc) this.athletenConcept.getAllAttributeDescs().get("sportart");
				regenerationsfaehigkeitDesc = (SymbolDesc) this.athletenConcept.getAllAttributeDescs().get("regenerationsfaehigkeit");
				motivationsfaktorDesc = (SymbolDesc) this.athletenConcept.getAllAttributeDescs().get("motivationsfaktor");

				// Insert values into query
				try {
					athletenRetrieve = new Retrieval(athletenConcept, athletenCaseBase);
					athletenRetrieve.setRetrievalMethod(RetrievalMethod.RETRIEVE_SORTED);
					Instance query = athletenRetrieve.getQueryInstance();
					query.addAttribute(koerpergroesseDesc, koerpergroesseDesc.getAttribute(athlete.getKoerpergroesse()));
					query.addAttribute(gewichtDesc, gewichtDesc.getAttribute(athlete.getGewicht()));
					query.addAttribute(erfahrungInJahreDesc, erfahrungInJahreDesc.getAttribute(athlete.getErfahrungInJahre()));
					query.addAttribute(koerperfettanteilDesc, koerperfettanteilDesc.getAttribute(athlete.getKoerperfettanteil()));
					query.addAttribute(ruhepulsDesc, ruhepulsDesc.getAttribute(athlete.getRuhepuls()));
					query.addAttribute(maxHerfrequenzDesc, maxHerfrequenzDesc.getAttribute(athlete.getMaxHerzfrequenz()));
					query.addAttribute(trainingszustandDesc, trainingszustandDesc.getAttribute(athlete.getTrainingszustand()));
					query.addAttribute(geschlechtDesc, geschlechtDesc.getAttribute(athlete.getGeschlecht()));
					query.addAttribute(zielDesc, zielDesc.getAttribute(athlete.getZiel()));
					query.addAttribute(sportartDesc, sportartDesc.getAttribute(athlete.getSportart()));
					query.addAttribute(regenerationsfaehigkeitDesc, regenerationsfaehigkeitDesc.getAttribute(athlete.getRegenerationsfaehigkeit()));
					query.addAttribute(motivationsfaktorDesc, motivationsfaktorDesc.getAttribute(athlete.getMotivationsfaktor()));
				} catch (ParseException e) {
					System.err.println("[ERROR] BookAgent: Error while creating the query! " + e.getMessage());
				}

				// Send query
				athletenRetrieve.start();
				System.out.println("[DEBUG] CbrAgent: Athleten Query successful!");
				return athletenRetrieve.getResult();
	}
	
	public List<Pair<Instance, Similarity>> startWorkoutQuery(Workout workout) {
		// Get the values of the request
        trainingszeitWocheDesc = (IntegerDesc) this.workoutConcept.getAllAttributeDescs().get("trainingsZeitProWoche");
        trainingszeitSessionDesc = (IntegerDesc) this.workoutConcept.getAllAttributeDescs().get("trainingszeitProWorkout");
        uebungenProWorkoutDesc = (IntegerDesc) this.workoutConcept.getAllAttributeDescs().get("uebungenProWorkout");
        wochenNachPlanDesc = (IntegerDesc) this.workoutConcept.getAllAttributeDescs().get("wochenNachPlan");
        verletzungenDesc = (BooleanDesc) this.workoutConcept.getAllAttributeDescs().get("verletzungen");
        vorliebeGeraet1Desc = (SymbolDesc) this.workoutConcept.getAllAttributeDescs().get("vorliebeGeraet1");
        vorliebeGeraet2Desc = (SymbolDesc) this.workoutConcept.getAllAttributeDescs().get("vorliebeGeraet2");
        hassGeraet1Desc = (SymbolDesc) this.workoutConcept.getAllAttributeDescs().get("hassGeraet1");
        hassGeraet2Desc = (SymbolDesc) this.workoutConcept.getAllAttributeDescs().get("hassGeraet2");
        vorhandeneGeraeteDesc = (SymbolDesc) this.workoutConcept.getAllAttributeDescs().get("vorhandeneGeraete");
        trainingsmethodeDesc = (SymbolDesc) this.workoutConcept.getAllAttributeDescs().get("trainingsmethode");
        zielmuskulaturDesc = (SymbolDesc) this.workoutConcept.getAllAttributeDescs().get("zielmuskulatur");
        intensitaetDesc = (SymbolDesc) this.workoutConcept.getAllAttributeDescs().get("intensitaet");
    	athleteDesc = (StringDesc) this.workoutConcept.getAllAttributeDescs().get("athlete");
        // Insert values into query
       try {
            workoutRetrieve = new Retrieval(workoutConcept, workoutCaseBase);
            workoutRetrieve.setRetrievalMethod(RetrievalMethod.RETRIEVE_SORTED);
            Instance query = workoutRetrieve.getQueryInstance();
            query.addAttribute(trainingszeitWocheDesc, trainingszeitWocheDesc.getAttribute(workout.getTrainingszeitWoche()));
            query.addAttribute(trainingszeitSessionDesc, trainingszeitSessionDesc.getAttribute(workout.getTrainingszeitSession()));
            query.addAttribute(uebungenProWorkoutDesc, uebungenProWorkoutDesc.getAttribute(workout.getUebungenProWorkout()));
            query.addAttribute(wochenNachPlanDesc, wochenNachPlanDesc.getAttribute(workout.getWochenNachPlan()));
            query.addAttribute(verletzungenDesc, verletzungenDesc.getAttribute(workout.isVerletzungen()));
            query.addAttribute(vorliebeGeraet1Desc, vorliebeGeraet1Desc.getAttribute(workout.getVorliebeGeraet1()));
            query.addAttribute(vorliebeGeraet2Desc, vorliebeGeraet2Desc.getAttribute(workout.getVorliebeGeraet2()));
            query.addAttribute(hassGeraet1Desc, hassGeraet1Desc.getAttribute(workout.getHassGeraet1()));
            query.addAttribute(hassGeraet2Desc, hassGeraet2Desc.getAttribute(workout.getHassGeraet2()));
            query.addAttribute(vorhandeneGeraeteDesc, vorhandeneGeraeteDesc.getAttribute(workout.getVorhandeneGeraete()));
            query.addAttribute(trainingsmethodeDesc, trainingsmethodeDesc.getAttribute(workout.getTrainingsmethode()));
            query.addAttribute(zielmuskulaturDesc, zielmuskulaturDesc.getAttribute(workout.getZielmuskulatur()));
            query.addAttribute(intensitaetDesc, intensitaetDesc.getAttribute(workout.getIntensitaet()));
            System.out.println("Workout Athlete Debug: " + workout.getAthlete());
            query.addAttribute(athleteDesc, athleteDesc.getAttribute(workout.getAthlete()));
            
        } catch (ParseException e) {
            System.err.println("[ERROR] BookAgent: Error while creating the query! " + e.getMessage());
        }

        // Send query
        workoutRetrieve.start();
        System.out.println("[DEBUG] CbrAgent: Workout Query successful!");
        return workoutRetrieve.getResult();
	}
	
	public ArrayList<Athlete>  printAthlete(List<Pair<Instance, Similarity>> athletenResult, int numberOfBestCases) {
		
		ArrayList<Athlete> resultingAthlete = new ArrayList<Athlete>();
		for (int i = 0; i < numberOfBestCases; i++) {

			Instance obj = athletenConcept.getInstance(athletenResult.get(i).getFirst().getName());
			Athlete athlete = new Athlete(
					Integer.parseInt(obj.getAttForDesc(koerpergroesseDesc).getValueAsString()),
					Double.parseDouble(obj.getAttForDesc(gewichtDesc).getValueAsString()),
					Integer.parseInt(obj.getAttForDesc(erfahrungInJahreDesc).getValueAsString()),
					Double.parseDouble(obj.getAttForDesc(koerperfettanteilDesc).getValueAsString()),
					Integer.parseInt(obj.getAttForDesc(ruhepulsDesc).getValueAsString()),
					Integer.parseInt(obj.getAttForDesc(maxHerfrequenzDesc).getValueAsString()),
					obj.getAttForDesc(trainingszustandDesc).getValueAsString(),
					obj.getAttForDesc(geschlechtDesc).getValueAsString(),
					obj.getAttForDesc(zielDesc).getValueAsString(),
					obj.getAttForDesc(sportartDesc).getValueAsString(),
					obj.getAttForDesc(regenerationsfaehigkeitDesc).getValueAsString(),
					obj.getAttForDesc(motivationsfaktorDesc).getValueAsString(),
					obj.getAttForDesc(athletenBezeichnungDesc).getValueAsString());
			resultingAthlete.add(athlete);
			resultingAthlete.get(i).setSimilarity(athletenResult.get(i).getSecond().getValue());
			System.out.println(athletenResult.get(i).getFirst().getName() + " - Similarity: "
					+ Math.floor(athletenResult.get(i).getSecond().getValue() * 100) / 100);
		}
		return resultingAthlete;
	}
	
	public ArrayList<Workout>  printWorkout(List<Pair<Instance, Similarity>> workoutResult, int numberOfBestCases) {
		  ArrayList<Workout> resultingWorkout = new ArrayList<Workout>();
		    for (int i = 0; i < numberOfBestCases; i++) {
		        
		        Instance obj = workoutConcept.getInstance(workoutResult.get(i).getFirst().getName());
		        Workout workout = new Workout(
		                Integer.parseInt(obj.getAttForDesc(trainingszeitWocheDesc).getValueAsString()),
	                    Integer.parseInt(obj.getAttForDesc(trainingszeitSessionDesc).getValueAsString()),
	                    Integer.parseInt(obj.getAttForDesc(uebungenProWorkoutDesc).getValueAsString()),
	                    Integer.parseInt(obj.getAttForDesc(wochenNachPlanDesc).getValueAsString()),
	                    Boolean.parseBoolean(obj.getAttForDesc(verletzungenDesc).getValueAsString()),
	                    obj.getAttForDesc(vorliebeGeraet1Desc).getValueAsString(),
	                    obj.getAttForDesc(vorliebeGeraet2Desc).getValueAsString(),
	                    obj.getAttForDesc(hassGeraet1Desc).getValueAsString(),
	                    obj.getAttForDesc(hassGeraet2Desc).getValueAsString(),
	                    obj.getAttForDesc(vorhandeneGeraeteDesc).getValueAsString(),
	                    obj.getAttForDesc(trainingsmethodeDesc).getValueAsString(),
		                obj.getAttForDesc(zielmuskulaturDesc).getValueAsString(),
		                obj.getAttForDesc(intensitaetDesc).getValueAsString(),
		                obj.getAttForDesc(athleteDesc).getValueAsString());
		        resultingWorkout.add(workout);
		        resultingWorkout.get(i).setSimilarity(workoutResult.get(i).getSecond().getValue());
	            System.out.println(workoutResult.get(i).getFirst().getName() + " - Similarity: "
	                    + Math.floor(workoutResult.get(i).getSecond().getValue() * 100) / 100);
		    }
		     return resultingWorkout;           
		                
		    
	}	
	
	
	
	public Ergebnis getWorkout(int id) {		
	     
		 try {
			 	
				
				String line = "";
				String cvsSplitBy = ",";
				int counter = 1;
				BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Paul\\eclipse-workspace\\Ntswwm-Workout-Planner\\src\\main\\java\\cbr\\Workouts.csv"));
				while ((line = br.readLine()) != null)
				{
					if(counter == id) {
						String[] athlete = line.split(cvsSplitBy);
						
						Ergebnis ergebnis = new Ergebnis(id, athlete[1], athlete[2], athlete[3], athlete[4], athlete[5], athlete[6],
								athlete[7], athlete[8], athlete[9], athlete[10], Double.parseDouble(athlete[11]),athlete[12]);
						
						br.close();
						return ergebnis;
					}								
				counter++;

				}
				br.close();
				return null;
			} catch (IOException e) {
				System.err.println("CbrAgent.java: Fehler beim abfragen des Workouts basierend auf der ID.");
				e.printStackTrace();
			}
		return null;
	}
	
	// Abspeichern des angepassten Workouts des Nutzers in der Workout.csv
	public void saveWorkout(Ergebnis ergebnis) {
		
		String csvFile = "C:\\Users\\Paul\\eclipse-workspace\\Ntswwm-Workout-Planner\\src\\main\\java\\cbr\\Workouts.csv";

		try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile, true))) {
			
			String[] addErgebnis = { String.valueOf(ergebnis.getId()), ergebnis.getUebung1(),ergebnis.getZeit1wdh1(), ergebnis.getUebung2(),
					ergebnis.getZeit2wdh2(),ergebnis.getUebung3(),ergebnis.getZeit3wdh3(),ergebnis.getUebung4(),ergebnis.getZeit4wdh4(),
					ergebnis.getUebung5(),ergebnis.getZeit5wdh5(),String.valueOf(ergebnis.getPauseInMin()),ergebnis.getKategorie()};

			csvWriter.writeNext(addErgebnis);
			System.out.println("Ergebnis zur Workout.csv hinzugefuegt");
			csvWriter.close();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			initWorkoutCaseBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// Abspeichern des Workouts in Workout_CaseBase.csv
	public void saveWorkoutInCaseBase(Workout workout) {
		String csvFile = "C:\\Users\\Paul\\eclipse-workspace\\Ntswwm-Workout-Planner\\src\\main\\java\\cbr\\Workout_CaseBase.csv";

		try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile, true))) {
			
			String[] addWorkout = { String.valueOf(workout.getId()), String.valueOf(workout.getTrainingszeitWoche()),String.valueOf(workout.getTrainingszeitSession()),
						String.valueOf(workout.getUebungenProWorkout()),String.valueOf(workout.getWochenNachPlan()),String.valueOf(workout.isVerletzungen()),
						workout.getVorliebeGeraet1(),workout.getVorliebeGeraet2(),workout.getHassGeraet1(),workout.getHassGeraet2(),workout.getVorhandeneGeraete(),
						workout.getTrainingsmethode(),workout.getZielmuskulatur(),workout.getIntensitaet(),workout.getAthlete()};

			csvWriter.writeNext(addWorkout);
			System.out.println("Bearbeitetes Workout zur Workout CaseBase hinzugefuegt");
			csvWriter.close();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			initWorkoutCaseBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getAthletenCaseBaseSize() {
		return athletenCaseBase.getCases().size();
		
	}
	
	public int getWorkoutCaseBaseSize() {
		return workoutCaseBase.getCases().size();
		
	}
	
}
