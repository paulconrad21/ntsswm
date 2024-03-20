<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hier ist dein passendes Workout ...</title>
</head>
<body>
	<form action="WorkoutServlet" method="post">	
		<table>
			<tr>
				<td>Uebung 1:</td>
				<td><input name="uebung1" value=${ uebung1 }></td>
			</tr>
			<tr>
				<td>Wdh/Zeit:</td>
				<td><input name="zeit1wdh1" value=${ zeit1wdh1 }></td>
			</tr>
			<tr>
				<td>Uebung 2:</td>
				<td><input name="uebung2" value=${ uebung2 }></td>
			</tr>
			<tr>
				<td>Wdh/Zeit:</td>
				<td><input name="zeit2wdh2" value=${ zeit2wdh2 }></td>
			</tr>
			<tr>
				<td>Uebung 3:</td>
				<td><input name="uebung3" value=${ uebung3 }></td>
			</tr>
			<tr>
				<td>Wdh/Zeit::</td>
				<td><input name="zeit3wdh3" value=${ zeit3wdh3 }></td>
			</tr>
			<tr>
				<td>Uebung 4:</td>
				<td><input name="uebung4" value=${ uebung4 }></td>
			</tr>
			<tr>
				<td>Wdh/Zeit:</td>
				<td><input name="zeit4wdh4" value=${ zeit4wdh4 }></td>
			</tr>
			<tr>
				<td>Uebung 5:</td>
				<td><input name="uebung5" value=${ uebung5 }></td>
			</tr>
			<tr>
				<td>Wdh/Zeit:</td>
				<td><input name="zeit5wdh5" value=${ zeit5wdh5 }></td>
			</tr>
			<tr>
				<td>Pause (Minuten):</td>
				<td><input name="pauseInMin" value=${ pauseInMin }></td>
			</tr>
			<tr>
				<td>Kategorie:</td>
				<td><input name="kategorie" value=${ kategorie }></td>
			</tr>
			<tr>
				<td><input type="submit" value="Änderungen speichern"></td>
			</tr>
		</table>
		<button>Workout Anpassen</button>
	</form>
</body>
</html>