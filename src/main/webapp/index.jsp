<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Workout Planner</title>
</head>
<body>

<div class="container">
		<h1>Find Your Customized Workout ...</h1>

		<form action="WorkoutServlet" method="get" class="form-horizontal">
			<table>
				<tr>
					<td>Körpergröße:</td>
					<td><input name="koerpergroesse" placeholder="z.B. 185" value=${ inputKoerpergroesse }></td>
				</tr>
				<tr>
					<td>Körpergewicht:</td>
					<td><input name="koepergewicht" placeholder="z.B. 90.3" value=${ inputKoerpergewicht }></td>
				</tr>
				<tr>
					<td>Trainingserfahrung (Jahre):</td>
					<td><input name="trainingserfahrung" placeholder="z.B. 2" value=${ inputTrainingserfahrung }></td>
				</tr>
				<tr>
					<td>Körperfettanteil:</td>
					<td><input name="koerperfettanteil" placeholder="z.B. 22" value=${ inputKoerperfettanteil }></td>
				</tr>
				<tr>
					<td>Ruhepuls:</td>
					<td><input name="ruhepuls" placeholder="z.B. 55" value=${ inputRuhepuls }></td>
				</tr>
				<tr>
					<td>Maximale Herzfrequenz:</td>
					<td><input name="maxherzfrequenz" placeholder="z.B. 180" value=${ inputMaxHerzfrequenz }></td>
				</tr>
				<tr>
					<td>Workouts pro Woche:</td>
					<td><input name="trainingszeitWoche" placeholder="z.B. 3" value=${ inputTrainingszeitWoche }></td>
				</tr>
				<tr>
					<td>Trainingszeit pro Session:</td>
					<td><input name="trainingszeitSession" placeholder="z.B. 30" value=${ inputTrainingszeitSession }></td>
				</tr>
				<tr>
					<td>Übungen pro Workout:</td>
					<td><input name="uebungenProWorkout" placeholder="z.B. 5" value=${ inputUebungenProWorkout }></td>
				</tr>
				<tr>
					<td>Wie viele Wochen nach Plan trainieren:</td>
					<td><input name="wochenNachPlan" placeholder="z.B. 5" value=${ inputWochenNachPlan }></td>
				</tr>
				<tr>
					<td>Verletzungen:</td>
					<td><select name="verletzungen" size="2">
							<option selected>Nein</option>
							<option>Ja</option>
					</select></td>
				</tr>				
				<tr>
					<td>Aktueller Trainingszustand:</td>
					<td><select name="trainingszustand" size="3">
							<option selected>Gut</option>
							<option>Mittelmaeßig</option>
							<option>Schlecht</option>
					</select></td>
				</tr>
				<tr>
					<td>Geschlecht:</td>
					<td><select name="geschlecht" size="2">
							<option selected>Mann</option>
							<option>Frau</option>
					</select></td>
				</tr>
				<tr>
					<td>Ziel:</td>
					<td><select name="ziel" size="3">
							<option selected>Muskelaufbau</option>
							<option>Fettabbau</option>
							<option>Ausdauer verbessern</option>
					</select></td>
				</tr>
				<tr>
					<td>Sportart:</td>
					<td><select name="sportart" size="3">
							<option selected>Krafttraining</option>
							<option>Laufen</option>
							<option>Fahrrad</option>
					</select></td>
				</tr>
				<tr>
					<td>Regenerationsfähigkeit:</td>
					<td><select name="regenerationsfaehigkeit" size="4">
							<option selected>Sehr gut</option>
							<option>Gut</option>
							<option>Durchschnittlich</option>
							<option>Unterdurchschnittlich</option>
					</select></td>
				</tr>
				<tr>
					<td>Motivationsfaktor:</td>
					<td><select name="motivationsfaktor" size="4">
							<option selected>Sehr hoch</option>
							<option>Hoch</option>
							<option>Durchschnittlich</option>
							<option>Unterdurchschnittlich</option>
					</select></td>
				</tr>
				<tr>
					<td>Lieblingsübungen 1:</td>
					<td><select name="vorliebeGerat1" size="4">
							<option selected>-</option>
							<option>Bankdrücken</option>
							<option>Klimmzüge</option>
							<option>Kniebeugen</option>
					</select></td>
				</tr>
				<tr>
					<td>Lieblingsübungen 2:</td>
					<td><select name="vorliebeGerat2" size="4">
							<option selected>-</option>
							<option>Bankdrücken</option>
							<option>Klimmzüge</option>
							<option>Kniebeugen</option>
					</select></td>
				</tr>
				<tr>
					<td>Weniger beliebte Übungen 1:</td>
					<td><select name="hassGeraet1" size="4">
							<option selected>-</option>
							<option>Bankdrücken</option>
							<option>Klimmzüge</option>
							<option>Kniebeugen</option>
					</select></td>
				</tr>
				<tr>
					<td>Weniger beliebte Übungen 2:</td>
					<td><select name="hassGeraet2" size="4">
							<option selected>-</option>
							<option>Bankdrücken</option>
							<option>Klimmzüge</option>
							<option>Kniebeugen</option>
					</select></td>
				</tr>
				<tr>
					<td>Vorhandene Geräte:</td>
					<td><select name="vorhandeneGeraete" size="4">
							<option selected>Körpergewicht</option>
							<option>Gesamtes Fitnessstudio</option>
							<option>Kurzhanteln</option>
							<option>Klimmzugstange</option>
					</select></td>
				</tr>
				<tr>
					<td>Trainingsmethode:</td>
					<td><select name="trainingsmethode" size="4">
							<option selected>Feste Wiederholungen</option>
							<option>Pyramidenförmig</option>
							<option>Supersätze</option>
							<option>Until Failure</option>
					</select></td>
				</tr>
				<tr>
					<td>Zielmuskulatur:</td>
					<td><select name="zielmuskulatur" size="3">
							<option selected>-</option>
							<option>Oberkoerper</option>
							<option>Unterkoerper</option>
					</select></td>
				</tr>
				<tr>
					<td>Workout Intensität:</td>
					<td><select name="intensitaet" size="3">
							<option selected>Mittel</option>
							<option>Hoch</option>
							<option>Niedrig</option>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Finde Dein Passendes Workout!"></td>
				</tr>
			</table>
		</form>
		
		<hr>

		<c:if test="${ resultingBooks != null }">
			<c:forEach var="resultingBooks" items="${ resultingBooks }" varStatus="loop">

				<table class="table table-bordered table-hover">
					<tr>
						<td><b>Rank ${ loop.count }</b></td>
						<td>Similarity: ${ resultingBooks.getSimilarity() }</td>
					</tr>
					<tr>
						<td>Title:</td>
						<td>${ resultingBooks.getTitle() }</td>
					</tr>
					<tr>
						<td>Year:</td>
						<td>${ resultingBooks.getYear() }</td>
					</tr>
					<tr>
						<td>Category:</td>
						<td>${ resultingBooks.getCategory() }</td>
					</tr>
					<tr>
						<td>Author:</td>
						<td>${ resultingBooks.getAuthor() }</td>
					</tr>
					<tr>
						<td>Award:</td>
						<td>${ resultingBooks.isAwarded() }</td>
					</tr>
				</table>
			</c:forEach>
		</c:if>
	</div>

</body>
</html>