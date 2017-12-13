package self.poc.designpatterns;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class InterpreterPattern {
	public static void main(String[] args) {
		System.out.println("Interpreter Pattern Demonstration.");
		System.out.println("----------------------------------");
		BookInterpreterContext bookInterpreterContext = new BookInterpreterContext();
		bookInterpreterContext.addTitle("Pickwick Papers");
		bookInterpreterContext.addTitle("Great Expectations");
		bookInterpreterContext.addTitle("Wuthering Heights");
		bookInterpreterContext.addTitle("Crossfile");
		bookInterpreterContext.addAuthor("William Shakespeare");
		bookInterpreterContext.addAuthor("Emily Bronte");
		bookInterpreterContext.addAuthor("James Marathon");
		bookInterpreterContext.addTitleAndAuthor(new TitleAndAuthor(
				"Pickwick Papers", "William Shakespeare"));
		bookInterpreterContext.addTitleAndAuthor(new TitleAndAuthor(
				"Great Expectations", "William Shakespeare"));
		bookInterpreterContext.addTitleAndAuthor(new TitleAndAuthor(
				"Wuthering Heights", "Emily Bronte"));
		bookInterpreterContext.addTitleAndAuthor(new TitleAndAuthor(
				"Crossfire", "James Marathon"));
		BookInterpreterClient bookInterpreterClient = new BookInterpreterClient(
				bookInterpreterContext);
		System.out.println("show author ->"
				+ bookInterpreterClient.interpret("show author"));
		System.out.println("show title ->"
				+ bookInterpreterClient.interpret("show title"));
		System.out.println("show author for title <Crossfire> ->"
				+ bookInterpreterClient
						.interpret("show author for title <Crossfire>"));
		System.out
				.println("show title for author <William Shakespeare> ->"
						+ bookInterpreterClient
								.interpret("show title for author <William Shakespeare>"));
		System.out.println();
	}
}

class BookInterpreterClient {
	BookInterpreterContext bookInterpreterContext;

	public BookInterpreterClient(BookInterpreterContext parm) {
		bookInterpreterContext = parm;
	}

	// language syntax:
	// show title
	// show author
	// show title for author <author-name>
	// show author for title <title-name>
	public String interpret(String expression) {
		StringTokenizer expressionTokens = new StringTokenizer(expression);
		String currentToken;
		char mainQuery = ' ';
		char subQuery = ' ';
		String searchString = null;
		boolean forUsed = false;
		boolean searchStarted = false;
		boolean searchEnded = false;
		StringBuffer result = new StringBuffer();
		while (expressionTokens.hasMoreTokens()) {
			currentToken = expressionTokens.nextToken();
			if (currentToken.equals("show")) {
				continue;// show in all queries, not really used
			} else if (currentToken.equals("title")) {
				if (mainQuery == ' ') {
					mainQuery = 'T';
				} else {
					if ((subQuery == ' ') && (forUsed)) {
						subQuery = 'T';
					}
				}
			} else if (currentToken.equals("author")) {
				if (mainQuery == ' ') {
					mainQuery = 'A';
				} else {
					if ((subQuery == ' ') && (forUsed)) {
						subQuery = 'A';
					}
				}
			} else if (currentToken.equals("for")) {
				forUsed = true;
			} else if ((searchString == null) && (subQuery != ' ')
					&& (currentToken.startsWith("<"))) {
				searchString = currentToken;
				searchStarted = true;
				if (currentToken.endsWith(">")) {
					searchEnded = true;
				}
			} else if ((searchStarted) && (!searchEnded)) {
				searchString = searchString + " " + currentToken;
				if (currentToken.endsWith(">")) {
					searchEnded = true;
				}
			}
		}
		if (searchString != null) {
			searchString = searchString.substring(1,
					(searchString.length() - 1));// remove <>
		}
		BookAbstractExpression abstractExpression;
		switch (mainQuery) {
		case 'A': {
			switch (subQuery) {
			case 'T': {
				abstractExpression = new BookAuthorTitleExpression(searchString);
				break;
			}
			default: {
				abstractExpression = new BookAuthorExpression();
				break;
			}
			}
			break;
		}
		case 'T': {
			switch (subQuery) {
			case 'A': {
				abstractExpression = new BookTitleAuthorExpression(searchString);
				break;
			}
			default: {
				abstractExpression = new BookTitleExpression();
				break;
			}
			}
			break;
		}
		default:
			return result.toString();
		}
		result.append(abstractExpression.interpret(bookInterpreterContext));
		return result.toString();
	}
}

class BookInterpreterContext {
	private ArrayList titles = new ArrayList();
	private ArrayList authors = new ArrayList();
	private ArrayList titlesAndAuthors = new ArrayList();

	public void addTitle(String title) {
		titles.add(title);
	}

	public void addAuthor(String author) {
		authors.add(author);
	}

	public void addTitleAndAuthor(TitleAndAuthor titleAndAuthor) {
		titlesAndAuthors.add(titleAndAuthor);
	}

	public ArrayList getAllTitles() {
		return titles;
	}

	public ArrayList getAllAuthors() {
		return authors;
	}

	public ArrayList getAuthorsForTitle(String titleIn) {
		ArrayList authorsForTitle = new ArrayList();
		TitleAndAuthor tempTitleAndAuthor;
		ListIterator titlesAndAuthorsIterator = titlesAndAuthors.listIterator();
		while (titlesAndAuthorsIterator.hasNext()) {
			tempTitleAndAuthor = (TitleAndAuthor) titlesAndAuthorsIterator
					.next();
			if (titleIn.equals(tempTitleAndAuthor.getTitle())) {
				authorsForTitle.add(tempTitleAndAuthor.getAuthor());
			}
		}
		return authorsForTitle;
	}

	public ArrayList getTitlesForAuthor(String authorIn) {
		ArrayList titlesForAuthor = new ArrayList();
		TitleAndAuthor tempTitleAndAuthor;
		ListIterator authorsAndTitlesIterator = titlesAndAuthors.listIterator();
		while (authorsAndTitlesIterator.hasNext()) {
			tempTitleAndAuthor = (TitleAndAuthor) authorsAndTitlesIterator
					.next();
			if (authorIn.equals(tempTitleAndAuthor.getAuthor())) {
				titlesForAuthor.add(tempTitleAndAuthor.getTitle());
			}
		}
		return titlesForAuthor;
	}
}

abstract class BookAbstractExpression {
	public abstract String interpret(BookInterpreterContext parm);
}

class BookAuthorExpression extends BookAbstractExpression {
	public String interpret(BookInterpreterContext parm) {
		ArrayList authors = parm.getAllAuthors();
		ListIterator authorsIterator = authors.listIterator();
		StringBuffer titleBuffer = new StringBuffer("");
		boolean first = true;
		while (authorsIterator.hasNext()) {
			if (!first) {
				titleBuffer.append(", ");
			} else {
				first = false;
			}
			titleBuffer.append((String) authorsIterator.next());
		}
		return titleBuffer.toString();
	}
}

class BookAuthorTitleExpression extends BookAbstractExpression {
	String title;

	public BookAuthorTitleExpression(String parm) {
		title = parm;
	}

	public String interpret(BookInterpreterContext parm) {
		ArrayList authorsAndTitles = parm.getAuthorsForTitle(title);
		ListIterator authorsAndTitlesIterator = authorsAndTitles.listIterator();
		StringBuffer authorBuffer = new StringBuffer("");
		boolean first = true;
		while (authorsAndTitlesIterator.hasNext()) {
			if (!first) {
				authorBuffer.append(", ");
			} else {
				first = false;
			}
			authorBuffer.append((String) authorsAndTitlesIterator.next());
		}
		return authorBuffer.toString();
	}
}

class BookTitleExpression extends BookAbstractExpression {
	public String interpret(BookInterpreterContext parm) {
		ArrayList titles = parm.getAllTitles();
		ListIterator titlesIterator = titles.listIterator();
		StringBuffer titleBuffer = new StringBuffer("");
		boolean first = true;
		while (titlesIterator.hasNext()) {
			if (!first) {
				titleBuffer.append(", ");
			} else {
				first = false;
			}
			titleBuffer.append((String) titlesIterator.next());
		}
		return titleBuffer.toString();
	}
}

class BookTitleAuthorExpression extends BookAbstractExpression {
	String title;

	public BookTitleAuthorExpression(String parm) {
		title = parm;
	}

	public String interpret(BookInterpreterContext parm) {
		ArrayList titlesAndAuthors = parm.getTitlesForAuthor(title);
		ListIterator titlesAndAuthorsIterator = titlesAndAuthors.listIterator();
		StringBuffer titleBuffer = new StringBuffer("");
		boolean first = true;
		while (titlesAndAuthorsIterator.hasNext()) {
			if (!first) {
				titleBuffer.append(", ");
			} else {
				first = false;
			}
			titleBuffer.append((String) titlesAndAuthorsIterator.next());
		}
		return titleBuffer.toString();
	}
}

class TitleAndAuthor {
	private String title;
	private String author;

	public TitleAndAuthor(String parm1, String parm2) {
		title = parm1;
		author = parm2;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}
}
