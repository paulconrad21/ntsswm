<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Workout Planner</title>
</head>
<body>

<div class="container">
		<h1>Finde dein perfektes Workout!</h1>

		<form action="WorkoutServlet" method="get" style="display: flex;" class="form-horizontal">
					<div class="row col-md-4" style="box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);width: 33%;margin: 10px;">
					<div class="form-group">
					<p>Körpergröße:</p>
					<input name="koerpergroesse" placeholder="z.B. 185" value=${ inputKoerpergroesse }>
					</div>
					<div class="form-group">
					<p>Körpergewicht:</p>
					<input name="koepergewicht" placeholder="z.B. 90.3" value=${ inputKoerpergewicht }>
					</div>
					<div class="form-group">
					<P>Trainingserfahrung (Jahre):</p>
					<input name="trainingserfahrung" placeholder="z.B. 2" value=${ inputTrainingserfahrung }>
					</div>
					<div class="form-group">
					<p>Körperfettanteil:</p>
					<input name="koerperfettanteil" placeholder="z.B. 22" value=${ inputKoerperfettanteil }>
					</div>
					<div class="form-group">
					<p>Ruhepuls:</p>
					<input name="ruhepuls" placeholder="z.B. 55" value=${ inputRuhepuls }>
					</div>
					<div class="form-group">
					<p>Maximale Herzfrequenz:</p>
					<input name="maxherzfrequenz" placeholder="z.B. 180" value=${ inputMaxHerzfrequenz }>
					</div>
					<div class="form-group">
					<p>Workouts pro Woche:</p>
					<input name="trainingszeitWoche" placeholder="z.B. 3" value=${ inputTrainingszeitWoche }>
					</div>
					<div class="form-group">
					<p>Trainingszeit pro Session:</p>
					<input name="trainingszeitSession" placeholder="z.B. 30" value=${ inputTrainingszeitSession }>
					</div>
					<div class="form-group">
					<p>Übungen pro Workout:</p>
					<input name="uebungenProWorkout" placeholder="z.B. 5" value=${ inputUebungenProWorkout }>
					</div>
					<div class="form-group">
					<p>Wie viele Wochen nach Plan trainieren:</p>
					<input name="wochenNachPlan" placeholder="z.B. 5" value=${ inputWochenNachPlan }>
					</div>
					<div class="form-group">
					<p>Verletzungen:</p>
					<select name="verletzungen" size="2">
							<option selected>Nein</option>
							<option>Ja</option>
					</select>
					</div>
					</div>
					<div class="row col-md-4" style="box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);width: 33%;margin: 10px;">
					<div class="form-group">
					<p>Aktueller Trainingszustand:</p>
					<select name="trainingszustand" size="3">
							<option selected>Gut</option>
							<option>Mittelmaeßig</option>
							<option>Schlecht</option>
					</select>
					</div>					
					<div class="form-group">
					<p>Geschlecht:</p>
					<select name="geschlecht" size="2">
							<option selected>Mann</option>
							<option>Frau</option>
					</select>
					</div>
					<div class="form-group">
					<p>Ziel:</p>
					<select name="ziel" size="3">
							<option selected>Muskelaufbau</option>
							<option>Fettabbau</option>
							<option>Ausdauer verbessern</option>
					</select>
					</div>
					<div class="form-group">
					<p>Sportart:</p>
					<select name="sportart" size="3">
							<option selected>Krafttraining</option>
							<option>Laufen</option>
							<option>Fahrrad</option>
					</select>
					</div>
					<div class="form-group">
					<p>Regenerationsfähigkeit:</p>
					<select name="regenerationsfaehigkeit" size="4">
							<option selected>Sehr gut</option>
							<option>Gut</option>
							<option>Durchschnittlich</option>
							<option>Unterdurchschnittlich</option>
					</select>
					</div>
					<div class="form-group">
					<p>Motivationsfaktor:</p>
					<select name="motivationsfaktor" size="4">
							<option selected>Sehr hoch</option>
							<option>Hoch</option>
							<option>Durchschnittlich</option>
							<option>Unterdurchschnittlich</option>
					</select>
					</div>
					<div class="form-group">
					<p>Lieblingsübungen 1:</p>
					<select name="vorliebeGerat1" size="4">
							<option selected>-</option>
							<option>Bankdrücken</option>
							<option>Klimmzüge</option>
							<option>Kniebeugen</option>
					</select>
					</div>
					</div>
					<div class="row col-md-4" style="box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);width: 33%;margin: 10px;">
					<div class="form-group">
					<p>Lieblingsübungen 2:</p>
					<select name="vorliebeGerat2" size="4">
							<option selected>-</option>
							<option>Bankdrücken</option>
							<option>Klimmzüge</option>
							<option>Kniebeugen</option>
					</select>
					</div>
					<div class="form-group">
					<p>Weniger beliebte Übungen 1:</p>
					<select name="hassGeraet1" size="4">
							<option selected>-</option>
							<option>Bankdrücken</option>
							<option>Klimmzüge</option>
							<option>Kniebeugen</option>
					</select>
					</div>
					<div class="form-group">
					<p>Weniger beliebte Übungen 2:</p>
					<select name="hassGeraet2" size="4">
							<option selected>-</option>
							<option>Bankdrücken</option>
							<option>Klimmzüge</option>
							<option>Kniebeugen</option>
					</select>
					</div>
					<div class="form-group">
					<p>Vorhandene Geräte:</p>
					<select name="vorhandeneGeraete" size="4">
							<option selected>Ja</option>
							<option>Nein</option>
					</select>
					</div>
					<div class="form-group">
					<p>Trainingsmethode:</p>
					<select name="trainingsmethode" size="4">
							<option selected>Kraftsport</option>
							<option>Cardio</option>
							<option>Körpergewicht</option>
					</select>
					</div>
					<div class="form-group">
					<p>Zielmuskulatur:</p>
					<select name="zielmuskulatur" size="3">
							<option selected>-</option>
							<option>Oberkoerper</option>
							<option>Unterkoerper</option>
					</select>
					</div>
					<div class="form-group">
					<p>Workout Intensität:</p>
					<select name="intensitaet" size="3">
							<option selected>Mittel</option>
							<option>Hoch</option>
							<option>Niedrig</option>
					</select>
					</div>
					</div>
					<div class="form-group">
					<div class="col-md-12">
					<input type="submit" class="btn btn-primary" value="Finde Dein Passendes Workout!">
					</div>
					</div>
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