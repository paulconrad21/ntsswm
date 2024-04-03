<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Hier ist dein passendes Workout ...</title>

<script>

	function enableForm() {
		
		var inputs = document.querySelectorAll('input');
		for(var i = 0; i < inputs.length; i++) {
			inputs[i].removeAttribute('disabled');
		}
		
		document.getElementById('submitButton').removeAttribute('disabled');
		document.getElementById('enableButton').setAttribute('disabled','disabled');
		
	}

</script>

</head>
<body>
	<p style="margin: 20px;">Anmerkung: Syntax: "<b>[wiederholungen] wdh * [Sätze]</b>" ODER "<b>[minuten] min * [Sätze]</b>" - Pause als Int oder Dezimalzahl</p>
	<form action="WorkoutServlet" method="post" class="container">	
				<div class="row">
				<div class="form-group">
				<p>Uebung 1:</p>
				<input name="uebung1" disabled value=${ uebung1 }>
				</div>
				
				<div class="form-group">
				<p>Wdh/Zeit:</p>
				<input name="zeit1wdh1" disabled value="<c:out value='${zeit1wdh1}'/>">
				</div>
			
				<div class="form-group">
				<p>Uebung 2:</p>
				<input name="uebung2" disabled value=${ uebung2 }>
				</div>
				<div class="form-group">
				<p>Wdh/Zeit:</p>
				<input name="zeit2wdh2" disabled value="<c:out value='${zeit2wdh2}'/>">
				</div>
				<div class="form-group">
				<p>Uebung 3:</p>
				<input name="uebung3" disabled value=${ uebung3 }>
				</div>
				</div>
				<div class="row">
				<div class="form-group">
				<p>Wdh/Zeit:</p>
				<input name="zeit3wdh3" disabled value="<c:out value='${zeit3wdh3}'/>">
				</div>
				<div class="form-group">
				<p>Uebung 4:</p>
				<input name="uebung4" disabled value=${ uebung4 }>
				</div>
				<div class="form-group">
				<p>Wdh/Zeit:</p>
				<input name="zeit4wdh4" disabled value="<c:out value='${zeit4wdh4}'/>">
				</div>
				<div class="form-group">
				<p>Uebung 5:</p>
				<input name="uebung5" disabled value=${ uebung5 }>
				</div>
				<div class="form-group">
				<p>Wdh/Zeit:</p>
				<input name="zeit5wdh5" disabled value="<c:out value='${zeit5wdh5}'/>">
				</div>
				<div class="form-group">
				<p>Pause (Minuten):</p>
				<input name="pauseInMin" disabled value=${ pauseInMin }>
				</div>
				<div class="form-group">
				<p>Kategorie:</p>
				<input name="kategorie" disabled value=${ kategorie }>
				</div>
			</div>
				<div style="margin-top: 10px">		
				<input disabled type="submit" value="Änderungen speichern" class="btn btn-primary" id="submitButton">
			
		
		<button type="button" id="enableButton" class="btn btn-primary" onclick="enableForm()">Workout Anpassen</button>
		</div>
	</form>
</body>
</html>