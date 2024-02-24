<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
					<td>Info 1:</td>
					<td><input name="title" placeholder="Booktitle" value=${ inputTitle }></td>
				</tr>
				<tr>
					<td>Info 2:</td>
					<td><input name="year" placeholder="Year" value=${ inputYear }></td>
				</tr>
				<tr>
					<td>Info 3:</td>
					<td><select name="category" size="3">
							<option selected>Drama</option>
							<option>Comedy</option>
							<option>Thriller</option>
					</select></td>
				</tr>
				<tr>
					<td>Info 4:</td>
					<td><input name="author" placeholder="Author" value=${ inputAuthor }></td>
				</tr>
				<tr>
					<td>Info 5:</td>
					<td><input type="radio" name="award" value="true">Yes <input type="radio" name="award" value="false">No</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Let's find a workout!"></td>
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